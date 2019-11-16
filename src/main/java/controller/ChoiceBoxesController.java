package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.utils.readers.TxtToFqdnGroupReader;
import controller.utils.readers.TxtToIpGroupReader;
import controller.utils.readers.TxtToIpReader;
import controller.utils.readers.TxtToIrmcReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import model.Fqdn;
import model.Ip;
import model.Irmc;
import model.holders.ConfigData;
import model.holders.HostData;
import model.holders.UserData;

public class ChoiceBoxesController {
	static final Logger LOG = LoggerFactory.getLogger(ChoiceBoxesController.class);

	private ChoiceBox<Irmc> bx;
	private ChoiceBox<Fqdn> cx1;
	private ChoiceBox<Irmc> cx2;
	private ChoiceBox<Ip> es;
	private ChoiceBox<Ip> vc1;
	private ChoiceBox<Ip> vc2;

	private List<List<Fqdn>> fqdnListOfLists;
	private List<List<Ip>> vcsListOfLists;

	public ChoiceBoxesController(ChoiceBox<Irmc> bx, ChoiceBox<Fqdn> cx1, ChoiceBox<Irmc> cx2, ChoiceBox<Ip> es,
			ChoiceBox<Ip> vc1, ChoiceBox<Ip> vc2) {
		this.bx = bx;
		this.cx1 = cx1;
		this.cx2 = cx2;
		this.es = es;
		this.vc1 = vc1;
		this.vc2 = vc2;
	}

	public void init() {
		initBxList();
		initEsList();
		initCxList();
		initVcList();
		LOG.info("Initialized choice box lists");
	}

	private void initBxList() {
		TxtToIrmcReader txtToIrmcReader = new TxtToIrmcReader();
		bx.setItems(txtToIrmcReader.read("bx.txt"));
	}

	private void initEsList() {
		TxtToIpReader txtToIpReader = new TxtToIpReader();
		es.setItems(txtToIpReader.read("es.txt"));
	}

	private void initCxList() {
		TxtToFqdnGroupReader txtToFqdnGroupReader = new TxtToFqdnGroupReader();
		fqdnListOfLists = txtToFqdnGroupReader.read("cx.txt");
		ObservableList<Fqdn> fqdnList = processFqdnGroup(fqdnListOfLists);
		cx1.setItems(fqdnList);

		cx1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			LOG.info("Cx1 was set, setting cx2 list");
			ObservableList<Irmc> filteredList = getAppriopriateCxList(newValue);
			cx2.setItems(filteredList);
			if (!filteredList.isEmpty()) {
				cx2.setValue(filteredList.get(0));
			}
		});
	}

	private ObservableList<Fqdn> processFqdnGroup(List<List<Fqdn>> fqdnListOfLists) {
		List<Fqdn> bigList = new ArrayList<>();
		fqdnListOfLists.forEach(list -> list.forEach(bigList::add));
		return FXCollections.observableArrayList(bigList);
	}

	private ObservableList<Irmc> getAppriopriateCxList(Fqdn newValue) {
		for (List<Fqdn> observableList : fqdnListOfLists) {
			for (Fqdn fqdn : observableList) {
				if (fqdn.equals(newValue)) {
					return FXCollections.observableArrayList(observableList.stream()
							.filter(c -> !c.getIp().equals(newValue.getIp()))
							.map(c -> new Irmc(c.getIp()))
							.collect(Collectors.toList()));
				}
			}
		}
		return FXCollections.observableArrayList();
	}

	private void initVcList() {
		TxtToIpGroupReader txtToIpGroupReader = new TxtToIpGroupReader();
		vcsListOfLists = txtToIpGroupReader.read("vc.txt");
		ObservableList<Ip> vcList = processVcGroup(vcsListOfLists);
		vc1.setItems(vcList);

		vc1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			LOG.info("Vc1 was set, setting vc2 list");
			ObservableList<Ip> filteredList = getAppriopriateVcList(newValue);
			vc2.setItems(filteredList);
			if (!filteredList.isEmpty()) {
				vc2.setValue(filteredList.get(0));
			}
		});
	}

	private ObservableList<Ip> processVcGroup(List<List<Ip>> vcsListOfLists) {
		List<Ip> bigList = new ArrayList<>();
		vcsListOfLists.forEach(list -> list.forEach(bigList::add));
		return FXCollections.observableArrayList(bigList);
	}

	private ObservableList<Ip> getAppriopriateVcList(Ip newValue) {
		for (List<Ip> observableList : vcsListOfLists) {
			for (Ip ip : observableList) {
				if (ip.equals(newValue)) {
					return FXCollections.observableArrayList(observableList.stream()
							.filter(c -> !c.getIp().equals(newValue.getIp()))
							.collect(Collectors.toList()));
				}
			}
		}
		return FXCollections.observableArrayList();
	}

	public ConfigData createConfigData() {
		return new ConfigData(es.getValue(), vc1.getValue(), vc2.getValue());
	}

	public HostData createHostData() {
		return new HostData(bx.getValue(), cx1.getValue(), cx2.getValue());
	}

	public void setValues(UserData userData) {
		bx.setValue(userData.getBx());
		cx1.setValue(userData.getCx1());
		cx2.setValue(userData.getCx2());
		es.setValue(userData.getEs());
		vc1.setValue(userData.getVc1());
		vc2.setValue(userData.getVc2());
	}
}
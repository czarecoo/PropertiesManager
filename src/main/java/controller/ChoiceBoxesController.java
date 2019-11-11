package controller;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.utils.TxtFileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import model.ConfigData;
import model.HostData;
import model.UserData;

public class ChoiceBoxesController {
	static final Logger LOG = LoggerFactory.getLogger(ChoiceBoxesController.class);

	private ChoiceBox<String> bx;
	private ChoiceBox<String> cx1;
	private ChoiceBox<String> cx2;
	private ChoiceBox<String> es;
	private ChoiceBox<String> vc1;
	private ChoiceBox<String> vc2;

	private List<String> vcList;

	private List<String> cxList;

	public ChoiceBoxesController(ChoiceBox<String> bx, ChoiceBox<String> cx1, ChoiceBox<String> cx2,
			ChoiceBox<String> es, ChoiceBox<String> vc1, ChoiceBox<String> vc2) {
		this.bx = bx;
		this.cx1 = cx1;
		this.cx2 = cx2;
		this.es = es;
		this.vc1 = vc1;
		this.vc2 = vc2;
	}

	public void init() {
		TxtFileReader txtFileReader = new TxtFileReader();
		bx.setItems(FXCollections.observableArrayList(txtFileReader.read("bx.txt")));
		es.setItems(FXCollections.observableArrayList(txtFileReader.read("es.txt")));

		vcList = txtFileReader.read("vc.txt");
		List<String> processedVcList = processList();
		ObservableList<String> processedObservableVcList = FXCollections.observableArrayList(processedVcList);
		vc1.setItems(processedObservableVcList);
		vc2.setItems(processedObservableVcList);

		cxList = txtFileReader.read("cx.txt");
		List<String> processedCx1List = processCx1List();
		ObservableList<String> processedObservableCx1List = FXCollections.observableArrayList(processedCx1List);
		cx1.setItems(processedObservableCx1List);
		List<String> processedCx2List = processCx2List();
		ObservableList<String> processedObservableCx2List = FXCollections.observableArrayList(processedCx2List);
		cx2.setItems(processedObservableCx2List);
		LOG.info("Initialized choice box lists");
	}

	private List<String> processCx1List() {
		Predicate<String> isNotEmpty = s -> !s.isEmpty();
		UnaryOperator<String> fqdnFirst = s -> {
			String[] split = s.split(" ");
			return split[1] + " (" + split[0] + ")";
		};
		return cxList.stream().filter(isNotEmpty).map(fqdnFirst).collect(Collectors.toList());
	}

	private List<String> processCx2List() {
		Predicate<String> isNotEmpty = s -> !s.isEmpty();
		UnaryOperator<String> removeFqdn = s -> {
			return s.split(" ")[0];
		};
		return cxList.stream().filter(isNotEmpty).map(removeFqdn).collect(Collectors.toList());
	}

	private List<String> processList() {
		Predicate<String> isNotEmpty = e -> !e.isEmpty();
		return vcList.stream().filter(isNotEmpty).collect(Collectors.toList());
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
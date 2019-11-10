package controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.utils.ConfigDataSaver;
import controller.utils.HostDataSaver;
import controller.utils.PropertiesHandler;
import controller.utils.TxtFileReader;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.ConfigData;
import model.HostData;
import model.UserData;

public class Controller {
	static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	@FXML
	private ChoiceBox<String> bx;
	@FXML
	private ChoiceBox<String> cx1;
	@FXML
	private ChoiceBox<String> cx2;
	@FXML
	private ChoiceBox<String> es;
	@FXML
	private ChoiceBox<String> vc1;
	@FXML
	private ChoiceBox<String> vc2;

	@FXML
	private Button browse;
	@FXML
	private TextField path;

	@FXML
	private Button update;

	@FXML
	private MenuItem save;
	@FXML
	private MenuItem load;
	@FXML
	private MenuItem quit;

	@FXML
	private void initialize() {
		initDefaultValues();
		initChoiceBoxLists();
		initBrowseButton();
		initUpdateButton();
		initSaveMenuButton();
		initLoadMenuButton();
		initQuitMenuButton();
	}

	private void initDefaultValues() {
		loadDefaultData();
	}

	private void initChoiceBoxLists() {
		TxtFileReader txtFileReader = new TxtFileReader();
		bx.setItems(txtFileReader.read("bx.txt"));
		ObservableList<String> cxList = txtFileReader.read("cx.txt");
		cx1.setItems(cxList);
		cx2.setItems(cxList);
		es.setItems(txtFileReader.read("es.txt"));
		ObservableList<String> vcList = txtFileReader.read("vc.txt");
		vc1.setItems(vcList);
		vc2.setItems(vcList);
		LOG.info("Initialized choice box lists");
	}

	private void initBrowseButton() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("C:/Users/Czareg/eclipse-workspace/testProperties"));
		browse.setOnAction(e -> {
			Stage stage = Stage.class.cast(Control.class.cast(e.getSource()).getScene().getWindow());
			File selectedDirectory = directoryChooser.showDialog(stage);
			if (selectedDirectory != null && selectedDirectory.getAbsolutePath() != null) {
				path.setText(selectedDirectory.getAbsolutePath());
				LOG.info("Selected directory: {}", selectedDirectory.getAbsolutePath());
			}
		});
		LOG.info("Initialized browse button");
	}

	private void initUpdateButton() {
		update.setOnAction(e -> {
			ConfigData configData = new ConfigData(es.getValue(), vc1.getValue(), vc2.getValue());
			ConfigDataSaver configSaver = new ConfigDataSaver();
			configSaver.save(configData, path.getText());
			HostData hostData = new HostData(bx.getValue(), cx1.getValue(), cx2.getValue());
			HostDataSaver hostSaver = new HostDataSaver();
			hostSaver.save(hostData, path.getText());
		});
		LOG.info("Initialized update button");
	}

	private void initSaveMenuButton() {
		save.setOnAction(e -> {
			ConfigData configData = new ConfigData(es.getValue(), vc1.getValue(), vc2.getValue());
			HostData hostData = new HostData(bx.getValue(), cx1.getValue(), cx2.getValue());
			PropertiesHandler propertiesHandler = new PropertiesHandler();
			propertiesHandler.save(new UserData(hostData, configData, path.getText()));
		});
		LOG.info("Initialized save button");
	}

	private void initLoadMenuButton() {
		load.setOnAction(e -> {
			loadDefaultData();
		});
		LOG.info("Initialized load button");
	}

	private void initQuitMenuButton() {
		quit.setOnAction(e -> {
			Platform.exit();
			LOG.info("Quitting application");
		});
		LOG.info("Initialized quit button");
	}

	private void loadDefaultData() {
		PropertiesHandler propertiesHandler = new PropertiesHandler();
		UserData userData = propertiesHandler.load();
		bx.setValue(userData.getBx());
		cx1.setValue(userData.getCx1());
		cx2.setValue(userData.getCx2());
		es.setValue(userData.getEs());
		vc1.setValue(userData.getVc1());
		vc2.setValue(userData.getVc2());
		path.setText(userData.getPath());
		LOG.info("Loaded default data");
	}
}
package controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.PropertiesManagerUI;
import controller.utils.properties.UserDataHandler;
import controller.utils.writers.DataSaver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Fqdn;
import model.Ip;
import model.Irmc;
import model.holders.ConfigData;
import model.holders.HostData;
import model.holders.UserData;

public class Controller {
	static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	@FXML
	private ChoiceBox<Irmc> bx;
	@FXML
	private ChoiceBox<Fqdn> cx1;
	@FXML
	private ChoiceBox<Irmc> cx2;
	@FXML
	private ChoiceBox<Ip> es;
	@FXML
	private ChoiceBox<Ip> vc1;
	@FXML
	private ChoiceBox<Ip> vc2;

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

	private ChoiceBoxesController choiceBoxesController;

	@FXML
	private void initialize() {
		initChoiceBoxesController();
		initDefaultValues();
		initBrowseButton();
		initUpdateButton();
		initSaveMenuButton();
		initLoadMenuButton();
		initQuitMenuButton();
	}

	private void initDefaultValues() {
		loadDefaultData();
	}

	private void initChoiceBoxesController() {
		choiceBoxesController = new ChoiceBoxesController(bx, cx1, cx2, es, vc1, vc2);
		choiceBoxesController.init();
	}

	private void initBrowseButton() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		browse.setOnAction(e -> {
			LOG.info("Browse button clicked, popup window should show");
			Stage stage = PropertiesManagerUI.getStage();
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
			LOG.info("Update button clicked");
			DataSaver dataSaver = new DataSaver();

			ConfigData configData = choiceBoxesController.createConfigData();
			dataSaver.save(configData, path.getText());
			HostData hostData = choiceBoxesController.createHostData();
			dataSaver.save(hostData, path.getText());

			saveUserDataToProperties(configData, hostData);
		});
		LOG.info("Initialized update button");
	}

	private void initSaveMenuButton() {
		save.setOnAction(e -> {
			LOG.info("Save button clicked");
			ConfigData configData = choiceBoxesController.createConfigData();
			HostData hostData = choiceBoxesController.createHostData();
			saveUserDataToProperties(configData, hostData);
		});
		LOG.info("Initialized save button");
	}

	private void saveUserDataToProperties(ConfigData configData, HostData hostData) {
		UserDataHandler propertiesHandler = new UserDataHandler();
		propertiesHandler.save(new UserData(hostData, configData, path.getText()));
	}

	private void initLoadMenuButton() {
		load.setOnAction(e -> {
			LOG.info("Load button clicked");
			loadDefaultData();
		});
		LOG.info("Initialized load button");
	}

	private void initQuitMenuButton() {
		quit.setOnAction(e -> {
			LOG.info("Quit button clicked");
			Platform.exit();
		});
		LOG.info("Initialized quit button");
	}

	private void loadDefaultData() {
		UserDataHandler propertiesHandler = new UserDataHandler();
		UserData userData = propertiesHandler.load();
		if (userData != null) {
			LOG.info(userData.toString());
			choiceBoxesController.setValues(userData);
			path.setText(userData.getPath());
			LOG.info("Loaded default data");
		} else {
			LOG.error("Could not load default data");
		}
	}
}
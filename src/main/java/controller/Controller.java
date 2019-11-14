package controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.utils.PropertiesHandler;
import controller.utils.writers.ConfigDataSaver;
import controller.utils.writers.HostDataSaver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
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
			ConfigData configData = choiceBoxesController.createConfigData();
			ConfigDataSaver configSaver = new ConfigDataSaver();
			configSaver.save(configData, path.getText());
			HostData hostData = choiceBoxesController.createHostData();
			HostDataSaver hostSaver = new HostDataSaver();
			hostSaver.save(hostData, path.getText());
		});
		LOG.info("Initialized update button");
	}

	private void initSaveMenuButton() {
		save.setOnAction(e -> {
			ConfigData configData = choiceBoxesController.createConfigData();
			HostData hostData = choiceBoxesController.createHostData();
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
		if (userData != null) {
			choiceBoxesController.setValues(userData);
			path.setText(userData.getPath());
			LOG.info("Loaded default data");
		}
	}
}
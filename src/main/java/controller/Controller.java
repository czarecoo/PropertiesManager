package controller;

import java.io.File;

import controller.utils.ConfigDataSaver;
import controller.utils.HostDataSaver;
import controller.utils.TxtFileReader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.ConfigData;
import model.HostData;

public class Controller {
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
	private void initialize() {
		initChoiceBoxes();
		initBrowseButton();
		initCreateButton();
	}

	private void initChoiceBoxes() {
		TxtFileReader txtFileReader = new TxtFileReader();
		bx.setItems(txtFileReader.read("bx.txt"));
		ObservableList<String> cxList = txtFileReader.read("cx.txt");
		cx1.setItems(cxList);
		cx2.setItems(cxList);
		es.setItems(txtFileReader.read("es.txt"));
		ObservableList<String> vcList = txtFileReader.read("vc.txt");
		vc1.setItems(vcList);
		vc2.setItems(vcList);
	}

	private void initBrowseButton() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("C:/Users/Czareg/eclipse-workspace/testProperties"));
		browse.setOnAction(e -> {
			Stage stage = Stage.class.cast(Control.class.cast(e.getSource()).getScene().getWindow());
			File selectedDirectory = directoryChooser.showDialog(stage);
			if (selectedDirectory != null && selectedDirectory.getAbsolutePath() != null) {
				path.setText(selectedDirectory.getAbsolutePath());
			}
		});
	}

	private void initCreateButton() {
		update.setOnAction(e -> {
			ConfigData configData = new ConfigData(es.getValue(), vc1.getValue(), vc2.getValue());
			ConfigDataSaver configSaver = new ConfigDataSaver();
			configSaver.save(configData, path.getText());
			HostData hostData = new HostData(bx.getValue(), cx1.getValue(), cx2.getValue());
			HostDataSaver hostSaver = new HostDataSaver();
			hostSaver.save(hostData, path.getText());
		});
	}
}
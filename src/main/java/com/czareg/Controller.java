package com.czareg;

import java.io.File;

import com.czareg.utils.TxtFileReader;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

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
	private Button save;
	@FXML
	private CheckBox loadOnStart;
	@FXML
	private Button load;

	@FXML
	private Button create;

	@FXML
	private void initialize() {
		initChoiceBoxes();
		initBrowseButton();
	}

	private void initChoiceBoxes() {
		TxtFileReader txtFileReader = new TxtFileReader();
		bx.setItems(txtFileReader.read("bx.txt"));
		ObservableList<String> cxList = txtFileReader.read("cx.txt");
		cx1.setItems(cxList);
		cx2.setItems(cxList);
		ObservableList<String> vcList = txtFileReader.read("vc.txt");
		vc1.setItems(vcList);
		vc2.setItems(vcList);
		es.setItems(txtFileReader.read("es.txt"));
	}

	private void initBrowseButton() {
		DirectoryChooser directoryChooser = new DirectoryChooser();

		browse.setOnAction(e -> {
			Stage stage = Stage.class.cast(Control.class.cast(e.getSource()).getScene().getWindow());
			File selectedDirectory = directoryChooser.showDialog(stage);
			if (selectedDirectory != null && selectedDirectory.getAbsolutePath() != null) {
				path.setText(selectedDirectory.getAbsolutePath());
			}
		});
	}

	private void initCreateButton() {
		create.setOnAction(e -> {

		});
	}
}
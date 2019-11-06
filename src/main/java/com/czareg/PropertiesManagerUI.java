package com.czareg;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PropertiesManagerUI extends Application {
	private static final int MIN_WIDTH = 420;
	private static final int MIN_HEIGHT = 280;
	static final Logger LOG = LoggerFactory.getLogger(PropertiesManagerUI.class);
	private static final String ICON_FILENAME = "icon.png";
	private static final String APPLICATION_TITLE = "Properties Manager";

	@FXML
	private ChoiceBox<String> bx;
	@FXML
	private Button browse;

	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
		Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
		stage.setTitle(APPLICATION_TITLE);
		stage.setResizable(false);
		stage.setMinWidth(MIN_WIDTH);
		stage.setMinHeight(MIN_HEIGHT);
		stage.setScene(scene);
		stage.getIcons().add(new Image(ICON_FILENAME));
		LOG.info("Started UI");
		stage.show();
	}

	@FXML
	private void initialize() {
		bx.setItems(FXCollections.observableArrayList("10.172.181.192", "10.172.181.140"));
	}

	public static void main() {
		launch(PropertiesManagerUI.class);
	}
}
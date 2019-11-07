package com.czareg;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PropertiesManagerUI extends Application {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesManagerUI.class);
	private static final int MIN_WIDTH = 450;
	private static final int MIN_HEIGHT = 300;
	private static final String ICON_FILENAME = "icon.png";
	private static final String APPLICATION_TITLE = "Properties Manager";

	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
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

	public static void main() {
		launch(PropertiesManagerUI.class);
	}
}
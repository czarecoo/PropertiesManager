package controller.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesHandler {
	static final Logger LOG = LoggerFactory.getLogger(PropertiesHandler.class);

	public void writeToProperties(String key, String value, String path, String comments) {
		createFileIfNotExists(path);
		Properties prop = new Properties();
		try {
			try (InputStream input = new FileInputStream(path)) {
				prop.load(input);
			}
			try (OutputStream output = new FileOutputStream(path)) {
				prop.setProperty(key, value);
				prop.store(output, comments);
			}
		} catch (IOException e) {
			LOG.error(String.format("Failed to write key: %s with value: %s to file", key, value), e);
		}
	}

	public String readFromProperties(String key, String path) {
		createFileIfNotExists(path);
		try (InputStream input = new FileInputStream(path)) {
			Properties prop = new Properties();
			prop.load(input);
			return prop.getProperty(key, null);
		} catch (IOException e) {
			LOG.error(String.format("Failed to read property: %s from file", key), e);
		}
		return null;
	}

	private void createFileIfNotExists(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				LOG.info("Created file {}", file.createNewFile());
			}
		} catch (IOException e) {
			LOG.error("Failed to create file", e);
		}
	}
}
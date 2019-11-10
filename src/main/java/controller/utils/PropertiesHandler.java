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

import model.ConfigData;
import model.HostData;
import model.UserData;

public class PropertiesHandler {
	static final Logger LOG = LoggerFactory.getLogger(PropertiesHandler.class);
	private static final String FILE_NAME = "user.properties";
	private static final String BX_KEY = "bx";
	private static final String CX1_KEY = "cx1";
	private static final String CX2_KEY = "cx2";
	private static final String ES_KEY = "es";
	private static final String VC1_KEY = "vc1";
	private static final String VC2_KEY = "vc2";
	private static final String PATH = "path";

	public void save(UserData userData) {
		createFileIfNotExists(FILE_NAME);
		Properties prop = new Properties();
		try {
			try (InputStream input = new FileInputStream(FILE_NAME)) {
				prop.load(input);
			}
			try (OutputStream output = new FileOutputStream(FILE_NAME)) {
				prop.setProperty(BX_KEY, userData.getBx());
				prop.setProperty(CX1_KEY, userData.getCx1());
				prop.setProperty(CX2_KEY, userData.getCx2());
				prop.setProperty(ES_KEY, userData.getEs());
				prop.setProperty(VC1_KEY, userData.getVc1());
				prop.setProperty(VC2_KEY, userData.getVc2());
				prop.setProperty(PATH, userData.getPath());
				prop.store(output, null);
			}
		} catch (IOException e) {
			LOG.error("Failed to save data to property", e);
		}
	}

	public UserData load() {
		createFileIfNotExists(FILE_NAME);
		try (InputStream input = new FileInputStream(FILE_NAME)) {
			Properties prop = new Properties();
			prop.load(input);
			HostData hostData = new HostData(prop.getProperty(BX_KEY), prop.getProperty(CX1_KEY),
					prop.getProperty(CX2_KEY));
			ConfigData configData = new ConfigData(prop.getProperty(ES_KEY), prop.getProperty(VC1_KEY),
					prop.getProperty(VC2_KEY));
			return new UserData(hostData, configData, prop.getProperty(PATH));
		} catch (IOException e) {
			LOG.error("Failed to load data to property", e);
		}
		return null;
	}

	private void createFileIfNotExists(String fileName) {
		try {
			File file = new File(fileName);
			if (file.createNewFile()) {
				LOG.info("Created file {}", fileName);
			} else {
				LOG.info("File {} already exists", fileName);
			}
		} catch (IOException e) {
			LOG.error("Failed to create file", e);
		}
	}
}
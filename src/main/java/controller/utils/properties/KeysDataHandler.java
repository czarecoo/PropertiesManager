package controller.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.holders.KeysData;

public class KeysDataHandler {
	static final Logger LOG = LoggerFactory.getLogger(KeysDataHandler.class);

	private static final String KEYS_FILE_PATH = "config/keys.txt";
	private static final String ES1_KEY = "ES1_KEY";
	private static final String ES2_KEY = "ES2_KEY";
	private static final String VC1_KEY = "VC1_KEY";
	private static final String VC2_KEY = "VC2_KEY";

	private static final String BX_KEY = "BX_KEY";
	private static final String CX1_KEY = "CX1_KEY";
	private static final String CX2_KEY = "CX2_KEY";
	private static final String BX_IRMC_KEY = "BX_IRMC_KEY";
	private static final String CX1_IRMC_KEY = "CX1_IRMC_KEY";
	private static final String CX2_IRMC_KEY = "CX2_IRMC_KEY";

	public KeysData load() {
		checkIfFileExists(KEYS_FILE_PATH);
		try (InputStream input = new FileInputStream(KEYS_FILE_PATH)) {
			Properties prop = new Properties();
			prop.load(input);
			KeysData keysData = new KeysData();
			keysData.setEs1Key(prop.getProperty(ES1_KEY));
			keysData.setEs2Key(prop.getProperty(ES2_KEY));
			keysData.setVc1Key(prop.getProperty(VC1_KEY));
			keysData.setVc2Key(prop.getProperty(VC2_KEY));
			keysData.setBxKey(prop.getProperty(BX_KEY));
			keysData.setCx1Key(prop.getProperty(CX1_KEY));
			keysData.setCx2Key(prop.getProperty(CX2_KEY));
			keysData.setBxIrmcKey(prop.getProperty(BX_IRMC_KEY));
			keysData.setCx1IrmcKey(prop.getProperty(CX1_IRMC_KEY));
			keysData.setCx2IrmcKey(prop.getProperty(CX2_IRMC_KEY));
			return keysData;
		} catch (IOException e) {
			LOG.error("Failed to load data to property", e);
		}
		return null;
	}

	private void checkIfFileExists(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			LOG.info("File {} exists", fileName);
		} else {
			LOG.error("File {} does not exist", fileName);
		}
	}
}
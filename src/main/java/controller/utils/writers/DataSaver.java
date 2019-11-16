package controller.utils.writers;

import static controller.utils.writers.StringReplaceUtil.replace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.utils.properties.KeysDataHandler;
import model.holders.ConfigData;
import model.holders.HostData;
import model.holders.KeysData;

public class DataSaver {
	static final Logger LOG = LoggerFactory.getLogger(DataSaver.class);
	private static final String TEST_CONFIG_FILE_PATH = "test_config.properties";
	private static final String HOSTS_FILE_PATH = "hosts.properties";
	private KeysData keysData;

	public DataSaver() {
		KeysDataHandler keysDataHandler = new KeysDataHandler();
		keysData = keysDataHandler.load();
		if (keysData != null) {
			LOG.info(keysData.toString());
		} else {
			LOG.error("Could not load keys");
		}

	}

	public void save(ConfigData config, String userPath) {
		String configPath = TEST_CONFIG_FILE_PATH;
		Path path = Paths.get(userPath, configPath);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, keysData.getEs1Key(), config.getEs().getIp()))
					.map(line -> replace(line, keysData.getEs2Key(), config.getEs().getIp()))
					.map(line -> replace(line, keysData.getVc1Key(), config.getVc1().getIp()))
					.map(line -> replace(line, keysData.getVc2Key(), config.getVc2().getIp()))
					.collect(Collectors.toList());
			Files.write(path, replaced);
			LOG.info("Saved config data to file: {}", configPath);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", configPath), e);
		}
	}

	public void save(HostData hostData, String userPath) {
		String hostPath = HOSTS_FILE_PATH;
		Path path = Paths.get(userPath, hostPath);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, keysData.getBxKey(), hostData.getBx().getIp()))
					.map(line -> replace(line, keysData.getCx1Key(), hostData.getCx1().getFqdn()))
					.map(line -> replace(line, keysData.getCx2Key(), hostData.getCx2().getIp()))
					.map(line -> replace(line, keysData.getBxIrmcKey(), hostData.getBx().getIrmc()))
					.map(line -> replace(line, keysData.getCx1IrmcKey(), hostData.getCx1().getIrmc()))
					.map(line -> replace(line, keysData.getCx2IrmcKey(), hostData.getCx2().getIrmc()))
					.collect(Collectors.toList());
			Files.write(path, replaced);
			LOG.info("Saved host data to file: {}", hostPath);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", hostPath), e);
		}
	}
}
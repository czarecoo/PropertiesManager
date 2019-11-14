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

import model.holders.ConfigData;

public class ConfigDataSaver {
	static final Logger LOG = LoggerFactory.getLogger(ConfigDataSaver.class);
	private static final String FILE_PATH = "test_config.properties";
	private static final String ES1_KEY = "es.host";
	private static final String ES2_KEY = "soa.address";
	private static final String VC1_KEY = "vc.multi.1.host";
	private static final String VC2_KEY = "vc.mutli.2.host";

	public void save(ConfigData config, String userPath) {
		Path path = Paths.get(userPath, FILE_PATH);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, ES1_KEY, config.getEs().getIp()))
					.map(line -> replace(line, ES2_KEY, config.getEs().getIp()))
					.map(line -> replace(line, VC1_KEY, config.getVc1().getIp()))
					.map(line -> replace(line, VC2_KEY, config.getVc2().getIp()))
					.collect(Collectors.toList());
			Files.write(path, replaced);
			LOG.info("Saved config data to file: {}", FILE_PATH);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", FILE_PATH), e);
		}
	}
}
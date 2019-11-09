package controller.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigSaver {
	static final Logger LOG = LoggerFactory.getLogger(ConfigSaver.class);

	private String filename = "test_config.properties";
	private String es1_key = "es.host";
	private String es2_key = "soa.address";
	private String vc1_key = "vc.multi.1.host";
	private String vc2_key = "vc.mutli.2.host";

	private String separator = "=";

	public void save(String es, String vc1, String vc2, String userPath) {
		Path path = Paths.get(userPath, filename);
		try (Stream<String> lines = Files.lines(path)) {
			List<String> replaced = lines.map(line -> replace(line, es1_key, es))
					.map(line -> replace(line, es2_key, es))
					.map(line -> replace(line, vc1_key, vc1))
					.map(line -> replace(line, vc2_key, vc2))
					.collect(Collectors.toList());
			Files.write(path, replaced);
		} catch (IOException e) {
			LOG.error(String.format("Failed to save %s.", filename), e);
		}
	}

	private String replace(String line, String key, String replacement) {
		if (line.contains(key)) {
			String[] split = line.split(separator);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(split[0]);
			stringBuilder.append(separator);
			stringBuilder.append(replacement);
			return stringBuilder.toString();
		}
		return line;
	}
}
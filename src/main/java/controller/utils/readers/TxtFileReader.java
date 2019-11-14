package controller.utils.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TxtFileReader<T> {
	private static final Logger LOG = LoggerFactory.getLogger(TxtFileReader.class);

	private static final String FILE_PATH = "config/";

	public T read(String fileName) {
		T collection = createCollection();
		Path path = Paths.get(FILE_PATH, fileName);
		try {
			List<String> lines = Files.readAllLines(path);
			convertToObject(collection, lines);
			LOG.info("Read data from file: {}", fileName);
		} catch (IOException e) {
			LOG.error("Failed to read from file", e);
		}
		return collection;
	}

	protected abstract T createCollection();

	public abstract void convertToObject(T collection, List<String> lines);
}
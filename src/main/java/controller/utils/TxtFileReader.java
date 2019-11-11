package controller.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxtFileReader {
	private static final Logger LOG = LoggerFactory.getLogger(TxtFileReader.class);

	private static final String FILE_PATH = "config/";

	public List<String> read(String fileName) {
		List<String> list = new ArrayList<>(15);
		File file = new File(createPath(fileName));
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			LOG.info("Read data from file: {}", fileName);
		} catch (IOException e) {
			LOG.error("Failed to read from file", e);
		}
		return list;
	}

	private String createPath(String filename) {
		return FILE_PATH + filename;
	}
}
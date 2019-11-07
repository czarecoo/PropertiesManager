package com.czareg.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class TxtFileReader {
	private static final Logger LOG = LoggerFactory.getLogger(TxtFileReader.class);

	public ObservableList<String> read(String fileName) {
		ObservableList<String> list = FXCollections.observableArrayList();
		try (BufferedReader br = new BufferedReader(
				new FileReader(getClass().getClassLoader().getResource(fileName).getPath()))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			LOG.error("Failed to read from file", e);
		}
		return list;
	}
}
package controller.utils.readers;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ip;

public class TxtToIpReader extends TxtFileReader<ObservableList<Ip>> {
	@Override
	protected ObservableList<Ip> createCollection() {
		return FXCollections.observableArrayList();
	}

	@Override
	public void convertToObject(ObservableList<Ip> list, List<String> lines) {
		lines.forEach(line -> list.add(Ip.load(line)));
	}
}
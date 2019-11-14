package controller.utils.readers;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Irmc;

public class TxtToIrmcReader extends TxtFileReader<ObservableList<Irmc>> {
	@Override
	protected ObservableList<Irmc> createCollection() {
		return FXCollections.observableArrayList();
	}

	@Override
	public void convertToObject(ObservableList<Irmc> list, List<String> lines) {
		Predicate<String> isNotEmpty = s -> !s.isEmpty();
		UnaryOperator<String> onlyIp = s -> {
			if (s.contains(" ")) {
				return s.split(" ")[0];
			}
			return s;
		};
		lines.stream().filter(isNotEmpty).map(onlyIp).forEach(line -> list.add(Irmc.load(line)));
	}
}
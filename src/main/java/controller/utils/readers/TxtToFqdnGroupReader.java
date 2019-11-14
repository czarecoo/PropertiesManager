package controller.utils.readers;

import java.util.ArrayList;
import java.util.List;

import model.Fqdn;

public class TxtToFqdnGroupReader extends TxtFileReader<List<List<Fqdn>>> {
	@Override
	protected List<List<Fqdn>> createCollection() {
		return new ArrayList<>();
	}

	@Override
	public void convertToObject(List<List<Fqdn>> listOfLists, List<String> lines) {
		int numberOfArrays = lines.stream().filter(String::isEmpty).mapToInt(i -> 1).sum() + 1;
		for (int i = 0; i < numberOfArrays; i++) {
			listOfLists.add(new ArrayList<>());
		}
		int index = 0;
		for (String line : lines) {
			if (line.isEmpty()) {
				index++;
			} else {
				listOfLists.get(index).add(Fqdn.load(line));
			}
		}
	}
}
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import controller.utils.readers.TxtToFqdnGroupReader;
import javafx.collections.ObservableList;
import model.Fqdn;

public class TxtToFqdnGroupReaderTest {

	@Test
	public void test() {
		List<String> lines = new ArrayList<>();
		lines.add("1 a");
		lines.add("");
		lines.add("2 b");
		lines.add("3 x");
		lines.add("");
		lines.add("4 z");

		List<ObservableList<Fqdn>> listOflists = new ArrayList<>();
		TxtToFqdnGroupReader txtToFqdnGroupReader = new TxtToFqdnGroupReader();
		// txtToFqdnGroupReader.convertToObject(listOflists, lines);

		System.out.println(listOflists);
		Assertions.assertEquals(3, listOflists.size());
	}
}

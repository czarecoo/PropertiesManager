package controller.utils;

public class Saver {
	private String separator = "=";

	protected String replace(String line, String key, String replacement) {
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
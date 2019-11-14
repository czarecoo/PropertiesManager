package controller.utils.writers;

public final class StringReplaceUtil {
	private static final String SEPARATOR = "=";

	private StringReplaceUtil() {

	}

	public static String replace(String line, String key, String replacement) {
		if (line.contains(key)) {
			String[] split = line.split(SEPARATOR);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(split[0]);
			stringBuilder.append(SEPARATOR);
			stringBuilder.append(replacement);
			return stringBuilder.toString();
		}
		return line;
	}
}
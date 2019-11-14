package model;

public class Fqdn extends Irmc {
	String fqdn;

	public Fqdn(String fqdn, String ip) {
		super(ip);
		this.fqdn = fqdn != null ? fqdn.toUpperCase() : EMPTY_STRING;
	}

	public String getFqdn() {
		return fqdn;
	}

	@Override
	public String toString() {
		if (!fqdn.isEmpty() && !ip.isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(fqdn);
			stringBuilder.append(" (");
			stringBuilder.append(ip);
			stringBuilder.append(")");
			return stringBuilder.toString();
		} else {
			return EMPTY_STRING;
		}
	}

	public static Fqdn load(String string) {
		if (string == null || string.isEmpty()) {
			return new Fqdn(null, null);
		}
		String[] split = string.split(SPACE);
		return new Fqdn(split[1], split[0]);
	}
}
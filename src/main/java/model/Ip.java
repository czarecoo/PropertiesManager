package model;

public class Ip {
	static final String EMPTY_STRING = "";
	static final String SPACE = " ";
	String ip;

	public Ip(String ip) {
		this.ip = ip != null ? ip : EMPTY_STRING;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		return ip;
	}

	public String save() {
		return getIp();
	}

	public static Ip load(String string) {
		return new Ip(string);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ip other = (Ip) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		return true;
	}
}
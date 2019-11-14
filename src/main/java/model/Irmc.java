package model;

public class Irmc extends Ip {
	static final String FUJI_THIRD_OCTET = ".181.";
	static final String IRMC_THIRD_OCTET = ".201.";

	String irmc;

	public Irmc(String ip) {
		super(ip);
		this.irmc = ip != null ? ip.replace(FUJI_THIRD_OCTET, IRMC_THIRD_OCTET) : EMPTY_STRING;
	}

	public String getIrmc() {
		return irmc;
	}

	@Override
	public String toString() {
		return ip;
	}

	public static Irmc load(String string) {
		return new Irmc(string);
	}
}
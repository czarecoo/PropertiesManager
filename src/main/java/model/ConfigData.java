package model;

public class ConfigData {
	private String es;
	private String vc1;
	private String vc2;

	public ConfigData(String es, String vc1, String vc2) {
		this.es = es != null ? es : "";
		this.vc1 = vc1 != null ? vc1 : "";
		this.vc2 = vc2 != null ? vc2 : "";
	}

	public String getEs() {
		return es;
	}

	public String getVc1() {
		return vc1;
	}

	public String getVc2() {
		return vc2;
	}
}
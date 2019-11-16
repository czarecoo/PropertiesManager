package model.holders;

import model.Ip;

public class ConfigData {
	private Ip es;
	private Ip vc1;
	private Ip vc2;

	public ConfigData(Ip es, Ip vc1, Ip vc2) {
		this.es = es;
		this.vc1 = vc1;
		this.vc2 = vc2;
	}

	public Ip getEs() {
		return es;
	}

	public Ip getVc1() {
		return vc1;
	}

	public Ip getVc2() {
		return vc2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigData [es=");
		builder.append(es);
		builder.append(", vc1=");
		builder.append(vc1);
		builder.append(", vc2=");
		builder.append(vc2);
		builder.append("]");
		return builder.toString();
	}
}
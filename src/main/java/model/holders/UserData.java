package model.holders;

import model.Fqdn;
import model.Ip;
import model.Irmc;

public class UserData {
	private HostData hostData;
	private ConfigData configData;
	private String path;

	public UserData(HostData hostData, ConfigData configData, String path) {
		this.hostData = hostData;
		this.configData = configData;
		this.path = path != null ? path : "";
	}

	public Irmc getBx() {
		return hostData.getBx();
	}

	public Fqdn getCx1() {
		return hostData.getCx1();
	}

	public Irmc getCx2() {
		return hostData.getCx2();
	}

	public Ip getEs() {
		return configData.getEs();
	}

	public Ip getVc1() {
		return configData.getVc1();
	}

	public Ip getVc2() {
		return configData.getVc2();
	}

	public String getPath() {
		return path;
	}
}
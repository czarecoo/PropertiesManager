package model;

public class UserData {
	private HostData hostData;
	private ConfigData configData;
	private String path;

	public UserData(HostData hostData, ConfigData configData, String path) {
		this.hostData = hostData;
		this.configData = configData;
		this.path = path != null ? path : "";
	}

	public String getBx() {
		return hostData.getBx();
	}

	public String getCx1() {
		return hostData.getCx1();
	}

	public String getCx2() {
		return hostData.getCx2();
	}

	public String getEs() {
		return configData.getEs();
	}

	public String getVc1() {
		return configData.getVc1();
	}

	public String getVc2() {
		return configData.getVc2();
	}

	public String getPath() {
		return path;
	}
}
package model;

public class HostData {
	private String bx;
	private String cx1;
	private String cx2;

	public HostData(String bx, String cx1, String cx2) {
		this.bx = bx != null ? bx : "";
		this.cx1 = cx1 != null ? cx1 : "";
		this.cx2 = cx2 != null ? cx2 : "";
	}

	public String getBx() {
		return bx;
	}

	public String getCx1() {
		return cx1;
	}

	public String getCx2() {
		return cx2;
	}
}
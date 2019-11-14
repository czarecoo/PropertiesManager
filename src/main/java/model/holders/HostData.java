package model.holders;

import model.Fqdn;
import model.Irmc;

public class HostData {
	private Irmc bx;
	private Fqdn cx1;
	private Irmc cx2;

	public HostData(Irmc bx, Fqdn cx1, Irmc cx2) {
		this.bx = bx;
		this.cx1 = cx1;
		this.cx2 = cx2;
	}

	public Irmc getBx() {
		return bx;
	}

	public Fqdn getCx1() {
		return cx1;
	}

	public Irmc getCx2() {
		return cx2;
	}
}
package model.holders;

public class KeysData {
	private String es1Key;
	private String es2Key;
	private String vc1Key;
	private String vc2Key;
	private String bxKey;
	private String cx1Key;
	private String cx2Key;
	private String bxIrmcKey;
	private String cx1IrmcKey;
	private String cx2IrmcKey;

	public String getBxIrmcKey() {
		return bxIrmcKey;
	}

	public String getBxKey() {
		return bxKey;
	}

	public String getCx1IrmcKey() {
		return cx1IrmcKey;
	}

	public String getCx1Key() {
		return cx1Key;
	}

	public String getCx2IrmcKey() {
		return cx2IrmcKey;
	}

	public String getCx2Key() {
		return cx2Key;
	}

	public String getEs1Key() {
		return es1Key;
	}

	public String getEs2Key() {
		return es2Key;
	}

	public String getVc1Key() {
		return vc1Key;
	}

	public String getVc2Key() {
		return vc2Key;
	}

	public void setBxIrmcKey(String property) {
		bxIrmcKey = property;
	}

	public void setBxKey(String property) {
		bxKey = property;
	}

	public void setCx1IrmcKey(String property) {
		cx1IrmcKey = property;
	}

	public void setCx1Key(String property) {
		cx1Key = property;
	}

	public void setCx2IrmcKey(String property) {
		cx2IrmcKey = property;
	}

	public void setCx2Key(String property) {
		cx2Key = property;
	}

	public void setEs1Key(String property) {
		es1Key = property;
	}

	public void setEs2Key(String property) {
		es2Key = property;
	}

	public void setVc1Key(String property) {
		vc1Key = property;
	}

	public void setVc2Key(String property) {
		vc2Key = property;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeysData [es1Key=");
		builder.append(es1Key);
		builder.append(", es2Key=");
		builder.append(es2Key);
		builder.append(", vc1Key=");
		builder.append(vc1Key);
		builder.append(", vc2Key=");
		builder.append(vc2Key);
		builder.append(", bxKey=");
		builder.append(bxKey);
		builder.append(", cx1Key=");
		builder.append(cx1Key);
		builder.append(", cx2Key=");
		builder.append(cx2Key);
		builder.append(", bxIrmcKey=");
		builder.append(bxIrmcKey);
		builder.append(", cx1IrmcKey=");
		builder.append(cx1IrmcKey);
		builder.append(", cx2IrmcKey=");
		builder.append(cx2IrmcKey);
		builder.append("]");
		return builder.toString();
	}
}
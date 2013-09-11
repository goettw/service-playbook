package serviceplaybook.model;

public class BigPlayItem {
	String id;
	String level1;
	String level2;
	String display;

	public String getDisplay() {
		return getLevel1() + "/" + getLevel2();
	}



	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}
}

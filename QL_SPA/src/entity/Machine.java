package entity;

public class Machine {
	public String itemIdM;
	public String itemNameM;
	private int time;
	public int amountM;
	public int amountImport;
	private String laBel;
	public float moneyImport;
	public Machine() {
		super();
	}
	public Machine(String itemIdM, String itemNameM, int time, int amountM, int amountImport, String laBel, float moneyImport) {
		super();
		this.itemIdM = itemIdM;
		this.itemNameM = itemNameM;
		this.time = time;
		this.amountM = amountM;
		this.amountImport = amountImport;
		this.laBel = laBel;
		this.moneyImport = moneyImport;
	}
	public String getItemIdM() {
		return itemIdM;
	}
	public void setItemIdM(String itemIdM) {
		this.itemIdM = itemIdM;
	}
	public String getItemNameM() {
		return itemNameM;
	}
	public void setItemNameM(String itemNameM) {
		this.itemNameM = itemNameM;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getAmountM() {
		return amountM;
	}
	public void setAmountM(int amountM) {
		this.amountM = amountM;
	}
	public int getAmountImport() {
		return amountImport;
	}
	public void setAmountImport(int amountImport) {
		this.amountImport = amountImport;
	}
	public String getLaBel() {
		return laBel;
	}
	public void setLaBel(String laBel) {
		this.laBel = laBel;
	}
	public float getMoneyImport() {
		return moneyImport;
	}
	public void setMoneyImport(float moneyImport) {
		this.moneyImport = moneyImport;
	}	

}

package ca.danib.gameoflife.model;

public enum LifeStatus {
	ALIVE("X"),
	DEAD("_");

	private String printChar;

	LifeStatus(String printChar) {
		this.printChar = printChar;
	}

	public String getPrintChar() {
		return printChar;
	}

}

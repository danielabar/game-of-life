package ca.danib.gameoflife.model;

public class Cell {

	private Position position;
	private LifeStatus lifeStatus;

	public Cell(Position position) {
		this(position, LifeStatus.DEAD);
	}

	public Cell(Position position, LifeStatus lifeStatus) {
		super();
		this.position = position;
		this.lifeStatus = lifeStatus;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public LifeStatus getLifeStatus() {
		return lifeStatus;
	}
	public void setLifeStatus(LifeStatus lifeStatus) {
		this.lifeStatus = lifeStatus;
	}

}

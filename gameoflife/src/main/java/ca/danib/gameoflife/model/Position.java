package ca.danib.gameoflife.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Position {

	private Integer row;
	private Integer column;

	public Position(Integer row, Integer column) {
		super();
		this.row = row;
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Position rhs = (Position) obj;
		return new EqualsBuilder()
			.append(this.row, rhs.row)
			.append(this.column, rhs.column)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(row)
			.append(column)
			.toHashCode();
	}

	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }

}

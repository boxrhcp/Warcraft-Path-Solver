package WarcraftPack;

import aima.core.agent.impl.DynamicAction;

public class Operators extends DynamicAction {
	@Override
	public String toString() {
		return "Operators [type=" + type + ", x=" + x + ", y=" + y + "]";
	}

	public enum opType {
		UP, DOWN, LEFT, RIGHT
	}

	public opType type;
	public int x;
	public int y;

	public Operators(String name, opType type, int x, int y) {
		super(name);
		this.type = type;
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operators other = (Operators) obj;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

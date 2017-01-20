package WarcraftPack;

public class State {

	public static char[][] matrix;
	public int x;
	public int y;
	public static int[] endPos;

	public State(char[][] map, int[] initPos, int[] finalPos) {
		matrix = map;
		x = initPos[0];
		y = initPos[1];
		endPos = finalPos;
	}

	public State(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String resultado = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				resultado += matrix[i][j];
			}
			resultado += '\n';
		}
		return resultado;
	}
}

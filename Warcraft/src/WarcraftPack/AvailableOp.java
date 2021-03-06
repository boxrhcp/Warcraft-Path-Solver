package WarcraftPack;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;

public class AvailableOp implements ActionsFunction {

	@Override
	public Set<Action> actions(Object s) {

		State state = (State) s;
		Set<Action> operators = new LinkedHashSet<Action>();
		int i = state.x;
		int j = state.y;
		int row = state.x;
		int col = state.y;
		
		/* Add possible operators */
		if (i != State.matrix.length - 1 && State.matrix[i + 1][j] != '@') {
			row++;
			operators.add(new Operators("Move Down", Operators.opType.DOWN, row, col));
		}

		if (i != 0 && State.matrix[i - 1][j] != '@') {
			row--;
			operators.add(new Operators("Move Up", Operators.opType.UP, row, col));
		}

		if (j != State.matrix[i].length - 1 && State.matrix[i][j + 1] != '@') {
			col++;
			operators.add(new Operators("Move Right", Operators.opType.RIGHT, row, col));
		}

		if (j != 0 && State.matrix[i][j - 1] != '@') {
			col--;
			operators.add(new Operators("Move Left", Operators.opType.LEFT, row, col));
		}

		return operators;
	}

}

package WarcraftPack;

import aima.core.agent.Action;
import aima.core.search.framework.ResultFunction;

public class OpResult implements ResultFunction {

	@Override
	public Object result(Object s, Action a) {
		State lastState = (State) s;
		Operators op = (Operators) a;
		int x = lastState.x;
		int y = lastState.y;

		switch (op.type) {
		case UP:

			x = lastState.x - 1;
			break;
		case LEFT:

			y = lastState.y - 1;
			break;
		case RIGHT:

			y = lastState.y + 1;
			break;
		case DOWN:

			x = lastState.x + 1;
			break;
		}
		State newState = new State(x, y);

		return newState;
	}

}

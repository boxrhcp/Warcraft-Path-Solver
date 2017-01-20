package WarcraftOptionalPack;

import aima.core.search.framework.GoalTest;

public class GoalFunction implements GoalTest {

	@Override
	public boolean isGoalState(Object staterec) {
		State state = (State) staterec;
		if (state.x == State.endPos[0] && state.y == State.endPos[1]) {
			return true;
		}

		return false;
	}
}

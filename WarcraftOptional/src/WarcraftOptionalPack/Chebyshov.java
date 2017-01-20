package WarcraftOptionalPack;

import aima.core.search.framework.HeuristicFunction;

public class Chebyshov implements HeuristicFunction {

	@Override
	public double h(Object stateRec) {
		State state = (State) stateRec;

		int x = Math.abs(State.endPos[0] - state.x);
		int y = Math.abs(State.endPos[1] - state.y);

		int max = Math.max(x, y);

		return max;
	}

}

package WarcraftOptionalPack;

import aima.core.search.framework.HeuristicFunction;

public class Manhattan implements HeuristicFunction {

	@Override
	public double h(Object staterec) {
		State state = (State) staterec;
		int hvalue = 0;

		hvalue = Math.abs(State.endPos[0] - state.x) + Math.abs(State.endPos[1] - state.y);
		
		return hvalue;
	}
}

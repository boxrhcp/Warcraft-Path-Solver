package WarcraftPack;

import aima.core.search.framework.HeuristicFunction;

public class Euclidean implements HeuristicFunction {

	@Override
	public double h(Object staterec) {
		State state = (State) staterec;
		double hvalue = 0;
		
		int x = Math.abs(State.endPos[0] - state.x);
		int y = Math.abs(State.endPos[1] - state.y);

		hvalue = Math.sqrt(x * x + y * y);
		return hvalue;
	}
}

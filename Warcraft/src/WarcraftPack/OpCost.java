package WarcraftPack;

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;

public class OpCost implements StepCostFunction {

	@Override
	public double c(Object s, Action a, Object sDelta) {
		State state = (State) s;
		double cost = 0;
		switch (State.matrix[state.x][state.y]) {
		/* path */
		case '.':
			cost = 1;
			break;
		/* water */
		case 'W':
			cost = 2;
			break;
		/* sand */
		case 'S':
			cost = 2;
			break;
		/* tree */
		case 'T':
			cost = 4;
			break;
		default:
			cost = 1;
		}

		return cost;
	}

}

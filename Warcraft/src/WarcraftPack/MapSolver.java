package WarcraftPack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Metrics;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class MapSolver {
	public static int[] initPos;
	public static String path;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Algorithm algorithm = getAlgorithm(args[1]);
		Heuristic heuristic = getHeuristic(args[2]);
		path = args[0];
		initPos = getPos(args[3]);
		int[] endPos = getPos(args[4]);
		State initialState = read_file(path, initPos, endPos);
		AvailableOp opAvailable = new AvailableOp();
		GoalFunction goalF = new GoalFunction();
		OpCost costOp = new OpCost();
		OpResult opResult = new OpResult();
		try {
			/* adding the problem space */
			Problem problem = new Problem(initialState, opAvailable, opResult, goalF, costOp);

			HeuristicFunction hf = null;
			switch (heuristic) {
			case Manhattan:
				System.out.println("Heuristic: Manhattan");
				hf = new Manhattan();
				break;
			case Euclidean:
				System.out.println("Heuristic: Euclidean");
				hf = new Euclidean();
				break;
			default:
				System.out.println("Not valid");
				System.exit(-1);
			}

			Search search = null;
			switch (algorithm) {
			case Breadth:
				System.out.println("Breadth First Search");
				search = new BreadthFirstSearch();
				break;
			case Depth:
				System.out.println("Depth First Search");
				search = new DepthFirstSearch(new GraphSearch());
				break;
			case Astar:
				System.out.println("A*");
				search = new AStarSearch(new GraphSearch(), hf);
				break;
			case GBFS:
				System.out.println("GBFS");
				search = new GreedyBestFirstSearch(new GraphSearch(), hf);
				break;
			default:
				System.out.println("Not valid");
				System.exit(-1);
			}
			/* adding the search algorithm and if possible with heuristic */
			long t = System.currentTimeMillis();
			List<Action> actionList = search.search(problem);
			long t2 = System.currentTimeMillis();
			/* print of map and statistics in file */
			printStadistics(actionList, actionList, initialState, t, t2, search);
			printResultFile(actionList, initialState);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printStadistics(List<Action> actionList, List<Action> actionList2, State initialState, long t,
			long t2, Search search) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(path + ".statistics", "UTF-8");
		writer.println("Time: " + (t2 - t) / 1000.0 + " s");
		writer.println(printActions(actionList));
		System.out.println("Time: " + (t2 - t) / 1000.0 + " s");
		writer.println("Plan length: " + actionList2.size());
		System.out.println("Plan length: " + actionList2.size());
		writer.println(printInstrumentation(search.getMetrics()));
		writer.close();
	}

	private static void printResultFile(List<Action> actionList, State state)
			throws FileNotFoundException, UnsupportedEncodingException {
		State.matrix[initPos[0]][initPos[1]] = 'I';
		for (Action act : actionList) {
			Operators op = (Operators) act;
			State.matrix[op.x][op.y] = 'X';
		}
		State.matrix[State.endPos[0]][State.endPos[1]] = 'F';
		PrintWriter writer = new PrintWriter(path + ".output", "UTF-8");
		writer.println(state.toString());
		writer.close();
	}

	private static String printInstrumentation(Metrics metric) {
		String result = null;
		for (String key : metric.keySet()) {
			String property = metric.get(key);
			System.out.println(key + " : " + property);
			result += key + " : " + property + "\n";
		}
		return result;
	}

	private static String printActions(List<Action> actions) {
		String result = null;
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
		result = "Plan Length: " + actions.size();
		return result;
	}

	private static int[] getPos(String string) {
		int[] Pos = new int[2];
		String[] position = string.split("-");
		Pos[0] = Integer.parseInt(position[0]);
		Pos[1] = Integer.parseInt(position[1]);
		System.out.println("Position retrieved: " + Pos[0] + " " + Pos[1]);
		return Pos;
	}

	private static Heuristic getHeuristic(String string) {
		if (string.equals("manhattan")) {
			return Heuristic.Manhattan;
		} else if (string.equals("euclidean")) {
			return Heuristic.Euclidean;
		} else {
			System.err.println("Invalid heuristic: " + string);
			System.exit(-1);
			return null;
		}
	}

	private static Algorithm getAlgorithm(String input) {
		if (input.equals("bfs")) {
			return Algorithm.Breadth;
		} else if (input.equals("dfs")) {
			return Algorithm.Depth;
		} else if (input.equals("astar")) {
			return Algorithm.Astar;
		} else if (input.equals("gbfs")) {
			return Algorithm.GBFS;
		} else {
			System.err.println("Invalid algorithm: " + input);
			System.exit(-1);
			return null;
		}
	}

	public enum Algorithm {
		Breadth, Depth, Astar, GBFS
	};

	public enum Heuristic {
		Manhattan, Euclidean
	};

	public static State read_file(String file_path, int[] initPos, int[] endPos) throws IOException {
		FileReader fr = new FileReader(file_path);
		@SuppressWarnings("resource")
		BufferedReader textReader = new BufferedReader(fr);
		String lines;
		int i = 0;
		int totalRows = 0;
		int totalCols = 0;
		ArrayList<String> lineArray = new ArrayList<String>();
		while ((lines = textReader.readLine()) != null) {
			if (i > 3) {
				lineArray.add(lines);
			}
			i++;
		}
		totalRows = lineArray.size();
		totalCols = lineArray.get(0).length();
		char map[][] = new char[totalRows][totalCols];

		System.out.println("Rows = " + totalRows + " Cols = " + totalCols);
		for (int x = 0; x < totalRows; x++) {
			for (int y = 0; y < totalCols; y++) {
				map[x][y] = lineArray.get(x).charAt(y);

			}

		}
		if (map[initPos[0]][initPos[1]] == '@') {
			System.out.println("The initial position is on a Wall, the problem is unsolvable");
			System.exit(-1);
		}
		return new State(map, initPos, endPos);
	}

}

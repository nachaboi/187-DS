package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	
	List<Integer> startingValues;
	List<List<Integer>> solutions = new ArrayList<>();
	
	public EightPuzzle(List<Integer> startingValues) {
		if(startingValues.size() != 9) {
			throw new IllegalArgumentException();
		}
		List<Integer> tracker = new ArrayList<>();
		for(int i = 0; i < startingValues.size(); i++) {
			Integer thisOne = i;
			if(tracker.contains(thisOne) || startingValues.get(i) < 0 || startingValues.get(i) > 8) {
				throw new IllegalArgumentException();
			}
			else {
				tracker.add(thisOne);
			}
		}
		this.startingValues = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		return startingValues;
	}
	
	public int findX(int zeroIndex) {
		int x = -1;
		if(zeroIndex == 0 || zeroIndex == 3 || zeroIndex == 6) {
			x = 0;
		}
		else if(zeroIndex == 1 || zeroIndex == 4 || zeroIndex == 7) {
			x = 1;
		}
		else {
			x = 2;
		}
		return x;
	}
	
	public int findY(int zeroIndex) {
		int y = -1;
		if(zeroIndex == 0 || zeroIndex == 1 || zeroIndex == 2) {
			y = 0;
		}
		else if(zeroIndex == 3 || zeroIndex == 4 || zeroIndex == 5) {
			y = 1;
		}
		else {
			y = 2;
		}
		return y;
	}

	public List<Integer> swap(List<Integer> state, int first, int second) {
		List<Integer> theNew = new ArrayList<>();
		theNew.addAll(state);
		Integer save = theNew.get(first);
		Integer other = theNew.get(second);
		theNew.remove(first);
		theNew.add(first, other);
		theNew.remove(second);
		theNew.add(second, save);
		return theNew;
		
	}
	
	
	//0 = left
		//1 = right
		//2 = up
		//3 = down
		public void addDir(int dir, List<Integer> currentState, int zeroIndex) {
			if(dir == 0) {
				solutions.add(swap(currentState, zeroIndex, zeroIndex - 1));
			}
			else if(dir == 1) {
				solutions.add(swap(currentState, zeroIndex, zeroIndex + 1));
			}
			else if(dir == 2) {
				solutions.add(swap(currentState, zeroIndex, zeroIndex - 3));
			}
			else if(dir == 3) {
				solutions.add(swap(currentState, zeroIndex, zeroIndex + 3));
			}
		}
	
	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		solutions.clear();
		List<Integer> indexer = new ArrayList<Integer>();
		for(int i = 0; i < currentState.size(); i++) {
			indexer.add(currentState.get(i));
		}
		int zeroIndex = -1;
		
		for(int j = 0; j < indexer.size(); j++) {
			if(indexer.get(j) == 0) {
				zeroIndex = j;
			}
		}
		int x = findX(zeroIndex);
		int y = findY(zeroIndex);
		//Laterally
		if(x == 0) {
			addDir(1, currentState, zeroIndex);
		}
		else if(x == 1) {
			addDir(0, currentState, zeroIndex);
			addDir(1, currentState, zeroIndex);
		}
		else {
			addDir(0, currentState, zeroIndex);
		}
		//Vertically
		if(y == 0) {
			addDir(3, currentState, zeroIndex);
		}
		else if(y == 1) {
			addDir(2, currentState, zeroIndex);
			addDir(3, currentState, zeroIndex);
		}
		else {
			addDir(2, currentState, zeroIndex);
		}
		return solutions;

	}


	@Override
	public boolean isGoal(List<Integer> state) {
		List<Integer> theGoal = new ArrayList<>();
		for(int i = 1; i <= 8; i++) {
			theGoal.add(i);
		}
		theGoal.add(0);
		if(state.equals(theGoal)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}

package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}
	
	public class DualObject {
		
		T current;
		T predecessor;
		
		public DualObject(T current, T predecessor) {
			this.current = current;
			this.predecessor = predecessor;
		}
		
		
		public T getCurrent() {
			return current;
		}
		
		public T getPredecessor() {
			return predecessor;
		}
	}

	@Override
	public List<T> findSolution() {
		T start = searchProblem.getInitialState();
		Stack<T> frontier = new Stack<>();
		frontier.add(start);
		
		List<DualObject> predecessor = new ArrayList<>();
		predecessor.add(new DualObject(start, null));

		List<T> path = new ArrayList<>();
		
		while(!frontier.isEmpty()) {
			T current = frontier.pop();
			for(T next : searchProblem.getSuccessors(current) ) {
				boolean hasIt = false;
				for(DualObject e : predecessor) {
					if(next.equals(e.getCurrent())) {
						hasIt = true;
						break;
					}
				}
				if(!hasIt) {
					frontier.add(next);
					predecessor.add(new DualObject(next, current));
				}
			}
			
			if(searchProblem.isGoal(current)) {
				path.add(current);
				T previous = null;
				for(DualObject e : predecessor) {
					if(e.getCurrent().equals(current)) {
						previous = e.getPredecessor();
					}
				}
				while(previous != null) {
					path.add(0, previous);
					current = previous;
					for(DualObject e : predecessor) {
						if(e.getCurrent().equals(current)) {
							previous = e.getPredecessor();
						}
					}
				}
				break;
			}
		}
		return path;
	}
}

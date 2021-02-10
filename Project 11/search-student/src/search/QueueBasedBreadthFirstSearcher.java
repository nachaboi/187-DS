package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */

public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
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
		Queue<T> frontier = new LinkedList<>();
		frontier.add(start);
		
		List<DualObject> predecessor = new ArrayList<>();
		predecessor.add(new DualObject(start, null));
		List<T> outsetPred = new ArrayList<>();
		outsetPred.add(start);

		List<T> path = new ArrayList<>();
		
		while(!frontier.isEmpty()) {
			T current = frontier.remove();
			for(T next : searchProblem.getSuccessors(current) ) {
				if(!outsetPred.contains(next)) {
					frontier.add(next);
					predecessor.add(new DualObject(next, current));
					outsetPred.add(next);
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

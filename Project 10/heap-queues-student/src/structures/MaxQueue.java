package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
	
	Comparator<Integer> theComparator = new IntegerComparator();
	
	
	AbstractArrayHeap<Integer, V> heap = new StudentArrayHeap<Integer, V>(theComparator);
	int size = 0;
	
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		if(priority == null || value == null) {
			throw new NullPointerException();
		}
		heap.add(priority, value);
		size++;
		heap.bubbleUp(size-1);
		return this;
		
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		if(size==0) {
			throw new IllegalStateException();
		}
		
        V root = heap.remove();
        size--;
        if(!heap.isEmpty()) {
            heap.bubbleDown(0);
        }
        return root;
		
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
//		Iterator<Entry<Integer, V>> it = new integerIterator();
//		return it;
//		ArrayList<Entry<Integer, V>> a = (ArrayList<Entry<Integer, V>>) heap.asList();
//		return a.iterator();
		return heap.asList().iterator();
	}
	
//	private class integerIterator implements Iterator<Entry<Integer, V>> {
//
//		int index;
//		
//		public integerIterator() {
//			index = 0;
//		}
//		
//		@Override
//		public boolean hasNext() {
//			return (index < heap.size() -1);
//		}
//
//		@Override
//		public Entry<Integer, V> next() {
//			if(index < heap.size() -1) {
//				Entry<Integer, V> save = heap.asList().get(index);
//				index++;
//				return save;
//			}
//			throw new NullPointerException();
//			
//		}
//		
//	}

	@Override
	public Comparator<Integer> getComparator() {
		return theComparator;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
}

package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
	
    public Comparator<P> a = getComparator();


	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLeftChildOf(int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return 2*index+1;
	}

	@Override
	protected int getRightChildOf(int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return 2*index+2;
	}

	@Override
	protected int getParentOf(int index) {
		if(index < 1) {
			throw new IndexOutOfBoundsException();
		}
		return (index-1)/2;
	}

	@Override
	protected void bubbleUp(int index) {
		Entry<P, V> value = heap.get(index);
        int parent = (index-1)/2;
        if(parent < 0) {
        		return;
        }
        Entry<P, V> parentValue = heap.get(parent);
        if(index > 0 && a.compare(value.getPriority(), parentValue.getPriority()) > 0) {
        		heap.set(index, parentValue);
        		heap.set(parent, value);
        		bubbleUp(parent);
        }
	}

	@Override
	protected void bubbleDown(int index) {
		int largerChild;
        Entry<P, V> value = heap.get(index);
        if(index < size()/2) {
        		int left = 2*index+1, right = 2*index+2;
        		//might have to check this later (not checking if those parts of the heap exist
        		if(left > size()-1) { //if both don't exist
        			return;
        		}
        		else if(left <=size()-1 && right > size()-1) {
        			largerChild =left;
        		}
        		else {
        			if(right < size() && a.compare(heap.get(left).getPriority(), heap.get(right).getPriority()) <0) {
            			largerChild = right;
            		}
            		else {
            			largerChild = left;
            		}
        		}
        		
        		if(a.compare(value.getPriority(), heap.get(largerChild).getPriority()) >= 0) {
        			return;
        		}
        		heap.set(index, heap.get(largerChild));
        		heap.set(largerChild, value);
        		bubbleDown(largerChild);
        		
        }
		
	}
	
	
}


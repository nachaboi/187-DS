package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;
	public BSTNode<T> head;
	public int size;

	public ScapegoatTree() {
		head = null;
		size = 0;
	}
	
	@Override
	public void add(T t) {
		if(head == null) {
			head = new BSTNode<T>(t, null, null);
			size++;
			return;
		}
		
		
	}

	@Override
	public boolean remove(T element) {
		// TODO
		return false;
	}
}

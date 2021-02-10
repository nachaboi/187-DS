package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {
	
	int size;
	public LinkedNode<T> firstElement;
	public LinkedNode<T> lastElement;
	boolean tick;
	
	public RecursiveList() {
		size = 0;
		firstElement = null;
		lastElement = null;
		tick = false;
	}

	/**
	 * Returns the number of elements in this {@link ListInterface}. This method
	 * runs in O(1) time.
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the front of this {@link ListInterface}. This method
	 * runs in O(1) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertFirst(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(size == 0) {
			firstElement = new LinkedNode<T>(elem, null);
			lastElement = firstElement;
		}
		else {
			LinkedNode<T> newHead = new LinkedNode<T>(elem, firstElement);
			firstElement = newHead;
		}
		size++;
		return this; //uhh
	}

	/**
	 * Adds an element to the end of this {@link ListInterface}. This method
	 * runs in O(size) time. For convenience, this method returns the
	 * {@link ListInterface} that was modified.
	 * 
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return the modified {@link ListInterface}
	 */
	public ListInterface<T> insertLast(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(size == 0) {
			firstElement = new LinkedNode<T>(elem, null);
			lastElement = firstElement;
		}
		else {
			LinkedNode<T> newTail = new LinkedNode<T>(elem, null);
			lastElement.setNext(newTail);
			lastElement = newTail;
		}
		size++;
		return this;
	}

	/**
	 * Adds an element at the specified index such that a subsequent call to
	 * {@link ListInterface#get(int)} at {@code index} will return the inserted
	 * value. This method runs in O(index) time. For convenience, this method
	 * returns the {@link ListInterface} that was modified.
	 * 
	 * @param index
	 *            the index to add the element at
	 * @param elem
	 *            the element to add
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @throws IndexOutOfBoundsException
	 *             if {@code index} is less than 0 or greater than
	 *             {@link ListInterface#size()}
	 * @return The modified {@link ListInterface}
	 */
	public ListInterface<T> insertAt(int index, T elem) throws NullPointerException, IndexOutOfBoundsException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
			if(index == 0) {
				return insertFirst(elem);
			}
			if(index == size) {
				return insertLast(elem);
			}
		else {
			LinkedNode<T> cur = getIndex(firstElement, 0, index-1);
			LinkedNode<T> inserter = new LinkedNode<T>(elem, cur.getNext());
			cur.setNext(inserter);
			size++;
		}
		return this;
	}
	
	

	/**
	 * Removes the first element from this {@link ListInterface} and returns it.
	 * This method runs in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeFirst() throws IllegalStateException{
		if(size == 0 ) {
			throw new IllegalStateException();
		}
		T firstData = firstElement.getData();
		firstElement = firstElement.getNext();
		size--;
		if(size == 0) {
			lastElement = null;
		}
		return firstData;
	}

	/**
	 * <p>
	 * Removes the last element from this {@link ListInterface} and returns it.
	 * This method runs in O(size) time.
	 *</p>
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the removed element
	 */
	public T removeLast() throws IllegalStateException{
		if(size == 0 ) {
			throw new IllegalStateException();
		}
		T lastData = lastElement.getData();
		if(size == 1) {
			firstElement = null;
			lastElement = null;
			size--;
			return lastData;
		}
		else {
			LinkedNode<T> newTail = getSecondLast(firstElement, 1);
			newTail.setNext(null);
			lastElement = newTail;
			size--;
			return lastData;
		}
	
		
		
	}
	
	private LinkedNode<T> getSecondLast(LinkedNode<T> cur, int pos) {
		if(pos != size-1) {
			return getSecondLast(cur.getNext(), pos +1);
		}
		return cur;
	}

	/**
	 * Removes the ith element in this {@link ListInterface} and returns it.
	 * This method runs in O(i) time.
	 * 
	 * @param i
	 *            the index of the element to remove
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return The removed element
	 */
	public T removeAt(int i) throws IndexOutOfBoundsException{
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 1) {
			T data = firstElement.getData();
			removeFirst();
			return data;
		}
		if(i == 0) {
			T data = firstElement.getData();
			removeFirst();
			return data;
		}
		if(i == size -1) {
			LinkedNode<T> beforeRemoval = getIndex(firstElement, 0, i-1);
			LinkedNode<T> toRemove = beforeRemoval.getNext();
			T dataToReturn = toRemove.getData();
			beforeRemoval.setNext(null);
			lastElement = beforeRemoval;
			size--;
			return dataToReturn;
		}
		LinkedNode<T> beforeRemoval = getIndex(firstElement, 0, i-1);
		LinkedNode<T> toRemove = beforeRemoval.getNext();
		T dataToReturn = toRemove.getData();
		beforeRemoval.setNext(toRemove.getNext());
		size--;
		return dataToReturn;
	}
	
	private LinkedNode<T> getIndex(LinkedNode<T> cur, int index, int wanted) {
		if(index == wanted) {
			return cur;
		}
		else {
			return getIndex(cur.getNext(), index + 1, wanted);
		}
	}

	/**
	 * Returns the first element in this {@link ListInterface}. This method runs
	 * in O(1) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the first element in this {@link ListInterface}.
	 */
	public T getFirst() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		return firstElement.getData();
	}

	/**
	 * Returns the last element in this {@link ListInterface}. This method runs
	 * in O(size) time.
	 * 
	 * @throws IllegalStateException
	 *             if the {@link ListInterface} is empty.
	 * @return the last element in this {@link ListInterface}.
	 */
	public T getLast() throws IllegalStateException{
		if(size == 0) {
			throw new IllegalStateException();
		}
		return lastElement.getData();
	}

	/**
	 * Returns the ith element in this {@link ListInterface}. This method runs
	 * in O(i) time.
	 * 
	 * @param i
	 *            the index to lookup
	 * @throws IndexOutOfBoundsException
	 *             if {@code i} is less than 0 or {@code i} is greater than or
	 *             equal to {@link ListInterface#size()}
	 * @return the ith element in this {@link ListInterface}.
	 */
	public T get(int i) throws IndexOutOfBoundsException {
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			LinkedNode<T> theOne = getIndex(firstElement, 0, i);
			return theOne.getData();
		}
	}

	/**
	 * Removes {@code elem} from this {@link ListInterface} if it exists. If
	 * multiple instances of {@code elem} exist in this {@link ListInterface}
	 * the one associated with the smallest index is removed. This method runs
	 * in O(size) time.
	 * 
	 * @param elem
	 *            the element to remove
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return {@code true} if this {@link ListInterface} was altered and
	 *         {@code false} otherwise.
	 */
	public boolean remove(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(size == 0) {
			return false;
		}
		if(firstElement.getData().equals(elem)) {
			removeFirst();
			return true;
		}
		LinkedNode<T> pre = goThrough(firstElement, 0, elem);
		if(pre == null) {
			return false;
		}
		LinkedNode<T> removing = pre.getNext();
		pre.setNext(removing.getNext());
		if(pre.getNext() == null) {
			lastElement = pre;
		}
//		if(tick) {
//			firstElement = pre;
//			tick = false;
//		}
		size--;
		return true;
		
	}
	
	public LinkedNode<T> goThrough(LinkedNode<T> cur, int index, T elem) {
		if(index >= size) {
			return null;
		}
		else {
			if(cur.getNext() != null) {
				if(cur.getNext().getData().equals(elem)) {
//					if(index == 0) {
//						tick = true;
//					}
					return cur;
				}
			}
			return goThrough(cur.getNext(), index+1, elem);
		}
	}

	/**
	 * Returns the smallest index which contains {@code elem}. If there is no
	 * instance of {@code elem} in this {@link ListInterface} then -1 is
	 * returned. This method runs in O(size) time.
	 * 
	 * @param elem
	 *            the element to search for
	 * @throws NullPointerException
	 *             if {@code elem} is {@code null}
	 * @return the smallest index which contains {@code elem} or -1 if
	 *         {@code elem} is not in this {@link ListInterface}
	 */
	public int indexOf(T elem) throws NullPointerException{
		if(elem == null) {
			throw new NullPointerException();
		}
		if(size == 0) {
			return -1;
		}
		else {
			return indexOfElm(1, firstElement, elem);
		}
	}
	
	private int indexOfElm(int index, LinkedNode<T> cur, T elem) {
		if(index > size) {
			return -1;
		}
		else {
			if(cur.getData().equals(elem)) {
				return index-1;
			}
			else {
				return indexOfElm(index + 1, cur.getNext(), elem);
			}
		}
	}
	
	public Iterator<T> iterator() {
		Iterator<T> toReturn = this.iterator();
		return toReturn;
	}
	/**
	 * Returns {@code true} if this {@link ListInterface} contains no elements
	 * and {@code false} otherwise. This method runs in O(1) time.
	 * 
	 * @return {@code true} if this {@link ListInterface} contains no elements
	 *         and {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}
}

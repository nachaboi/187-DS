package structures;

public class Iterator<T>{
	
	LinkedNode<T> firstElement;
	
	public Iterator(RecursiveList<T> list) {
		this.firstElement = list.firstElement;
	}
	
	public boolean hasNext() {
		return firstElement.getNext() != null;
	}
	
	public T next() {
		T data = firstElement.getData();
		firstElement = firstElement.getNext();
		return data;
	}

}

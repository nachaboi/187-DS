package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	public Node<T> head;
	public int size;
	public Node<T> tail;
	
	public Queue() {		
            // TODO 1
		size = 0;
		head = null;
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
		
		if(other.isEmpty()) {
			size = 0;
			head = null;
			return;
		}
		
		head = null;
		size = 0;
		Node<T> otherTemp = other.head;
		int otherSize = other.size;
		boolean first = true;
		Node<T> temp = head;
		for(int i = 0; i < otherSize; i++) {
			if(first) {
				head = new Node<T>(other.peek());
				otherTemp = otherTemp.next;
				temp = head;
				first = false;
			}
			else {
				temp.next = new Node<T>(otherTemp.data);
				temp = temp.next;
				otherTemp = otherTemp.next;
				if(otherTemp == null) {
					tail = temp;
				}
			}
		}
		size = otherSize;
	}
	
	@Override
	public boolean isEmpty() {
		return (size<=0);
	}

	@Override
	public int size() {
            return size;
	}

	@Override
	public void enqueue(T element) {
            if(head == null) {
            		head = new Node<T>(element);
            		tail = head;
            }
            else {
            		Node<T> newTail = new Node<T>(element);
            		tail.next = newTail;
            		tail = newTail;
            }
            size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			Node<T> temp = head;
			Node<T> tempTwo = head.next;
			head = tempTwo;
			size--;
			if(size == 0) {
				tail = null;
			}
			return temp.data;
		}
           
	}

	@Override
	public T peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
            return head.data;
	}
	
	
	@Override
	public UnboundedQueueInterface<T> reversed() {
		if(size == 0) {
			return new Queue<T>();
		}
		Queue<T> copy = new Queue<T>(this);
		int copySize = copy.size();
		Node<T> top = new Node<T>(copy.dequeue());
		while(!copy.isEmpty()) {
			Node<T> temp = new Node<T>(copy.dequeue(), top);
			top = temp;
		}
		copy.head = top;
		copy.size = copySize;
		return copy;
	}
	
//	public String daString() {
//		String a = "";
//		Node<T> it = head;
//		for(int i = 0; i <= size; i++) {
//			a +=  it.data;
//			it = it.next;
//		}
//		return a;
//	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
	
}


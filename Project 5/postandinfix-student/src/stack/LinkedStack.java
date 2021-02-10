package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	LLNode<T> top;
	int counter;
	
	public LinkedStack() {
		counter = 0;
	}
	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		if(top == null) {
			return null;
		}
		else if(top.link == null) {
			counter--;
			T thisReturn = top.info;
			top = null;
			return thisReturn;
		}
		LLNode<T> newHead = top.link;
		T toReturn = top.info;
		top = newHead;
		counter--;
		return toReturn;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		if(top==null) {
			return null;
		}
		return top.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return (top==null);
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
//		int count = 0;
//		LLNode<T> current = top;
//		while(current != null) {
//			count++;
//			current = current.link;
//		}
//		System.out.println(count + "VS" + counter);
//		return count;
		return counter;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		LLNode<T> currentHead = top;
		LLNode<T> newHead = new LLNode<T>(elem);
		newHead.link = currentHead;
		top = newHead;
		counter++;
	}

}

package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    E data = null;
    LinkedNode<E> next = null;
    LinkedNode<E> current = null;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
	  if(head != null) {
		  data = head.getData();
	      next = head.getNext();
	      current = head;
	  }
  }

  @Override
  public boolean hasNext() {
    if(current == null) {
    		return false;
    }
    return true;
  }

  @Override
  public E next() {
	  if(current == null) {
    		throw new NoSuchElementException();
    }
	  else {
		  
		  E toReturn = current.getData();
		  current = next;
		  if(current == null) {
			  data = null;
			  next = null;
		  }
		  else {
			  data = current.getData();
			  next = current.getNext();
		  }
		 
		  return toReturn;
	  }
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}

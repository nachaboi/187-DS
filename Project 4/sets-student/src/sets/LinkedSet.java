package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
//    int counter = 0;
//    LinkedNode<E> current = head;
//    if(current == null) {
//    		return 0;
//    }
//    if(current.getNext() == null) {
//    		return 1;
//    }
//    while(current.getNext() != null) {
//    		counter++;
//    		current = current.getNext();
//    }
//    counter++;
//    return counter;
//	  Set<E> ours = new LinkedSet<E>(head);
//	  Iterator<E> a = ours.iterator();
//	  for(E e : a) {
//		  
//	  }
	  if(head == null) {
		  return 0;
	  }
    int counter = 0;
    LinkedSet<E> ours = new LinkedSet<E>(head);
    for(E e : ours) {
    		counter++;
    }
    return counter;
	  
  }
  
  @Override
  public boolean isEmpty() {
    return (head==null);
  }

  @Override
  public Iterator<E> iterator() {
	  
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
	  if(head == null) {
		  return false;
	  }
//    if(head.equals(o)) {
//    	return true;
//    }
//    LinkedNode<E> current = head;
//    while(current.getNext() != null) {
//    		if(current.getData().equals(o)) {
//    			return true;
//    		}
//    		current = current.getNext();
//    }
//    if(current.getData().equals(o)) {
//    	return true;
//    }
//    return false;
	  LinkedSet<E> ours = new LinkedSet<E>(head);
	  for(E e: ours) {
		  if(e.equals(o)) {
			  return true;
		  }
	  }
	  return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    //check if that set is in our set
	  LinkedSet<E> ours = new LinkedSet<E>(head);
	  Set<E> theirs = that;
	  for(E e : ours) {
		  if(!theirs.contains(e)) {
			  return false;
		  }
	  }
	  return true;
	  
  }

  @Override
  public boolean isSuperset(Set<E> that) {
	  LinkedSet<E> ours = new LinkedSet<E>(head);
	  Set<E> theirs = that;
	  for(E e : theirs) {
		  if(!ours.contains(e)) {
			  return false;
		  }
	  }
	  return true;
  }

  @Override
  public Set<E> adjoin(E e) {
	  Set<E> ours = new LinkedSet<E>(head);
	  if(ours.contains(e)) {
		  return ours;
	  }
	  else {
		  LinkedNode<E> current = new LinkedNode<E>(e, head);
		  Set<E> toReturn = new LinkedSet<E>(current);
		  return toReturn;
	  }
  }

  @Override
  public Set<E> union(Set<E> that) {
	  Set<E> ours = new LinkedSet<E>(head);
	  Set<E> theirs = that;
	  for(E e : theirs) {
		  if(!ours.contains(e)) {
			  ours = ours.adjoin(e);
		  }
	  }
	  return ours;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
	  Set<E> ours = new LinkedSet<E>(head);
	  Set<E> theirs = that;
	  Set<E> toReturn = new LinkedSet<E>();
	  for(E e : ours) {
		  if(theirs.contains(e)) {
			  toReturn = toReturn.adjoin(e);
		  }
	  }
	  return toReturn;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9) in this but not in that
	  LinkedSet<E> ours = new LinkedSet<E>(head);
	  LinkedSet<E> theirs = (LinkedSet<E>)that;
	  Set<E> toReturn = new LinkedSet<E>();
	  for(E e : ours) {
		  if(!theirs.contains(e)) {
			  toReturn = toReturn.adjoin(e);
		  }
	  }
	  return toReturn;
	  
  }

  @Override
  public Set<E> remove(E e) {
	  Set<E> ours = new LinkedSet<E>(head);
	  Set<E> toReturn = new LinkedSet<E>();
	  for(E a : ours) {
		  if(!a.equals(e)) {
			  toReturn = toReturn.adjoin(a);
		  }
	  }
	  return toReturn;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}

package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		if(t==null) {
			throw new NullPointerException();
		}
		if(get(t) == null) {
			return false;
		}
		return true;
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
			
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		if(helper(root, t) != null) {
			return t;
		}
		return null;
		
	}
	
	private BSTNode<T> helper(BSTNode<T> cur, T t) {
		if(cur == null) {
			return null;
		}
		if(cur.getData().equals(t)) {
			return cur;
		}
		else {
//			boolean wentIn = false;
			if(cur.getLeft() != null) {
//				wentIn = true;
				return helper(cur.getLeft(), t);
			}
			if(cur.getRight() != null) {
//				wentIn = true;
				return helper(cur.getRight(), t);
			}
			return null;
		}
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}
	
	private BSTNode<T> helper2(BSTNode<T> cur, BSTNode<T> smallest) {
		if(cur.getData().compareTo(smallest.getData()) < 0) {
			smallest = cur;
		}
//			boolean wentIn = false;
			if(cur.getLeft() != null) {
//				wentIn = true;
				return helper2(cur.getLeft(), smallest);
			}
			if(cur.getRight() != null) {
//				wentIn = true;
				return helper2(cur.getRight(), smallest);
			}
			return smallest;
	}

	@Override
	public T getMinimum() {
		if(isEmpty()) {
			return null;
		}
		return (helper2(root, root).getData());
	}

	private BSTNode<T> helper3(BSTNode<T> cur, BSTNode<T> smallest) {
		if(cur.getData().compareTo(smallest.getData()) > 0) {
			smallest = cur;
		}
//			boolean wentIn = false;
			if(cur.getLeft() != null) {
//				wentIn = true;
				return helper3(cur.getLeft(), smallest);
			}
			if(cur.getRight() != null) {
//				wentIn = true;
				return helper3(cur.getRight(), smallest);
			}
			return smallest;
	}

	@Override
	public T getMaximum() {
		if(isEmpty()) {
			return null;
		}
		return (helper3(root, root).getData());
	}

	private int depth = -1;

	@Override
	public int height() {
		depth = -1;
		halp(0, root);
		return depth;
	}
	
	private int halp(int dep, BSTNode<T> cur) {
		if(cur == null) {
			return dep;
		}
		if(cur.getLeft() != null) {
			return halp(dep+1, cur.getLeft());
		}
		if(cur.getRight() != null) {
			return halp(dep+1, cur.getRight());
		}
		if(cur.getLeft() == null && cur.getRight() == null) {
			if(dep > depth) {
				depth = dep;
			}
		}
		return -1;
	}


	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if(node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if(node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	private boolean everFalse = false;
	
	@Override
	public boolean equals(BSTInterface<T> other) {
		everFalse = false;
		BSTNode<T> secRoot = other.getRoot();
		sameDeal(root, secRoot);
		return !everFalse;
		
	}
	
	
	private void sameDeal(BSTNode<T> root1, BSTNode<T> root2) {
		if(root1 == null && root2 == null) {
			return;
		}
		if(root1 == null && root2 != null) {
			everFalse = true;
			return;
		}
		if(root1 != null && root2 == null) {
			everFalse = true;
			return;
		}
		if(!root1.getData().equals(root2.getData())) {
			everFalse = true;
			return;
		}
		sameDeal(root1.getLeft(), root2.getRight());
		sameDeal(root2.getRight(), root2.getRight());
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		Queue<T> queue1 = new LinkedList<T>();
		Queue<T> queue2 = new LinkedList<T>();
		if(queue1.size() == 0 && queue2.size() == 0) {
			return true;
		}
		else if(queue1.size() == 0 || queue2.size() == 0) {
			return false;
		}
		inorderTraverse(queue1, root);
		inorderTraverse(queue2, other.getRoot());
		Iterator<T> one = queue1.iterator();
		Iterator<T> two = queue2.iterator();
		for(int i = 0; i < queue1.size(); i++) {
			if(one == null && two == null) {
			}
			else if(one == null || two == null) {
				return false;
			}
			else if(!one.next().equals(two.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isBalanced() {
		int n = size();
		int h = height();
		int con1 = (int) Math.pow(2, h);
		int con2 = (int) Math.pow(2, h+1);
		if(n >= con1 && n < con2) {
			return true;
		}
		return false;
	}
	
	private T[] prep() {
		Queue<T> queueOrder = new LinkedList<T>();
		inorderTraverse(queueOrder, root);
		Iterator<T> one = queueOrder.iterator();
		T[] array = (T[]) new Comparable[queueOrder.size()];
		for(int i = 0; i < queueOrder.size(); i++) {
			array[i] = one.next();
		}
//		array = (T[]) queueOrder.toArray();
		return array;
	}
	

	
	private T[] daArray = null;
	@Override
    @SuppressWarnings("unchecked")
	
	
	
	
	public void balance() {
		T[] array = prep();
		daArray = array;
		root = sorter(0, daArray.length-1);
		
		
	}
	
	private BSTNode<T> sorter(int low, int high) {
		if(low > high) {
			return null;
		}
		int mid = (low + high)/2;
		BSTNode<T> node = new BSTNode<T>(daArray[mid], null, null);
		node.setLeft(sorter(low, mid-1));
		node.setRight(sorter(mid+1, high));
		return node;
	}


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}
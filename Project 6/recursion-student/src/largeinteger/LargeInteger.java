package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		if(input.length() < 1) {
			return;
		}
		Integer temp = input.charAt(input.length()-1)-'0';
		head = new LLNode<Integer>(temp, null);
		LLNode<Integer> old = head;
		size++;
		for(int i = input.length()-2; i >= 0; i--) {
			Integer num = input.charAt(i)-'0';
			LLNode<Integer> current = new LLNode<Integer>(num, null);
			old.link = current;
			old = current;
			size++;
		}
		
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		if(size <= 1) {
			head.data = 0;
			size = 1;
		}
		else {
			LLNode<Integer> newHead = head.link;
			head = newHead;
			size--;
		}
		
		
		return this;
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		if(size == 1 && head.data == 0) {
		}
		else {
			LLNode<Integer> newHead = new LLNode<Integer>(0, head);
			head = newHead;
			size++;
		}
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		LLNode<Integer> thatHead = that.getList();
		String number = "";
		int toCarry = 0;
		int smallestSize;
		//1 = this is bigger | 2 = that is bigger | 3 = same size
		int type;
		if(that.size() < size) {
			smallestSize = that.size();
			type = 1;
		}
		else if(size < that.size()) {
			smallestSize = size;
			type = 2;
		}
		else {
			smallestSize = size;
			type = 3;
		}
		
		int i;
		
		LLNode<Integer> tHC = thatHead;
		LLNode<Integer> hC = head;
		for (i = 0; i < smallestSize; i++) {
			int sum = tHC.data + hC.data + toCarry;
			if (sum > 9) {
				toCarry = 1;
				sum = sum - 10;
			} else {
				toCarry = 0;
			}
			number = sum + number;
			tHC = tHC.link;
			hC = hC.link;
		}
		
		while(toCarry == 1) {
			if(type == 3) {
				number = 1 + number;
				toCarry = 0;
			}
			else if(type == 1) {
				int sum = hC.data + toCarry;
				if (sum > 9) {
					toCarry = 1;
					sum = sum - 10;
				} else {
					toCarry = 0;
				}
				number = sum + number;
				hC = hC.link;
				i++;
				if(i >= size) {
					if(toCarry == 1) {
						number = toCarry + number;
					}
					break;
				}
			}
			else if(type == 2) {
				int sum = tHC.data + toCarry;
				if (sum > 9) {
					toCarry = 1;
					sum = sum - 10;
				} else {
					toCarry = 0;
				}
				number = sum + number;
				tHC = tHC.link;
				i++;
				if(i >= that.size()) {
					if(toCarry == 1) {
						number = toCarry + number;
					}
					break;
				}
			}
		}
		
		int finalSize = 0;
		
		if(type == 1) {
			finalSize = size;
			for(int j = i; j < finalSize; j++) {
				number = hC.data + number;
				hC = hC.link;
			}
		}
		else if(type == 2) {
			finalSize = that.size();
			for(int j = i; j < finalSize; j++) {
				number = tHC.data + number;
				tHC = tHC.link;
			}
		}
		
		return new LargeInteger(number);
		
		
		
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		if(x == 0) {
			return new LargeInteger("0");
		}
		LargeInteger thisOne = new LargeInteger(this.toString());
		LargeInteger original = new LargeInteger(this.toString());
		for(int i = 1; i < x; i++) {
			thisOne = thisOne.add(original);
			System.out.println(thisOne.toString());
		}
		return thisOne;
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
//		String toReturn = "";
//		LLNode<Integer> curr_node = head;
//		for(int i = 0; i < size; i++) {
//			toReturn = current.data + toReturn;
//			current = current.link;
//		}
		String toReturn = "";
		if(curr_node.link == null) {
			return "" + curr_node.data;
		}
		toReturn = toString(curr_node.link) + curr_node.data + toReturn;
//		while(toReturn.charAt(0) == '0') {
//			toReturn = toReturn.substring(1, toReturn.length());
//		}
		return toReturn;
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}

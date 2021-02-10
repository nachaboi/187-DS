package guessme;


/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary
	
	LLIntegerNode head;
	LLIntegerNode firstGuess;
	boolean gameOn;
	int guesses;
	int guess;
	
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		gameOn = true;
		guesses = 0;
		guess = 1000;
		head = new LLIntegerNode();
		firstGuess = new LLIntegerNode();
		head.setInfo(1000);
		LLIntegerNode current = head;
		for(int i = 1001; i<10000; i++) {
			LLIntegerNode creator = new LLIntegerNode();
			creator.setInfo(i);
			current.setLink(creator);
			current = creator;
		}
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		gameOn = true;
		guesses = 0;
		guess = 1000;
		head = new LLIntegerNode();
		firstGuess = new LLIntegerNode();
		head.setInfo(1000);
		LLIntegerNode current = head;
		for(int i = 1001; i<10000; i++) {
			LLIntegerNode creator = new LLIntegerNode();
			creator.setInfo(i);
			current.setLink(creator);
			current = creator;
		}
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		LLIntegerNode current = firstGuess;
		if(current.getInfo() == n) {
			return true;
		}
		while(current.getLink() != null) {
			if(current.getLink().getInfo() == n) {
				return true;
			}
			LLIntegerNode temp = current.getLink();
			current = temp;
		}
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
//		int counter = 0;
//		LLIntegerNode current = firstGuess;
//		while(current.getLink() != null) {
//			counter++;
//			LLIntegerNode temp = current.getLink();
//			current = temp;
//		}
//		return counter;
		return guesses;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
	
		LLIntegerNode a1 = new LLIntegerNode();
		LLIntegerNode b1 = new LLIntegerNode();
		
		int firsta1 = a%10;
		int firstb1 = b%10;
		a1.setInfo(firsta1);
		b1.setInfo(firstb1);
		a = a /10;
		b = b /10;
		
		LLIntegerNode currentA1 = a1;
		LLIntegerNode currentB1 = b1;
		
		for(int i = 1; i <=3; i++) {
			LLIntegerNode tempa1 = new LLIntegerNode();
			LLIntegerNode tempb1 = new LLIntegerNode();
			int a1Data = a % 10;
			int b1Data = b % 10;
			tempa1.setInfo(a1Data);
			tempb1.setInfo(b1Data);
			a = a /10;
			b = b /10;
			currentA1.setLink(tempa1);
			currentB1.setLink(tempb1);
			currentA1 = tempa1;
			currentB1 = tempb1;
		}
		
		int counter = 0;
		LLIntegerNode checkerA1 = a1;
		LLIntegerNode checkerB1 = b1;
		
		for(int j = 0; j <= 3; j++) {
			if(checkerA1.getInfo() == checkerB1.getInfo()) {
				counter++;
			}
			LLIntegerNode saveA1 = checkerA1.getLink();
			LLIntegerNode saveB1 = checkerB1.getLink();
			checkerA1 = saveA1;
			checkerB1 = saveB1;
		}
		return counter;
		
		
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		return !gameOn;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		
		if(firstGuess.getInfo() == 0) {
			firstGuess.setInfo(guess);
			guesses++;
			return guess;
		}
		
		LLIntegerNode toAdd = firstGuess;
		
		while(toAdd.getLink() != null) {
			LLIntegerNode temp = toAdd.getLink();
			toAdd = temp;
		}
		
		LLIntegerNode guessNode = new LLIntegerNode();
		guessNode.setInfo(guess);
		
		toAdd.setLink(guessNode);
		
		guesses++;
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		
		if(nmatches == 4) {
			gameOn = false;
			return true;
		}
		
		LLIntegerNode current = head;
		boolean firstValid = true;
		LLIntegerNode newHead = new LLIntegerNode();
		LLIntegerNode currentNew = newHead;
		boolean callTrue = false;
		
		while(current.getLink() != null) {
			if(numMatches(guess,current.getLink().getInfo()) == nmatches) {
				LLIntegerNode noTrail = new LLIntegerNode();
				noTrail.setInfo(current.getLink().getInfo());
				if(firstValid) {
					
					newHead = noTrail;
					currentNew = noTrail;
					firstValid = false;
					callTrue = true;
				}
				else {
					currentNew.setLink(noTrail);
					currentNew = noTrail;
				}
			}
			LLIntegerNode temp = current.getLink();
			current = temp;
		}
		
		head = newHead;
		guess = head.getInfo();
		return callTrue;
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		if(guesses == 0) {
			return null;
		}
		return firstGuess;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		String send = "";
		if(firstGuess.getInfo() == 0) {
			return send;
		}
		send += firstGuess.getInfo();
		LLIntegerNode current = firstGuess;
		while (current.getLink() != null) {
			send+= ", " + current.getLink().getInfo();
			LLIntegerNode temp = current.getLink();
			current = temp;
		}
		return send;
	}

	
}

package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess = 1000;
	private int guessCount = 0;
	private int[] guesses = new int[8999];
	private boolean gameOn = true;
	private boolean[] valid = new boolean[10000];
	private int[] matches = new int[8999];
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		for(int i = 0; i < 1000; i++) {
			valid[i] = false;
		}
		for(int j = 1000; j < 10000; j++) {
			valid[j] = true;
			System.out.println(valid[j]);
		}
		guess = 1000;
		guessCount = 0;
		guesses = new int[8999];
		gameOn = true;
		matches = new int[8999];
		System.out.println("BOYEFIRST" + valid[1352]);

	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		for(int i = 0; i < 1000; i++) {
			valid[i] = false;
		}
		for(int j = 1000; j < 10000; j++) {
			valid[j] = true;
		}
		guess = 1000;
		guessCount = 0;
		guesses = new int[8999];
		gameOn = true;
		matches = new int[8999];
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		for(Integer e : guesses) {
			if(e == n) {
				return true;
			}
		}
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return guessCount;
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
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		int[] a1 = new int[4];
		int[] b1 = new int[4];
		for(int i = 0; i <= 3; i++) {
			a1[i] = a % 10;
			b1[i] = b % 10;
			a = a /10;
			b = b /10;
		}
		int counter = 0;
		for(int j = 0; j <= 3; j++) {
			if(a1[j] == b1[j]) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		return !gameOn;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		System.out.println("BOYE1" + valid[1352]);
		guesses[guessCount] = guess;
		guessCount++;
		System.out.println("BOYE2" + valid[1352]);
		return guess;
	}
	
	public boolean doubles(int check, int[] num) {
		int theNumber = check;
		int[] backnum = new int[4];
		for(int i = 0; i <= 3; i++) {
			backnum[i] = theNumber % 10;
			theNumber = theNumber /10;
		}
		int[] toCheck = new int[4];
		int counter = 0;
		for(int j = 3; j >= 0; j--) {
			toCheck[counter] = backnum[j];
			counter++;
		}
		if(num[0] == toCheck[0]) {
			for(int k = 1; k < 4; k++) {
				if(num[k] == toCheck[k]) {
					return true;
				}
			}
		}
		else if(num[1] == toCheck[1]) {
			for(int k = 2; k < 3; k++) {
				if(num[k] == toCheck[k]) {
					return true;
				}
			}
		}
		else if(num[2] == toCheck[3]) {
			return true;
		}
		return false;
	}
	
	public boolean triples(int check, int[] num) {
		int theNumber = check;
		int[] backnum = new int[4];
		for(int i = 0; i <= 3; i++) {
			backnum[i] = theNumber % 10;
			theNumber = theNumber /10;
		}
		int[] toCheck = new int[4];
		int counter = 0;
		for(int j = 3; j >= 0; j--) {
			toCheck[counter] = backnum[j];
			counter++;
		}
		if(num[0] == toCheck[0]) {
			if(num[1] == toCheck[1]) {
				if(num[2] == toCheck[2]) {
					return true;
				}
				else if(num[3] == toCheck[3]) {
					return true;
				}
			}
			else if(num[2] == toCheck[2] && num[3] == toCheck[3]) {
				return true;
			}
		}
		else if(num[1] == toCheck [1] && num[2] == toCheck[2] && num[3] == toCheck[3]) {
			return true;
		}
		return false;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
//	public boolean updateGuess(int nmatches) {
//		matches[guessCount-1] = nmatches;
//		if(nmatches == 4) {
//			gameOn = false;
//			return true;
//		}
//		valid[guess] = false;
//		int theNumber = guess;
//		int[] backnum = new int[4];
//		for(int i = 0; i <= 3; i++) {
//			backnum[i] = theNumber % 10;
//			theNumber = theNumber /10;
//		}
//		int[] num = new int[4];
//		int counter = 0;
//		for(int j = 3; j >= 0; j--) {
//			num[counter] = backnum[j];
//			counter++;
//		}
//		if(nmatches == 0) {
//			for(int i = 1000; i < valid.length; i++) {
//				if(valid[i]) {
//					int tn = i;
//					int[] bn = new int[4];
//					for(int f = 0; f <= 3; f++) {
//						bn[f] = tn % 10;
//						tn = tn /10;
//					}
//					int[] toCheck = new int[4];
//					int ct = 0;
//					for(int j = 3; j >= 0; j--) {
//						toCheck[ct] = bn[j];
//						ct++;
//					}
//					for(int k = 0; k < 4; k++) {
//						if(num[k] == toCheck[k]) {
//							valid[i] = false;
//						}
//					}
//				}
//			}
//		}
//		else if(nmatches == 2 || nmatches == 1) {
//			for(int i = 1000; i < valid.length; i++) {
//				if(valid[i]) {
//					if(nmatches == 1) {
//						if(doubles(i, num)) {
//							valid[i] = false;
//						}
//					}
//					if(triples(i, num)) {
//						valid[i] = false;
//					}
//				}
//			}
//		}
//		
//		for(int i = guess; i < valid.length; i++) {
//			boolean beTrue = true;
//			if(valid[i]) {
//				int tn = i;
//				int[] bn = new int[4];
//				for(int f = 0; f <= 3; f++) {
//					bn[f] = tn % 10;
//					tn = tn /10;
//				}
//				int[] toCheck = new int[4];
//				int ct = 0;
//				for(int j = 3; j >= 0; j--) {
//					toCheck[ct] = bn[j];
//					ct++;
//				}
//				for(int r = 0; r < guessCount; r++) {
//					int thisNumber = guesses[r];
//					int[] tempNumber = new int[4];
//					for(int u = 0; u <=3; u++) {
//						tempNumber[u] = thisNumber % 10;
//						thisNumber = thisNumber / 10;
//					}
//					int[] fit = new int[4];
//					int theCount = 0;
//					for(int y = 3; y >=0; y--) {
//						fit[theCount] = tempNumber[y];
//						theCount++;
//					}
//					int match = 0;
//					for(int s = 0; s < 4; s++) {
//						if(toCheck[s] == fit[s]) {
//							match++;
//						}
//					}
//					if(!(match >= matches[r])) { //>= or >
//						if(nmatches < matches[r]) {
//							return false;
//						}
//						beTrue = false;
//						break;
//					}
//				}
//				if(beTrue) {
//					guess = i;
//					return true;
//				}
//				
//				
//			}
//			else {
//				System.out.println(valid[i]);
//			}
//		}
//		return false;
//		
//		
//	}
	
	public boolean updateGuess(int nmatches) {
		if(nmatches == 4) {
			gameOn = false;
			return true;
		}
		for(int i = 1000; i < valid.length; i++) {
			if(numMatches(guess,i) != nmatches) {
				valid[i] =false;
			}
		}
		for(int i = 1000; i < valid.length; i++) {
			if(valid[i]) {
				guess = i;
				return true;
			}
		}
		return false;
		
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		if(guessCount == 0) {
			return null;
		}
		int[] toReturn = new int[guessCount];
		for(int i = 0; i < guessCount; i++) {
			toReturn[i] = guesses[i];
		}
		return toReturn;
	}
	
	public int guessCount() {
		if(guessCount == 0) {
			return -1;
		}
		int[] toReturn = new int[guessCount];
		for(int i = 0; i < guessCount; i++) {
			toReturn[i] = guesses[i];
		}
		return toReturn[2];
	}
}

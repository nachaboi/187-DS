package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	Queue<File> done = new Queue<File>();
	Queue<File> toDo = new Queue<File>();
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if(!rootNode.exists()) {
			throw new FileNotFoundException();
		}
        toDo.enqueue(rootNode);
        while(!toDo.isEmpty()) {
        		if(toDo.peek().isDirectory()) {
        			File[] temp = toDo.peek().listFiles();
        			Arrays.sort(temp);
        			for(File e : temp) {
        				toDo.enqueue(e);
        			}
        		}
        		done.enqueue(toDo.dequeue());
        }
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return !done.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
            if(done.isEmpty()) {
            		throw new NoSuchElementException();
            }
            return done.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}

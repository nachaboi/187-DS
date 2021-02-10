package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	
	@Test
	public void testAssort() {
		ListInterface<Integer> list2 = new RecursiveList<Integer>();
		assertTrue(list2.isEmpty());
		assertEquals(0, list2.size());
		list2.insertFirst(1);
		assertEquals(1, list2.size());
		list2.insertFirst(2);
		assertEquals(2, (int)list2.getFirst());
		list2.removeLast();
		assertEquals(2, (int)list2.getFirst());
		assertEquals(2, (int)list2.getLast());
		list2.removeFirst();
		list2.insertAt(0, 2);
		assertEquals(2, (int)list2.getFirst());
		list2.insertAt(0, 3);
		assertEquals(3, (int)list2.getFirst());
		assertEquals(0, list2.indexOf(3));
		assertEquals(1, list2.indexOf(2));
		//3, 2
		list2.insertLast(5);
		assertEquals(5, (int)list2.getLast());
		list2.insertAt(3, 7);
		assertEquals(7, (int)list2.getLast());
		assertEquals(0, list2.indexOf(3));
		assertEquals(1, list2.indexOf(2));
		assertEquals(2, list2.indexOf(5));
		assertEquals(3, list2.indexOf(7));
		list2.remove(2);
		assertEquals(0, list2.indexOf(3));
		assertEquals(1, list2.indexOf(5));
		assertEquals(2, list2.indexOf(7));
		list2.remove(7);
		assertEquals(0, list2.indexOf(3));
		assertEquals(1, list2.indexOf(5));
		list2.insertAt(0, 19);
		assertEquals(0, list2.indexOf(19));
		assertEquals(1, list2.indexOf(3));
		assertEquals(2, list2.indexOf(5));
		list2.remove(19);
		assertEquals(0, list2.indexOf(3));
		assertEquals(1, list2.indexOf(5));
		assertEquals(-1, list2.indexOf(19));
		//3, 5
	}
}

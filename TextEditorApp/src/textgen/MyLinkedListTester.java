/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		LLNode testerNode = list1.head.next.prev;
		int b = (int) testerNode.data;
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		assertEquals("Checking prev pointer after remove", 21, b);
		try {
			list1.remove(5);
			fail("Out of bounds");
		}catch (IndexOutOfBoundsException e){
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		shortList.add("C");
		LLNode testerNode = shortList.tail.prev;
		String b = (String) testerNode.data;
		assertEquals("Checking if adding to the end works.", "C", shortList.get(2));
		assertEquals("Checking size when adding", 3, shortList.size());
		assertEquals("Checking prev of last node added", "B", b);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Checking size of empty list", 0, emptyList.size());
		assertEquals("Checking size of shortList", 2, shortList.size());
		assertEquals("Checking size of list1", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		list1.add(2,10);
		assertEquals("Checking adding at a random index", (Integer)10 ,list1.get(2));
		assertEquals("Checking adding at a random index", (Integer)21 ,list1.get(1));
		assertEquals("Checking size", 4 ,list1.size);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		list1.set(2, 69);
		assertEquals("Checking seting at a random index", (Integer)69 ,list1.get(2));
		assertEquals("Checking size after seting", 3 ,list1.size());
		assertEquals("Checking seting at a random index", (Integer)65 ,list1.get(0));
		try {
			list1.set(5, 0);
			fail("Out of bounds");
		}catch (IndexOutOfBoundsException e) {
			
		}
	}
	
}

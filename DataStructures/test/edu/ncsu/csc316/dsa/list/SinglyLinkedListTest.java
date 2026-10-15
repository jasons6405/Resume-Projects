package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SinglyLinkedList. Checks the expected outputs of the List
 * abstract data type behaviors when using an array-based list data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class SinglyLinkedListTest {

	/**
	 * Creates a basic list object that we need to set
	 */
	private List<String> list;

	/**
	 * Create a new instance of an array-based list before each test case executes
	 */
	@Before
	public void setUp() {
		list = new SinglyLinkedList<String>();
	}

	/**
	 * Tests the add and get methods
	 */
	@Test
	public void testAddAndGet() {
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}

	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");

		assertEquals("B", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}

	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");

		assertEquals("B", list.set(1, "X"));
		assertEquals("X", list.get(1));
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		assertEquals(0, list.size());
		list.add(0, "A");
		list.add(1, "B");
		assertEquals(2, list.size());
	}

	/**
	 * Tests the iterator
	 */
	@Test
	public void testIterator() {
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");

		Iterator<String> it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals("A", it.next());
		assertEquals("B", it.next());
		assertEquals("C", it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests removing an element with the iterator
	 */
	@Test
	public void testRemoveWithIterator() {
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");

		Iterator<String> it = list.iterator();
		assertThrows(UnsupportedOperationException.class, it::remove);
	}

	/**
	 * Tests adding an element from an incorrect index, ensuring the correct
	 * exception is thrown
	 */
	@Test
	public void testAddInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "A"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));
	}

	/**
	 * Tests removing an element from an incorrect index, ensuring the correct
	 * exception is thrown
	 */
	@Test
	public void testRemoveInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
	}

	/**
	 * Tests getting an element from an incorrect index, ensuring the correct
	 * exception is thrown
	 */
	@Test
	public void testGetInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
	}

	/**
	 * Tests setting an invalid index element, ensuring the correct exception is
	 * thrown
	 */
	@Test
	public void testSetInvalidIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "A"));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "A"));
	}

	/**
	 * Tests adding a null element, ensuring the correct exception is thrown
	 */
	@Test
	public void testAddNullElement() {
		assertThrows(NullPointerException.class, () -> list.add(0, null));
	}

	/**
	 * Tests setting a null element, ensuring the correct exception is thrown
	 */
	@Test
	public void testSetNullElement() {
		list.add(0, "A");
		assertThrows(NullPointerException.class, () -> list.set(0, null));
	}
}
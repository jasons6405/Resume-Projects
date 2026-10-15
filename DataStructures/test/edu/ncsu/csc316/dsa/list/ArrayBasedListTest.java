package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList. Checks the expected outputs of the List
 * abstract data type behaviors when using an array-based list data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class ArrayBasedListTest {

	/**
	 * Creates a basic list object that we need to set
	 */
	private List<String> list;

	/**
	 * Create a new instance of an array-based list before each test case executes
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * Test the output of the add(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());

		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you should
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.

		try {
			list.add(15, "fifteen");
			fail("An IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

	}

	/**
	 * Test the output of the addLast behavior
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.addLast("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));

		list.addLast("two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));

		list.addLast("three");
		assertEquals(3, list.size());
		assertEquals("three", list.get(2));

		// Check resizing by adding more elements
		for (int i = 0; i < 20; i++) {
			list.addLast("item" + i);
		}
		assertEquals(23, list.size());
		assertEquals("item19", list.get(22));
	}

	/**
	 * Test the output of the last() behavior, including expected exceptions
	 */
	@Test
	public void testLast() {
		try {
			list.last();
			fail("IndexOutOfBoundsException should have been thrown for an empty list");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		list.addLast("first");
		assertEquals("first", list.last());

		list.addLast("last");
		assertEquals("last", list.last());
	}

	/**
	 * Test the output of the addFirst behavior
	 */
	@Test
	public void testAddFirst() {
		list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));

		list.addFirst("zero");
		assertEquals(2, list.size());
		assertEquals("zero", list.get(0));
		assertEquals("one", list.get(1));
	}

	/**
	 * Test the output of the first() behavior, including expected exceptions
	 */
	@Test
	public void testFirst() {
		try {
			list.first();
			fail("IndexOutOfBoundsException should have been thrown for an empty list");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}

		list.addFirst("first");
		assertEquals("first", list.first());

		list.addFirst("new first");
		assertEquals("new first", list.first());
	}

	/**
	 * Test the iterator behaviors, including expected exceptions
	 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();

		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");

		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));

		// Create an iterator for the list that has 1 element
		it = list.iterator();

		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		// Add more elements to the list
		list.addLast("two");
		list.addLast("three");

		// Use accessor methods to check that the list is correct
		assertEquals(3, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		assertEquals("three", list.get(2));

		// Create a new iterator for the updated list
		it = list.iterator();

		// Test iterator behavior for a list with multiple elements
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertTrue(it.hasNext());
		assertEquals("two", it.next());
		assertTrue(it.hasNext());
		assertEquals("three", it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		// Test remove behavior
		it = list.iterator();
		assertEquals("one", it.next());
		it.remove(); // Remove the first element
		assertEquals(2, list.size());
		assertEquals("two", list.get(0));
		assertEquals("three", list.get(1));
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}

		// Remove another element
		assertEquals("two", it.next());
		it.remove(); // Remove the second element
		assertEquals(1, list.size());
		assertEquals("three", list.get(0));
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}

		// Test with an empty list after removals
		assertEquals("three", it.next());
		it.remove(); // Remove the last element
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		try {
			it.remove();
			fail("An IllegalStateException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}

		// Verify that the iterator behaves as expected on an empty list
		it = list.iterator();
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}

	/**
	 * Test the output of the remove(index) behavior, including expected exceptions
	 */
	@Test
	public void testRemoveIndex() {
		list.addLast("one");
		list.addLast("two");
		list.addLast("three");

		assertEquals("two", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("one", list.get(0));
		assertEquals("three", list.get(1));

		try {
			list.remove(5);
			fail("IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the removeFirst() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveFirst() {
		list.addLast("one");
		list.addLast("two");

		assertEquals("one", list.removeFirst());
		assertEquals(1, list.size());
		assertEquals("two", list.get(0));

		try {
			list.removeFirst();
			list.removeFirst();
			fail("IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the removeLast() behavior, including expected exceptions
	 */
	@Test
	public void testRemoveLast() {
		list.addLast("one");
		list.addLast("two");

		assertEquals("two", list.removeLast());
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));

		try {
			list.removeLast();
			list.removeLast();
			fail("IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}

	/**
	 * Test the output of the set(index, e) behavior, including expected exceptions
	 */
	@Test
	public void testSet() {
		list.addLast("one");
		list.addLast("two");

		assertEquals("one", list.set(0, "new one"));
		assertEquals("new one", list.get(0));
		assertEquals("two", list.get(1));

		try {
			list.set(5, "out of bounds");
			fail("IndexOutOfBoundsException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
}
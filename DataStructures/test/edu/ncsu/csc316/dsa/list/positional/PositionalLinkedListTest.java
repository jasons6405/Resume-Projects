package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList. Checks the expected outputs of the
 * Positional List abstract data type behaviors when using an doubly-linked
 * positional list data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class PositionalLinkedListTest {

	/**
	 * Creates a new empty list for testing
	 */
	private PositionalList<String> list;

	/**
	 * Create a new instance of an positional linked list before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}

	/**
	 * Test the output of the first() behavior, including any expected exceptions
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.first());

		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());

		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
	}

	/**
	 * Test the output of the last() behavior, including any expected exceptions
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		assertNull(list.last());

		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());

		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
	}

	/**
	 * Test the output of the addFirst(element) behavior
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		@SuppressWarnings("unused")
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());

		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
	}

	/**
	 * Test the output of the addLast(element) behavior
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		@SuppressWarnings("unused")
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());

		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
	}

	/**
	 * Test the output of the before(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testBefore() {
		Position<String> first = list.addFirst("one");
		assertNull(list.before(first));

		Position<String> second = list.addLast("two");
		assertEquals(first, list.before(second));

		Position<String> third = list.addLast("three");
		assertEquals(second, list.before(third));

		try {
			list.before(null);
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the after(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testAfter() {
		Position<String> first = list.addFirst("one");
		assertNull(list.after(first));

		Position<String> second = list.addLast("two");
		assertEquals(second, list.after(first));

		Position<String> third = list.addLast("three");
		assertEquals(third, list.after(second));

		try {
			list.after(null);
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the addBefore(position, element) behavior, including any
	 * expected exceptions
	 */
	@Test
	public void testAddBefore() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addBefore(first, "zero");
		assertEquals(second, list.first());
		assertEquals(first, list.after(second));

		try {
			list.addBefore(null, "invalid");
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the addAfter(position, element) behavior, including any
	 * expected exceptions
	 */
	@Test
	public void testAddAfter() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addAfter(first, "two");
		assertEquals(second, list.after(first));
		assertNull(list.after(second));

		try {
			list.addAfter(null, "invalid");
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the set(position, element) behavior, including any
	 * expected exceptions
	 */
	@Test
	public void testSet() {
		Position<String> first = list.addFirst("one");
		assertEquals("one", list.set(first, "updated"));
		assertEquals("updated", first.getElement());

		try {
			list.set(null, "invalid");
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the remove(position) behavior, including any expected
	 * exceptions
	 */
	@Test
	public void testRemove() {
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		assertEquals("one", list.remove(first));
		assertEquals(1, list.size());
		assertEquals(second, list.first());

		try {
			list.remove(null);
			fail("An IllegalArgumentException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the iterator behavior for elements in the list, including
	 * any expected exceptions
	 */
	@Test
	public void testIterator() {
		assertEquals(0, list.size());

		list.addFirst("one");
		list.addLast("two");
		list.addLast("three");
		assertEquals(3, list.size());

		Iterator<String> it = list.iterator();

		assertTrue(it.hasNext());
		assertEquals("one", it.next());

		assertTrue(it.hasNext());
		assertEquals("two", it.next());

		assertTrue(it.hasNext());
		assertEquals("three", it.next());

		assertFalse(it.hasNext());

		try {
			it.next(); // No more elements, should throw an exception
			fail("An NoSuchElementException should have been thrown");
		} catch (NoSuchElementException e) {
			// Expected exception
		}
	}

	/**
	 * Test the output of the positions() behavior to iterate through positions in
	 * the list, including any expected exceptions
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());

		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());

		assertFalse(it.hasNext());

		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (NoSuchElementException e) {
			// Expected exception
		}
	}

	/**
	 * Tests the remove method for the PositionIterator
	 */
	@Test
	public void testPositionIteratorRemove() {
		Position<String> pos1 = list.addFirst("A");
		Position<String> pos2 = list.addLast("B");
		list.addLast("C");

		Iterator<Position<String>> positionIterator = list.positions().iterator();

		assertTrue(positionIterator.hasNext());
		assertEquals(pos1, positionIterator.next());
		assertTrue(positionIterator.hasNext());
		assertEquals(pos2, positionIterator.next());

		positionIterator.remove();

		assertEquals(2, list.size());
		Iterator<String> elementIterator = list.iterator();
		assertEquals("A", elementIterator.next());
		assertEquals("C", elementIterator.next());
		assertFalse(elementIterator.hasNext());
	}

	/**
	 * Tests the remove method for the ElementIterator
	 */
	@Test
	public void testElementIteratorRemove() {
		list.addFirst("X");
		list.addLast("Y");
		list.addLast("Z");

		Iterator<String> elementIterator = list.iterator();

		assertTrue(elementIterator.hasNext());
		assertEquals("X", elementIterator.next());
		assertTrue(elementIterator.hasNext());
		assertEquals("Y", elementIterator.next());

		elementIterator.remove();

		assertEquals(2, list.size());
		Iterator<String> updatedIterator = list.iterator();
		assertEquals("X", updatedIterator.next());
		assertEquals("Z", updatedIterator.next());
		assertFalse(updatedIterator.hasNext());
	}

}
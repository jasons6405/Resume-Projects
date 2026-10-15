package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap Checks the expected outputs of the Map and
 * Tree abstract data type behaviors when using an linked binary tree data
 * structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class BinarySearchTreeMapTest {

	/**
	 * Creates a BST map for testing
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a binary search tree map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int) tree.root().getElement().getKey());

		tree.put(2, "two");
		tree.put(3, "three");
		assertEquals(3, tree.size());
		assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		assertEquals("one", tree.get(1));
		assertNull(tree.get(2));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		tree.put(1, "one");
		assertEquals(1, tree.size());

		assertNull(tree.remove(10));
		assertEquals(1, tree.size());

		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());

		tree.put(50, "50");
		tree.put(30, "30");
		tree.put(70, "70");
		tree.put(20, "20");
		tree.put(40, "40");
		tree.put(60, "60");
		tree.put(80, "80");
		assertEquals(7, tree.size());
		assertEquals("30", tree.remove(30)); // Remove node with two children
		assertEquals(6, tree.size());
		// Verify in-order successor replaces 30
		Position<Entry<Integer, String>> newRoot = tree.root();
		Position<Entry<Integer, String>> leftOf50 = tree.left(newRoot);
		assertEquals(40, (int) leftOf50.getElement().getKey());
	}

	/**
	 * Test the output of the entrySet() behavior
	 */
	@Test
	public void testEntrySet() {

		// Insert entries and verify the entrySet contains them in in-order traversal
		// order
		tree.put(3, "C");
		tree.put(1, "A");
		tree.put(2, "B");

		Iterator<Entry<Integer, String>> iterator = tree.entrySet().iterator();
		assertTrue(iterator.hasNext());
		assertEquals(1, (int) iterator.next().getKey());
		assertTrue(iterator.hasNext());
		assertEquals(2, (int) iterator.next().getKey());
		assertTrue(iterator.hasNext());
		assertEquals(3, (int) iterator.next().getKey());
		assertFalse(iterator.hasNext());
	}

}
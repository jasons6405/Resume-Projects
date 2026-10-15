package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a splay tree data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class SplayTreeMapTest {

	/**
	 * Creates a BST map for testing
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a splay tree-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new SplayTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		tree.put(1, "A");
		assertEquals(1, (int) tree.root().getElement().getKey());

		tree.put(2, "B");
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());

		tree.put(3, "C");
		assertEquals(3, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		tree.put(3, "C");
		tree.put(1, "A");
		tree.put(2, "B");

		assertEquals("A", tree.get(1));
		assertEquals(1, (int) tree.root().getElement().getKey());
		assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		tree.put(2, "B");
		tree.put(1, "A");
		tree.put(3, "C");

		assertEquals("A", tree.remove(1));
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
	}
}
package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for AVLTreeMap Checks the expected outputs of the Map abstract
 * data type behaviors when using an AVL tree data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class AVLTreeMapTest {

	/**
	 * Creates a BST map for testing
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of an AVL tree-based map before each test case executes
	 */
	@Before
	public void setUp() {
		tree = new AVLTreeMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		tree.put(3, "C");
		tree.put(2, "B");
		tree.put(1, "A");
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());

		// Reset tree
		tree = new AVLTreeMap<>();

		// Test right rotation
		tree.put(1, "A");
		tree.put(2, "B");
		tree.put(3, "C");
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());

		// Reset tree
		tree = new AVLTreeMap<>();

		// Test double rotation
		tree.put(3, "C");
		tree.put(1, "A");
		tree.put(2, "B");
		assertEquals(2, (int) tree.root().getElement().getKey());
		assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
		assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(tree.isEmpty());
		assertNull(tree.get(1));

		tree.put(3, "C");
		tree.put(1, "A");
		tree.put(2, "B");
		assertEquals("C", tree.get(3));
		assertEquals("A", tree.get(1));
		assertEquals("B", tree.get(2));
		assertNull(tree.get(4));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {

		// Build a balanced AVL tree
		tree.put(4, "D");
		tree.put(2, "B");
		tree.put(6, "F");
		tree.put(1, "A");
		tree.put(3, "C");
		tree.put(5, "E");
		tree.put(7, "G");

		// Verify initial structure
		assertEquals(4, (int) tree.root().getElement().getKey());

		// Remove node 2, causing re-balancing
		assertEquals("B", tree.remove(2));

		// Verify new structure after re-balance
		assertEquals(4, (int) tree.root().getElement().getKey());
		assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
		Position<Entry<Integer, String>> rightOfRoot = tree.right(tree.root());
		assertEquals(6, (int) rightOfRoot.getElement().getKey());
		assertEquals(5, (int) tree.left(rightOfRoot).getElement().getKey());
		assertEquals(7, (int) tree.right(rightOfRoot).getElement().getKey());
	}
}
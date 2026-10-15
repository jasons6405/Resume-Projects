package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for RedBlackTreeMap Checks the expected outputs of the Map
 * abstract data type behaviors when using a red-black tree data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class RedBlackTreeMapTest {

	/**
	 * Creates a BST map for testing
	 */
	private BinarySearchTreeMap<Integer, String> tree;

	/**
	 * Create a new instance of a red-black tree-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		tree = new RedBlackTreeMap<Integer, String>();
	}

	/**
	 * Returns true if the given position is black (it's property = 0)
	 * 
	 * 
	 * @param p the position for which to determine if the color is black
	 * @return true if the position's property/color is black
	 */
	private boolean isBlack(Position<Entry<Integer, String>> p) {
		return ((RedBlackTreeMap<Integer, String>) tree).getProperty(p) == 0;
	}

	/**
	 * Returns true if the given position is red (it's property = 1)
	 * 
	 * @param p the position for which to determine if the color is red
	 * @return true if the position's property/color is red
	 */
	private boolean isRed(Position<Entry<Integer, String>> p) {
		return ((RedBlackTreeMap<Integer, String>) tree).getProperty(p) == 1;
	}

	private void makeBlack(Position<Entry<Integer, String>> p) {
		tree.setProperty(p, 0);
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		// Test root insertion
		tree.put(10, "A");
		assertEquals(1, tree.size());
		assertTrue(isBlack(tree.root()));

		// Insert right child
		tree.put(20, "B");
		Position<Entry<Integer, String>> root = tree.root();
		Position<Entry<Integer, String>> right = tree.right(root);
		assertEquals(20, (int) right.getElement().getKey());
		assertTrue(isRed(right));

		// Insert third node causing restructuring
		tree.put(30, "C");
		root = tree.root();
		assertEquals(20, (int) root.getElement().getKey()); // Restructured root
		assertTrue(isBlack(root));
		Position<Entry<Integer, String>> left = tree.left(root);
		right = tree.right(root);
		assertEquals(10, (int) left.getElement().getKey());
		assertEquals(30, (int) right.getElement().getKey());
		assertTrue(isRed(left));
		assertTrue(isRed(right));

		// Insert node causing recoloring
		tree.put(15, "D");
		Position<Entry<Integer, String>> leftRight = tree.right(left);
		assertEquals(15, (int) leftRight.getElement().getKey());
		assertTrue(isRed(leftRight));
		assertTrue(isBlack(left)); // Parent recolored
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		// Build a Red-Black tree with known structure
		tree.put(20, "B");
		tree.put(10, "A");
		tree.put(30, "C");
		tree.put(25, "D");

		// Verify initial structure and colors
		Position<Entry<Integer, String>> root = tree.root();
		assertEquals(20, (int) root.getElement().getKey());
		assertTrue(isBlack(root));

		Position<Entry<Integer, String>> left = tree.left(root);
		assertEquals(10, (int) left.getElement().getKey());
		assertTrue(isBlack(left));

		Position<Entry<Integer, String>> right = tree.right(root);
		assertEquals(30, (int) right.getElement().getKey());
		assertTrue(isBlack(right));

		Position<Entry<Integer, String>> rightLeft = tree.left(right);
		assertEquals(25, (int) rightLeft.getElement().getKey());
		assertTrue(isRed(rightLeft));

		// Test retrieval of existing keys
		assertEquals("A", tree.get(10));
		assertEquals("B", tree.get(20));
		assertEquals("C", tree.get(30));
		assertEquals("D", tree.get(25));

		// Test retrieval of non-existent key
		assertNull(tree.get(40));

		// Ensure tree structure and colors remain unchanged
		assertEquals(20, (int) tree.root().getElement().getKey());
		assertTrue(isBlack(tree.root()));
		assertEquals(10, (int) tree.left(tree.root()).getElement().getKey());
		assertTrue(isBlack(tree.left(tree.root())));
		assertEquals(30, (int) tree.right(tree.root()).getElement().getKey());
		assertTrue(isBlack(tree.right(tree.root())));
		assertEquals(25, (int) tree.left(tree.right(tree.root())).getElement().getKey());
		assertTrue(isRed(tree.left(tree.right(tree.root()))));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());

		// Build a tree that will trigger double-black after removal
		tree.put(20, "B");
		tree.put(10, "A");
		tree.put(30, "C");
		tree.put(25, "D");

		// Remove 10
		assertEquals("A", tree.remove(10));
		Position<Entry<Integer, String>> root = tree.root();
		assertEquals(25, (int) root.getElement().getKey());
		Position<Entry<Integer, String>> newRight = tree.right(root);
		assertEquals(30, (int) newRight.getElement().getKey());
		assertTrue(isBlack(newRight)); // Promoted node recolored
	}

	/**
	 * Tests the case where we have a double black with sibling with two black
	 * (sentinel) nodes
	 */
	@Test
	public void testRemedyDoubleBlackCase2() {
		// Build the tree with specific structure and colors:
		// 50B
		// / \
		// 30B 70B

		// Insert nodes (nodes are initially red; manually re-color)
		tree.put(50, "50");
		Position<Entry<Integer, String>> root = tree.root();
		makeBlack(root); // 50B

		tree.put(30, "30");
		Position<Entry<Integer, String>> left = tree.left(root);
		makeBlack(left); // 30B

		tree.put(70, "70");
		Position<Entry<Integer, String>> right = tree.right(root);
		makeBlack(right); // 70B

		// Ensure all nodes are correctly being assigned to black
		assertTrue(isBlack(right));
		assertTrue(isBlack(left));
		assertTrue(isBlack(tree.left(right)));
		assertTrue(isBlack(tree.right(right)));

		tree.remove(30);

		// Check root
		assertTrue(isBlack(root));

		// Check that left subtree is sentinel
		Position<Entry<Integer, String>> newLeft = tree.left(root);
		assertTrue(isBlack(newLeft));

		// Check right subtree (recolored to red)
		Position<Entry<Integer, String>> newRight = tree.right(root);
		assertEquals(70, (int) newRight.getElement().getKey());
		assertTrue(isRed(newRight));

	}

	/**
	 * Tests some extra BinarySearchTree behaviors, such as treeMin
	 */
	@Test
	public void testBinarySearchTreeMap() {
		tree.put(20, "20"); // Add root
		tree.put(40, "40"); // Right child
		tree.put(30, "30"); // Left of right, restructure so 30 is root
		tree.put(25, "25"); // Right of new left, re-color 20 and 40 to black

		assertEquals("30", tree.remove(30));

		// Verify structure and colors after rotation and recoloring:
		Position<Entry<Integer, String>> root = tree.root();
		assertEquals(25, (int) root.getElement().getKey());
		assertTrue(isBlack(root)); // New root after rotation

		Position<Entry<Integer, String>> left = tree.left(root);
		assertEquals(20, (int) left.getElement().getKey());

		Position<Entry<Integer, String>> right = tree.right(root);
		assertEquals(40, (int) right.getElement().getKey());
		assertTrue(isBlack(right)); // Original right child remains black
	}

}
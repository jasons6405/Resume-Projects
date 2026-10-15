package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree Checks the expected outputs of the BinaryTree
 * abstract data type behaviors when using a linked data structure to store
 * elements
 *
 * @author Dr. King
 * @author // Your Name Here
 *
 */
public class LinkedBinaryTreeTest {

	/**
	 * LinkedBinaryTree for testing
	 */
	private LinkedBinaryTree<String> tree;

	/**
	 * Position #1 for testing
	 */
	private Position<String> one;

	/**
	 * Position #2 for testing
	 */
	private Position<String> two;

	/**
	 * Position #3 for testing
	 */
	private Position<String> three;

	/**
	 * Position #4 for testing
	 */
	private Position<String> four;

	/**
	 * Position #5 for testing
	 */
	private Position<String> five;

	/**
	 * Position #6 for testing
	 */
	private Position<String> six;

	/**
	 * Position #7 for testing
	 */
	private Position<String> seven;

	/**
	 * Position #8 for testing
	 */
	private Position<String> eight;

	/**
	 * Position #9 for testing
	 */
	private Position<String> nine;

	/**
	 * Position #10 for testing
	 */
	private Position<String> ten;

	/**
	 * Create a new instance of a linked binary tree before each test case executes
	 */
	@Before
	public void setUp() {
		tree = new LinkedBinaryTree<String>();
	}

	/**
	 * Sample tree to help with testing
	 *
	 * One -> Two -> Six -> Ten -> Seven -> Five -> Three -> Four -> Eight -> Nine
	 * 
	 * Or, visually: one / \ two three / \ / six ten four / \ / \ seven five eight
	 * nine
	 */
	private void createTree() {
		one = tree.addRoot("one");
		two = tree.addLeft(one, "two");
		three = tree.addRight(one, "three");
		six = tree.addLeft(two, "six");
		ten = tree.addRight(two, "ten");
		four = tree.addLeft(three, "four");
		seven = tree.addLeft(ten, "seven");
		five = tree.addRight(ten, "five");
		eight = tree.addLeft(four, "eight");
		nine = tree.addRight(four, "nine");
	}

	/**
	 * Test the output of the set(p,e) behavior
	 */
	@Test
	public void testSet() {
		createTree();
		// Test setting a valid node
		assertEquals("two", two.getElement());
		String oldValue = tree.set(two, "newTwo");
		assertEquals("two", oldValue);
		assertEquals("newTwo", two.getElement());

		// Test setting the root
		assertEquals("one", one.getElement());
		oldValue = tree.set(one, "newOne");
		assertEquals("one", oldValue);
		assertEquals("newOne", one.getElement());

		// Test invalid position (null)
		try {
			tree.set(null, "invalid");
			fail("Should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

	}

	/**
	 * Test the output of the size() behavior
	 */
	@Test
	public void testSize() {
		assertTrue(tree.isEmpty());
		createTree();
		assertEquals(10, tree.size());
		tree.remove(seven);
		assertEquals(9, tree.size());
	}

	/**
	 * Test the output of the numChildren(p) behavior
	 */
	@Test
	public void testNumChildren() {
		createTree();
		assertEquals(2, tree.numChildren(one)); // two and three
		assertEquals(2, tree.numChildren(two)); // six and ten
		assertEquals(1, tree.numChildren(three)); // four
		assertEquals(0, tree.numChildren(seven)); // leaf
	}

	/**
	 * Test the output of the parent(p) behavior
	 */
	@Test
	public void testParent() {
		createTree();
		assertNull(tree.parent(one)); // root has no parent
		assertEquals(one, tree.parent(two));
		assertEquals(one, tree.parent(three));
		assertEquals(two, tree.parent(six));
		assertEquals(three, tree.parent(four));
	}

	/**
	 * Test the output of the sibling behavior
	 */
	@Test
	public void testSibling() {
		createTree();
		assertEquals(three, tree.sibling(two));
		assertEquals(two, tree.sibling(three));
		assertEquals(ten, tree.sibling(six));
		assertEquals(six, tree.sibling(ten));
		assertNull(tree.sibling(one)); // root has no sibling
		assertEquals(nine, tree.sibling(eight));
		assertEquals(eight, tree.sibling(nine));
	}

	/**
	 * Test the output of the isInternal behavior
	 */
	@Test
	public void testIsInternal() {
		createTree();
		assertTrue(tree.isInternal(one)); // has children
		assertTrue(tree.isInternal(two));
		assertTrue(tree.isInternal(three));
		assertTrue(tree.isInternal(ten));
		assertFalse(tree.isInternal(seven)); // leaf
		assertFalse(tree.isInternal(nine));
	}

	/**
	 * Test the output of the isLeaf behavior
	 */
	@Test
	public void isLeaf() {
		createTree();
		assertFalse(tree.isLeaf(one));
		assertTrue(tree.isLeaf(seven));
		assertTrue(tree.isLeaf(five));
		assertTrue(tree.isLeaf(eight));
		assertTrue(tree.isLeaf(nine));
	}

	/**
	 * Test the output of the isRoot(p)
	 */
	@Test
	public void isRoot() {
		createTree();
		assertTrue(tree.isRoot(one));

		// Create a new tree where root can be safely removed
		LinkedBinaryTree<String> smallTree = new LinkedBinaryTree<>();
		Position<String> smallRoot = smallTree.addRoot("root");
		Position<String> smallLeft = smallTree.addLeft(smallRoot, "left");

		// Remove root (has 1 child, which is allowed)
		smallTree.remove(smallRoot);
		assertTrue(smallTree.isRoot(smallLeft));
		assertEquals(1, smallTree.size());

		// Verify original tree's root remains unchanged
		assertTrue(tree.isRoot(one));
	}

	/**
	 * Test the output of the preOrder traversal behavior
	 */
	@Test
	public void testPreOrder() {
		createTree();
		Iterator<Position<String>> it = tree.preOrder().iterator();
		String[] expected = { "one", "two", "six", "ten", "seven", "five", "three", "four", "eight", "nine" };
		for (String element : expected) {
			assertTrue(it.hasNext());
			assertEquals(element, it.next().getElement());
		}
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the postOrder traversal behavior
	 */
	@Test
	public void testPostOrder() {
		createTree();
		Iterator<Position<String>> it = tree.postOrder().iterator();
		String[] expected = { "six", "seven", "five", "ten", "two", "eight", "nine", "four", "three", "one" };
		for (String element : expected) {
			assertTrue(it.hasNext());
			assertEquals(element, it.next().getElement());
		}
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the inOrder traversal behavior
	 */
	@Test
	public void testInOrder() {
		createTree();
		Iterator<Position<String>> it = tree.inOrder().iterator();
		String[] expected = { "six", "two", "seven", "ten", "five", "one", "eight", "four", "nine", "three" };
		for (String element : expected) {
			assertTrue(it.hasNext());
			assertEquals(element, it.next().getElement());
		}
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the Binary Tree ADT behaviors on an empty tree
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyTree() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertNull(tree.root());
		// Attempt to get parent of null (invalid position)
		tree.parent(null);
	}

	/**
	 * Test the output of the levelOrder traversal behavior
	 */
	@Test
	public void testLevelOrder() {
		createTree();
		Iterator<Position<String>> it = tree.levelOrder().iterator();
		String[] expected = { "one", "two", "three", "six", "ten", "four", "seven", "five", "eight", "nine" };
		for (String element : expected) {
			assertTrue(it.hasNext());
			assertEquals(element, it.next().getElement());
		}
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the addLeft(p,e) behavior, including expected exceptions
	 */
	@Test
	public void testAddLeft() {
		try {
			tree.addLeft(null, "invalid");
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		Position<String> root = tree.addRoot("root");
		Position<String> left = tree.addLeft(root, "left");
		assertEquals("left", left.getElement());
		assertEquals(root, tree.parent(left));
		assertEquals(2, tree.size());

		try {
			tree.addLeft(root, "another-left");
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the addRight(p,e) behavior, including expected exceptions
	 */
	@Test
	public void testAddRight() {
		try {
			tree.addRight(null, "invalid");
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		Position<String> root = tree.addRoot("root");
		Position<String> right = tree.addRight(root, "right");
		assertEquals("right", right.getElement());
		assertEquals(root, tree.parent(right));
		assertEquals(2, tree.size());

		try {
			tree.addRight(root, "another-right");
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the remove(p) behavior, including expected exceptions
	 */
	@Test
	public void testRemove() {
		createTree();
		try {
			tree.remove(two);
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		assertEquals("five", tree.remove(five));
		assertNull(tree.right(ten));
		assertEquals(9, tree.size());

		assertEquals("ten", tree.remove(ten));
		assertEquals("seven", tree.right(two).getElement());
		assertEquals(8, tree.size());

		try {
			tree.remove(one);
			fail("Should throw IllegalArgumentException");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test the output of the toString() method
	 */
	@Test
	public void testToString() {
		// Test empty tree
		assertEquals("LinkedBinaryTree[\n]", tree.toString());

		// Test tree with a single node
		tree.addRoot("root");
		String expectedSingleNode = "LinkedBinaryTree[\n" + "root\n" + "]";
		assertEquals(expectedSingleNode, tree.toString());
		tree = new LinkedBinaryTree<>(); // Reset tree

		// Test multi-level tree (using the createTree structure)
		createTree();
		String expectedTree = "LinkedBinaryTree[\n" + "one\n" + " two\n" + "  six\n" + "  ten\n" + "   seven\n"
				+ "   five\n" + " three\n" + "  four\n" + "   eight\n" + "   nine\n" + "]";
		assertEquals(expectedTree, tree.toString());
	}
}
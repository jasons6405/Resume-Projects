package edu.ncsu.csc316.dsa.disjoint_set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest Checks the expected outputs of the
 * Disjoint Set abstract data type behaviors when using an up-tree data
 * structure
 *
 * @author Dr. King
 * @author // Your Name Here
 *
 */
public class UpTreeDisjointSetForestTest {

	/**
	 * The DisjointSetForest for testing
	 */
	private DisjointSetForest<String> set;

	/**
	 * Create a new instance of a up-tree forest before each test case executes
	 */
	@Before
	public void setUp() {
		set = new UpTreeDisjointSetForest<>();
	}

	/**
	 * Test the output of the makeSet behavior
	 */
	@Test
	public void testMakeSet() {
		Position<String> one = set.makeSet("one");
		assertEquals("one", one.getElement());
		Position<String> two = set.makeSet("two");
		assertEquals("two", two.getElement());

		try {
			set.makeSet("one");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Element already exists.", e.getMessage());
		}
	}

	/**
	 * Test the output of the union-find behaviors
	 */
	@Test
	public void testUnionFind() {
		Position<String> one = set.makeSet("one");
		Position<String> two = set.makeSet("two");
		Position<String> three = set.makeSet("three");
		Position<String> four = set.makeSet("four");
		Position<String> five = set.makeSet("five");
		Position<String> six = set.makeSet("six");
		Position<String> seven = set.makeSet("seven");
		Position<String> eight = set.makeSet("eight");
		Position<String> nine = set.makeSet("nine");
		Position<String> ten = set.makeSet("ten");

		assertEquals(one, set.find("one"));
		assertEquals(two, set.find("two"));
		assertEquals(three, set.find("three"));
		assertEquals(four, set.find("four"));
		assertEquals(five, set.find("five"));
		assertEquals(six, set.find("six"));
		assertEquals(seven, set.find("seven"));
		assertEquals(eight, set.find("eight"));
		assertEquals(nine, set.find("nine"));
		assertEquals(ten, set.find("ten"));

		// Union pairs (one-two, three-four, five-six, seven-eight, nine-ten)
		set.union(one, two);
		set.union(three, four);
		set.union(five, six);
		set.union(seven, eight);
		set.union(nine, ten);

		// Verify roots after initial unions
		Position<String> root1 = set.find("one");
		assertEquals(root1, set.find("two"));

		Position<String> root2 = set.find("three");
		assertEquals(root2, set.find("four"));

		Position<String> root3 = set.find("five");
		assertEquals(root3, set.find("six"));

		Position<String> root4 = set.find("seven");
		assertEquals(root4, set.find("eight"));

		Position<String> root5 = set.find("nine");
		assertEquals(root5, set.find("ten"));

		// Union groups: root1 <-> root2, root3 <-> root4, then root1 <-> root5
		set.union(root1, root2);
		set.union(root3, root4);
		set.union(root1, root5);

		// Verify merged roots
		Position<String> mergedRoot = set.find("one");
		assertEquals(mergedRoot, set.find("two"));
		assertEquals(mergedRoot, set.find("three"));
		assertEquals(mergedRoot, set.find("four"));
		assertEquals(mergedRoot, set.find("nine"));
		assertEquals(mergedRoot, set.find("ten"));

		// Verify roots of remaining elements
		Position<String> mergedRoot2 = set.find("five");
		assertEquals(mergedRoot2, set.find("six"));
		assertEquals(mergedRoot2, set.find("seven"));
		assertEquals(mergedRoot2, set.find("eight"));

		// Final union of all groups
		set.union(mergedRoot, mergedRoot2);

		// All elements should now have the same root
		Position<String> finalRoot = set.find("one");
		assertEquals(finalRoot, set.find("five"));
		assertEquals(finalRoot, set.find("six"));
		assertEquals(finalRoot, set.find("seven"));
		assertEquals(finalRoot, set.find("eight"));
		assertEquals(finalRoot, set.find("nine"));
		assertEquals(finalRoot, set.find("ten"));
	}
}
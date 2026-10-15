package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap Checks the expected outputs of the Map
 * abstract data type behaviors when using a separate chaining hash map data
 * structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class SeparateChainingHashMapTest {

	// 'Testing' Map used (no randomization) to check ordering of contents
	/**
	 * Test map with no randomization to check ordering
	 */
	private Map<Integer, String> testMap;

	// 'Production' Map (with randomization) to check correctness of ADT behaviors
	/**
	 * Production map with randomization to check ADT behaviors
	 */
	private Map<Integer, String> prodMap;

	/**
	 * Create a new instance of a separate chaining hash map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are TESTING.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		testMap = new SeparateChainingHashMap<Integer, String>(7, true);
		prodMap = new SeparateChainingHashMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, testMap.size());
		assertTrue(testMap.isEmpty());
		assertNull(testMap.put(3, "string3"));

		Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey());

		assertNull(testMap.put(4, "string4"));
		assertEquals(2, testMap.size());
		assertFalse(testMap.isEmpty());
		it = testMap.entrySet().iterator();
		assertEquals(3, (int) it.next().getKey());
		assertEquals(4, (int) it.next().getKey());

		// Test collisions
		assertNull(testMap.put(6, "string6"));
		assertNull(testMap.put(13, "string13"));
		assertEquals(4, testMap.size());

		Iterator<Map.Entry<Integer, String>> itCollision = testMap.entrySet().iterator();
		assertEquals(6, (int) itCollision.next().getKey());
		assertEquals(13, (int) itCollision.next().getKey());
		assertEquals(3, (int) itCollision.next().getKey());
		assertEquals(4, (int) itCollision.next().getKey());
		assertFalse(itCollision.hasNext());

		// Test update existing key
		assertEquals("string6", testMap.put(6, "newString6"));
		assertEquals(4, testMap.size());

		// Test prodMap
		assertNull(prodMap.put(1, "one"));
		assertEquals(1, prodMap.size());
		assertEquals("one", prodMap.get(1));
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(testMap.isEmpty());
		testMap.put(3, "three");
		assertEquals("three", testMap.get(3));
		assertNull(testMap.get(4));

		testMap.put(6, "six"); // hash0
		testMap.put(13, "thirteen"); // hash0
		assertEquals("six", testMap.get(6));
		assertEquals("thirteen", testMap.get(13));

		// Test prodMap
		prodMap.put(2, "two");
		assertEquals("two", prodMap.get(2));
		assertNull(prodMap.get(5));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(testMap.isEmpty());
		testMap.put(1, "one");
		assertEquals("one", testMap.remove(1));
		assertEquals(0, testMap.size());
		assertNull(testMap.remove(1));

		testMap.put(6, "a");
		testMap.put(13, "b");
		assertEquals("a", testMap.remove(6));
		assertEquals(1, testMap.size());
		assertEquals("b", testMap.get(13));

		// Test prodMap
		prodMap.put(5, "five");
		assertEquals("five", prodMap.remove(5));
		assertNull(prodMap.get(5));
	}

	/**
	 * Test the output of the iterator() behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		testMap.put(6, "a");
		testMap.put(13, "b");
		testMap.put(3, "c");
		Iterator<Integer> it = testMap.iterator();
		assertTrue(it.hasNext());
		assertEquals(6, (int) it.next());
		assertEquals(13, (int) it.next());
		assertEquals(3, (int) it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the entrySet() behavior
	 */
	@Test
	public void testEntrySet() {
		testMap.put(6, "a");
		testMap.put(13, "b");
		Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(6, (int) entry.getKey());
		assertEquals("a", entry.getValue());
		entry = it.next();
		assertEquals(13, (int) entry.getKey());
		assertEquals("b", entry.getValue());
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the values() behavior
	 */
	@Test
	public void testValues() {
		testMap.put(6, "a");
		testMap.put(13, "b");
		Iterator<String> it = testMap.values().iterator();
		assertEquals("a", it.next());
		assertEquals("b", it.next());
		assertFalse(it.hasNext());
	}
}
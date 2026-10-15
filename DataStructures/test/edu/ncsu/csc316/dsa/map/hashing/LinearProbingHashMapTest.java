package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for LinearProbingHashMap Checks the expected outputs of the Map
 * abstract data type behaviors when using a linear probing hash map data
 * structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class LinearProbingHashMapTest {

	// 'Testing' Map used (no randomization) to check placement of entries in the
	// hash table
	/**
	 * Test map with no randomization
	 */
	private Map<Integer, String> testMap;

	// 'Production' Map (with randomization) to check correctness of ADT behaviors
	/**
	 * Test "production" map with randomization
	 */
	private Map<Integer, String> prodMap;

	/**
	 * Create a new instance of a linear probing hash map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are testing.
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
		testMap = new LinearProbingHashMap<Integer, String>(7, true);
		prodMap = new LinearProbingHashMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, testMap.size());
		assertTrue(testMap.isEmpty());

		assertNull(testMap.put(3, "three"));
		assertEquals(1, testMap.size());

		assertNull(testMap.put(6, "six"));
		assertNull(testMap.put(13, "thirteen"));
		assertEquals(3, testMap.size());

		Iterator<Entry<Integer, String>> it = testMap.entrySet().iterator();
		assertEquals(6, (int) it.next().getKey());
		assertEquals(13, (int) it.next().getKey());
		assertEquals(3, (int) it.next().getKey());

		// Test update
		assertEquals("six", testMap.put(6, "newSix"));
		assertEquals("newSix", testMap.get(6));

		// Test prodMap
		prodMap.put(5, "five");
		assertEquals("five", prodMap.get(5));
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(testMap.isEmpty());
		testMap.put(6, "six");
		testMap.put(13, "thirteen");
		assertEquals("six", testMap.get(6));
		assertEquals("thirteen", testMap.get(13));
		assertNull(testMap.get(3));

		// Test deleted entry
		testMap.remove(6);
		assertNull(testMap.get(6));
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(testMap.isEmpty());
		testMap.put(6, "six");
		testMap.put(13, "thirteen");
		assertEquals("six", testMap.remove(6));
		assertEquals(1, testMap.size());
		assertNull(testMap.get(6));

		// Remove non-existent key
		assertNull(testMap.remove(6));
	}

	/**
	 * Test the output of the iterator() behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		testMap.put(6, "A"); // Hash index 0
		testMap.put(13, "B"); // Hash index 0, probes to 1
		testMap.put(20, "C"); // Hash index 0, probes to 2

		Iterator<Integer> it = testMap.iterator();

		// Check the order of keys
		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(6), it.next());
		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(13), it.next());
		assertTrue(it.hasNext());
		assertEquals(Integer.valueOf(20), it.next());
		assertFalse(it.hasNext());

		// Verify NoSuchElementException
		try {
			it.next();
			fail("Expected NoSuchElementException");
		} catch (NoSuchElementException e) {
			// Expected behavior
		}

	}

	/**
	 * Test the output of the entrySet() behavior
	 */
	@Test
	public void testEntrySet() {
		testMap.put(6, "six");
		testMap.put(13, "thirteen");
		testMap.put(3, "three");
		Iterator<Entry<Integer, String>> it = testMap.entrySet().iterator();
		assertEquals(6, (int) it.next().getKey());
		assertEquals(13, (int) it.next().getKey());
		assertEquals(3, (int) it.next().getKey());
		assertFalse(it.hasNext());
	}

	/**
	 * Test the output of the values() behavior
	 */
	@Test
	public void testValues() {
		testMap.put(6, "six");
		testMap.put(13, "thirteen");
		Iterator<String> it = testMap.values().iterator();
		assertEquals("six", it.next());
		assertEquals("thirteen", it.next());
		assertFalse(it.hasNext());
	}
}
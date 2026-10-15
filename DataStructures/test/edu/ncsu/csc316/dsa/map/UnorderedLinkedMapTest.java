package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UnorderedLinkedMap Checks the expected outputs of the Map
 * abstract data type behaviors when using an unordered link-based list data
 * structure that uses the move-to-front heuristic for self-organizing entries
 * based on access frequency
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class UnorderedLinkedMapTest {

	/**
	 * Creates a new empty Map for testing
	 */
	private Map<Integer, String> map;

	/**
	 * Create a new instance of an unordered link-based map before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());

		// Insert more entries. New entries are inserted at the front.
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(5, map.size());

		String oldValue = map.put(2, "newString2");
		assertEquals("string2", oldValue);
		assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
	}

	/**
	 * Test the output of the get(k) behavior
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("string5", map.get(5));
		assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
	}

	/**
	 * Test the output of the remove(k) behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		assertEquals(5, map.size());

		String removed = map.remove(4);
		assertEquals("string4", removed);
		assertEquals("UnorderedLinkedMap[1, 2, 5, 3]", map.toString());
		assertEquals(4, map.size());

		assertNull(map.remove(10));
	}

	/**
	 * Test the output of the iterator behavior, including expected exceptions
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
		assertEquals((Integer) 1, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 4, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 2, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 5, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 3, it.next());
		assertFalse(it.hasNext());
		try {
			it.next();
			fail("A NoSuchElementException should have been thrown");
		} catch (Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}

	/**
	 * Test the output of the entrySet() behavior, including expected exceptions
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");
		}
		String entries = sb.toString();
		assertTrue(entries.contains("1=string1; "));
		assertTrue(entries.contains("4=string4; "));
		assertTrue(entries.contains("2=string2; "));
		assertTrue(entries.contains("5=string5; "));
		assertTrue(entries.contains("3=string3; "));
	}

	/**
	 * Test the output of the values() behavior, including expected exceptions
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));

		StringBuilder sb = new StringBuilder();
		for (String value : map.values()) {
			sb.append(value).append("; ");
		}
		String values = sb.toString();
		assertTrue(values.contains("string1; "));
		assertTrue(values.contains("string4; "));
		assertTrue(values.contains("string2; "));
		assertTrue(values.contains("string5; "));
		assertTrue(values.contains("string3; "));
	}
}
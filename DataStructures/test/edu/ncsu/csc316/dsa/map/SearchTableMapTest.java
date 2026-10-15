package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SearchTableMap Checks the expected outputs of the Map abstract
 * data type behaviors when using a sorted array-based data structure that uses
 * binary search to locate entries based on the key of the entry
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class SearchTableMapTest {

	/**
	 * Creates a new Map object for testing
	 */
	private Map<Integer, String> map;

	/**
	 * Creates a new StudentMap object for testing Student objects
	 */
	private Map<Student, Integer> studentMap;

	/**
	 * Create a new instance of a search table map before each test case executes
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}

	/**
	 * Test the output of the put(k,v) behavior
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());

		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		assertEquals(5, map.size());

		String oldValue = map.put(2, "newString2");
		assertEquals("string2", oldValue);
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		assertNull(map.get(10));
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());

		String removed = map.remove(4);
		assertEquals("string4", removed);
		assertEquals("SearchTableMap[1, 2, 3, 5]", map.toString());
		assertEquals(4, map.size());

		assertNull(map.remove(10));
	}

	/**
	 * Tests Map abstract data type behaviors to ensure the behaviors work as
	 * expected when using arbitrary objects as keys
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");

		assertNull(studentMap.put(s1, 100));
		assertNull(studentMap.put(s2, 200));
		assertNull(studentMap.put(s3, 300));
		assertNull(studentMap.put(s4, 400));
		assertNull(studentMap.put(s5, 500));

		String mapStr = studentMap.toString();
		assertTrue(mapStr.contains(s1.toString()));
		assertTrue(mapStr.contains(s2.toString()));
		assertTrue(mapStr.contains(s3.toString()));
		assertTrue(mapStr.contains(s4.toString()));
		assertTrue(mapStr.contains(s5.toString()));
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
		assertEquals((Integer) 2, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 3, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 4, it.next());
		assertTrue(it.hasNext());
		assertEquals((Integer) 5, it.next());
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

		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(1, (int) entry.getKey());
		assertEquals("string1", (String) entry.getValue());

		Map.Entry<Integer, String> entry2 = it.next();
		assertEquals((Integer) 2, entry2.getKey());
		assertEquals("string2", entry2.getValue());
		Map.Entry<Integer, String> entry3 = it.next();
		assertEquals((Integer) 3, entry3.getKey());
		assertEquals("string3", entry3.getValue());
		Map.Entry<Integer, String> entry4 = it.next();
		assertEquals((Integer) 4, entry4.getKey());
		assertEquals("string4", entry4.getValue());
		Map.Entry<Integer, String> entry5 = it.next();
		assertEquals((Integer) 5, entry5.getKey());
		assertEquals("string5", entry5.getValue());
		assertFalse(it.hasNext());
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

		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());

		String val1 = it.next();
		assertEquals("string1", val1);
		String val2 = it.next();
		assertEquals("string2", val2);
		String val3 = it.next();
		assertEquals("string3", val3);
		String val4 = it.next();
		assertEquals("string4", val4);
		String val5 = it.next();
		assertEquals("string5", val5);
		assertFalse(it.hasNext());
	}
}
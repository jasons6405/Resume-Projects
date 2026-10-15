package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue Checks the expected outputs of the
 * Adaptable Priorty Queue abstract data type behaviors when using a min-heap
 * data structure
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/**
	 * Heap to be used in testing
	 */
	private HeapAdaptablePriorityQueue<Integer, String> heap;

	/**
	 * Create a new instance of a heap before each test case executes
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}

	/**
	 * Test the output of the replaceKey behavior
	 */
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.replaceKey(e8, -5);
		assertEquals(-5, (int) heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(9, heap.size());

		// Replace e0's key to 10 (no longer min)
		heap.replaceKey(e0, 10);
		assertEquals(-5, (int) heap.min().getKey());

		// Replace e7's key to -10 (new min)
		heap.replaceKey(e7, -10);
		assertEquals(-10, (int) heap.min().getKey());
		assertEquals("seven", heap.min().getValue());

		// Replace e3's key to -1 (bubble up)
		heap.replaceKey(e3, -1);
		assertEquals(-10, (int) heap.min().getKey()); // Still min

		// Replace e1's key to -15 (new min)
		heap.replaceKey(e1, -15);
		assertEquals(-15, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());

		// Test e2 (key=2)
		heap.replaceKey(e2, -2);
		assertEquals(-15, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());

		// Replace e2's key back to 10 (no longer min)
		heap.replaceKey(e2, 10);
		assertEquals(-15, (int) heap.min().getKey()); // Assuming previous state

		// Test e4 (key=4)
		heap.replaceKey(e4, -4);
		assertEquals(-15, (int) heap.min().getKey()); // Still -5
		heap.replaceKey(e4, -6);
		assertEquals(-15, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());

		// Test e5 (key=5)
		heap.replaceKey(e5, -7);
		assertEquals(-15, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());

		// Test e6 (key=6)
		heap.replaceKey(e6, -8);
		assertEquals(-15, (int) heap.min().getKey());
		assertEquals("one", heap.min().getValue());
	}

	/**
	 * Test the output of the replaceValue behavior
	 */
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		heap.replaceValue(e8, "eight");
		assertEquals("eight", e8.getValue());
		assertEquals(8, (int) e8.getKey());

		heap.replaceValue(e7, "seven");
		assertEquals("seven", e7.getValue());

		heap.replaceValue(e6, "six");
		assertEquals("six", e6.getValue());

		// Ensure min remains correct
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("zero", heap.min().getValue());

		// Replace all entries 0-5
		heap.replaceValue(e0, "zero");
		assertEquals("zero", e0.getValue());

		heap.replaceValue(e1, "one");
		assertEquals("one", e1.getValue());

		heap.replaceValue(e2, "two");
		assertEquals("two", e2.getValue());

		heap.replaceValue(e3, "three");
		assertEquals("three", e3.getValue());

		heap.replaceValue(e4, "four");
		assertEquals("four", e4.getValue());

		heap.replaceValue(e5, "five");
		assertEquals("five", e5.getValue());

		// Ensure min remains unchanged (still e0)
		assertEquals(0, (int) heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
	}

	/**
	 * Test the output of the remove behavior
	 */
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());

		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());

		// Remove min (e0)
		heap.remove(e0);
		assertEquals(1, (int) heap.min().getKey());
		assertEquals(8, heap.size());

		// Remove new min (e1)
		heap.remove(e1);
		assertEquals(2, (int) heap.min().getKey());
		assertEquals(7, heap.size());

		// Remove e2
		heap.remove(e2);
		assertEquals(3, (int) heap.min().getKey());
		assertEquals(6, heap.size());

		// Remove e3
		heap.remove(e3);
		assertEquals(4, (int) heap.min().getKey());
		assertEquals(5, heap.size());

		// Remove e4
		heap.remove(e4);
		assertEquals(5, (int) heap.min().getKey());
		assertEquals(4, heap.size());

		// Remove e5
		heap.remove(e5);
		assertEquals(6, (int) heap.min().getKey());
		assertEquals(3, heap.size());

		// Remove e6
		heap.remove(e6);
		assertEquals(7, (int) heap.min().getKey());
		assertEquals(2, heap.size());

		// Remove e7
		heap.remove(e7);
		assertEquals(8, (int) heap.min().getKey());
		assertEquals(1, heap.size());

		// Remove e8
		heap.remove(e8);
		assertTrue(heap.isEmpty());
	}

	/**
	 * Test the output of the heap behavior when using arbitrary key objects to
	 * represent priorities
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(
				new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");

		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());

		Entry<Student, String> e1 = sHeap.insert(s3, "sh3");
		Entry<Student, String> e2 = sHeap.insert(s1, "jk1");
		Entry<Student, String> e3 = sHeap.insert(s5, "lb5");
		Entry<Student, String> e4 = sHeap.insert(s2, "js2");
		Entry<Student, String> e5 = sHeap.insert(s4, "jj4");

		// Initial min is s1 (ID=1)
		assertEquals(s1, sHeap.min().getKey());

		// Replace s3's ID to 0 (new min)
		Student s3Updated = new Student("S", "H", 0, 1, 3, "sh0");
		sHeap.replaceKey(e1, s3Updated);
		assertEquals(s3Updated, sHeap.min().getKey());

		// Remove s3Updated
		sHeap.remove(e1);
		assertEquals(s1, sHeap.min().getKey());

		// Replace s5's ID to 1 (duplicate, but s1 still min)
		Student s5Updated = new Student("L", "B", 1, 1, 5, "lb1");
		sHeap.replaceKey(e3, s5Updated);
		assertEquals(s1, sHeap.min().getKey()); // s1 still has original insertion order

		// Remove s1
		sHeap.remove(e2);
		assertEquals(s5Updated, sHeap.min().getKey());

		// Remove s5Updated
		sHeap.remove(e3);
		assertEquals(s2, sHeap.min().getKey());

		// Remove s2
		sHeap.remove(e4);
		assertEquals(s4, sHeap.min().getKey());

		// Remove s4
		sHeap.remove(e5);
		assertTrue(sHeap.isEmpty());
	}
}
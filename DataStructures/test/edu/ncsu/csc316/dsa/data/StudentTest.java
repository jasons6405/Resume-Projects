package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the student POJO
 * 
 * @author Jason Stout
 */
public class StudentTest {

	/**
	 * Student object for first student
	 */
	private Student sOne;
	/**
	 * Student object for second student
	 */
	private Student sTwo;

	/**
	 * Sets up both students
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
	}

	/**
	 * Tests the setFirst method
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast method
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the setId method
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa method
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}

	/**
	 * Tests the setUnityID method
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}

	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals() {
		Student duplicateOfOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertTrue(sOne.equals(duplicateOfOne));
		assertFalse(sOne.equals(sTwo));
		assertNotNull(sOne);
	}

	/**
	 * Tests the hashCode method
	 */
	@Test
	public void testHashCode() {
		Student duplicateOfOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		assertEquals(sOne.hashCode(), duplicateOfOne.hashCode());
		assertNotEquals(sOne.hashCode(), sTwo.hashCode());
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		String expected = "Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]";
		assertEquals(expected, sOne.toString());
	}

	/**
	 * Tests the setCreditHours method
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(15);
		assertEquals(15, sOne.getCreditHours());
	}

}

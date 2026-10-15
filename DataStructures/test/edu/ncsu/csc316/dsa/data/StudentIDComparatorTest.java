package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the StudentIDComparator
 * 
 * @author Jason Stout
 */
public class StudentIDComparatorTest {

	/**
	 * Student object for first student
	 */
	private Student sOne;

	/**
	 * Student object for second student
	 */
	private Student sTwo;

	/**
	 * Student object for third student
	 */
	private Student sThree;

	/**
	 * Student object for fourth student
	 */
	private Student sFour;

	/**
	 * Student object for fifth student
	 */
	private Student sFive;

	/**
	 * Custom comparator for student's IDs
	 */
	private StudentIDComparator comparator;

	/**
	 * Sets up each student and the custom comparator
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Tests the compare method for the ID comparator
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		// Test ascending order
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sTwo, sThree) < 0);
		assertTrue(comparator.compare(sFour, sFive) < 0);

		// Test descending order
		assertTrue(comparator.compare(sFive, sFour) > 0);
		assertTrue(comparator.compare(sThree, sTwo) > 0);
		// Test equal IDs
		assertEquals(0, comparator.compare(sThree, new Student("Test", "Case", 3, 6, 3.0, "testUnityID")));
	}

}

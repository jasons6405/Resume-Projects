package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the RadixSorter class
 * 
 * @author Jason Stout
 */
public class RadixSorterTest {

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
	 * Creates a RadixSorter object
	 */
	private RadixSorter<Student> sorter;

	/**
	 * Sets up each student and the sorter object
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		sorter = new RadixSorter<>();
	}

	/**
	 * Tests sorting student in a random order
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}

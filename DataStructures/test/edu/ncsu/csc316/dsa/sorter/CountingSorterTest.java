package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the CountingSorter class
 * 
 * @author Jason Stout
 */
public class CountingSorterTest {

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
	 * Creates a CountingSorter object
	 */
	private CountingSorter<Student> sorter;

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

		sorter = new CountingSorter<Student>();
	}

	/**
	 * Tests sorting students in random order
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

	/**
	 * Tests sorting students that are already sorted
	 */
	@Test
	public void testSortSorted() {
		Student[] original = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

	/**
	 * Tests sorting students that are in reverse order
	 */
	@Test
	public void testSortReverse() {
		Student[] original = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}

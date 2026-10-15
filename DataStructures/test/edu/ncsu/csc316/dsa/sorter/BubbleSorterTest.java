package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the BubbleSorter class
 * 
 * @author Jason Stout
 */
public class BubbleSorterTest {

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
	 * Creates a BubbleSorter object
	 */
	private BubbleSorter<Student> sorter;

	/**
	 * Sets up each student and the sorter
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		sorter = new BubbleSorter<>();
	}

	/**
	 * Tests the bubble sort algorithm using random ordered objects
	 */
	@Test
	public void testSortRandomOrder() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		Student[] expected = { sFive, sFour, sOne, sThree, sTwo };
		sorter.sort(original);
		assertArrayEquals(expected, original);
	}

	/**
	 * Tests the bubble sort algorithm using reverse ordered objects
	 */
	@Test
	public void testSortReverseOrder() {
		Student[] original = { sFive, sFour, sThree, sTwo, sOne };
		Student[] expected = { sFive, sFour, sOne, sThree, sTwo };
		sorter.sort(original);
		assertArrayEquals(expected, original);
	}

	/**
	 * Tests the bubble sort algorithm using already sorted objects
	 */
	@Test
	public void testSortAlreadySorted() {
		Student[] original = { sFive, sFour, sOne, sThree, sTwo };
		Student[] expected = { sFive, sFour, sOne, sThree, sTwo };
		sorter.sort(original);
		assertArrayEquals(expected, original);
	}

}

package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the InsertionSorter class
 * 
 * @author Jason Stout
 */
public class InsertionSorterTest {

	/**
	 * Integer array of ascending numbers
	 */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };

	/**
	 * Integer array of descending numbers
	 */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };

	/**
	 * Inter array of randomly sorted numbers
	 */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/**
	 * Unsorted array of students
	 */
	private Student[] studentsUnsorted;

	/**
	 * Array of students sorted by name
	 */
	private Student[] studentsSorted;

	/**
	 * Insertion sorter for integers
	 */
	private InsertionSorter<Integer> integerSorter;

	/**
	 * Insertion sorter for students
	 */
	private InsertionSorter<Student> studentSorter;

	/**
	 * Sets up arrays and sorters before testing
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<>();
		studentSorter = new InsertionSorter<>();

		// Create a list of unsorted students
		studentsUnsorted = new Student[] { new Student("Alice", "Smith", 3, 30, 3.2, "asmith"),
				new Student("Bob", "Jones", 1, 25, 3.8, "bjones"),
				new Student("Charlie", "Brown", 2, 28, 3.5, "cbrown") };

		// Create a list of what the students should be ordered as according to
		// their last names
		studentsSorted = new Student[] { new Student("Charlie", "Brown", 2, 28, 3.5, "cbrown"),
				new Student("Bob", "Jones", 1, 25, 3.8, "bjones"),
				new Student("Alice", "Smith", 3, 30, 3.2, "asmith") };
	}

	/**
	 * Tests sorting each array of integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, dataAscending);

		integerSorter.sort(dataDescending);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, dataDescending);

		integerSorter.sort(dataRandom);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, dataRandom);
	}

	/**
	 * Tests sorting students by last name
	 */
	@Test
	public void testSortStudentsByLastName() {
		Comparator<Student> lastNameComparator = (s1, s2) -> s1.getLast().compareTo(s2.getLast());
		studentSorter = new InsertionSorter<>(lastNameComparator);
		studentSorter.sort(studentsUnsorted);
		assertArrayEquals(studentsSorted, studentsUnsorted);
	}

	/**
	 * Tests sorting an empty array
	 */
	@Test
	public void testSortEmptyArray() {
		Integer[] emptyArray = {};
		integerSorter.sort(emptyArray);
		assertArrayEquals(new Integer[] {}, emptyArray);
	}

	/**
	 * Tests the natural ordering from the AbstractComparisonSorter
	 */
	@Test
	public void testNaturalOrderCompare() {
		AbstractComparisonSorter<Integer> sorter = new InsertionSorter<>();
		Integer first = 10;
		Integer second = 20;

		// Directly compare using the sorter's compare method
		int result = sorter.compare(first, second);

		// Verify the comparison result
		assertEquals(-1, result);
	}
}

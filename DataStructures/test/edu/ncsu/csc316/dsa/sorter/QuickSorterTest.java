package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the QuickSorter class and algorithm
 * 
 * @author Jason Stout
 */
public class QuickSorterTest {
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
	private QuickSorter<Integer> integerSorter;

	/**
	 * Insertion sorter for students
	 */
	private QuickSorter<Student> studentSorter;

	/**
	 * Sets up arrays and sorters before testing
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<>();
		studentSorter = new QuickSorter<>();

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
		studentSorter = new QuickSorter<>(lastNameComparator);
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

	/**
	 * Tests the FirstElementSelector
	 */
	@Test
	public void testFirstElementSelector() {
		Integer[] data = { 4, 2, 7, 1, 9, 3 };
		Integer[] expected = { 1, 2, 3, 4, 7, 9 };

		QuickSorter<Integer> sorter = new QuickSorter<>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		sorter.sort(data);

		assertArrayEquals(expected, data);
	}

	/**
	 * Tests the LastElementSelector
	 */
	@Test
	public void testLastElementSelector() {
		Integer[] data = { 4, 2, 7, 1, 9, 3 };
		Integer[] expected = { 1, 2, 3, 4, 7, 9 };

		QuickSorter<Integer> sorter = new QuickSorter<>(QuickSorter.LAST_ELEMENT_SELECTOR);
		sorter.sort(data);

		assertArrayEquals(expected, data);
	}

	/**
	 * Tests the MiddleElementSelector
	 */
	@Test
	public void testMiddleElementSelector() {
		Integer[] data = { 4, 2, 7, 1, 9, 3 };
		Integer[] expected = { 1, 2, 3, 4, 7, 9 };

		QuickSorter<Integer> sorter = new QuickSorter<>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		sorter.sort(data);

		assertArrayEquals(expected, data);
	}

	/**
	 * Tests the RandomElementSelector
	 */
	@Test
	public void testRandomElementSelector() {
		Integer[] data = { 4, 2, 7, 1, 9, 3 };
		Integer[] expected = { 1, 2, 3, 4, 7, 9 };

		QuickSorter<Integer> sorter = new QuickSorter<>(QuickSorter.RANDOM_ELEMENT_SELECTOR);
		sorter.sort(data);

		assertArrayEquals(expected, data);
	}
}

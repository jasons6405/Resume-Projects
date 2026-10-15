package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the SelectionSorter class
 * 
 * @author Jason Stout
 */
public class SelectionSorterTest {

	/**
	 * Unsorted array of students
	 */
	private Student[] studentsUnsorted;

	/**
	 * Array of students sorted by name
	 */
	private Student[] studentsSorted;

	/**
	 * Selection sorter for students
	 */
	private SelectionSorter<Student> studentSorter;

	/**
	 * Sets up student arrays
	 */
	@Before
	public void setUp() {
		new SelectionSorter<>();
		studentSorter = new SelectionSorter<>();

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
	 * Tests sorting integers in a descending order
	 */
	@Test
	public void testDescendingIntegerArray() {
		Integer[] data = { 10, 8, 6, 4, 2 };
		SelectionSorter<Integer> sorter = new SelectionSorter<>();
		sorter.sort(data);

		Integer[] expected = { 2, 4, 6, 8, 10 };
		assertArrayEquals(expected, data);
	}

	/**
	 * Tests sorting students by last name
	 */
	@Test
	public void testSortStudentsByLastName() {
		Comparator<Student> lastNameComparator = (s1, s2) -> s1.getLast().compareTo(s2.getLast());
		studentSorter = new SelectionSorter<>(lastNameComparator);
		studentSorter.sort(studentsUnsorted);
		assertArrayEquals(studentsSorted, studentsUnsorted);
	}

}

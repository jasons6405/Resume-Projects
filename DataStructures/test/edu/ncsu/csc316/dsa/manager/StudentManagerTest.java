package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/**
 * Tests the StudentManager class
 * 
 * @author Jason Stout
 */
public class StudentManagerTest {

	/**
	 * Creates a new StudentManager object
	 */
	private StudentManager sm;

	/**
	 * Creates an array of students
	 */
	private Student[] students;

	/**
	 * Sets up the manager and array
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		students = sm.sort();
	}

	/**
	 * Tests the default sort method
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}

	/**
	 * Tests the sort method using GPA comparator
	 */
	@Test
	public void testSortByGPA() {
		Comparator<Student> gpaComparator = new StudentGPAComparator();
		InsertionSorter<Student> sorter = new InsertionSorter<Student>(gpaComparator);

		sorter.sort(students);

		assertEquals("Nichole", students[0].getFirst());
		assertEquals("Alicia", students[1].getFirst());
		assertEquals("Charlene", students[2].getFirst());
		assertEquals("Cristine", students[3].getFirst());
		assertEquals("Dante", students[4].getFirst());
		assertEquals("Lacie", students[5].getFirst());
		assertEquals("Idalia", students[6].getFirst());
		assertEquals("Ara", students[7].getFirst());
		assertEquals("Loise", students[8].getFirst());
		assertEquals("Tanner", students[9].getFirst());
		assertEquals("Amber", students[10].getFirst());
		assertEquals("Roxann", students[11].getFirst());
		assertEquals("Tyree", students[12].getFirst());
		assertEquals("Evelin", students[13].getFirst());
		assertEquals("Shanti", students[14].getFirst());
		assertEquals("Lewis", students[15].getFirst());
	}

	/**
	 * Tests the sort method using ID comparator
	 */
	@Test
	public void testSortByID() {
		Comparator<Student> idComparator = new StudentIDComparator();
		InsertionSorter<Student> sorter = new InsertionSorter<Student>(idComparator);

		sorter.sort(students);

		assertEquals("Amber", students[0].getFirst());
		assertEquals("Ara", students[1].getFirst());
		assertEquals("Lacie", students[2].getFirst());
		assertEquals("Idalia", students[3].getFirst());
		assertEquals("Evelin", students[4].getFirst());
		assertEquals("Lewis", students[5].getFirst());
		assertEquals("Alicia", students[6].getFirst());
		assertEquals("Tyree", students[7].getFirst());
		assertEquals("Loise", students[8].getFirst());
		assertEquals("Roxann", students[9].getFirst());
		assertEquals("Nichole", students[10].getFirst());
		assertEquals("Charlene", students[11].getFirst());
		assertEquals("Shanti", students[12].getFirst());
		assertEquals("Cristine", students[13].getFirst());
		assertEquals("Tanner", students[14].getFirst());
		assertEquals("Dante", students[15].getFirst());
	}
}
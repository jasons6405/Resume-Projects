package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the StudentReader method
 * 
 * @author Jason Stout
 */
public class StudentReaderTest {

	/**
	 * Tests the readFile method
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}

	/**
	 * Tests the processline method
	 */
	@Test
	public void testProcessLine() {
		Student[] students = StudentReader.readInputAsArray("input/student_ascendingID.csv");

		assertNotNull(students);

		assertEquals("Amber", students[0].getFirst());
		assertEquals("Michael", students[0].getLast());
		assertEquals("michaea", students[0].getUnityID());
		assertEquals(1, students[0].getId());
		assertEquals(1.10, students[0].getGpa(), 0.001);
		assertEquals(10, students[0].getCreditHours());

		assertEquals("Ara", students[1].getFirst());
		assertEquals("Marsh", students[1].getLast());
		assertEquals("marsha", students[1].getUnityID());
		assertEquals(3, students[1].getId());
		assertEquals(2.25, students[1].getGpa(), 0.001);
		assertEquals(11, students[1].getCreditHours());
	}
}

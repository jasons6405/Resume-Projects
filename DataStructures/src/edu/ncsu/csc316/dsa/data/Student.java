package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Jason Stout
 */
public class Student implements Comparable<Student>, Identifiable {

	/**
	 * First name of the student
	 */
	private String first;

	/**
	 * Last name of the student
	 */
	private String last;

	/**
	 * The student's Id
	 */
	private int id;

	/**
	 * The student's number of credit hours
	 */
	private int creditHours;

	/**
	 * The student's GPA
	 */
	private double gpa;

	/**
	 * The student's Unity ID
	 */
	private String unityID;

	/**
	 * Creates a Student object using given parameters
	 * 
	 * @param first       first name
	 * @param last        last name
	 * @param id          student id
	 * @param creditHours credit hours
	 * @param gpa         GPA
	 * @param unityID     student unity id
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Returns the student's first name
	 * 
	 * @return first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the student's first name
	 * 
	 * @param first the student's first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns the student's last name
	 * 
	 * @return last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last name of the student
	 * 
	 * @param last student's last name
	 */
	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the student's ID
	 * 
	 * @param id id of the student
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the student's credit hours
	 * 
	 * @return creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the student's number of credit hours
	 * 
	 * @param creditHours student's number of credit hours
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns the student's GPA
	 * 
	 * @return gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the student's GPA
	 * 
	 * @param gpa the student's GPA
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns the student's Unity ID
	 * 
	 * @return unityId
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the student's Unity ID
	 * 
	 * @param unityID the student's Unity ID
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Generates hashCode for a student object
	 * 
	 * @return object's hash
	 */
	@Override
	public int hashCode() {
		return Objects.hash(creditHours, first, gpa, id, last, unityID);
	}

	/**
	 * Checks if two objects are equal
	 * 
	 * @return object's equality
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(first, other.first) && id == other.id && Objects.equals(last, other.last);
	}

	/**
	 * Compares current student to another
	 * 
	 * @return comparison
	 */
	@Override
	public int compareTo(Student other) {
		// Compare by last name
		int lastNameComparison = this.last.compareTo(other.last);
		if (lastNameComparison != 0) {
			return lastNameComparison;
		}

		// If last names are the same, compare by first name
		int firstNameComparison = this.first.compareTo(other.first);
		if (firstNameComparison != 0) {
			return firstNameComparison;
		}

		// If both names are the same, compare by student ID
		return Integer.compare(this.id, other.id);
	}

	/**
	 * Generates string representation of a student object
	 * 
	 * @return the student object in string form
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

}

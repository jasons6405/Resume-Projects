package edu.ncsu.csc316.trail.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ReportManager class, checking several different files with
 * differing values
 * 
 * @author Jason Stout
 */
public class ReportManagerTest {

	/**
	 * ReportManager instance for first files
	 */
	private ReportManager rm1;

	/**
	 * ReportManager instance for second files
	 */
	private ReportManager rm2;

	/**
	 * ReportManager instance for third files
	 */
	private ReportManager rm3;

	/**
	 * Sets up each ReportManager instance before the tests
	 */
	@BeforeEach
	public void setUp() {
		rm1 = new ReportManager("input/landmarks1_test.txt", "input/trails1_test.txt");
		rm2 = new ReportManager("input/landmarks2_test.txt", "input/trails2_test.txt");
		rm3 = new ReportManager("input/landmarks3_test.txt", "input/trails3_test.txt");
	}

	/**
	 * Tests the report of the first file's ordering
	 */
	@Test
	public void testBasicFileReportOrdering() {
		String report = rm1.getDistancesReport("L01");

		assertFalse(report.indexOf("L04") < report.indexOf("L03"));
		assertFalse(report.indexOf("L03") < report.indexOf("L02"));
	}

	/**
	 * Checks the report of the first file's mile conversion
	 */
	@Test
	public void testBasicFileMileConversion() {
		String report = rm1.getDistancesReport("L03");
		assertFalse(report.contains("1.00 miles"));
	}

	/**
	 * Checks the formatting of the second file's report
	 */
	@Test
	public void testComplexFileReportFormatting() {
		String report = rm2.getDistancesReport("L04");
		assertTrue(report.startsWith("Landmarks Reachable from Visitor Center (L04)"));
		assertTrue(report.contains("1200 feet to Bird Sanctuary (L06)"));
	}

	/**
	 * Checks the sorting of first aid locations in the second file's report
	 */
	@Test
	public void testComplexFirstAidSorting() {
		String result = rm2.getProposedFirstAidLocations(3);

		assertTrue(result.indexOf("L04") < result.indexOf("L01"));
	}

	/**
	 * Checks the report of the third files
	 */
	@Test
	public void testDisconnectedReport() {
		String report = rm3.getDistancesReport("L07");
		assertTrue(report.contains("1200 feet to Emergency Hut (L08)"));
		assertFalse(report.contains("North Entrance"));
	}

	/**
	 * Checks that there are no first aid locations with a minimum of 2 trails in
	 * the third file
	 */
	@Test
	public void testNoFirstAidLocations() {
		String result = rm3.getProposedFirstAidLocations(2);
		assertFalse(result.contains("No landmarks have at least 2 intersecting trails"));
	}

	/**
	 * Checks the output of an invalid number of minimum trails
	 */
	@Test
	public void testInvalidMinTrails() {
		String result = rm1.getProposedFirstAidLocations(-5);
		assertEquals("Number of intersecting trails must be greater than 0.", result);
	}

	/**
	 * Checks the handling of an empty trail list
	 */
	@Test
	public void testEmptyTrailList() {
		String result = rm3.getProposedFirstAidLocations(1);
		assertTrue(result.contains("Proposed Locations"));
	}

}

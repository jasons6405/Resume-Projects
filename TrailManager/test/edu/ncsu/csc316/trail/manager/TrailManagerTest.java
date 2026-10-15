package edu.ncsu.csc316.trail.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.trail.data.Landmark;
import edu.ncsu.csc316.trail.data.Trail;

/**
 * Tests the TrailManager class, checking several different files with differing values
 * cases
 * 
 * @author Jason Stout
 */
public class TrailManagerTest {

	/**
	 * TrailManager instance for first files
	 */
	private TrailManager tm1;

	/**
	 * TrailManager instance for the second files
	 */
	private TrailManager tm2;

	/**
	 * TrailManager instance for the third files
	 */
	private TrailManager tm3;

	/**
	 * Sets up each TrailManager instance using their respective test files
	 */
	@Before
	public void setUp() {
		tm1 = new TrailManager("input/landmarks1_test.txt", "input/trails1_test.txt");
		tm2 = new TrailManager("input/landmarks2_test.txt", "input/trails2_test.txt");
		tm3 = new TrailManager("input/landmarks3_test.txt", "input/trails3_test.txt");
	}

	/**
	 * Tests the first files, making sure they load correctly
	 */
	@Test
	public void testBasicFileLandmarkLoading() {
		assertNotNull(tm1.getLandmarkByID("L01"));
		assertNotNull(tm1.getLandmarkByID("L08"));
		assertEquals("Overlook Restrooms", tm1.getLandmarkByID("L08").getDescription());
	}

	/**
	 * Tests the first file's trail connections
	 */
	@Test
	public void testBasicFileTrailConnections() {
		Map<Landmark, List<Trail>> firstAid = tm1.getProposedFirstAidLocations(2);
		assertEquals(6, firstAid.size());
	}

	/**
	 * Tests the second file's paths
	 */
	@Test
	public void testComplexFileCircularPaths() {
		Map<Landmark, Integer> distances = tm2.getDistancesToDestinations("L07");

		assertEquals(5000, (int) distances.get(tm2.getLandmarkByID("L02")));
	}

	/**
	 * Tests the connections of the second files
	 */
	@Test
	public void testComplexFileMultipleConnections() {
		Map<Landmark, List<Trail>> firstAid = tm2.getProposedFirstAidLocations(3);
		assertEquals(1, firstAid.size());
	}

	/**
	 * Tests the components of the third files
	 */
	@Test
	public void testDisconnectedComponents() {
		Map<Landmark, Integer> distances = tm3.getDistancesToDestinations("L01");
		assertTrue(distances.get(tm3.getLandmarkByID("L04")) < Integer.MAX_VALUE);
	}

	/**
	 * Tests the first aid location output of the third files
	 */
	@Test
	public void testDisconnectedFirstAid() {
		Map<Landmark, List<Trail>> firstAid = tm3.getProposedFirstAidLocations(2);
		assertEquals(2, firstAid.size());
	}

	/**
	 * Tests the threshold for the minimum number of trails for a first aid station
	 */
	@Test
	public void testMinimumTrailThreshold() {
		Map<Landmark, List<Trail>> firstAid = tm1.getProposedFirstAidLocations(1);
		assertEquals(8, firstAid.size());
	}

	/**
	 * Tests unreachable/unconnected landmarks
	 */
	@Test
	public void testUnreachableLandmarks() {
		Map<Landmark, Integer> distances = tm3.getDistancesToDestinations("L07");
		assertEquals(Integer.MAX_VALUE, (int) distances.get(tm3.getLandmarkByID("L01")));
	}
}

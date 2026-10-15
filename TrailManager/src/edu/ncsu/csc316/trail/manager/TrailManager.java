package edu.ncsu.csc316.trail.manager;

import edu.ncsu.csc316.trail.data.Landmark;
import edu.ncsu.csc316.trail.data.Trail;
import edu.ncsu.csc316.trail.dsa.DSAFactory;
import edu.ncsu.csc316.trail.dsa.Algorithm;
import edu.ncsu.csc316.trail.dsa.DataStructure;
import edu.ncsu.csc316.trail.io.TrailInputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

import java.io.FileNotFoundException;
import java.util.Comparator;

/**
 * TrailManager class that handles calculations and delegates output to
 * ReportManager
 * 
 * @author Jason Stout
 */
public class TrailManager {

	/**
	 * Represents a list of adjacent landmarks
	 */
	private Map<Landmark, List<Trail>> adjacencyList;

	/**
	 * Represents the landmark returned when calling getLandmarkById()
	 */
	private Map<String, Landmark> idToLandmark;

	/**
	 * Represents the comparator to use when sorting landmarks
	 */
	private Comparator<Landmark> landmarkComparator;

	/**
	 * Constructor for the TrailManager class, delegates some exception handling to
	 * ReportManager
	 * 
	 * @param landmarkFile the file containing the list of landmarks
	 * @param trailFile    the file containing the list of trails
	 */
	public TrailManager(String landmarkFile, String trailFile) {
		DSAFactory.setMapType(DataStructure.SKIPLIST);
		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
		DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);

		this.landmarkComparator = (l1, l2) -> l1.getId().compareTo(l2.getId());
		this.adjacencyList = DSAFactory.getMap(landmarkComparator);
		this.idToLandmark = DSAFactory.getMap((s1, s2) -> s1.compareTo(s2));

		try {
			List<Landmark> landmarks = TrailInputReader.readLandmarks(landmarkFile);
			for (Landmark l : landmarks) {
				idToLandmark.put(l.getId(), l);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Landmark file not found: " + landmarkFile, e);
		}

		try {
			List<Trail> trails = TrailInputReader.readTrails(trailFile);
			for (Trail trail : trails) {
				addTrail(trail);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Trail file not found: " + trailFile, e);
		}
	}

	/**
	 * Helper method that adds valid trails to the list
	 * 
	 * @param trail trail to be added
	 */
	private void addTrail(Trail trail) {
		Landmark l1 = idToLandmark.get(trail.getLandmarkOne());
		Landmark l2 = idToLandmark.get(trail.getLandmarkTwo());

		addEdge(l1, trail);
		addEdge(l2, trail);
	}

	/**
	 * Helper method that adds edges to the adjacency list
	 * 
	 * @param landmark the landmark to be added
	 * @param trail    the trail to be added
	 */
	private void addEdge(Landmark landmark, Trail trail) {
		if (adjacencyList.get(landmark) == null) {
			adjacencyList.put(landmark, DSAFactory.getIndexedList());
		}
		adjacencyList.get(landmark).add(adjacencyList.get(landmark).size(), trail);
	}

	/**
	 * Algorithm that returns the distance between the origin landmark and each
	 * other valid landmark
	 * 
	 * @param originId the origin landmark id
	 * @return the list of distances
	 */
	public Map<Landmark, Integer> getDistancesToDestinations(String originId) {
		Landmark origin = idToLandmark.get(originId);
		Map<Landmark, Integer> distances = DSAFactory.getMap(landmarkComparator);

		if (origin == null)
			return distances;

		// Initialize distances for all landmarks
		for (Landmark landmark : idToLandmark.values()) {
			distances.put(landmark, Integer.MAX_VALUE);
		}
		distances.put(origin, 0);

		Map<Landmark, Landmark> parent = DSAFactory.getMap(landmarkComparator);
		DSAFactory.setListType(DataStructure.SINGLYLINKEDLIST);
		List<Landmark> queue = DSAFactory.getIndexedList();

		queue.add(0, origin);

		while (!queue.isEmpty()) {
			Landmark current = queue.remove(0);

			List<Trail> trails = adjacencyList.get(current);
			if (trails == null)
				continue;
			for (Trail trail : trails) {
				Landmark neighbor = getConnectedLandmark(trail, current);
				if (neighbor.equals(parent.get(current)))
					continue;
				int newDist = distances.get(current) + trail.getLength();
				if (newDist < distances.get(neighbor)) {
					distances.put(neighbor, newDist);
					queue.add(queue.size(), neighbor);
				}
			}

		}
		return distances;
	}

//	/**
//	 * Gets the smallest distance between landmarks
//	 * 
//	 * @param queue     list of landmarks to check for the minimum distance
//	 * @param distances the distance between each landmark
//	 * @return the minimum distance
//	 */
//	private Landmark extractMin(List<Landmark> queue, Map<Landmark, Integer> distances) {
//		int minIndex = -1;
//		int minDist = Integer.MAX_VALUE;
//		for (int i = 0; i < queue.size(); i++) {
//			Landmark node = queue.get(i);
//			int currentDist = distances.get(node);
//			if (currentDist < minDist) {
//				minDist = currentDist;
//				minIndex = i;
//			}
//		}
//		return minIndex != -1 ? queue.remove(minIndex) : null;
//	}

	/**
	 * Gets each connected landmark to the current one
	 * 
	 * @param trail   the trail between the current landmark and the landmark we are
	 *                checking
	 * @param current the current landmark
	 * @return valid trail
	 */
	private Landmark getConnectedLandmark(Trail trail, Landmark current) {
		return trail.getLandmarkOne().equals(current.getId()) ? idToLandmark.get(trail.getLandmarkTwo())
				: idToLandmark.get(trail.getLandmarkOne());
	}

	/**
	 * Gets a list of possible first aid stations given a minimum number of
	 * intersecting trails
	 * 
	 * @param numberOfIntersectingTrails the minimum number of intersecting trails
	 *                                   for a landmark to be considered a first aid
	 *                                   location
	 * @return a list of possible first aid locations
	 */
	public Map<Landmark, List<Trail>> getProposedFirstAidLocations(int numberOfIntersectingTrails) {
		Map<Landmark, List<Trail>> result = DSAFactory.getMap(landmarkComparator);
		for (Entry<Landmark, List<Trail>> entry : adjacencyList.entrySet()) {
			int degree = entry.getValue().size();
			if (degree >= numberOfIntersectingTrails) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}

	/**
	 * Returns the landmark given it's id
	 * 
	 * @param id id of the landmark
	 * @return the landmark
	 */
	public Landmark getLandmarkByID(String id) {
		return idToLandmark.get(id);
	}
}
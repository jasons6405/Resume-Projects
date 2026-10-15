package edu.ncsu.csc316.trail.manager;

import edu.ncsu.csc316.trail.data.Landmark;
import edu.ncsu.csc316.trail.data.Trail;
import edu.ncsu.csc316.trail.dsa.DSAFactory;
import edu.ncsu.csc316.trail.dsa.Algorithm;
import edu.ncsu.csc316.trail.dsa.DataStructure;
import edu.ncsu.csc316.dsa.data.Identifiable;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.sorter.Sorter;

import java.util.Comparator;

/**
 * Handles generating reports based off information received from the
 * TrailManager
 * 
 * @author Jason Stout
 */
public class ReportManager {

	/**
	 * TrailManager instance that ReportManager receives information from
	 */
	private TrailManager manager;

	/**
	 * Constructor for the ReportManager
	 * 
	 * @param landmarkFile the file containing a list of landmarks
	 * @param trailFile    the file containing a list of trails
	 */
	public ReportManager(String landmarkFile, String trailFile) {
		DSAFactory.setMapType(DataStructure.SKIPLIST);
		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
		DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
		this.manager = new TrailManager(landmarkFile, trailFile);
	}

	/**
	 * Generates a report of the distances of landmarks from the starting landmark
	 * 
	 * 
	 * @param originLandmark the starting landmark
	 * @return a report of distances if no errors are found
	 */
	public String getDistancesReport(String originLandmark) {

		if (manager.getLandmarkByID(originLandmark) == null) {
			return "The provided landmark ID (" + originLandmark + ") is invalid for the park.";
		}

		try {
			Map<Landmark, Integer> distances = manager.getDistancesToDestinations(originLandmark);

			if (distances == null) {
				return "The provided landmark ID (" + originLandmark + ") is invalid for the park.";
			}

			if (distances.isEmpty()) {
				Landmark origin = manager.getLandmarkByID(originLandmark);
				return "No landmarks are reachable from " + origin.getDescription() + " (" + originLandmark + ").";
			}

			List<Landmark> sorted = sortByDistanceAndDescription(distances);

			if (sorted.isEmpty()) {
				Landmark origin = manager.getLandmarkByID(originLandmark);
				return "No landmarks are reachable from " + origin.getDescription() + " (" + originLandmark + ").";
			}

			StringBuilder sb = new StringBuilder();
			sb.append("Landmarks Reachable from ").append(manager.getLandmarkByID(originLandmark).getDescription())
					.append(" (").append(originLandmark).append(") {\n");
			for (Landmark l : sorted) {
				int feet = distances.get(l);
				sb.append("   ").append(formatDistance(l, feet)).append("\n");
			}
			sb.append("}");
			return sb.toString();
		} catch (IllegalArgumentException e) {
			return "The provided landmark ID (" + e.getMessage() + ") is invalid for the park.";
		}
	}

	/**
	 * Sorts the distances based off distance and the description of the landmark
	 * 
	 * @param distances the map of distances from the origin landmark
	 * @return the sorted map of landmarks
	 */
	private List<Landmark> sortByDistanceAndDescription(Map<Landmark, Integer> distances) {
		List<Landmark> filtered = DSAFactory.getIndexedList();
		for (Entry<Landmark, Integer> entry : distances.entrySet()) {
			if (entry.getValue() != Integer.MAX_VALUE && entry.getValue() != 0) {
				filtered.add(filtered.size(), entry.getKey());
			}
		}

		List<Landmark> sortedByDescription = sort(filtered, Comparator.comparing(Landmark::getDescription));

		LandmarkWrapper[] array = new LandmarkWrapper[sortedByDescription.size()];
		for (int i = 0; i < sortedByDescription.size(); i++) {
			Landmark l = sortedByDescription.get(i);
			array[i] = new LandmarkWrapper(l, distances.get(l));
		}

		Sorter<LandmarkWrapper> sorter = DSAFactory.getNonComparisonSorter();
		sorter.sort(array);

		List<Landmark> result = DSAFactory.getIndexedList();
		for (LandmarkWrapper wrapper : array) {
			result.add(result.size(), wrapper.getLandmark());
		}

		return result;
	}

	/**
	 * Formats the distances displayed in the report
	 * 
	 * @param l    current landmark
	 * @param feet the distance between landmarks in feet
	 * @return formatted distance
	 */
	private String formatDistance(Landmark l, int feet) {
		if (feet > 5280) {
			double miles = Math.round(feet / 5280.0) * 100.0 / 100.0;
			return String.format("%d feet (%.2f miles) to %s (%s)", feet, miles, l.getDescription(), l.getId());
		}
		return String.format("%d feet to %s (%s)", feet, l.getDescription(), l.getId());
	}

	/**
	 * Returns a list of proposed first aid locations based on the given number of
	 * intersecting trails
	 * 
	 * @param numberOfIntersectingTrails the minimum number of intersecting trails
	 *                                   between landmarks to be considered for a
	 *                                   first aid station
	 * @return the list of proposed first aid stations
	 */
	public String getProposedFirstAidLocations(int numberOfIntersectingTrails) {
		if (numberOfIntersectingTrails <= 0) {
			return "Number of intersecting trails must be greater than 0.";
		}

		Map<Landmark, List<Trail>> locations = manager.getProposedFirstAidLocations(numberOfIntersectingTrails);
		if (locations.isEmpty()) {
			return String.format("No landmarks have at least %d intersecting trails.", numberOfIntersectingTrails);
		}

		List<Landmark> sorted = sortByTrailCountAndDescription(locations);
		return buildFirstAidString(sorted, locations);
	}

	/**
	 * Sorts the distances based off the number of trails and the description
	 * 
	 * @param locations the map of locations to be sorted
	 * @return the sorted map of landmarks
	 */
	private List<Landmark> sortByTrailCountAndDescription(Map<Landmark, List<Trail>> locations) {
		List<Landmark> keys = DSAFactory.getIndexedList();
		Map<Landmark, Integer> counts = DSAFactory.getMap(Comparator.comparing(Landmark::getDescription));

		for (Entry<Landmark, List<Trail>> entry : locations.entrySet()) {
			keys.add(keys.size(), entry.getKey());
			counts.put(entry.getKey(), entry.getValue().size());
		}

		List<Landmark> sortedByDescription = sort(keys, Comparator.comparing(Landmark::getDescription));

		int maxCount = 0;
		for (int count : counts.values()) {
			if (count > maxCount) {
				maxCount = count;
			}
		}

		LandmarkWrapper[] array = new LandmarkWrapper[sortedByDescription.size()];
		for (int i = 0; i < sortedByDescription.size(); i++) {
			Landmark l = sortedByDescription.get(i);
			array[i] = new LandmarkWrapper(l, maxCount - counts.get(l));
		}

		Sorter<LandmarkWrapper> sorter = DSAFactory.getNonComparisonSorter();
		sorter.sort(array);

		List<Landmark> result = DSAFactory.getIndexedList();
		for (LandmarkWrapper wrapper : array) {
			result.add(result.size(), wrapper.getLandmark());
		}

		return result;
	}

	/**
	 * Builds the string of proposed first aid stations
	 * 
	 * @param sorted    the sorted list of locations
	 * @param locations the original map of all locations
	 * @return the string included in the first aid report
	 */
	private String buildFirstAidString(List<Landmark> sorted, Map<Landmark, List<Trail>> locations) {
		StringBuilder sb = new StringBuilder("Proposed Locations for First Aid Stations {\n");
		for (Landmark l : sorted) {
			sb.append("   ").append(l.getDescription()).append(" (").append(l.getId()).append(") - ")
					.append(locations.get(l).size()).append(" intersecting trails\n");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Default sorting method if no tie-breakers are needed
	 * 
	 * @param list       the list of landmarks to be sorted
	 * @param comparator the comparator used in sorting the list
	 * @return the sorted list
	 */
	private List<Landmark> sort(List<Landmark> list, Comparator<Landmark> comparator) {
		Landmark[] array = new Landmark[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}

		DSAFactory.getComparisonSorter(comparator).sort(array);

		List<Landmark> sorted = DSAFactory.getIndexedList();
		for (int i = 0; i < array.length; i++) {
			sorted.add(i, array[i]);
		}
		return sorted;
	}

	/**
	 * Wraps a Landmark to implement Identifiable for counting sort.
	 */
	public class LandmarkWrapper implements Identifiable {

		/**
		 * The landmark to be wrapped
		 */
		private Landmark landmark;

		/**
		 * integer key for sorting
		 */
		private int key;

		/**
		 * Constructs a LandmarkWrapper.
		 * 
		 * @param landmark the landmark to wrap
		 * @param key      the integer key for sorting (distance or trail count)
		 */
		public LandmarkWrapper(Landmark landmark, int key) {
			this.landmark = landmark;
			this.key = key;
		}

		@Override
		public int getId() {
			return key;
		}

		/**
		 * Returns the wrapped landmark.
		 * 
		 * @return wrapped landmark
		 */
		public Landmark getLandmark() {
			return landmark;
		}
	}
}

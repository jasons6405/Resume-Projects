package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Pivot selection strategy that uses the element at the first index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the last index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the middle index each time
	 * a pivot must be selected
	 */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();

	/**
	 * Pivot selection strategy that uses the element at a randomly-chosen index
	 * each time a pivot must be selected
	 */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	/**
	 * Selector that is used to determine the pivot point
	 */
	private PivotSelector selector;

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and a
	 * specified PivotSelector strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 * @param selector   the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Constructs a new QuickSorter using the natural ordering of elements. Pivots
	 * are selected using the provided PivotSelector strategy
	 * 
	 * @param selector the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and the
	 * default random pivot selection strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Constructs a new QuickSorter that uses an element's natural ordering and uses
	 * the random pivot selection strategy
	 */
	public QuickSorter() {
		this(null, null);
	}

	private void setSelector(PivotSelector selector) {
		if (selector == null) {
			this.selector = new RandomElementSelector();
		} else {
			this.selector = selector;
		}
	}

	@Override
	public void sort(E[] data) {
		if (data == null || data.length < 2) {
			return;
		}
		quickSort(data, 0, data.length - 1);

	}

	/**
	 * Defines the behaviors of a PivotSelector
	 * 
	 * @author Dr. King
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * FirstElementSelector chooses the first index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return low;
		}
	}

	/**
	 * LastElementSelector chooses the last index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Jason Stout
	 *
	 */
	public static class LastElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
	}

	/**
	 * MiddleElementSelector chooses the middle index of the array as the index of
	 * the pivot element that should be used when sorting
	 * 
	 * @author Jason Stout
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}
	}

	/**
	 * RandomElementSelector chooses a random index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Jason Stout
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {
		/**
		 * Random number selector
		 */
		private final Random rand = new Random();

		@Override
		public int selectPivot(int low, int high) {
			return rand.nextInt(high - low + 1) + low;
		}
	}

	/**
	 * Recursively sorts the portion of the array between the given indices using
	 * the QuickSort algorithm
	 *
	 * @param data the array to be sorted
	 * @param low  the starting index of the portion to be sorted
	 * @param high the ending index of the portion to be sorted
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}

	/**
	 * Partitions the array around a pivot element selected by the current pivot.
	 * Elements smaller than or equal to the pivot are placed before it, and
	 * elements greater than the pivot are placed after it.
	 *
	 * @param data the array to partition
	 * @param low  the starting index of the partition
	 * @param high the ending index of the partition
	 * @return the final index of the pivot element after partitioning
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}

	/**
	 * Helper method that partitions the array using the last element as the pivot
	 * 
	 * @param data the array to partition
	 * @param low  the starting index of the partition
	 * @param high the ending index of the partition
	 * @return the final index of the pivot element after partitioning
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int separator = low;
		for (int j = low; j < high; j++) {
			if (compare(data[j], pivot) <= 0) {
				swap(data, separator, j);
				separator++;
			}
		}
		swap(data, separator, high);
		return separator;
	}

	/**
	 * Swaps two elements in the given array
	 *
	 * @param data   the array containing elements to swap
	 * @param index1 the index of the first element
	 * @param index2 the index of the second element
	 */
	private void swap(E[] data, int index1, int index2) {
		E temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}
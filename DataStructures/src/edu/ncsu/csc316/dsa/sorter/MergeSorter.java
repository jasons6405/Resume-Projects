package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs a new MergeSorter with a specified custom Comparator
	 * 
	 * @param comparator a custom Comparator to use when sorting
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructs a new MergeSorter with comparisons based on the element's natural
	 * ordering
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {
		if (data == null || data.length < 2) {
			return;
		}
		mergeSort(data);

	}

	/**
	 * Recursively applies merge sort on the given array.
	 * 
	 * @param data the array to sort
	 */
	private void mergeSort(E[] data) {
		int n = data.length;
		if (n < 2) {
			return;
		}

		int mid = n / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, n);

		mergeSort(left);
		mergeSort(right);
		merge(left, right, data);
	}

	/**
	 * Merges two sorted subarrays into a single sorted array.
	 * 
	 * @param left  the left half
	 * @param right the right half
	 * @param data  the original array where the merged elements will be placed
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIdx = 0;
		int rightIdx = 0;
		int mergedIdx = 0;

		while (leftIdx < left.length && rightIdx < right.length) {
			if (compare(left[leftIdx], right[rightIdx]) <= 0) {
				data[mergedIdx++] = left[leftIdx++];
			} else {
				data[mergedIdx++] = right[rightIdx++];
			}
		}

		while (leftIdx < left.length) {
			data[mergedIdx++] = left[leftIdx++];
		}

		while (rightIdx < right.length) {
			data[mergedIdx++] = right[rightIdx++];
		}
	}

}
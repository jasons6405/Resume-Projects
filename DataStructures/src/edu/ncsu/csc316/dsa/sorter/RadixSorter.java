package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Sorting algorithm for RadixSorter
	 * 
	 * @param data the data to be sorted
	 */
	@Override
	public void sort(E[] data) {
		if (data == null || data.length == 0) {
			return;
		}

		// Find the max ID
		int max = 0;
		for (E element : data) {
			max = Math.max(max, element.getId());
		}

		// Determine number of digits
		int k = 1;
		while (max / k > 0) {
			countingSortByDigit(data, k);
			k *= 10;
		}
	}

	/**
	 * Helper method for the sorting algorithm
	 * 
	 * @param data the data to be sorted
	 * @param k    k
	 */
	private void countingSortByDigit(E[] data, int k) {
		int n = data.length;
		@SuppressWarnings("unchecked")
		E[] output = (E[]) new Identifiable[n];
		int[] count = new int[10];

		for (E element : data) {
			count[element.getId() / k % 10]++;
		}

		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {
			int digit = data[i].getId() / k % 10;
			output[count[digit] - 1] = data[i];
			count[digit]--;
		}

		for (int i = 0; i < n; i++) {
			data[i] = output[i];
		}
	}

}

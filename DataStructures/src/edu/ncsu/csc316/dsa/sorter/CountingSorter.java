package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Sorting algorithm for CountingSorter
	 * 
	 * @param data the data to be sorted
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort(E[] data) {
		if (data == null || data.length == 0) {
			return;
		}

		int min = data[0].getId();
		int max = data[0].getId();

		for (E element : data) {
			min = Math.min(min, element.getId());
			max = Math.max(max, element.getId());
		}

		int range = max - min + 1;
		int[] count = new int[range];

		for (E element : data) {
			count[element.getId() - min]++;
		}

		for (int i = 1; i < range; i++) {
			count[i] += count[i - 1];
		}

		E[] sorted = (E[]) new Identifiable[data.length];
		for (int i = data.length - 1; i >= 0; i--) {
			int id = data[i].getId();
			sorted[count[id - min] - 1] = data[i];
			count[id - min]--;
		}

		for (int i = 0; i < data.length; i++) {
			data[i] = sorted[i];
		}

	}

}

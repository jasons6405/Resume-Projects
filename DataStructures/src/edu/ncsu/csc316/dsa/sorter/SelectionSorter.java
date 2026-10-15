package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor for SelectionSorter when given a comparator to pass through the
	 * superclass
	 * 
	 * @param comparator the comparator to be set
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * SelectionSorter constructor for when given no comparator
	 */
	public SelectionSorter() {
		super(null);
	}

	/**
	 * Sorting algorithm for SelectionSorter
	 * 
	 * @param data the data to be sorted
	 */
	public void sort(E[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (compare(data[j], data[min]) < 0) {
					min = j;
				}
			}

			if (i != min) {
				E temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
	}
}

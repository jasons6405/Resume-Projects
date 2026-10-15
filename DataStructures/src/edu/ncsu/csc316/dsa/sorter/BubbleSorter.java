package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter class that sorts objects using the bubble sort algorithm
 * 
 * @param <E> Generic type parameter for sorting
 * @author Jason Stout
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructs the comparator to be used based on superclass
	 * 
	 * @param comparator the comparator to be set based on superclass
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructs comparator if no comparator is specified
	 */
	public BubbleSorter() {
		super(null);
	}

	/**
	 * Sorting algorithm for bubble sort
	 * 
	 * @param data the data to be sorted
	 */
	public void sort(E[] data) {
		boolean r = true;
		while (r) {
			r = false;
			for (int i = 1; i < data.length; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E temp = data[i - 1];
					data[i - 1] = data[i];
					data[i] = temp;

					r = true;
				}
			}
		}
	}

}

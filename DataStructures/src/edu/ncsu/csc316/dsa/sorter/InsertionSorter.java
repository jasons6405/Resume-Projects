package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Jason Stout
 * 
 * @param <E> generic type parameter for sorting objects
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor for InsertionSorter when given a comparator to construct through
	 * the superclass
	 * 
	 * @param comparator the comparator to set through the superclass
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);

	}

	/**
	 * Constructs a comparator using natural ordering through superclass
	 */
	public InsertionSorter() {
		super(null);
	}

	/**
	 * Sorting algorithm for InsertionSorter class
	 * 
	 * @param data the data to sort
	 */
	public void sort(E[] data) {
		for (int i = 1; i <= data.length - 1; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;
		}
	}
}

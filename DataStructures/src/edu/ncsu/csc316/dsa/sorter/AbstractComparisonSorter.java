package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract comparison class that contains shared functionality for sorting
 * comparators
 * 
 * @param <E> generic type parameter
 * @author Jason Stout
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/**
	 * Comparator object for deciding comparison method
	 */
	private Comparator<E> comparator;

	/**
	 * Constructor for comparison sorter that sets the comparator based on
	 * setComparator logic
	 * 
	 * @param comparator comparator to be set
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}

	/**
	 * Sets the comparator, making it the natural order if its null
	 * 
	 * @param comparator the comparator we are setting
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			this.comparator = new NaturalOrder();
		} else {
			this.comparator = comparator;
		}
	}

	/**
	 * Defines the natural ordering for the sorting algorithms
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}

	/**
	 * Compares two objects using the comparator
	 * 
	 * @param first  first object to compare
	 * @param second second object to compare
	 * @return comparison
	 */
	public int compare(E first, E second) {
		return comparator.compare(first, second);
	}
}

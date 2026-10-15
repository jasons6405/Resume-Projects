package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * 
 * @author Dr. King
 * @author Jason Stout
 * 
 * @param <E> generic type parameter
 */
public interface Sorter<E> {

	/**
	 * basic sort method to be shared
	 * 
	 * @param data the data to be sorted
	 */
	void sort(E[] data);
}

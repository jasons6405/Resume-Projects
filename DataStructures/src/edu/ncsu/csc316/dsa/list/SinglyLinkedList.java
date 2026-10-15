package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	private static class LinkedListNode<E> {

		/**
		 * Generic element in the list
		 */
		private E element;

		/**
		 * Reference to the next node in the list
		 */
		private LinkedListNode<E> next;

		/**
		 * Constructs a new node with the given element.
		 * 
		 * @param element the element stored in the node
		 */
		public LinkedListNode(E element) {
			this.element = element;
			this.next = null;
		}
	}

	/** A reference to the dummy/sentinel node at the front of the list **/
	private LinkedListNode<E> front;

	/** A reference to the last/final node in the list **/
	private LinkedListNode<E> tail;

	/** The number of elements stored in the list **/
	private int size;

	/**
	 * Constructs an empty singly-linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null); // Dummy node
		tail = null;
		size = 0;
	}

	/**
	 * Adds the element to the given index
	 */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		if (element == null) {
			throw new NullPointerException("Element cannot be null");
		}

		LinkedListNode<E> newNode = new LinkedListNode<>(element);

		if (index == size) {
			if (tail == null) {
				front.next = newNode;
			} else {
				tail.next = newNode;
			}
			tail = newNode;
		} else {
			LinkedListNode<E> prev = getNodeAt(index - 1);
			newNode.next = prev.next;
			prev.next = newNode;
		}

		size++;
	}

	/**
	 * Returns the element at the given index
	 * 
	 * @return the element at the given node
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		return getNodeAt(index).element;
	}

	/**
	 * Removes the element at the given index
	 * 
	 * @return the element removed
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);

		LinkedListNode<E> prev = getNodeAt(index - 1);
		LinkedListNode<E> toRemove = prev.next;

		prev.next = toRemove.next;

		if (toRemove == tail) {
			tail = prev == front ? null : prev;
		}

		size--;
		return toRemove.element;
	}

	/**
	 * Changes the element at the given index to the new element
	 * 
	 * @return the old element that was changed
	 */
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		if (element == null) {
			throw new NullPointerException("Element cannot be null");
		}

		LinkedListNode<E> node = getNodeAt(index);
		E oldElement = node.element;
		node.element = element;
		return oldElement;
	}

	/**
	 * Returns the current size of the list
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Iterates through the list using ElementIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Helper method to get the node at a specific index. Assumes the index is valid
	 * since index checks are handled by other methods.
	 * 
	 * @param index the index of the node
	 * @return the node at the specified index
	 */
	private LinkedListNode<E> getNodeAt(int index) {
		LinkedListNode<E> current = front;
		for (int i = -1; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private class ElementIterator implements Iterator<E> {
		/**
		 * Keep track of the next node that will be processed
		 */
		private LinkedListNode<E> current;

		/**
		 * Construct a new element iterator where the cursor is initialized to the
		 * beginning of the list.
		 */
		public ElementIterator() {
			this.current = front.next;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E element = current.element;
			current = current.next;
			return element;
		}

		@Override
		public void remove() {
			// DO NOT CHANGE THIS METHOD
			throw new UnsupportedOperationException(
					"This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
		}
	}
}

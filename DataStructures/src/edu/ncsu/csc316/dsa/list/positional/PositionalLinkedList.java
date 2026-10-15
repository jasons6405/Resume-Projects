package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	/** A dummy/sentinel node representing at the front of the list **/
	private PositionalNode<E> front;

	/** A dummy/sentinel node representing at the end/tail of the list **/
	private PositionalNode<E> tail;

	/** The number of elements in the list **/
	private int size;

	/**
	 * Constructs an empty positional linked list
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

	private static class PositionalNode<E> implements Position<E> {

		/**
		 * Reference to the current element
		 */
		private E element;

		/**
		 * Reference to the next node
		 */
		private PositionalNode<E> next;

		/**
		 * Reference to the previous node
		 */
		private PositionalNode<E> previous;

		public PositionalNode(E value) {
			this(value, null);
		}

		public PositionalNode(E value, PositionalNode<E> next) {
			this(value, next, null);
		}

		public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
			setElement(value);
			setNext(next);
			setPrevious(prev);
		}

		public void setPrevious(PositionalNode<E> prev) {
			previous = prev;
		}

		public PositionalNode<E> getPrevious() {
			return previous;
		}

		public void setNext(PositionalNode<E> next) {
			this.next = next;
		}

		public PositionalNode<E> getNext() {
			return next;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node.getNext(), node);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		return addBetween(element, node, node.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		return node.getNext() == tail ? null : node.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		return node.getPrevious() == front ? null : node.getPrevious();
	}

	@Override
	public Position<E> first() {
		return isEmpty() ? null : front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		return isEmpty() ? null : tail.getPrevious();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> prev = node.getPrevious();
		PositionalNode<E> next = node.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
		E element = node.getElement();
		node.setNext(null);
		node.setPrevious(null);
		node.setElement(null);
		return element;
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E old = node.getElement();
		node.setElement(element);
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
		if (p instanceof PositionalNode) {
			return (PositionalNode<E>) p;
		}
		throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> newNode = new PositionalNode<>(element, next, prev);
		prev.setNext(newNode);
		next.setPrevious(newNode);
		size++;
		return newNode;
	}

	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * Sets the current node to the next
		 */
		private Position<E> current = front.getNext();

		/**
		 * Sets the previous node to null
		 */
		private Position<E> previous = null;

		/**
		 * Initialize removeOK to false
		 */
		private boolean removeOK = false;

		@Override
		public boolean hasNext() {
			return current != tail;
		}

		@Override
		public Position<E> next() {
			if (!hasNext())
				throw new NoSuchElementException("No more elements");
			previous = current;
			current = validate(current).getNext();
			removeOK = true;
			return previous;
		}

		@Override
		public void remove() {
			if (!removeOK)
				throw new IllegalStateException("Illegal state to remove");
			PositionalLinkedList.this.remove(previous);
			removeOK = false;
		}
	}

	private class ElementIterator implements Iterator<E> {

		/**
		 * Creates a new iterator
		 */
		private Iterator<Position<E>> it = new PositionIterator();

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}

		@Override
		public void remove() {
			it.remove();
		}
	}

	private class PositionIterable implements Iterable<Position<E>> {
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}

}
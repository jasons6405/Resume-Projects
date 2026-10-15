package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil provides a collection of behaviors for computing
 * minimum spanning trees for a given graph.
 * 
 * The MinimumSpanningTreeUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class MinimumSpanningTreeUtil {

	/**
	 * Returns a positional list of minimum spanning tree edges for the given graph
	 * using Kruskal's minimum spanning tree algorithm.
	 * 
	 * @param <V> the type of data in the graph vertices
	 * @param <E> the type of data in the graph edges
	 * @param g   the graph for which to compute a minimum spanning tree
	 * @return a positional list of minimum spanning tree edges
	 */
	public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
		PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
		PriorityQueue<Integer, Edge<E>> pq = new HeapPriorityQueue<>();

		// Populate the priority queue with edges sorted by weight
		for (Edge<E> e : g.edges()) {
			pq.insert(e.getElement().getWeight(), e);
		}

		// Initialize Union-Find (Disjoint Set Forest)
		Map<Vertex<V>, Vertex<V>> parent = new LinearProbingHashMap<>();
		Map<Vertex<V>, Integer> rank = new LinearProbingHashMap<>();
		for (Vertex<V> v : g.vertices()) {
			parent.put(v, v);
			rank.put(v, 0);
		}

		int components = g.numVertices();
		while (components > 1 && !pq.isEmpty()) {
			Entry<Integer, Edge<E>> entry = pq.deleteMin();
			Edge<E> edge = entry.getValue();
			Vertex<V>[] endpoints = g.endVertices(edge);
			Vertex<V> u = endpoints[0];
			Vertex<V> v = endpoints[1];

			Vertex<V> rootU = find(u, parent);
			Vertex<V> rootV = find(v, parent);

			if (rootU != rootV) {
				tree.addLast(edge);
				union(rootU, rootV, parent, rank);
				components--;
			}
		}
		return tree;
	}

	/**
	 * Returns a positional list of minimum spanning tree edges for the given graph
	 * using Prim-Jarnik's minimum spanning tree algorithm.
	 * 
	 * @param <V> the type of data in the graph vertices
	 * @param <E> the type of data in the graph edges
	 * @param g   the graph for which to compute a minimum spanning tree
	 * @return a positional list of minimum spanning tree edges
	 */
	public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
		AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
		Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
		Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();

		PositionalList<Edge<E>> tree = new PositionalLinkedList<>();

		Vertex<V> src = g.vertices().iterator().next();

		for (Vertex<V> v : g.vertices()) {
			if (v == src) {
				weights.put(v, 0);
			} else {
				weights.put(v, Integer.MAX_VALUE);
			}
			pqEntries.put(v, q.insert(weights.get(v), v));
		}
		while (!q.isEmpty()) {
			Entry<Integer, Vertex<V>> entry = q.deleteMin();
			Vertex<V> u = entry.getValue();
			if (connectingEdges.get(u) != null) {
				tree.addLast(connectingEdges.get(u));
			}
			known.add(u);
			for (Edge<E> e : g.outgoingEdges(u)) {
				Vertex<V> z = g.opposite(u, e);
				int r = e.getElement().getWeight();
				if (!known.contains(z) && r < weights.get(z)) {
					weights.put(z, r);
					connectingEdges.put(z, e);
					q.replaceKey(pqEntries.get(z), r);
				}
			}
		}
		return tree;
	}

	/**
	 * Private helper method for finding a vertex
	 * 
	 * @param <V>    the vertex type parameter
	 * @param x      the vertex we are finding
	 * @param parent the parent of the vertex
	 * @return the vertex
	 */
	private static <V> Vertex<V> find(Vertex<V> x, Map<Vertex<V>, Vertex<V>> parent) {
		if (!parent.get(x).equals(x)) {
			parent.put(x, find(parent.get(x), parent));
		}
		return parent.get(x);
	}

	/**
	 * Private helper method for performing the union behavior
	 * 
	 * @param <V>    the vertex type parameter
	 * @param x      the first vertex we are performing union on
	 * @param y      the second vertex we are performing union on
	 * @param parent the parent of the vertex
	 * @param rank   the rank of the vertex
	 */
	private static <V> void union(Vertex<V> x, Vertex<V> y, Map<Vertex<V>, Vertex<V>> parent,
			Map<Vertex<V>, Integer> rank) {
		Vertex<V> rootX = find(x, parent);
		Vertex<V> rootY = find(y, parent);
		if (rootX == rootY)
			return;

		if (rank.get(rootX) > rank.get(rootY)) {
			parent.put(rootY, rootX);
		} else {
			parent.put(rootX, rootY);
			if (rank.get(rootX).equals(rank.get(rootY))) {
				rank.put(rootY, rank.get(rootY) + 1);
			}
		}
	}

}
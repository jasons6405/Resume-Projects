package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil Checks the expected outputs of Dijksra's
 * algorithm and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class ShortestPathUtilTest {

	/**
	 * Test the output of Dijkstra's algorithm
	 */
	@SuppressWarnings("unused")
	@Test
	public void testDijkstra() {
		Graph<String, Weighted> graph = new AdjacencyListGraph<>();
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");

		Edge<Weighted> ab = graph.insertEdge(a, b, new Weighted() {
			@Override
			public int getWeight() {
				return 1;
			}
		});
		Edge<Weighted> bc = graph.insertEdge(b, c, new Weighted() {
			@Override
			public int getWeight() {
				return 2;
			}
		});
		Edge<Weighted> ac = graph.insertEdge(a, c, new Weighted() {
			@Override
			public int getWeight() {
				return 4;
			}
		});

		Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(graph, a);
		assertEquals(0, (int) distances.get(a));
		assertEquals(1, (int) distances.get(b));
		assertEquals(3, (int) distances.get(c));
	}

	/**
	 * Test the output of the shortest path tree construction method
	 */
	@Test
	public void testShortestPathTree() {
		Graph<String, Weighted> graph = new AdjacencyListGraph<>();
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");

		Edge<Weighted> ab = graph.insertEdge(a, b, new Weighted() {
			@Override
			public int getWeight() {
				return 1;
			}
		});
		Edge<Weighted> bc = graph.insertEdge(b, c, new Weighted() {
			@Override
			public int getWeight() {
				return 2;
			}
		});
		graph.insertEdge(a, c, new Weighted() {
			@Override
			public int getWeight() {
				return 4;
			}
		});

		Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(graph, a);
		Map<Vertex<String>, Edge<Weighted>> tree = ShortestPathUtil.shortestPathTree(graph, a, distances);
		assertEquals(ab, tree.get(b));
		assertEquals(bc, tree.get(c));
	}

}
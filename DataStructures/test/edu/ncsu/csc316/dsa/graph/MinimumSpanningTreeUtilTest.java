package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil Checks the expected outputs of
 * Prim-Jarnik's algorithm and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class MinimumSpanningTreeUtilTest {

	/**
	 * Test the output of Prim-Jarnik's algorithm
	 */
	@Test
	public void testPrimJarnik() {
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

		PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.primJarnik(graph);
		assertEquals(2, mst.size());
		assertTrue(containsEdge(mst, ab));
		assertTrue(containsEdge(mst, bc));
	}

	/**
	 * Test the output of Kruskal's algorithm
	 */
	@Test
	public void testKruskal() {
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

		PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.kruskal(graph);
		assertEquals(2, mst.size());
		assertTrue(containsEdge(mst, ab));
		assertTrue(containsEdge(mst, bc));
	}

	private boolean containsEdge(PositionalList<Edge<Weighted>> list, Edge<Weighted> edge) {
		for (Edge<Weighted> e : list) {
			if (e == edge)
				return true;
		}
		return false;
	}

}
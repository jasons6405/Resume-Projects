package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil Checks the expected outputs of depth first
 * search and breadth first search
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class GraphTraversalUtilTest {

	/**
	 * Test the output of depth first search on a graph
	 */
	@Test
	public void testDepthFirstSearch() {
		Graph<String, Integer> graph = new EdgeListGraph<>();
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");

		Edge<Integer> ab = graph.insertEdge(a, b, 1);
		Edge<Integer> ac = graph.insertEdge(a, c, 2);
		Edge<Integer> bd = graph.insertEdge(b, d, 3);

		Map<Vertex<String>, Edge<Integer>> dfsForest = GraphTraversalUtil.depthFirstSearch(graph, a);

		assertEquals(3, dfsForest.size());
		assertEquals(ab, dfsForest.get(b));
		assertEquals(ac, dfsForest.get(c));
		assertEquals(bd, dfsForest.get(d));
	}

	/**
	 * Test the output of the breadth first search
	 */
	@Test
	public void testBreadthFirstSearch() {
		Graph<String, Integer> graph = new EdgeListGraph<>();
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");

		Edge<Integer> ab = graph.insertEdge(a, b, 1);
		Edge<Integer> ac = graph.insertEdge(a, c, 2);
		Edge<Integer> bd = graph.insertEdge(b, d, 3);

		Map<Vertex<String>, Edge<Integer>> bfsForest = GraphTraversalUtil.breadthFirstSearch(graph, a);

		assertEquals(3, bfsForest.size());
		assertEquals(ab, bfsForest.get(b));
		assertEquals(ac, bfsForest.get(c));
		assertEquals(bd, bfsForest.get(d));
	}

}
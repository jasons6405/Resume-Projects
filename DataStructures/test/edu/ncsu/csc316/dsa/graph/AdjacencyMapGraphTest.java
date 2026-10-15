package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for AdjacencyMapGraph. Checks the expected outputs of the Graph
 * abstract data type behaviors when using an adjacency map graph data
 * structure.
 *
 * @author Dr. King
 * @author Jason Stout
 *
 */
public class AdjacencyMapGraphTest {

	/**
	 * The undirectedGraph we use for testing
	 */
	private Graph<String, Integer> undirectedGraph;

	/**
	 * The directedGraph we use for testing
	 */
	private Graph<String, Integer> directedGraph;

	/**
	 * Create a new instance of an adjacency map graph before each test case
	 * executes
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyMapGraph<String, Integer>();
		directedGraph = new AdjacencyMapGraph<String, Integer>(true);
	}

	private void buildUndirectedSample() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);
	}

	private void buildDirectedSample() {
		Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = directedGraph.insertVertex("Asheville");
		Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = directedGraph.insertVertex("Durham");
		Vertex<String> v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");

		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);
	}

	/**
	 * Test the output of the numVertices() behavior
	 */
	@Test
	public void testNumVertices() {
		buildUndirectedSample();
		assertEquals(5, undirectedGraph.numVertices());

		buildDirectedSample();
		assertEquals(6, directedGraph.numVertices());
	}

	/**
	 * Test the output of the vertices() behavior
	 */
	@Test
	public void testVertices() {
		// We cannot call buildUndirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		undirectedGraph.insertEdge(v1, v2, 5);
		undirectedGraph.insertEdge(v1, v3, 10);
		undirectedGraph.insertEdge(v1, v4, 15);
		undirectedGraph.insertEdge(v1, v5, 20);
		undirectedGraph.insertEdge(v2, v3, 25);
		undirectedGraph.insertEdge(v2, v4, 30);
		undirectedGraph.insertEdge(v2, v5, 35);
		undirectedGraph.insertEdge(v3, v4, 40);
		undirectedGraph.insertEdge(v3, v5, 45);
		undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(5, undirectedGraph.numVertices());
		Iterable<Vertex<String>> vertices = undirectedGraph.vertices();
		int count = 0;
		for (@SuppressWarnings("unused")
		Vertex<String> v : vertices) {
			count++;
		}
		assertEquals(5, count);

		// DIRECTED
		// We cannot call buildDirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		directedGraph.insertEdge(v1, v2, 5);
		directedGraph.insertEdge(v1, v3, 10);
		directedGraph.insertEdge(v1, v4, 15);
		directedGraph.insertEdge(v1, v5, 20);
		directedGraph.insertEdge(v2, v3, 25);
		directedGraph.insertEdge(v2, v4, 30);
		directedGraph.insertEdge(v2, v5, 35);
		directedGraph.insertEdge(v3, v4, 40);
		directedGraph.insertEdge(v3, v5, 45);
		directedGraph.insertEdge(v4, v5, 50);
		directedGraph.insertEdge(v5, v6, 55);

		assertEquals(6, directedGraph.numVertices());
		vertices = directedGraph.vertices();
		count = 0;
		for (@SuppressWarnings("unused")
		Vertex<String> v : vertices) {
			count++;
		}
		assertEquals(6, count);
	}

	/**
	 * Test the output of the numEdges() behavior
	 */
	@Test
	public void testNumEdges() {
		buildUndirectedSample();
		assertEquals(10, undirectedGraph.numEdges());

		buildDirectedSample();
		assertEquals(11, directedGraph.numEdges());
	}

	/**
	 * Test the output of the edges() behavior
	 */
	@SuppressWarnings("unused")
	@Test
	public void testEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(10, undirectedGraph.numEdges());
		int edgeCount = 0;
		for (Edge<Integer> e : undirectedGraph.edges()) {
			edgeCount++;
		}
		assertEquals(10, edgeCount);

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(11, directedGraph.numEdges());
		edgeCount = 0;
		for (Edge<Integer> e : directedGraph.edges()) {
			edgeCount++;
		}
		assertEquals(11, edgeCount);
	}

	/**
	 * Test the output of the getEdge(v1,v2) behavior
	 */
	@Test
	public void testGetEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(e1, undirectedGraph.getEdge(v1, v2));
		assertEquals(e1, undirectedGraph.getEdge(v2, v1));
		assertEquals(e2, undirectedGraph.getEdge(v1, v3));
		assertEquals(e3, undirectedGraph.getEdge(v1, v4));
		assertEquals(e4, undirectedGraph.getEdge(v1, v5));
		assertEquals(e5, undirectedGraph.getEdge(v2, v3));
		assertEquals(e6, undirectedGraph.getEdge(v2, v4));
		assertEquals(e7, undirectedGraph.getEdge(v2, v5));
		assertEquals(e8, undirectedGraph.getEdge(v3, v4));
		assertEquals(e9, undirectedGraph.getEdge(v3, v5));
		assertEquals(e10, undirectedGraph.getEdge(v4, v5));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(e1, directedGraph.getEdge(v1, v2));
		assertNull(directedGraph.getEdge(v2, v1));
		assertEquals(e2, directedGraph.getEdge(v1, v3));
		assertEquals(e3, directedGraph.getEdge(v1, v4));
		assertEquals(e4, directedGraph.getEdge(v1, v5));
		assertEquals(e5, directedGraph.getEdge(v2, v3));
		assertEquals(e6, directedGraph.getEdge(v2, v4));
		assertEquals(e7, directedGraph.getEdge(v2, v5));
		assertEquals(e8, directedGraph.getEdge(v3, v4));
		assertEquals(e9, directedGraph.getEdge(v3, v5));
		assertEquals(e10, directedGraph.getEdge(v4, v5));
		assertEquals(e11, directedGraph.getEdge(v5, v6));
	}

	/**
	 * Test the output of the endVertices(e) behavior
	 */
	@Test
	public void testEndVertices() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		Vertex<String>[] ends = undirectedGraph.endVertices(e1);
		assertTrue((ends[0] == v1 && ends[1] == v2) || (ends[0] == v2 && ends[1] == v1));
		Vertex<String>[] endsE2 = undirectedGraph.endVertices(e2);
		assertTrue((endsE2[0] == v1 && endsE2[1] == v3) || (endsE2[0] == v3 && endsE2[1] == v1));
		Vertex<String>[] endsE3 = undirectedGraph.endVertices(e3);
		assertTrue((endsE3[0] == v1 && endsE3[1] == v4) || (endsE3[0] == v4 && endsE3[1] == v1));
		Vertex<String>[] endsE4 = undirectedGraph.endVertices(e4);
		assertTrue((endsE4[0] == v1 && endsE4[1] == v5) || (endsE4[0] == v5 && endsE4[1] == v1));
		Vertex<String>[] endsE5 = undirectedGraph.endVertices(e5);
		assertTrue((endsE5[0] == v2 && endsE5[1] == v3) || (endsE5[0] == v3 && endsE5[1] == v2));
		Vertex<String>[] endsE6 = undirectedGraph.endVertices(e6);
		assertTrue((endsE6[0] == v2 && endsE6[1] == v4) || (endsE6[0] == v4 && endsE6[1] == v2));
		Vertex<String>[] endsE7 = undirectedGraph.endVertices(e7);
		assertTrue((endsE7[0] == v2 && endsE7[1] == v5) || (endsE7[0] == v5 && endsE7[1] == v2));
		Vertex<String>[] endsE8 = undirectedGraph.endVertices(e8);
		assertTrue((endsE8[0] == v3 && endsE8[1] == v4) || (endsE8[0] == v4 && endsE8[1] == v3));
		Vertex<String>[] endsE9 = undirectedGraph.endVertices(e9);
		assertTrue((endsE9[0] == v3 && endsE9[1] == v5) || (endsE9[0] == v5 && endsE9[1] == v3));
		Vertex<String>[] endsE10 = undirectedGraph.endVertices(e10);
		assertTrue((endsE10[0] == v4 && endsE10[1] == v5) || (endsE10[0] == v5 && endsE10[1] == v4));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		ends = directedGraph.endVertices(e1);
		assertEquals(v1, ends[0]);
		assertEquals(v2, ends[1]);
		Vertex<String>[] endsE2Dir = directedGraph.endVertices(e2);
		assertEquals(v1, endsE2Dir[0]);
		assertEquals(v3, endsE2Dir[1]);
		Vertex<String>[] endsE3Dir = directedGraph.endVertices(e3);
		assertEquals(v1, endsE3Dir[0]);
		assertEquals(v4, endsE3Dir[1]);
		Vertex<String>[] endsE4Dir = directedGraph.endVertices(e4);
		assertEquals(v1, endsE4Dir[0]);
		assertEquals(v5, endsE4Dir[1]);
		Vertex<String>[] endsE5Dir = directedGraph.endVertices(e5);
		assertEquals(v2, endsE5Dir[0]);
		assertEquals(v3, endsE5Dir[1]);
		Vertex<String>[] endsE6Dir = directedGraph.endVertices(e6);
		assertEquals(v2, endsE6Dir[0]);
		assertEquals(v4, endsE6Dir[1]);
		Vertex<String>[] endsE7Dir = directedGraph.endVertices(e7);
		assertEquals(v2, endsE7Dir[0]);
		assertEquals(v5, endsE7Dir[1]);
		Vertex<String>[] endsE8Dir = directedGraph.endVertices(e8);
		assertEquals(v3, endsE8Dir[0]);
		assertEquals(v4, endsE8Dir[1]);
		Vertex<String>[] endsE9Dir = directedGraph.endVertices(e9);
		assertEquals(v3, endsE9Dir[0]);
		assertEquals(v5, endsE9Dir[1]);
		Vertex<String>[] endsE10Dir = directedGraph.endVertices(e10);
		assertEquals(v4, endsE10Dir[0]);
		assertEquals(v5, endsE10Dir[1]);
		Vertex<String>[] endsE11Dir = directedGraph.endVertices(e11);
		assertEquals(v5, endsE11Dir[0]);
		assertEquals(v6, endsE11Dir[1]);
	}

	/**
	 * Test the output of the opposite(v, e) behavior
	 */
	@Test
	public void testOpposite() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(v2, undirectedGraph.opposite(v1, e1));
		assertEquals(v1, undirectedGraph.opposite(v2, e1));
		assertEquals(v3, undirectedGraph.opposite(v1, e2));
		assertEquals(v1, undirectedGraph.opposite(v3, e2));
		assertEquals(v4, undirectedGraph.opposite(v1, e3));
		assertEquals(v1, undirectedGraph.opposite(v4, e3));
		assertEquals(v5, undirectedGraph.opposite(v1, e4));
		assertEquals(v1, undirectedGraph.opposite(v5, e4));
		assertEquals(v3, undirectedGraph.opposite(v2, e5));
		assertEquals(v2, undirectedGraph.opposite(v3, e5));
		assertEquals(v4, undirectedGraph.opposite(v2, e6));
		assertEquals(v2, undirectedGraph.opposite(v4, e6));
		assertEquals(v5, undirectedGraph.opposite(v2, e7));
		assertEquals(v2, undirectedGraph.opposite(v5, e7));
		assertEquals(v4, undirectedGraph.opposite(v3, e8));
		assertEquals(v3, undirectedGraph.opposite(v4, e8));
		assertEquals(v5, undirectedGraph.opposite(v3, e9));
		assertEquals(v3, undirectedGraph.opposite(v5, e9));
		assertEquals(v5, undirectedGraph.opposite(v4, e10));
		assertEquals(v4, undirectedGraph.opposite(v5, e10));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(v2, directedGraph.opposite(v1, e1));
		assertEquals(v1, directedGraph.opposite(v2, e1));
		assertEquals(v3, directedGraph.opposite(v1, e2));
		assertEquals(v1, directedGraph.opposite(v3, e2));
		assertEquals(v4, directedGraph.opposite(v1, e3));
		assertEquals(v1, directedGraph.opposite(v4, e3));
		assertEquals(v5, directedGraph.opposite(v1, e4));
		assertEquals(v1, directedGraph.opposite(v5, e4));
		assertEquals(v3, directedGraph.opposite(v2, e5));
		assertEquals(v2, directedGraph.opposite(v3, e5));
		assertEquals(v4, directedGraph.opposite(v2, e6));
		assertEquals(v2, directedGraph.opposite(v4, e6));
		assertEquals(v5, directedGraph.opposite(v2, e7));
		assertEquals(v2, directedGraph.opposite(v5, e7));
		assertEquals(v4, directedGraph.opposite(v3, e8));
		assertEquals(v3, directedGraph.opposite(v4, e8));
		assertEquals(v5, directedGraph.opposite(v3, e9));
		assertEquals(v3, directedGraph.opposite(v5, e9));
		assertEquals(v5, directedGraph.opposite(v4, e10));
		assertEquals(v4, directedGraph.opposite(v5, e10));
		assertEquals(v6, directedGraph.opposite(v5, e11));
		assertEquals(v5, directedGraph.opposite(v6, e11));
	}

	/**
	 * Test the output of the outDegree(v) behavior
	 */
	@SuppressWarnings("unused")
	@Test
	public void testOutDegree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(4, undirectedGraph.outDegree(v1));
		buildUndirectedSample();
		Vertex<String> vv1 = undirectedGraph.vertices().iterator().next();
		assertEquals(4, undirectedGraph.outDegree(vv1));
		assertEquals(4, undirectedGraph.outDegree(v2));
		assertEquals(4, undirectedGraph.outDegree(v3));
		assertEquals(4, undirectedGraph.outDegree(v4));
		assertEquals(4, undirectedGraph.outDegree(v5));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		buildDirectedSample();
		Vertex<String> vv5 = directedGraph.insertVertex("Greenville");
		assertEquals(0, directedGraph.outDegree(vv5));
		assertEquals(3, directedGraph.outDegree(v2));
		assertEquals(2, directedGraph.outDegree(v3));
		assertEquals(1, directedGraph.outDegree(v4));
	}

	/**
	 * Test the output of the inDegree(v) behavior
	 */
	@SuppressWarnings("unused")
	@Test
	public void testInDegree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		buildUndirectedSample();
		Vertex<String> vv1 = undirectedGraph.vertices().iterator().next();
		assertEquals(4, undirectedGraph.inDegree(vv1));
		assertEquals(4, undirectedGraph.inDegree(v2));
		assertEquals(4, undirectedGraph.inDegree(v3));
		assertEquals(4, undirectedGraph.inDegree(v4));
		assertEquals(4, undirectedGraph.inDegree(v5));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		buildDirectedSample();
		directedGraph.insertEdge(v5, v6, 55);
		assertEquals(1, directedGraph.inDegree(v6));
		assertEquals(0, directedGraph.inDegree(v1));
		assertEquals(2, directedGraph.inDegree(v3));
		assertEquals(4, directedGraph.inDegree(v5));
	}

	/**
	 * Test the output of the outgoingEdges(v) behavior
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testOutgoingEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		// We can use a custom arrayContains() helper method to check that
		// an array *contains* a certain target edge.
		// This is helpful for testing graph ADT behaviors where an order
		// of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
		// in adjacencyMaps, etc.)
		Edge<Integer>[] temp = (Edge<Integer>[]) new Edge[4];
		int count = 0;
		Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
		assertTrue(it.hasNext());
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		temp[count] = it.next();
		count++;
		assertFalse(it.hasNext());
		assertTrue(arrayContains(temp, e1));
		assertTrue(arrayContains(temp, e2));
		assertTrue(arrayContains(temp, e3));
		assertTrue(arrayContains(temp, e4));

		Edge<Integer>[] tempV2 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		it = undirectedGraph.outgoingEdges(v2).iterator();
		while (it.hasNext()) {
			tempV2[count] = it.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempV2, e1));
		assertTrue(arrayContains(tempV2, e5));
		assertTrue(arrayContains(tempV2, e6));
		assertTrue(arrayContains(tempV2, e7));

		Edge<Integer>[] tempV3 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		it = undirectedGraph.outgoingEdges(v3).iterator();
		while (it.hasNext()) {
			tempV3[count] = it.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempV3, e2));
		assertTrue(arrayContains(tempV3, e5));
		assertTrue(arrayContains(tempV3, e8));
		assertTrue(arrayContains(tempV3, e9));

		Edge<Integer>[] tempV4 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		it = undirectedGraph.outgoingEdges(v4).iterator();
		while (it.hasNext()) {
			tempV4[count] = it.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempV4, e3));
		assertTrue(arrayContains(tempV4, e6));
		assertTrue(arrayContains(tempV4, e8));
		assertTrue(arrayContains(tempV4, e10));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		Edge<Integer>[] dirTemp = (Edge<Integer>[]) new Edge[4];
		count = 0;
		Iterator<Edge<Integer>> dirIt = directedGraph.outgoingEdges(v1).iterator();
		while (dirIt.hasNext()) {
			dirTemp[count] = dirIt.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(dirTemp, e1));
		assertTrue(arrayContains(dirTemp, e2));
		assertTrue(arrayContains(dirTemp, e3));
		assertTrue(arrayContains(dirTemp, e4));

		// Test outgoing edges for v5 (directed)
		Edge<Integer>[] dirTempV5 = (Edge<Integer>[]) new Edge[1];
		count = 0;
		dirIt = directedGraph.outgoingEdges(v5).iterator();
		while (dirIt.hasNext()) {
			dirTempV5[count] = dirIt.next();
			count++;
		}
		assertEquals(1, count);
		assertTrue(arrayContains(dirTempV5, e11));
	}

	// Helper method to check that an array contains a certain target.
	// This is helpful for testing graph ADT behaviors where an order
	// of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
	private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
		for (Edge<Integer> e : temp) {
			if (e == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test the output of the incomingEdges(v) behavior
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testIncomingEdges() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		Edge<Integer>[] tempIn = (Edge<Integer>[]) new Edge[4];
		int count = 0;
		Iterator<Edge<Integer>> inIt = undirectedGraph.incomingEdges(v1).iterator();
		while (inIt.hasNext()) {
			tempIn[count] = inIt.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempIn, e1));
		assertTrue(arrayContains(tempIn, e2));
		assertTrue(arrayContains(tempIn, e3));
		assertTrue(arrayContains(tempIn, e4));

		Edge<Integer>[] tempInV2 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		inIt = undirectedGraph.incomingEdges(v2).iterator();
		while (inIt.hasNext()) {
			tempInV2[count] = inIt.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempInV2, e1));
		assertTrue(arrayContains(tempInV2, e5));
		assertTrue(arrayContains(tempInV2, e6));
		assertTrue(arrayContains(tempInV2, e7));

		Edge<Integer>[] tempInV3 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		inIt = undirectedGraph.incomingEdges(v3).iterator();
		while (inIt.hasNext()) {
			tempInV3[count] = inIt.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(tempInV3, e2));
		assertTrue(arrayContains(tempInV3, e5));
		assertTrue(arrayContains(tempInV3, e8));
		assertTrue(arrayContains(tempInV3, e9));

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		Edge<Integer>[] dirInTemp = (Edge<Integer>[]) new Edge[1];
		count = 0;
		inIt = directedGraph.incomingEdges(v6).iterator();
		while (inIt.hasNext()) {
			dirInTemp[count] = inIt.next();
			count++;
		}
		assertEquals(1, count);
		assertTrue(arrayContains(dirInTemp, e11));

		// Test incoming edges for v5 (multiple edges)
		Edge<Integer>[] dirInTempV5 = (Edge<Integer>[]) new Edge[4];
		count = 0;
		inIt = directedGraph.incomingEdges(v5).iterator();
		while (inIt.hasNext()) {
			dirInTempV5[count] = inIt.next();
			count++;
		}
		assertEquals(4, count);
		assertTrue(arrayContains(dirInTempV5, e4));
		assertTrue(arrayContains(dirInTempV5, e7));
		assertTrue(arrayContains(dirInTempV5, e9));
		assertTrue(arrayContains(dirInTempV5, e10));
	}

	/**
	 * Test the output of the insertVertex(x) behavior
	 */
	@Test
	public void testInsertVertex() {
		assertEquals(0, undirectedGraph.numVertices());
		Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
		assertEquals(1, undirectedGraph.numVertices());

		Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
		assertTrue(it.hasNext());
		assertEquals(v1, it.next());
		assertFalse(it.hasNext());
		@SuppressWarnings("unused")
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		assertEquals(2, undirectedGraph.numVertices());
	}

	/**
	 * Test the output of the insertEdge(v1, v2, x) behavior
	 */
	@Test
	public void testInsertEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");

		assertEquals(0, undirectedGraph.numEdges());
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
		assertEquals(1, undirectedGraph.numEdges());
		Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
		assertTrue(it.hasNext());
		assertEquals(e1, it.next());
		assertFalse(it.hasNext());
		@SuppressWarnings("unused")
		Edge<Integer> e2 = undirectedGraph.insertEdge(v2, v1, 100);
		assertEquals(2, undirectedGraph.numEdges());
	}

	/**
	 * Test the output of the removeVertex(v) behavior
	 */
	@SuppressWarnings("unused")
	@Test
	public void testRemoveVertex() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(5, undirectedGraph.numVertices());
		assertEquals(10, undirectedGraph.numEdges());
		undirectedGraph.removeVertex(v5);
		assertEquals(4, undirectedGraph.numVertices());
		assertEquals(6, undirectedGraph.numEdges());
		undirectedGraph.removeVertex(v1);
		assertEquals(3, undirectedGraph.numVertices());
		assertEquals(3, undirectedGraph.numEdges());
		undirectedGraph.removeVertex(v3);
		assertEquals(2, undirectedGraph.numVertices());
		assertEquals(1, undirectedGraph.numEdges());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		Vertex<String> v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);
		Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);

		assertEquals(6, directedGraph.numVertices());
		assertEquals(11, directedGraph.numEdges());
		directedGraph.removeVertex(v6);
		assertEquals(5, directedGraph.numVertices());
		assertEquals(10, directedGraph.numEdges());
		directedGraph.removeVertex(v1);
		assertEquals(4, directedGraph.numVertices());
		assertEquals(6, directedGraph.numEdges());
		directedGraph.removeVertex(v3);
		assertEquals(3, directedGraph.numVertices());
		assertEquals(3, directedGraph.numEdges());
	}

	/**
	 * Test the output of the removeEdge(e) behavior
	 */
	@Test
	public void testRemoveEdge() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
		@SuppressWarnings("unused")
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
		Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
		Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
		Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
		Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
		Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
		Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
		Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
		Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
		Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
		Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);

		assertEquals(6, undirectedGraph.numVertices());
		assertEquals(10, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e1);
		assertEquals(6, undirectedGraph.numVertices());
		assertEquals(9, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e10);
		assertEquals(8, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e5);
		assertEquals(7, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e8);
		assertEquals(6, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e9);
		assertEquals(5, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e7);
		assertEquals(4, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e6);
		assertEquals(3, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e4);
		assertEquals(2, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e3);
		assertEquals(1, undirectedGraph.numEdges());
		undirectedGraph.removeEdge(e2);
		assertEquals(0, undirectedGraph.numEdges());

		// DIRECTED
		v1 = directedGraph.insertVertex("Raleigh");
		v2 = directedGraph.insertVertex("Asheville");
		v3 = directedGraph.insertVertex("Wilmington");
		v4 = directedGraph.insertVertex("Durham");
		v5 = directedGraph.insertVertex("Greenville");
		v6 = directedGraph.insertVertex("Boone");
		e1 = directedGraph.insertEdge(v1, v2, 5);
		e2 = directedGraph.insertEdge(v1, v3, 10);
		e3 = directedGraph.insertEdge(v1, v4, 15);
		e4 = directedGraph.insertEdge(v1, v5, 20);
		e5 = directedGraph.insertEdge(v2, v3, 25);
		e6 = directedGraph.insertEdge(v2, v4, 30);
		e7 = directedGraph.insertEdge(v2, v5, 35);
		e8 = directedGraph.insertEdge(v3, v4, 40);
		e9 = directedGraph.insertEdge(v3, v5, 45);
		e10 = directedGraph.insertEdge(v4, v5, 50);

		assertEquals(6, directedGraph.numVertices());
		assertEquals(10, directedGraph.numEdges());
		directedGraph.removeEdge(e1);
		assertEquals(6, directedGraph.numVertices());
		assertEquals(9, directedGraph.numEdges());
		directedGraph.removeEdge(e10);
		assertEquals(8, directedGraph.numEdges());
		directedGraph.removeEdge(e5);
		assertEquals(7, directedGraph.numEdges());
		directedGraph.removeEdge(e8);
		assertEquals(6, directedGraph.numEdges());
		directedGraph.removeEdge(e9);
		assertEquals(5, directedGraph.numEdges());
		directedGraph.removeEdge(e7);
		assertEquals(4, directedGraph.numEdges());
		directedGraph.removeEdge(e6);
		assertEquals(3, directedGraph.numEdges());
		directedGraph.removeEdge(e4);
		assertEquals(2, directedGraph.numEdges());
		directedGraph.removeEdge(e3);
		assertEquals(1, directedGraph.numEdges());
		directedGraph.removeEdge(e2);
		assertEquals(0, directedGraph.numEdges());
	}

}

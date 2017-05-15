package com.vanildo.trains.graph;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphTest {

	private Graph graph;
	static final Logger logger = LoggerFactory.getLogger(GraphTest.class);
	
	@Before
	public void moutGraph() {
		this.graph = new Graph();
		
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		
		graph.addVertex(a, true);
		graph.addVertex(b, true);
		graph.addVertex(c, true);
		graph.addVertex(d, true);
		graph.addVertex(e, true);
		
		graph.addEdge(a, b, 5);
		graph.addEdge(b, c, 4);
		graph.addEdge(c, d, 8);
		graph.addEdge(d, c, 8);
		graph.addEdge(d, e, 6);
		graph.addEdge(a, d, 5);
		graph.addEdge(c, e, 2);
		graph.addEdge(e, b, 3);
		graph.addEdge(a, e, 7);
	}
	
	@Test
	public void assertPathLength() {
		Edge edge = new Edge(new Vertex("a"), new Vertex("b"));
		logger.info("Edge hashCode: {}", edge.hashCode());
		logger.info("Conains edge? {}", graph.containsEdge(edge));
	}
}

package com.vanildo.trains.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphTest {

	private Graph graph;
	static final Logger logger = LoggerFactory.getLogger(GraphTest.class);
	
	@Before
	public void moutGraph() {
		this.graph = new Graph(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
	}
	
	@Test
	public void getAdjacentsTest() {
		List<Vertex> adjacents = graph.getVertex("A").getAdjacentNodes();
		logger.info("Adjacent Verticies: {}", adjacents);
		
		assertThat(adjacents).hasSize(3);
		assertThat(adjacents).contains(graph.getVertex("B"));
		assertThat(adjacents).contains(graph.getVertex("D"));
		assertThat(adjacents).contains(graph.getVertex("E"));
	}
}

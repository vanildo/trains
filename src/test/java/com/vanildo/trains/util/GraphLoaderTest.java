package com.vanildo.trains.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.graph.Edge;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;


public class GraphLoaderTest {
	
	static final Logger logger = LoggerFactory.getLogger(GraphLoaderTest.class);
	
	@Test
	public void graphConstructorTest() {
		Graph graph = new Graph(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
		
		assertThat(graph.vertexKeys()).hasSize(5);
		assertThat(graph.getEdges()).hasSize(9);
		
		Vertex vertex = graph.getVertex("A");
		assertThat(vertex).isNotNull();
		assertThat(vertex.getNeighbors()).isNotNull()
										 .isNotEmpty();
		assertThat(vertex.getNeighbors()).contains(new Edge(graph.getVertex("A"), graph.getVertex("B"), 5));
		assertThat(vertex.getNeighbors()).contains(new Edge(graph.getVertex("A"), graph.getVertex("D"), 5));
	}

}

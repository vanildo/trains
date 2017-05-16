package com.vanildo.trains.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import com.vanildo.trains.graph.Graph;


public class GraphLoaderTest {
	
	static final Logger logger = LoggerFactory.getLogger(GraphLoaderTest.class);
	
	@Test
	public void graphConstructorTest() {
		Graph graph = new Graph(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
		
		assertThat(graph.vertexKeys()).hasSize(5);
		assertThat(graph.getEdges()).hasSize(8);
	}
}

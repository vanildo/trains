package com.vanildo.trains.util;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vanildo.trains.graph.Graph;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphLoaderTest {

	@Test
	public void graphoLoaderTest() {
		ILoader loader = new GraphLoader();
		Graph graph = loader.loadFromFile("/Users/vanildov/Documents/work71/trains/src/test/resources/graph.yml");
	}
}

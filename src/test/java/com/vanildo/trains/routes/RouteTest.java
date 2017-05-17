package com.vanildo.trains.routes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.GraphTest;

public class RouteTest {
	
	private Graph graph;
	private Route route;
	static final Logger logger = LoggerFactory.getLogger(GraphTest.class);
	
	@Before
	public void setup() {
		this.graph = new Graph(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
		this.route = new Route(graph);
		route.getVertices().addAll(Arrays.asList(graph.getVertex("A"), graph.getVertex("B"), graph.getVertex("C")));
	}
	
	@Test
	public void getDistanceTest() throws RouteNotFoundException {
		int totalDistance = route.getTotalDistance();
		
		assertThat(totalDistance).isEqualTo(9);
	}
	
	@Test
	public void getHopsTest() {
		assertThat(route.getHops()).isEqualTo(2);
	}
	
	@Test
	public void getEdgesTest() throws RouteNotFoundException {
		assertThat(route.getEdges()).isNotNull().isNotEmpty();
		assertThat(route.getEdges()).hasSize(2);
	}
	
	@Test
	public void raiseRouteNotFoundException() {
		route.addVertex(graph.getVertex("E"));
		try {
			route.getEdges();
		} catch (Exception e) {
			assertThat(e).isInstanceOf(RouteNotFoundException.class);
		}
	}

}

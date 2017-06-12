package com.vanildo.trains.graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.routes.IRouteCalculator;
import com.vanildo.trains.routes.Route;
import com.vanildo.trains.routes.RouteCalculator;


/**
 * 
 * @author vanildo vanni
 *
 */
public class GraphTest {

	private Graph graph;
	static final Logger logger = LoggerFactory.getLogger(GraphTest.class);
	
	@Before
	public void setup() {
		this.graph = new Graph(Arrays.asList("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
	}
	
	@Test
	public void fastestRouteTest() {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);
		Route route = routeCalculator.fastestPath(graph.getVertex("B"), graph.getVertex("B"));
		try {
			assertThat(route.getTripDuration()).isEqualTo(13);
		} catch (RouteNotFoundException e) {
			logger.error("Rota não encontrada: {}", e.getMessage());
		}
	}
	
	@Test
	public void routesWithMaximumHopsTest() {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);

		Set<Route> routes = routeCalculator.routesWithMaximumHops(graph.getVertex("C"), graph.getVertex("C"), 3);
		
		assertThat(routes).hasSize(2);
	}
	
	@Test
	public void shortestPathTest() throws RouteNotFoundException {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);
		Route route = routeCalculator.shortestPath(graph.getVertex("A"), graph.getVertex("C"));
		
		assertThat(route.getTotalDistance()).isEqualTo(9);
		
		route = routeCalculator.shortestPath(graph.getVertex("B"), graph.getVertex("B"));
		assertThat(route.getTotalDistance()).isEqualTo(9);
	}
	
	@Test
	public void routesWithMaximumDistanceTest() {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);
		Set<Route> routes = routeCalculator.routesWithMaximumDistance(graph.getVertex("C"), graph.getVertex("C"), 30);
		
		assertThat(routes).hasSize(7);
	}
	
	@Test
	public void routesWithExactHopsTest() {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);

		Set<Route> routes = routeCalculator.routesWithExactHops(graph.getVertex("A"), graph.getVertex("C"), 4);
		Route r = routes.iterator().next();
		logger.info(r.getVertices().toString());
		
		assertThat(routes).hasSize(3);
	}
	
	@Test
	public void getPossibleRoutesTest() {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);

		Set<Route> routes = routeCalculator.getPossibleRoutes(graph.getVertex("A"), graph.getVertex("C"));
		logger.debug("Rotas: {}", routes.size());
	}
	
	@Test
	public void totalDistanceTest() throws RouteNotFoundException {
		IRouteCalculator routeCalculator = new RouteCalculator(graph);
		int totalDistance = routeCalculator.getDistance(graph.getVertex("A"), graph.getVertex("B"), graph.getVertex("C"));
		
		assertThat(totalDistance).isEqualTo(9);
		
		totalDistance = routeCalculator.getDistance(graph.getVertex("A"), graph.getVertex("D"));
		assertThat(totalDistance).isEqualTo(5);
		
		totalDistance = routeCalculator.getDistance(graph.getVertex("A"), graph.getVertex("D"), graph.getVertex("C"));
		assertThat(totalDistance).isEqualTo(13);
		
		totalDistance = routeCalculator.getDistance(graph.getVertex("A"), graph.getVertex("E"), graph.getVertex("B"), graph.getVertex("C"), graph.getVertex("D"));
		assertThat(totalDistance).isEqualTo(22);
		
		try {
			totalDistance = routeCalculator.getDistance(graph.getVertex("A"), graph.getVertex("E"), graph.getVertex("D"));
		} catch (Exception e) {
			assertThat(e).isInstanceOf(RouteNotFoundException.class);
		}
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

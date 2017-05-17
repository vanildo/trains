package com.vanildo.trains;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;
import com.vanildo.trains.routes.RouteCalculator;

public class TrainsRoute {
	
	static final Logger logger = LoggerFactory.getLogger(TrainsRoute.class);
	private final Graph graph;
	private final RouteCalculator routeCalculator;
	
	public TrainsRoute(List<String> edges) {
		this.graph = new Graph(edges);
		this.routeCalculator = new RouteCalculator(this.graph);
	}

	public static void main(String[] args) {
		
		if (args.length < 3) {
			logger.info("Usage: java -jar TrainsRoute edge1 edge2 <edge3...>");
			logger.info("where edge is in the following format: AB4");
			System.exit(0);
		}
		
		TrainsRoute trainsRoute = new TrainsRoute(Arrays.asList(args));
		trainsRoute.calculateRoutes();
		
	}

	private void calculateRoutes() {
		int i = 1;
		
		showDistance(i++, graph.getVertex("A"), graph.getVertex("B"), graph.getVertex("C"));
		showDistance(i++, graph.getVertex("A"), graph.getVertex("D"));
		showDistance(i++, graph.getVertex("A"), graph.getVertex("D"), graph.getVertex("C"));
		showDistance(i++, graph.getVertex("A"), graph.getVertex("E"), graph.getVertex("B"), graph.getVertex("C"), graph.getVertex("D"));
		showDistance(i++, graph.getVertex("A"), graph.getVertex("E"), graph.getVertex("D"));
		
		logger.info("Output #{}: {}", i++, routeCalculator.routesWithMaximumHops(graph.getVertex("C"), graph.getVertex("C"), 3).size());
		logger.info("Output #{}: {}", i++, routeCalculator.routesWithExactHops(graph.getVertex("A"), graph.getVertex("C"), 4).size());
		
		showShortestPath(i++, graph.getVertex("A"), graph.getVertex("C"));
		showShortestPath(i++, graph.getVertex("B"), graph.getVertex("B"));
		
		logger.info("Output #{}: {}", i++, routeCalculator.routesWithMaximumDistance(graph.getVertex("C"), graph.getVertex("C"), 30).size());
		
	}

	private void showShortestPath(int i, Vertex start, Vertex end)  {
		try {
			logger.info("Output #{}: {}", i, routeCalculator.shortestPath(start, end).getTotalDistance());
		} catch (RouteNotFoundException e) {
			logger.info("Output #{}: NO SUCH ROUTE", i);
		}
	}

	private void showDistance(int i, Vertex ...vertices) {
		logger.debug("distance of the route A-B-C");
		try {
			logger.info("Output #{}: {}", i, routeCalculator.getDistance(vertices));
		} catch (RouteNotFoundException e) {
			logger.info("Output #{}: NO SUCH ROUTE", i);
		}
	}

}

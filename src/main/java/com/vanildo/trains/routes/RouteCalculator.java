package com.vanildo.trains.routes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;

/**
 * 
 * @author vanildo vanni
 *
 */
public class RouteCalculator implements IRouteCalculator {

	static final Logger logger = LoggerFactory.getLogger(RouteCalculator.class);
	private final int MAX_DEPTH;
	private final Graph graph;

	public RouteCalculator(Graph graph) {
		this.graph = graph;
		this.MAX_DEPTH = this.graph.getEdges().size() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vanildo.trains.routes.IRouteCalculator#getDistance(com.vanildo.trains
	 * .graph.Vertex)
	 */
	@Override
	public int getDistance(Vertex... vertices) throws RouteNotFoundException {
		if (vertices == null || vertices.length < 2) {
			throw new IllegalArgumentException();
		}
		
		Route route = new Route(graph);
		route.getVertices().addAll(Arrays.asList(vertices));
		
		return route.getTotalDistance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanildo.trains.routes.IRouteCalculator#shortestPath(com.vanildo.
	 * trains.graph.Vertex, com.vanildo.trains.graph.Vertex)
	 * TODO: use Dijkstras on the next version
	 */
	@Override
	public Route shortestPath(Vertex start, Vertex end) {
		Set<Route> routes =  getPossibleRoutes(start, end);
		
		Route route = routes.stream()
							.sorted()
							.collect(Collectors.toList())
							.get(0);
		return route;
	}

	@Override
	public Set<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops) {
		return getRoutesWithFilterPredicate(start, end, route -> route.getHops() <= maxHops);
	}

	@Override
	public Set<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance) {
		return getRoutesWithFilterPredicate(start, end, route -> {
			try {
				return route.getTotalDistance() < maxDistance;
			} catch (RouteNotFoundException e) {
				return false;
			}
		});
	}

	@Override
	public Set<Route> routesWithExactHops(Vertex start, Vertex end, int hops) {
		return getRoutesWithFilterPredicate(start, end, route -> route.getHops() == hops);
	}
	
	private Set<Route> getRoutesWithFilterPredicate(Vertex start, Vertex end, Predicate<Route> predicate) {
		Set<Route> routes =  getPossibleRoutes(start, end);
		
		return routes.stream()
					 .filter(predicate)
					 .collect(Collectors.toSet());
	}

	public Set<Route> getPossibleRoutes(Vertex start, Vertex end) {
		Set<Route> routes = new HashSet<>();
		LinkedList<Vertex> visited = new LinkedList<>();
		visited.add(start);

		depthFirst(visited, end, MAX_DEPTH, routes);
		
		return routes;
	}
	
//	private void depthFirst(LinkedList<Vertex> visited, Vertex end, int depth, Set<Route> routes) {
//		Vertex start = visited.getLast();
//		
//		for (Vertex v : start.getAdjacentNodes()) {
//			if (visited.contains(v)) {
//				continue;
//			}
//            if (v.equals(end)) {
//                visited.add(v);
//                Route route = new Route(graph);
//				route.getVertices().addAll(visited);
//				routes.add(route);
//                visited.removeLast();
//                break;
//            }
//        }
//		
//		for (Vertex v : start.getAdjacentNodes()) {
//			if (visited.contains(v) || v.equals(end)) {
//				continue;
//			}
//            visited.addLast(v);
//            depthFirst(visited, end, depth, routes);
//            visited.removeLast();
//        }
//		
//	}
	
	private void depthFirst(LinkedList<Vertex> visited, Vertex end, int depth, Set<Route> routes) {
		Vertex start = visited.getLast();
		
		while (depth > 0) {
        	// examine adjacent nodes
			for (Vertex v : start.getAdjacentNodes()) {
                if (v.equals(end)) {
                    visited.add(v);
                    Route route = new Route(graph);
					route.getVertices().addAll(visited);
					routes.add(route);
                    visited.removeLast();
                    depth -= 1;
                }
                visited.addLast(v);
                depthFirst(visited, end, depth, routes);
                visited.removeLast();
                depth -= 1;
            }
            
            depth -= 1;
        }
		
	}

}

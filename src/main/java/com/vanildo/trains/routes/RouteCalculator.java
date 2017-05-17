package com.vanildo.trains.routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	 */
	@Override
	public Route shortestPath(Vertex start, Vertex end) {
		return null;
	}

	@Override
	public List<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> routesWithExactHops(Vertex start, Vertex end, int hops) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Route> getPossibleRoutes(Vertex start, Vertex end) {
		List<Route> routes = new ArrayList<>();
		LinkedList<Vertex> visited = new LinkedList<>();
		visited.add(start);

		depthFirst(visited, end, MAX_DEPTH, routes);

		return routes;
	}

	public void depthFirst(LinkedList<Vertex> visited, Vertex end, int depth, List<Route> routes) {
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
            }
			for (Vertex v : start.getAdjacentNodes()) {
                visited.addLast(v);
                depthFirst(visited, end, depth, routes);
                visited.removeLast();
                depth -= 1;
            }
            
            depth -= 1;
        }
		
//		for (Vertex v : start.getAdjacentNodes()) {
//			//TODO criar a rota para o nó, caso ache
//			if (!visited.contains(v)) {
//				visited.add(v);
//				rotaAtual.add(v);
//				depthFirst(visited, end, 0, routes);
//				if(v.equals(end)) {
//					Route route = new Route(graph);
//					route.getVertices().addAll(rotaAtual);
//					routes.add(route);
//				}
//			}
//		}
	}

}

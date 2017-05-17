package com.vanildo.trains.routes;

import java.util.LinkedList;
import java.util.List;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Vertex;

public interface IRouteCalculator {

	int getDistance(Vertex... vertices) throws RouteNotFoundException;

	Route shortestPath(Vertex start, Vertex end);
	
	List<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops);
	
	List<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance);
	
	List<Route> routesWithExactHops(Vertex start, Vertex end, int hops);
	
	public void depthFirst(LinkedList<Vertex> visited, Vertex end, int depth, List<Route> routes);

}
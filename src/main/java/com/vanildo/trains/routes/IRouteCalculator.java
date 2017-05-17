package com.vanildo.trains.routes;

import java.util.List;
import java.util.Set;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Vertex;

public interface IRouteCalculator {

	int getDistance(Vertex... vertices) throws RouteNotFoundException;

	Route shortestPath(Vertex start, Vertex end);
	
	Set<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops);
	
	List<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance);
	
	List<Route> routesWithExactHops(Vertex start, Vertex end, int hops);
	
	public Set<Route> getPossibleRoutes(Vertex start, Vertex end);

}
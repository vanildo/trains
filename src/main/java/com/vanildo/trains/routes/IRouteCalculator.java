package com.vanildo.trains.routes;

import java.util.List;

import com.vanildo.trains.graph.Vertex;

public interface IRouteCalculator {

	Route getDistance(Vertex... vertices);

	Route shortestPath(Vertex start, Vertex end);
	
	List<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops);
	
	List<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance);
	
	List<Route> routesWithExactHops(Vertex start, Vertex end, int hops);

}
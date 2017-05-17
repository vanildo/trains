package com.vanildo.trains.routes;

import java.util.Set;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Vertex;


/**
 * 
 * @author vanildo vanni
 *
 */
public interface IRouteCalculator {

	int getDistance(Vertex... vertices) throws RouteNotFoundException;

	Route shortestPath(Vertex start, Vertex end);
	
	Set<Route> routesWithMaximumHops(Vertex start, Vertex end, int maxHops);
	
	Set<Route> routesWithMaximumDistance(Vertex start, Vertex end, int maxDistance);
	
	Set<Route> routesWithExactHops(Vertex start, Vertex end, int hops);
	
	public Set<Route> getPossibleRoutes(Vertex start, Vertex end);

}
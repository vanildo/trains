package com.vanildo.trains.routes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;


/**
 * 
 * @author vanildo vanni
 *
 */
public class RouteCalculator implements IRouteCalculator {
	
	
	static final Logger logger = LoggerFactory.getLogger(RouteCalculator.class);
	private final Graph graph;
	
	public RouteCalculator(Graph graph) {
		this.graph = graph;
	}
	
	/* (non-Javadoc)
	 * @see com.vanildo.trains.routes.IRouteCalculator#getDistance(com.vanildo.trains.graph.Vertex)
	 */
	@Override
	public Route getDistance(Vertex ...vertices) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vanildo.trains.routes.IRouteCalculator#shortestPath(com.vanildo.trains.graph.Vertex, com.vanildo.trains.graph.Vertex)
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

}

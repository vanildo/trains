package com.vanildo.trains.routes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Edge;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;

/**
 * @author vanildo vanni
 *
 */
public class Route implements Comparable<Route>{

	private List<Vertex> vertices = new ArrayList<>();
	private final Graph graph;
	static final Logger logger = LoggerFactory.getLogger(Route.class);
	
	
	public Route(Graph graph) {
		this.graph = graph;
	}
	
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	public void addVertex(Vertex vertex) {
		this.vertices.add(vertex);
	}

	public int getTotalDistance() throws RouteNotFoundException {
		int totalDistance = 0;
		
		for (Edge edge : getEdges()) {
			totalDistance += edge.getWeight();
		}
		
		return totalDistance;
	}
	
	public List<Edge> getEdges() throws RouteNotFoundException {
		List<Edge> edges = new ArrayList<>();
		int i = 0;
		
		Vertex left = vertices.get(i);
		Vertex right;
		while (++i < vertices.size()) {
			right = vertices.get(i);
			Edge edge = graph.getEdge(new Edge(left, right));
			if (edge == null) {
				throw new RouteNotFoundException();
			}
			edges.add(edge);
			left = right;
			right = vertices.get(i);
		}
		
		return edges;
	}

	public int getHops() {
		return this.vertices.size() - 1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Vertex v : getVertices()) {
			sb.append(v.getName());
		}
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
		return vertices.toString().hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
			return false;
		return true;
	}


	@Override
	public int compareTo(Route o) {
		try {
			return this.getTotalDistance() - o.getTotalDistance();
		} catch (RouteNotFoundException e) {
			logger.error("No such route: {}", e.getMessage());
		}
		return 0;
	}
	
}

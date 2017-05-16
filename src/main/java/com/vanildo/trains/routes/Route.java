package com.vanildo.trains.routes;

import java.util.ArrayList;
import java.util.List;

import com.vanildo.trains.exceptions.RouteNotFoundException;
import com.vanildo.trains.graph.Edge;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;

/**
 * @author vanildo vanni
 *
 */
public class Route {

	private List<Vertex> vertices = new ArrayList<>();
	private final Graph graph;
	private int hops;
	
	
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
		int i = 0;
		int totalDistance = 0;
		Vertex left = vertices.get(i);
		Vertex right;
		while (++i < vertices.size()) {
			right = vertices.get(i);
			Edge edge = graph.getEdge(new Edge(left, right));
			if (edge == null) {
				throw new RouteNotFoundException();
			}
			totalDistance += edge.getWeight();
			left = right;
			right = vertices.get(i);
		}
		return totalDistance;
	}

	public int getHops() {
		return hops;
	}
	public void setHops(int hops) {
		this.hops = hops;
	}
}

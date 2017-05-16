package com.vanildo.trains.routes;

import java.util.ArrayList;
import java.util.List;

import com.vanildo.trains.graph.Vertex;

/**
 * @author vanildo vanni
 *
 */
public class Route {

	private List<Vertex> vertices = new ArrayList<>();
	private int totalDistance;
	private int hops;
	

	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	public void addVertex(Vertex vertex) {
		this.vertices.add(vertex);
	}

	public int getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getHops() {
		return hops;
	}
	public void setHops(int hops) {
		this.hops = hops;
	}
}

package com.vanildo.trains.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private String name;
	private List<Edge> neighbors = new ArrayList<Edge>();
	
	public Vertex() { }
	
	public Vertex(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addNeighbor(Edge edge) {
		if (this.neighbors.contains(edge)) {
			return;
		}
		this.neighbors.add(edge);
	}
	
	public void removeNeighbor(Edge edge) {
		this.neighbors.remove(edge);
	}
	
	public boolean containsNeighbor(Edge other){
		return this.neighbors.contains(other);
	}
	
	public ArrayList<Edge> getNeighbors(){
        return new ArrayList<Edge>(this.neighbors);
    }
	
	public int getNeighborCount() {
		return this.neighbors.size();
	}
	
	public Edge getNeighbor(int index) {
		return this.neighbors.get(index);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vertex [name=").append(name).append("]");
		return builder.toString();
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((neighbors == null) ? 0 : neighbors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

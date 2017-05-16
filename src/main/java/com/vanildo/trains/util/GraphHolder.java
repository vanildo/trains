package com.vanildo.trains.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanildo.trains.graph.Vertex;

public class GraphHolder {

	@JsonProperty
	private List<Vertex> vertices = new ArrayList<Vertex>();
	@JsonProperty
	private List<EdgeHolder> edges = new ArrayList<EdgeHolder>();
	
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	
	public List<EdgeHolder> getEdges() {
		return edges;
	}
	public void setEdges(List<EdgeHolder> edges) {
		this.edges = edges;
	}
	
	public static class EdgeHolder {
		
		@JsonProperty
		private String left;
		@JsonProperty
		private String right;
		@JsonProperty
		private int weight;
		
		public String getLeft() {
			return left;
		}
		public void setLeft(String left) {
			this.left = left;
		}
		
		public String getRight() {
			return right;
		}
		public void setRight(String right) {
			this.right = right;
		}
		
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("EdgeHolder [left=").append(left).append(", right=").append(right).append(", weight=")
					.append(weight).append("]");
			return builder.toString();
		}
		
	}
	
}

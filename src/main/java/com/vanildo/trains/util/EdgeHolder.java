package com.vanildo.trains.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EdgeHolder {

	@JsonProperty
	private String edge;
	@JsonIgnore
	private String left;
	@JsonIgnore
	private String right;
	@JsonIgnore
	private int weight;

	public EdgeHolder(String edge) {
		setEdge(edge);
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
		populateEdge();
	}

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

	private void populateEdge() {
		if (this.edge.length() == 3) {
			this.left = String.valueOf(this.edge.charAt(0));
			this.right = String.valueOf(this.edge.charAt(1));
			this.weight = Integer.parseInt(String.valueOf(this.edge.charAt(2)));
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EdgeHolder [left=").append(left).append(", right=").append(right).append(", weight=")
				.append(weight).append("]");
		return builder.toString();
	}

}
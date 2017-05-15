package com.vanildo.trains.graph;

public class Edge implements Comparable<Edge> {

	private Vertex left, right;
	private int weight;

	public Edge(Vertex left, Vertex right) {
		this(left, right, 1);
	}

	public Edge(Vertex left, Vertex right, int weight) {
		super();
		this.left = left;
		this.right = right;
		this.weight = weight;
	}

	public Vertex getNeighbor(Vertex current) {
		if (!(current.equals(right) || current.equals(left))) {
			return null;
		}
		return (current.equals(right)) ? left : right;
	}

	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}

	public Vertex getLeft() {
		return left;
	}

	public Vertex getRight() {
		return right;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge [left=").append(left).append(", right=").append(right).append(", weight=").append(weight)
				.append("]");
		return builder.toString();
	}
	
}

package com.vanildo.trains.graph;

public class Edge implements Comparable<Edge> {

	private Vertex left, right;
	private int weight;

	public Edge(Vertex left, Vertex right) {
		this(left, right, 1);
	}

	public Edge(Vertex left, Vertex right, int weight) {
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
		return (left.getName() + right.getName()).hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Edge)) {
			return false;
		}
		Edge e = (Edge) other;

		return e.left.equals(this.left) && e.right.equals(this.right);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge [left=").append(left).append(", right=").append(right).append(", weight=").append(weight)
				.append("]");
		return builder.toString();
	}

}

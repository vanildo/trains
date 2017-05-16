package com.vanildo.trains.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanildo.trains.util.EdgeHolder;

public class Graph {

	private Map<String, Vertex> vertices = new HashMap<String, Vertex>();
	private Map<String, Edge> edges;
	static final Logger logger = LoggerFactory.getLogger(Graph.class);

	public Graph() { 
		this.edges = new HashMap<String, Edge>();
	}

	
	/**
	 * This constructor accepts an ArrayList<String> and populates
	 * this.vertices and edges. If multiple Vertex objects have the same Name(), then the
	 * last Vertex with the given Name() is used.
	 * 
	 * @param vertices
	 *            The initial Vertices and Edges to populate this Graph
	 */
	public Graph(List<String> edgesAsString) {
		this.edges = new HashMap<String, Edge>(edgesAsString.size());
		populateGraph(edgesAsString);

	}


	private void populateGraph(List<String> edgesAsString) {
		for (String edge : edgesAsString) {
			EdgeHolder edgeHolder = new EdgeHolder(edge);
			logger.debug("EdgeHolder: {}", edgeHolder);
			
			addVertex(new Vertex(edgeHolder.getLeft()), true);
			addVertex(new Vertex(edgeHolder.getRight()), true);
			addEdge(getVertex(edgeHolder.getLeft()), getVertex(edgeHolder.getRight()), edgeHolder.getWeight());
		}
	}

	/**
	 * This method adds am edge between Vertices one and two of weight 1, if no
	 * Edge between these Vertices already exists in the Graph.
	 * 
	 * @param one
	 *            The first vertex to add
	 * @param two
	 *            The second vertex to add
	 * @return true iff no Edge relating one and two exists in the Graph
	 */
	public boolean addEdge(Vertex one, Vertex two) {
		return addEdge(one, two, 1);
	}

	/**
	 * Accepts two vertices and a weight, and adds the edge ({one, two}, weight)
	 * iff no Edge relating one and two exists in the Graph.
	 * 
	 * @param one
	 *            The first Vertex of the Edge
	 * @param two
	 *            The second Vertex of the Edge
	 * @param weight
	 *            The weight of the Edge
	 * @return true iff no Edge already exists in the Graph
	 */
	public boolean addEdge(Vertex one, Vertex two, int weight) {
		if (one.equals(two)) {
			return false;
		}

		// ensures the Edge is not in the Graph
		Edge e = new Edge(one, two, weight);
		if (edges.containsKey(e.getEdgeName())) {
			return false;
		}

		// and that the Edge isn't already incident to one of the vertices
		else if (one.containsNeighbor(e) || two.containsNeighbor(e)) {
			return false;
		}

		logger.debug("Edge key: {}", e.getEdgeName());
		this.edges.put(e.getEdgeName(), e);
		logger.debug("Edges: {}", this.edges);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	}

	/**
	 * 
	 * @param e
	 *            The Edge to look up
	 * @return true iff this Graph contains the Edge e
	 */
	public boolean containsEdge(Edge e) {
		if (e.getLeft() == null || e.getRight() == null) {
			return false;
		}

		return this.edges.containsKey(e.getEdgeName());
	}
	
	/**
	 * 
	 * @param e
	 *            The Edge to look up
	 * @return Edge iff this Graph contains the Edge e
	 */
	public Edge getEdge(Edge e) {
		if (e.getLeft() == null || e.getRight() == null) {
			return null;
		}

		return this.edges.get(e.getEdgeName());
	}

	/**
	 * This method removes the specified Edge from the Graph, including as each
	 * vertex's incidence neighborhood.
	 * 
	 * @param e
	 *            The Edge to remove from the Graph
	 * @return Edge The Edge removed from the Graph
	 */
	public Edge removeEdge(Edge e) {
		e.getLeft().removeNeighbor(e);
		e.getRight().removeNeighbor(e);
		return this.edges.remove(e.getEdgeName());
	}

	/**
	 * 
	 * @param vertex
	 *            The Vertex to look up
	 * @return true iff this Graph contains vertex
	 */
	public boolean containsVertex(Vertex vertex) {
		return this.vertices.get(vertex.getName()) != null;
	}

	/**
	 * 
	 * @param Name()
	 *            The specified Vertex Name()
	 * @return Vertex The Vertex with the specified Name()
	 */
	public Vertex getVertex(String name) {
		return vertices.get(name);
	}

	/**
	 * This method adds a Vertex to the graph. If a Vertex with the same Name()
	 * as the parameter exists in the Graph, the existing Vertex is overwritten
	 * only if overwriteExisting is true. If the existing Vertex is overwritten,
	 * the Edges incident to it are all removed from the Graph.
	 * 
	 * @param vertex
	 * @param overwriteExisting
	 * @return true iff vertex was added to the Graph
	 */
	public boolean addVertex(Vertex vertex, boolean overwriteExisting) {
		Vertex current = this.vertices.get(vertex.getName());
		if (current != null) {
			if (!overwriteExisting) {
				return false;
			}

			while (current.getNeighborCount() > 0) {
				this.removeEdge(current.getNeighbor(0));
			}
		}

		vertices.put(vertex.getName(), vertex);
		return true;
	}

	/**
	 * 
	 * @param Name()
	 *            The Name() of the Vertex to remove
	 * @return Vertex The removed Vertex object
	 */
	public Vertex removeVertex(String name) {
		Vertex v = vertices.remove(name);

		while (v.getNeighborCount() > 0) {
			this.removeEdge(v.getNeighbor((0)));
		}

		return v;
	}

	/**
	 * 
	 * @return Set<String> The unique Name()s of the Graph's Vertex objects
	 */
	public Set<String> vertexKeys() {
		return this.vertices.keySet();
	}

	/**
	 * 
	 * @return Set<Edge> The Edges of this graph
	 */
	public Set<Edge> getEdges() {
		return new HashSet<Edge>(this.edges.values());
	}

}

package com.vanildo.trains.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author vanildo vanni
 *
 */
public class GraphHolder {

	@JsonProperty
	private List<EdgeHolder> edges = new ArrayList<EdgeHolder>();
	
	
	public List<EdgeHolder> getEdges() {
		return edges;
	}
	public void setEdges(List<EdgeHolder> edges) {
		this.edges = edges;
	}
	
}

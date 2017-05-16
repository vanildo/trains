package com.vanildo.trains.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.vanildo.trains.graph.Graph;
import com.vanildo.trains.graph.Vertex;
import com.vanildo.trains.util.EdgeHolder;

public class YamlGraphLoader implements ILoader<Graph>{
	
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	static final Logger logger = LoggerFactory.getLogger(YamlGraphLoader.class);

	public Graph loadFromFile(final String path) {

		Graph graph = new Graph();
		GraphHolder holder = null;
		
		try {
			holder = mapper.readValue(new File(path), GraphHolder.class);
		} catch (JsonParseException e) {
			logger.error("JsonParseException Error {}", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException Error {}", e);
		} catch (IOException e) {
			logger.error("IOException Error {}", e);
		}
		if (holder == null) {
			return graph;
		}

		for (EdgeHolder edgeHolder : holder.getEdges()) {
			logger.debug("EdgeHolder: {}", edgeHolder);
			graph.addVertex(new Vertex(edgeHolder.getLeft()), true);
			graph.addVertex(new Vertex(edgeHolder.getRight()), true);
			graph.addEdge(graph.getVertex(edgeHolder.getLeft()), graph.getVertex(edgeHolder.getRight()), edgeHolder.getWeight());
		}
		
		logger.debug("Edge: {}", graph.getEdges());
		
		return graph;
	}

	public Graph loadFromFile(final URI uri) {
		return loadFromFile(uri.getPath());
	}

}

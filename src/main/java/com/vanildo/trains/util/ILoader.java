package com.vanildo.trains.util;

import java.net.URI;

import com.vanildo.trains.graph.Graph;

public interface ILoader {
	
	public Graph loadFromFile(String path);
	
	public Graph loadFromFile(URI uri);

}

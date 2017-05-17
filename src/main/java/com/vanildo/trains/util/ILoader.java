package com.vanildo.trains.util;

import java.net.URI;


/**
 * 
 * @author vanildo vanni
 *
 */
public interface ILoader<T> {
	
	public T loadFromFile(String path);
	
	public T loadFromFile(URI uri);

}

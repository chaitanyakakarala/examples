package com.kc.demo.service;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface FileHandler.
 */
public interface FileHandler {

	/**
	 * Read fileto collection.
	 *
	 * @param filePath the file path
	 * @return the list
	 */
	List<String> readFiletoCollection(String filePath);
	
}

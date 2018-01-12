package com.kc.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kc.demo.factory.FileHandlerFactory;
import com.kc.demo.service.Operations;
import com.kc.demo.util.CommonUtil;

/**
 * The Class AppInitializer.
 */
public class AppInitializer {
	
	/** The operations. */
	Operations operations;
	
	public AppInitializer(Operations operations) {
		this.operations = operations;
	}
	
	/**
	 * Inits the and inject ops.
	 *
	 * @param fileElements the file elements
	 * @throws Exception the exception
	 */
	public Operations initAndInjectOps(List<List<String>> fileElements)throws Exception{
		
		this.operations.injectRepository(fileElements, System.in);
		return operations;
	}
	
	/**
	 * Run operations.
	 *
	 * @throws Exception the exception
	 */
	public void runOperations() throws Exception{
		
		this.operations.recursiveCall();
	}
	
	/**
	 * Read file.
	 *
	 * @param filename the filename
	 * @param extension the extension
	 * @return the list
	 */
	public List<List<String>> readFile(String filename,String extension) {
		return FileHandlerFactory.getFileHandler(extension)
                .readFiletoCollection(filename)
                .stream()
                .filter(line -> !line.isEmpty() && line.contains(","))
                .map(item -> CommonUtil.repeatSplitWithSeperator(item, new ArrayList<String>()))
                .collect(Collectors.toList());
	}
}

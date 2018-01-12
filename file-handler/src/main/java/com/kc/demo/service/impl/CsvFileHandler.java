package com.kc.demo.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kc.demo.service.FileHandler;

/**
 * The Class CsvFileHandler.
 */
public class CsvFileHandler implements FileHandler{

	/* (non-Javadoc)
	 * @see com.kc.demo.service.FileHandler#readFiletoCollection(java.lang.String)
	 */
	@Override
	public List<String> readFiletoCollection(String filePath) {
		List<String> lines =  new ArrayList<String>();
		try{
			lines = Files.readAllLines(Paths.get(filePath))
						.stream()
						.collect(Collectors.toList());
			return lines;
		}catch(Exception exp) {
			System.err.println(exp.getLocalizedMessage());
			throw new RuntimeException(String.format("%s occoured while parsing",
					exp.getMessage()),exp);
		}
	}

}

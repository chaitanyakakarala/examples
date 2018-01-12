package com.kc.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kc.demo.factory.FileHandlerFactory;
import com.kc.demo.util.CommonUtil;

import junit.framework.Assert;

public class OperationsTest {

	@InjectMocks
	Operations operations;
	
	Scanner scanner = null;
	
	List<List<String>> fileElements;
	
	@Before
	public void init() throws Exception{
		MockitoAnnotations.initMocks(this);
		scanner = new Scanner(new FileInputStream(new File("src/test/resources/inputs.txt")));
		
		this.operations = Mockito.spy(new Operations());
		makeElements();
		operations.injectRepository(fileElements, new FileInputStream(new File("src/test/resources/inputs.txt")));
	}
	
	@Test
	public void testdisplayConsole(){
		operations.scanner = scanner;
		String out = operations.displayConsole();
		Assert.assertEquals("1", out.trim());
	}
	
	
	
	@Test
	public void testRecursiveCall(){
		operations.scanner = scanner;
		operations.recursiveCall();
	}
	
	@Test
	public void testRecursiveCall_errors() throws Exception{
		operations.injectRepository(fileElements, 
				new FileInputStream(new File("src/test/resources/inputs1.txt")));
	}
	
	public void makeElements(){
		fileElements =  FileHandlerFactory.getFileHandler(".csv")
                .readFiletoCollection("src/test/resources/test.csv")
                .stream()
                .filter(line -> !line.isEmpty() && line.contains(","))
                .map(item -> CommonUtil.repeatSplitWithSeperator(item, new ArrayList<String>()))
                .collect(Collectors.toList());
	}
	
}

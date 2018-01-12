package com.kc.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kc.demo.service.Operations;

public class AppInitializerTest {
	
	@Mock
	Operations operations ;
	
	@InjectMocks
	AppInitializer app;
	
	List<List<String>> fileElements = new ArrayList<List<String>>();
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		app = Mockito.spy(new AppInitializer(operations));	
	}
	
	@Test
	public void testReadFile(){
		fileElements = app.readFile("src/test/resources/test.csv",".csv");
		Assert.assertFalse(fileElements.isEmpty());
		Assert.assertEquals(5, fileElements.size());
	}
	
	@Test(expected = RuntimeException.class)
	public void testReadFile_Exp(){
		app.readFile("src/test/resources/test2.csv",".csv");
	}
	
	@Test
	public void testInjectData() throws Exception{
		Mockito.doNothing().when(operations).injectRepository(Mockito.any(),Mockito.any());
		app.initAndInjectOps(fileElements);
		Mockito.verify(operations,Mockito.times(1)).injectRepository(fileElements,System.in);
	}
	
	@Test
	public void testRunOperations() throws Exception{
		Mockito.doNothing().when(operations).recursiveCall();
		app.runOperations();
		Mockito.verify(operations,Mockito.times(1)).recursiveCall();
	}
	
}

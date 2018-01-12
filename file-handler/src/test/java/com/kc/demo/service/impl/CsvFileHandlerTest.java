package com.kc.demo.service.impl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kc.demo.service.FileHandler;

import junit.framework.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class CsvFileHandlerTest.
 */
public class CsvFileHandlerTest {

	/** The csvreader. */
	FileHandler csvreader ;

	/**
	 * Inits the.
	 */
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		csvreader =  Mockito.spy(new CsvFileHandler());
	}
	
	/**
	 * Test read fileto collection.
	 */
	@Test
	public void testReadFiletoCollection() {
		List<String> rows = csvreader.readFiletoCollection("src/test/resources/test.csv");
		
		Assert.assertNotNull(rows);
		Assert.assertEquals(7, rows.size());
		Assert.assertEquals("", rows.get(1));
	}
	
	/**
	 * Test read fileto collection excep.
	 */
	@Test(expected = RuntimeException.class)
	public void testReadFiletoCollection_Excep() {
		csvreader.readFiletoCollection("src/test/resources/test1.csv");
	}

	
}

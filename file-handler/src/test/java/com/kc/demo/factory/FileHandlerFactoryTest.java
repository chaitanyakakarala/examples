package com.kc.demo.factory;

import org.junit.Test;

import com.kc.demo.service.impl.CsvFileHandler;

import junit.framework.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class FileHandlerFactoryTest.
 */
public class FileHandlerFactoryTest {

	/**
	 * Testget file handler.
	 */
	@Test
	public void testgetFileHandler(){
		Assert.assertEquals(CsvFileHandler.class, FileHandlerFactory.getFileHandler(".csv").getClass());
	}

	/**
	 * Testget file handler othrs.
	 */
	@Test(expected = RuntimeException.class)
	public void testgetFileHandler_othrs(){
		FileHandlerFactory.getFileHandler(".txt");
	}
	
	/**
	 * Testget file handler null.
	 */
	@Test(expected = RuntimeException.class)
	public void testgetFileHandler_Null(){
		FileHandlerFactory.getFileHandler(null);
	}
}

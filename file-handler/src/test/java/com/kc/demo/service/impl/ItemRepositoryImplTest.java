package com.kc.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemRepositoryImplTest.
 */
public class ItemRepositoryImplTest {

	/** The item repository impl. */
	@InjectMocks
	ItemRepositoryImpl itemRepositoryImpl;
	
	/** The cols. */
	List<String> cols = new ArrayList<String>(5);
	
	/** The rows. */
	List<List<String>> rows = new ArrayList<>(3);

	/**
	 * Inits the.
	 */
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		
		cols.add("one");
		cols.add("two");
		cols.add("three");
		cols.add("28-Jan-2018");
		cols.add("15 GBP");
		
		rows.add(cols);
		rows.add(cols);
		rows.add(cols);
		
		itemRepositoryImpl = Mockito.spy(new ItemRepositoryImpl(rows));
		
	}
	
	/**
	 * Testget all elements.
	 */
	@Test
	public void testgetAllElements(){
		Assert.assertNotNull(itemRepositoryImpl.getAllElements());
		Assert.assertEquals(3,itemRepositoryImpl.getAllElements().size());
	}
	
	/**
	 * Test get all elements fail.
	 */
	@Test(expected = RuntimeException.class)
	public void testGetAllElements_Fail(){
		itemRepositoryImpl = Mockito.spy(new ItemRepositoryImpl(new ArrayList<List<String>>(0)));
		itemRepositoryImpl.getAllElements();
	}

	/**
	 * Test get row by id.
	 */
	@Test
	public void testGetRowById(){
		Assert.assertNotNull(itemRepositoryImpl.getRowById(1));
		Assert.assertEquals("two", itemRepositoryImpl.getRowById(1).get(1));
	}
	
	/**
	 * Test get row by id fail.
	 */
	@Test(expected = RuntimeException.class)
	public void testGetRowById_Fail(){
		Assert.assertNotNull(itemRepositoryImpl.getRowById(5));
		Assert.assertEquals("two", itemRepositoryImpl.getRowById(1).get(1));
	}
	
	/**
	 * Testget specific element from row.
	 */
	@Test
	public void testgetSpecificElementFromRow(){
		Assert.assertNotNull(itemRepositoryImpl.getSpecificElementFromRow(2, 2));
		Assert.assertEquals("three", itemRepositoryImpl.getSpecificElementFromRow(2, 2));
	}
	
	/**
	 * Testget specific element from row fail.
	 */
	@Test(expected = RuntimeException.class)
	public void testgetSpecificElementFromRow_Fail(){
		Assert.assertNotNull(itemRepositoryImpl.getSpecificElementFromRow(2, 20));
	}
	
	
}

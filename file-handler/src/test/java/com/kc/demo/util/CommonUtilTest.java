package com.kc.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtilTest.
 */
public class CommonUtilTest {

	/**
	 * Testrepeat split with seperator.
	 */
	@Test
	public void testrepeatSplitWithSeperator(){
		String input = "24-Jul-2010,35,24,43,33,26,44,27,3,MERLIN";
		List<String> result = new ArrayList<String>(0);
		result = CommonUtil.repeatSplitWithSeperator(input, result);
		
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals("35", result.get(1));
	}
	
	/**
	 * Testrepeat split with seperator null chk.
	 */
	@Test
	public void testrepeatSplitWithSeperator_NullChk(){
		List<String> result = new ArrayList<String>(0);
		result = CommonUtil.repeatSplitWithSeperator(null, result);
		
		Assert.assertTrue(result.isEmpty());
	}
	
	/**
	 * Test is nan.
	 */
	@Test
	public void testIsNan(){
		Assert.assertTrue(CommonUtil.isNaN("456789"));
		
		Assert.assertFalse(CommonUtil.isNaN("123sfa0"));
		
		Assert.assertFalse(CommonUtil.isNaN(null));
	}

	/**
	 * Test is date.
	 */
	@Test
	public void testIsDate(){
		Assert.assertTrue(CommonUtil.isDate("28-Jul-1990"));
		Assert.assertFalse(CommonUtil.isDate("30021990"));
	}
	
}

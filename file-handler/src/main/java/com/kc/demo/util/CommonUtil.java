package com.kc.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonUtil.
 */
public class CommonUtil {

    /**
     * Repeat split with seperator.
     *
     * @param item the item
     * @param result the result
     * @return the list
     */
    public static List<String> repeatSplitWithSeperator(String item, List<String> result) {
        String temp = null;
        if (null != item && item.indexOf(",") >0  ){
            temp = item.substring(0,item.indexOf(","));
            item = item.substring(item.indexOf(",")+1);
            result.add(temp);

        }else if (null != item ){
            result.add(item);
            item = null;
        }

        if (null != item ){
            repeatSplitWithSeperator(item,result);
        }
        return result;
    }

    /**
     * Checks if is na N.
     *
     * @param input the input
     * @return true, if is na N
     */
    public static boolean isNaN(String input){
        String numbers = "0123456789";
        if (null == input || input.isEmpty())
            return false;

        for (int i = 0;i< input.length();i++) {
            if (numbers.indexOf(input.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if is date.
     *
     * @param input the input
     * @return true, if is date
     */
    public static boolean isDate(String input){
    	String[] configuredDatePatterns = {"ddMMyy", "dd-MMM-yyyy"};
    	boolean returnVal = false;
    	
    		for(String format : configuredDatePatterns){
    			try{
    				SimpleDateFormat df = new SimpleDateFormat(format);
        			df.setLenient(true);
        			df.parse(input);
        			returnVal = true;
    			}catch(ParseException exp){
    				returnVal =  false;
    			}
    		}
    	return returnVal;
    }

}

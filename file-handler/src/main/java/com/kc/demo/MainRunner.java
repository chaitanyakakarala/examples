package com.kc.demo;

import java.util.List;

import com.kc.demo.service.Operations;
import com.kc.demo.util.CleanCodeUtil;

/**
 * The Class MainRunner.
 */
public class MainRunner {

    public static void main(String[] args) throws Exception {
    	if(args.length != 1){
    		throw new RuntimeException("Please provide exactly one argument for file wit path");
    	}
    	
    	String filename = args[0].trim();
    	String extension = filename.substring(filename.lastIndexOf("."));
    	
    	Operations operations = (Operations)CleanCodeUtil.makeInstance(Operations.class.getName());
    	AppInitializer app= (AppInitializer) CleanCodeUtil.makeInstance(AppInitializer.class.getName(),
    			Operations.class,
    			new Object[]{operations});
    	
    	List<List<String>> lists = app.readFile(filename, extension);
        System.out.println("Reading the file completed and present in memory");
        
        if(lists.isEmpty()){
        	throw new RuntimeException("Provided file is empty");
        }
        	
        app.initAndInjectOps(lists);
        app.runOperations();
        System.out.println("Exit Application success");
    }

}

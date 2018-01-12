package com.kc.demo.service;

import com.kc.demo.service.impl.ItemRepositoryImpl;
import com.kc.demo.util.CommonUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Operations.
 */
public class Operations {

   /** The item repository. */
   private ItemRepository itemRepository;

   /** The scanner. */
   Scanner scanner = null;

   InputStream inputEntries = null;
   /**
    * Instantiates a new operations.
    */
   public Operations(){
   }
   
   public void injectRepository(List<List<String>> fileElements,InputStream entriesOfConsole){
	   this.itemRepository = new ItemRepositoryImpl(fileElements);
	   this.inputEntries = entriesOfConsole;
   }
    /**
     * Display console.
     *
     * @return the string
     */
    public String displayConsole() {
    	
    		System.out.println("<==============================================>");
            System.out.println("Please Enter the as per below options for requred Operation");
            System.out.println("Press 1 for List All Rows and elements as list");
            System.out.println("Press 2 for elements of a partular row list");
            System.out.println("Press 3 for particular element in a row");
            System.out.println("Press 5 to exit program");
            
            String input = scanner.nextLine();
            return input;
        
    }

    /**
     * Do operation for row.
     */
    public void doOperationForRow() {
        System.out.println("Please enter row index as integer");
       
        	 String rowindex = scanner.nextLine();
             if (CommonUtil.isNaN(rowindex)) {
                 System.out.println();
                 try{
                     System.out.println("Below is requested row from csv \n"+
                 itemRepository.getRowById(Integer.parseInt(rowindex)-1));
                 }catch (Exception exp) {
                     System.err.println(exp.getLocalizedMessage());
                 }
             } else {
                 System.out.println("Please pass numeric value for row index");
             }
    }

    /**
     * Do operation for element.
     */
    public void doOperationForElement() {
        System.out.println("Please enter row index as integer");
        try {
        	 String rowIndx = scanner.nextLine();
             if (!CommonUtil.isNaN(rowIndx)) {
                // System.err.println("Please pass numeric value for row index");
                 throw new RuntimeException("Please pass numeric value for row index");
             }
             System.out.println("Please enter Column index as integer");
             String colIndx = scanner.nextLine();
             if (!CommonUtil.isNaN(colIndx)) {
                // System.err.println("Please pass numeric value for column index");
                 throw new RuntimeException("Please pass numeric value for column index");
             }
             String item = itemRepository.getSpecificElementFromRow(Integer.parseInt(rowIndx)-1,
                     Integer.parseInt(colIndx)-1);
             String dataType = "string";
             if(CommonUtil.isNaN(item)){
            	 dataType = "number";
             }else if(CommonUtil.isDate(item)){
            	 dataType = "date";
             }
            	 
             System.out.println(item+" is the element in specified row and column and its data type is "+dataType);
             
        }catch (Exception exp) {
            System.out.println(exp.getLocalizedMessage());
        }
    }

    /**
     * Do operation.
     *
     * @param input the input
     */
    public void doOperation(String input) {
        switch (input.trim().toUpperCase()) {
            case "1" :
                System.out.println("Below is list of complete rows and elements");
                itemRepository.getAllElements().forEach(item -> System.out.println(item));
                break;
            case "2" :
                doOperationForRow();
                break;
            case "3" :
                doOperationForElement();
                break;
            case "5" :
                break;
            default :
                System.err.println("Please pass case as mentioned ");
                break;
        }

    }

    /**
     * Recursive call.
     */
    public void recursiveCall() {
        try{
        	scanner = new Scanner(inputEntries);
        	String input = displayConsole();
            doOperation(input);
            while (!"5".equals(input.trim())) {
                input = displayConsole();
                doOperation(input);
            }
        }catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }finally{
        	scanner.close();
        	scanner = null;
        }
    }

}

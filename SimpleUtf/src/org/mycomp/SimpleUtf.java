package org.mycomp;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class SimpleUtf {

	public  HashMap<String, List> validateDetailRecords(String sourcefile,String sourcefile_copy){
		System.out.println("in validation class");
		BufferedReader br=null;
		HashMap<String,List> recordStatusMap =null;
		File file=null;
		try{
			file=new File(sourcefile);
			
			int cnt=0;
				
			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			//SimpleUtf ediValidator=new SimpleUtf();
			while (((sCurrentLine = br.readLine()) !=null)) {
				
				Pattern p =Pattern.compile("[\u0000-\u9fff]");	
				Matcher m = p.matcher(sCurrentLine);
				
				System.out.println(m.matches()+" "+sCurrentLine.length()); 
				System.out.println(sCurrentLine);
				if(m.matches()||sCurrentLine.contains("?")){
					cnt=cnt+1;
					break;
				}
			}
			if(cnt>0){
				System.out.println("divert to utf-16");
				//recordStatusMap=ediValidator.validateDetailRecordsForUTF16(sourcefile, sourcefile_copy);
			}else{
				System.out.println(" divert to utf-8");
				//recordStatusMap=ediValidator.validateDetailRecordsForUTF8(sourcefile, sourcefile_copy);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(br!=null){
					br.close();	
				}
							
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return recordStatusMap;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleUtf ediValidator=new SimpleUtf();
		ediValidator.validateDetailRecords("MD001Or", "cpy-MD001Or");
	}

}

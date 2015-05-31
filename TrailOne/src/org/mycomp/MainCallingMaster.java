package org.mycomp;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import javax.xml.transform.stream.StreamSource;

import org.milyn.Smooks;
import org.milyn.io.StreamUtils;
import org.milyn.payload.JavaResult;



public class MainCallingMaster {

	private static byte[] messageIn = readInputMessage();
	
	private static byte[] readInputMessage() {
        try {
            return StreamUtils.readStream(new FileInputStream("input-message.edi"));
        } catch (Exception e) {
            e.printStackTrace();
            return "<no-message/>".getBytes();
        }
    }
	
	public static void main(String args[]){
		// Instantiate Smooks with the config...
		Smooks smooks = null;
		 
		try {
			
			smooks = new Smooks("smooks-config.xml");
		    JavaResult result = new JavaResult();
		 
		    // Filter the input message to the JavaResult...
		    smooks.filterSource(new StreamSource(new ByteArrayInputStream(messageIn)), result);
		 
		    Object ojb =  result.getBean("file");
		    System.out.println(ojb.getClass().getName());
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
		    smooks.close();
		}
	}
}

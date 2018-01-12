package com.kc.demo.factory;

import com.kc.demo.service.FileHandler;
import com.kc.demo.service.impl.CsvFileHandler;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating FileHandler objects.
 */
public class FileHandlerFactory {

    /**
     * Gets the file handler.
     *
     * @param type the type
     * @return the file handler
     */
    public static FileHandler getFileHandler(String type) {
    	FileHandler fileHandler = null;
    	System.out.println("type is "+type);
        switch (type.toLowerCase()){
        	case ".csv" :
        		fileHandler =  new CsvFileHandler();
        		break;
            default:
                throw new RuntimeException("Not yet Implmented");
        }
        return fileHandler;
    }

}

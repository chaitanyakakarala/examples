package com.kc.demo.util;

import java.lang.reflect.Constructor;

/**
 * The Class CleanCodeUtil.
 */
public class CleanCodeUtil {

	/**
	 * Make instance.
	 *
	 * @param className the class name
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object makeInstance(String className)throws Exception{
		return Class.forName(className).newInstance();
	}
	
	/**
	 * Make instance.
	 *
	 * @param className the class name
	 * @param classType the class type
	 * @param args the args
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object makeInstance(String className, Class classType, Object[] args)throws Exception{
		 Class cls = Class.forName(className);
		 Constructor<?> ctor = cls.getDeclaredConstructor(classType);
		 return ctor.newInstance(args);
	}
}

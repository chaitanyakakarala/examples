package org.errichen;

import java.lang.annotation.Annotation;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Starting the mapping main");
		Class c=Sample.class;
		Annotation [] anns=c.getAnnotations();
		System.out.println(anns.length);
		for(Annotation ann : anns){
			System.out.println(ann.annotationType());
			System.out.println(ann.toString());
		}
	}
}

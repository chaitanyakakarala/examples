package org.errichen;

@MyAnnotation
public class Sample {
	
	public Sample() {
		// TODO Auto-generated constructor stub
	}
	
	private int a=4;
	@MyAnnotation
	public void displayA(){
		System.out.println(this.a);
	}
}

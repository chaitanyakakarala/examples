package org.mycomp;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(endpointInterface = "org.mycomp.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public HelloWorldImpl() {
		System.out.println("We are creating implementation class method");
	}
	
	@Override
	public Object getHelloWorldAsString(String name) {
		System.out.println("We are calling webservice method");
		ArrayList list=new ArrayList();
		list.add(name);
		
		return list;
	}

}

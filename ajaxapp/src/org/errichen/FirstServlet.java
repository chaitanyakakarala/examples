package org.errichen;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response){
		System.out.println("we are in first servlet request object");
		PrintWriter out=null;
		try{
			out=response.getWriter();
			out.println("we are in first servlet");
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
		}
	}
}

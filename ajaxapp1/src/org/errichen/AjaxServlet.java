package org.errichen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AjaxServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("we are in service method of ajax servlet");
		System.out.println(request.getParameter("fone"));
		PrintWriter out=null;
		try{
			out=response.getWriter();
			out.println("true");
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			
		}
	}

}

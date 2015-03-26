package org.mycomp;

import java.io.UnsupportedEncodingException;

public class ResolveUtf8 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String sCurrentLine="柯尼卡美能达办公系统（中国）有限公司";
		System.out.println(sCurrentLine.length());
		System.out.println(sCurrentLine.getBytes("UTF-8").length); 
	}

}

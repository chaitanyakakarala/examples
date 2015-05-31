package org.mycomp.beans;

import java.util.List;

public class TotalFile {

	private Header header;
	private List<LineDetails> linedetails ;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<LineDetails> getLinedetails() {
		return linedetails;
	}
	public void setLinedetails(List<LineDetails> linedetails) {
		this.linedetails = linedetails;
	}
}

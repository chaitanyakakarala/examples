package org.mycomp;

public class HelloBean implements SessionBean{
		  public void ejbCreate() {
		    System.out.println("calling ejbcreate....");
		  }

		  public void ejbRemove() {
		    System.out.println("calling ejbremove");
		  }

		  public void ejbActivate() {
		    System.out.println("calling ejbactivate");
		  }

		  public void ejbPassivate() {
		    System.out.println("calling ejbpassivate");
		  }

		  public void setSessionContext(SessionContext sc) {
		    System.out.println("calling session context");
		  }
		  public String sayHello(){
			return "Hello World..!";  
		  }
		  
}

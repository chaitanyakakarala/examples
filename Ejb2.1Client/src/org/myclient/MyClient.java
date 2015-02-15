package org.myclient;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.*;
import org.mycomp.*;
import javax.ejb.*;

public class MyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Properties p = new Properties();
		      p.put(Context.INITIAL_CONTEXT_FACTORY,
		          "org.jnp.interfaces.NamingContextFactory");
		      p.put(Context.URL_PKG_PREFIXES,
		          "org.jboss.naming:org.jnp.interfaces");
		      p.put(Context.PROVIDER_URL, "localhost");
		      InitialContext ctx = new InitialContext(p);

		      HelloHome home = (HelloHome) ctx.lookup("hello");
		      HelloRemote rem=home.create();
		      String hel=rem.sayHello();
		      System.out.println(hel);			
		}catch(Exception exp){
			
		}finally{
			
		}
	}

}

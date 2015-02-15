import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.mycomp.HelloWorld;


public class HelloWorldClient {

	public static void main(String[] args) {
		try{
			URL url=new URL("http://localhost:9999/ws/hello?wsdl");			
			QName qname = new QName("http://mycomp.org/", "HelloWorldImplService");
			Service service = Service.create(url, qname);			 
	        HelloWorld hello = service.getPort(HelloWorld.class);
	        System.out.println(hello.getHelloWorldAsString("chaitu"));
		}catch(Exception exp){
			exp.printStackTrace();
		}
	}

}

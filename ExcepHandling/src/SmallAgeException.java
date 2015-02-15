
public class SmallAgeException extends RuntimeException {

	
	
	SmallAgeException(){
		System.out.println("Empty constructor");
	}
	
	SmallAgeException(String msg){
		super(msg);		
	}

}

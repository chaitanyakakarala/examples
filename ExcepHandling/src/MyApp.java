
public class MyApp {

	public static void main(String[] args) {

		int age=2;
		try{
			if(age<20){
				throw new SmallAgeException("Good news");
			}else{
				System.out.println("Happy date");
			}			
		}catch(SmallAgeException sae){
			System.out.println(sae.getMessage());
		}finally{
		}
	}

}

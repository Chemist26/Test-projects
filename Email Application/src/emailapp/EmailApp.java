package emailapp;

public class EmailApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		Email em1 = new Email("Antoan","Stoykov");
		
		//em1.setAlternateEmail("randomEmail@gmail.com");
		
		System.out.println("Your altern email is: " + em1.getAlternateEmail());
		
		System.out.println(em1.showInfo());
	}	
}

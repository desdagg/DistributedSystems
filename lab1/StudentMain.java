import ie.dit.comp.student.*;

public class StudentMain {

	public static void main(String args[]) {
		
		StudentAccount s = new StudentAccount("Mark", 21, "11234", "goatstown , Dublin");
		
		System.out.println("name = " + s.getName());
		System.out.println("address = " + s.getAddress());
		
	}
}
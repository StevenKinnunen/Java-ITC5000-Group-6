import java.util.Scanner;

public class HumberCollegeAdmissions {
	public static void main(String args[]) {
		
		System.out.println("Welcome to Humber College");
		
		Scanner input = new Scanner(System.in);
	
		System.out.println("Please enter a username: ");
		
		String username = input.next();
		
		System.out.println("Thank you " + username + ", please enter a password:");
	}
}

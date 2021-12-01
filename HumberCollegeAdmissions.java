import java.util.Arrays;
import java.util.Scanner;

public class HumberCollegeAdmissions {
	public static void main(String args[]) {
		
		System.out.println("Welcome to Humber College");
		
		Scanner input = new Scanner(System.in);
	
		System.out.println("Please enter a username: ");
		
		String username = input.next();
		
		System.out.println("Thank you " + username + ", please enter a password:");
		
		String humber_password = input.next();
		if(passwordValidation(humber_password)){
			System.out.println("Thank you " + username + ", please enter the number of students: ");
		}
		
		int student_num = 3; //This is a placeholder value, final variable will be assigned to the output of a method 
		System.out.println(Arrays.toString(nameList(student_num))); //Prints output of nameList with student_num as argument, will be saved to variable in final program
	}
	
public static Boolean passwordValidation(String humber_password) {
		
		Scanner input = new Scanner(System.in);
		
		int attempt = 0;
		
		Boolean valid_password = false;
		
		
		
			
		while(attempt < 3){ //while loop contains a for loop for accumulators but also conditional statements to assess password criteria
			attempt++;
			int upper_letter = 0;
			int num = 0;
			int spec_char = 0;
			
			for(int index = 0; index < humber_password.length(); index++) {
				if (Character.isDigit(humber_password.charAt(index))) {
					num += 1; //the number of digits in the password
			}
				else if(Character.isUpperCase(humber_password.charAt(index))) {
					upper_letter += 1; //the number of uppercase letters in the password
			}
				else if(!Character.isLetterOrDigit(humber_password.charAt(index))) {
					spec_char += 1; //the number of special characters in the password
			}
		
		
			} 
			
			if((humber_password.length() >= 10 && (num == 2 || num == 3) && upper_letter >= 1 && spec_char == 1)) {
				valid_password = true; //assuming all criteria are met, valid_password evaluates to true and program breaks out of loop
				break;
		}
			else if(attempt < 3 && !(humber_password.length() >= 10 && (num == 2 || num == 3) && upper_letter >= 1 && spec_char == 1)) { 
			
			System.out.println("You only have " + (3 - attempt) + " attempts left. Please enter a VALID password: ");
			humber_password = input.next();  //assuming one or more of the criteria are not met, the user has 2 more chances (iterations through the loop) to enter 
											// the correct password
		
		} 
			else if (!(humber_password.length() >= 10 && (num == 2 || num == 3) && upper_letter >= 1 && spec_char == 1)){
				System.out.println("Sorry, you have run out of attempts. This program is terminating now.");
				System.exit(0);  //assuming 3 incorrect attempts in a row, program terminates
		}
			}
		
	
		
		return valid_password;  //returns value of valid_password
	
}
	
public static  String nameList(int student_num) {
		
		Scanner input = new Scanner(System.in);
		int count = 0; // accumulator 
		String[] student_names = new String[student_num]; //ensuring the list is the same length as the number provided
		for(count = 0; count < student_num; count++) { //iterating through count as long as it is less than student_num
			System.out.println("Please enter a name: ");
			student_names[count] = input.nextLine(); //enter the names with spaces until count is equal to student_num
		}
		
		
		return student_names; //return user readable array of names
		
	}
}


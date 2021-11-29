import java.util.Arrays;
import java.util.Scanner;

public class HumberCollegeAdmissions {
	public static void main(String args[]) {
		
		System.out.println("Welcome to Humber College");
		
		Scanner input = new Scanner(System.in);
	
		int student_num = 3;  //This is a placeholder value, final variable will be assigned to the output of a method 
		System.out.println(nameList(student_num));
	}
	public static  String nameList(int student_num) {
		
		Scanner input = new Scanner(System.in);
		int count = 0; // accumulator 
		String[] student_names = new String[student_num]; //ensuring the list is the same length as the number provided
		for(count = 0; count < student_num; count++) { //iterating through count as long as it is less than student_num
			System.out.println("Please enter a name: ");
			student_names[count] = input.nextLine(); //enter the names with spaces until count is equal to student_num
		}
		
		input.close();
		return Arrays.toString(student_names); //return user readable array of names
		
	}
}

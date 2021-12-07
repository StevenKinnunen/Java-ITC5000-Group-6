import java.util.Arrays;
import java.util.Scanner;

public class HumberCollegeAdmissionsV2 {
	public static void main(String args[]) {
		
		System.out.println("Welcome to Humber College"); 
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter a username: "); //prompt user to enter a username
		
		String username = input.nextLine();
		
		System.out.println("Thank you " + username + ", please enter a password:");
		
		String humber_password = input.nextLine(); 
		if(passwordValidation(humber_password)){ //check if the password is valid using humber_password as an argument for passwordValidation
												// if passwordValidation evaluates to true, program continues, otherwise program terminates
			System.out.println("Thank you " + username + ", please enter the number of students: "); // asking user for number of students 
		}
		
		int student_num = validNum(input.nextInt()); //calling validNum method on user input and storing result in student_num variable
		System.out.println(student_num);
		
		
		String[] student_list = nameList(student_num); // creating 1D string array the store each students name using nameList method, student_num as argument
		System.out.println(Arrays.toString(student_list)); //Arrays.toString allows us to print user-readable string array of student_list
	
		
		System.out.println("Please enter the grades (0-100) for each student.");
		int[][] student_grades = studentGrades(student_list); //creating a 2D integer array to store each students grades using studentGrades method
		System.out.println(Arrays.deepToString(student_grades));  //Arrays.deepToString converts the array to a user-readable string array
		
		
		System.out.println(" "); // these lines are just to ensure that the output has some space between lines
		int[][] gpa_scores = studentGpaNum(student_grades); //creating 2D array of gpa_scores by calling studentGpaNum method on student_grades
		System.out.println(Arrays.deepToString(gpa_scores));
		
		
		System.out.println(" ");
		String[][] acceptance_list = schoolList(gpa_scores, student_list); //call schoolList method on gpa_scores and student_list
		schoolReport(acceptance_list); //school report method to get a more user friendly report of which school each student got accepted into (or none at all)
	
		
		
		System.out.println(" ");
		int[] school_totals = acceptedList(acceptance_list);
		acceptanceReport(school_totals); // report for the number of each students accepted in total as well as for each school
		
		System.out.println(" ");
		notAccepted(acceptance_list); // total number of students not accepted into Humber
		
		System.out.println(" ");
		highestGpaSchool(acceptance_list, gpa_scores);  //method prints the student with the highest GPA in each school (as well as amongst those not accepted)
		
		input.close();
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
				
		}
			else if(attempt < 3) { 
			
			System.out.println("You only have " + (3 - attempt) + " attempts left. Please enter a VALID password: ");
			humber_password = input.next();  //assuming one or more of the criteria are not met, the user has 2 more chances (iterations through the loop) to enter 
											// the correct password
		
		} 
			else{
				System.out.println("Sorry, you have run out of attempts. This program is terminating now.");
				System.exit(0);  //assuming 3 incorrect attempts in a row, program terminates
		}
			}
		
	
		
		return valid_password;  //returns value of valid_password
	
}

public static int validNum(int num) {
	Scanner input = new Scanner(System.in); //although scanner has already been initialized in main, must be initialized in this method
	
	int attempt = 0; //we set initial attempt at 0
	
	
	while(attempt < 3 && (num > 50 || num < 1)) { //the program checks if the attempt remains less than 3 AND if the number is either
		attempt++;                                // greater than 50 OR less than 1, attempt increments by 1 for each iteration of loop
		
		if(attempt < 3 && (num > 50 || num < 1)) { // program allows 2 more attempts to input correct number between 1 and 50 inclusive
			System.out.println("You only have " + (3 - attempt) + " attempts left. Please enter a VALID integer from 1 to 50: ");
			num = input.nextInt();
		}
		else{
			System.out.println("Sorry, you have run out of attempts. This program is terminating now.");
			System.exit(0); //upon invalid third attempt, the program terminates
		}
		
	}
	
	return num;  //the latest value of num is returned if the program is not terminated
}
	
public static  String[] nameList(int student_num) {
		
		Scanner input = new Scanner(System.in);
		int count = 0; // accumulator 
		String[] student_names = new String[student_num]; //ensuring the list is the same length as the number provided
		for(count = 0; count < student_num; count++) { //iterating through count as long as it is less than student_num
			System.out.println("Please enter a name: ");
			student_names[count] = input.nextLine(); //enter the names with spaces until count is equal to student_num
		}
		
		
		return student_names; //return user readable array of names
		
	}

public static int[][] studentGrades(String[] student_names){
	int[][] grades_list = new int[student_names.length][7];
	Scanner input = new Scanner(System.in);
	for(int student = 0; student < grades_list.length; student++) {
		
		grades_list[student][0] = student; // we code the first column as a numeric representation (student number) of each person, from 0 onwards.
		System.out.println("Please enter the grades (0-100) of " + student_names[student] + ".");
		
		System.out.println("Input " + student_names[student] + "'s mark in Math: "); // For each student, we prompt the user to enter grades
		grades_list[student][1] = input.nextInt();                                     // iterating through each student, a grade replaces default
																						// value of 0 when each grade is entered
		System.out.println("Input " + student_names[student] + "'s mark in Science: "); 
		grades_list[student][2] = input.nextInt();
		
		System.out.println("Input " + student_names[student] + "'s mark in Language: ");
		grades_list[student][3] = input.nextInt();
		
		System.out.println("Input " + student_names[student] + "'s mark in Drama: ");
		grades_list[student][4] = input.nextInt();
		
		System.out.println("Input " + student_names[student] + "'s mark in Music: ");
		grades_list[student][5] = input.nextInt();
		
		System.out.println("Input " + student_names[student] + "'s mark in Biology: ");
		grades_list[student][6] = input.nextInt();
		
		
	}
	
	return grades_list; //finally, the method returns the final array with student number in first column, then math grade, science grade, etc.
}

public static int[][] studentGpaNum(int[][] student_marks){
	
	int[][] GPA_list = new int[student_marks.length][2];
	
	for(int student = 0; student < student_marks.length; student++) {
		GPA_list[student][0] = student; // keeping the numeric representation of students
		GPA_list[student][1] = (student_marks[student][1] * 4 + // we are multiplying the grades by the weight/course hours for each subject
				student_marks[student][2] * 5 + 
				student_marks[student][3] * 4 +// we then add all values together
				student_marks[student][4] * 3 +
				student_marks[student][5] * 2 +
				student_marks[student][6] * 4) / 22; //Finally, we divide by total number of credit hours, using parentheses to enforce 
													   // desired order of operations
		
	}
	
	return GPA_list; // returns the array of student numbers and their gpa scores
}

public static String[][] schoolList(int[][] gpa, String[] names){
	String[][] acceptance_list = new String[names.length][2];
	
	for(int student = 0; student < names.length; student++) { 
		acceptance_list[student][0] = names[student]; //for each student, we get their name and add it to the acceptance_list array in first 
		                                              // index 
		if(gpa[student][1] >= 90 && gpa[student][1]  <= 100) {                   
			acceptance_list[student][1] = "School of Engineering"; //We check the Gpa for 4 different scenarios, GPA greater or equal to 90
		}														   // means the student gets accepted to Engineering
		else if(gpa[student][1] >= 80) {
			acceptance_list[student][1] = "School of Business";
		}
		else if(gpa[student][1] >= 70) {
			acceptance_list[student][1] = "Law School";
		}
		else {
			acceptance_list[student][1] = "Not accepted";
		}
	}
	
	return acceptance_list;
	
}

public static int[] acceptedList(String[][] school_list){
	int[] accepted_totals = new int[4];
	
	int eng_count = 0;
	int bus_count = 0;
	int law_count = 0;
	int total = 0;
	
	for(int student = 0; student < school_list.length; student++) {  
		if(school_list[student][1] == "School of Engineering") {
			eng_count += 1;  
			total += 1;
		}
		else if(school_list[student][1] == "School of Business") {
			bus_count += 1;
			total += 1;
		}
		else if(school_list[student][1] == "Law School") {
			law_count += 1;
			total += 1;
	}
		
}
	accepted_totals[0] = eng_count;
	accepted_totals[1] = bus_count;
	accepted_totals[2] = law_count;
	accepted_totals[3] = total;
	
	return accepted_totals;

}

public static void acceptanceReport(int[] accepted_list) {
	
	String[] school_names = {"School of Engineering", "School of Business", "Law School", "Total Accepted"};
	
	System.out.println("Report on the number of students accepted to each school: ");
	for(int school = 0; school < 4; school++) {
		System.out.println(school_names[school] + ": " + accepted_list[school]);
	}
}

public static void notAccepted(String[][] school_list) {
	
	System.out.println("Number of students not accepted into Humber: ");
	int num_students = 0;
	
	for(int student = 0; student < school_list.length; student++) {
		if(school_list[student][1] == "Not accepted") {
			num_students += 1;
		}
	}
	
	System.out.println(num_students);
}

public static void schoolReport(String[][] school_list) {
	System.out.println("Student acceptance report:");
	for(int student = 0; student < school_list.length; student++) {
		System.out.println(school_list[student][0] + "'s eligibility: " + school_list[student][1]);
	}
}

public static void highestGpaSchool(String[][] school_list, int[][] gpa_list){
	
	System.out.println("Report on students with the highest GPA per school: ");
	
	int eng_max = 0;
	String eng_name = "No one";
	
	int bus_max = 0;
	String bus_name= "No one";
	
	int law_max = 0;
	String law_name = "No one";
	
	int not_max = 0;
	String not_name = "No one";
	
	for(int student = 0; student < school_list.length; student++) {
		if(school_list[student][1] == "School of Engineering" && gpa_list[student][1] > eng_max) {
			eng_max = gpa_list[student][1];
			eng_name = school_list[student][0];
		}
		else if (school_list[student][1] == "School of Business" && gpa_list[student][1] > bus_max) {
			bus_max = gpa_list[student][1];
			bus_name = school_list[student][0];
		}
		else if (school_list[student][1] == "Law School" && gpa_list[student][1] > law_max) {
			law_max = gpa_list[student][1];
			law_name = school_list[student][0];
		}
		else if (school_list[student][1] == "Not accepted" && gpa_list[student][1] > not_max) {
			not_max = gpa_list[student][1];
			not_name = school_list[student][0];
		}
		
	}
	
	System.out.println("Highest GPA in Engineering is " + eng_name + " with a GPA of: " + String.valueOf(eng_max));
	System.out.println("Highest GPA in Business is " + bus_name + " with a GPA of: " + String.valueOf(bus_max));
	System.out.println("Highest GPA in Law is " + law_name + " with a GPA of: " + String.valueOf(law_max));
	System.out.println("Highest GPA for those not accepted is " + not_name + " with a GPA of: " + String.valueOf(not_max));
}

}

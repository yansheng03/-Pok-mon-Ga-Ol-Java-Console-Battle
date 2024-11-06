import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

//The Controller class handles user input and provides methods to retrieve integers and strings
public class Controller{
	private Scanner input;
	private Screen screen;
	
    // Constructor initializes the Scanner for user input
	public Controller() {
		input = new Scanner(System.in);
		screen = new Screen();
	}
	
    // Method to get an integer from the user
	public int getInt() {
	    int number = input.nextInt();
	    return number;
	}


    // Method to get a string from the user
	public String getString() {
		return input.nextLine();
	}
	
    // Method to get the next token (word) from the user input
	public String getNext() {
		return input.next();
	}
	
    // Method to read Enter key
	public void enterEnterKey(){
		screen.displayMessageLine("Press enter to continue....");
	    try {
	        System.in.read();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

	// Method to get a valid integer input within a specified range
	public int getValidInt(String prompt, int min, int max) {
	    int userInput = 0;
	    boolean validInput = false;
	    while (!validInput) {
	        try {
	            screen.displayMessage(prompt);
	            userInput = getInt();
	            if (userInput >= min && userInput <= max) {
	                validInput = true;
	            } else {
	                screen.displayMessageLine("Please enter a valid input between " + min + " to " + max + ".");
	            }
	        } catch (InputMismatchException e) {
	            screen.displayMessageLine("Please enter a valid integer.");
	            getString();
	        }
	    }
	    return userInput;
	}
}
/**
 * The Screen class provides methods for displaying messages to the console.
 * It is used for output messages in the game.
 */

public class Screen {
	//Displays a message without a new line.
	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	//* Displays a message with a new line.
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	// Method to print a line for better console separation
	public void printline() {
		System.out.println("\n\n\n*********************************************************");
	}
}

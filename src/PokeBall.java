/**
 * The PokeBall class represents a type of PokeBall in the Pokemon game.
 * It holds information such as the ball's name and grade.
 */

public class PokeBall {
	// The name of the PokeBall
	private String name;
    // The grade or effectiveness of the PokeBall
	private int grade;
	
	// Constructor for creating a new PokeBall with a given name and grade
	public PokeBall(String name, int grade) {
		this.name = name;
		this.grade = grade;
	}
	
	//Getter & Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

    // Override the toString method to provide a formatted string representation of the PokeBall
	@Override
	public String toString() {
		return String.format("PokeBall [grade=%s, name=%s]", grade, name);
	}

}
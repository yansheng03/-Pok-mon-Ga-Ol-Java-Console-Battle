import java.util.ArrayList;
/**
 * The Player class represents a player in the Pokemon game.
 * It holds information such as the player's name, owned Pokemon, and score.
 */

public class Player {
	private String name;
    // List of Pokemon owned by the player
	private ArrayList<Pokemon> player_pokemons;
	private int score = 0;
	
	//Constructor for creating a new player with a given name 
	//create empty pokemon list
	public Player(String name) {
		this.name = name;
		player_pokemons = new ArrayList<Pokemon>();
	}
	
	//getter & setter
	public String getName() {
		return name;
	}
	
	public ArrayList<Pokemon> getPlayer_pokemons() {
		return player_pokemons;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}	
	
    // Method to add a Pokemon to the player's list
	public void addPokemon(Pokemon p) {
		getPlayer_pokemons().add(p);
	}
	
    // Method to remove a dead Pokemon from the player's list
	public void pokemondead(Pokemon p) {
		player_pokemons.remove(p);
	}
	
	// Method to add a specified value to the player's score
	public void addScore(int value) {
		setScore(getScore()+value);
	}
	
	


}

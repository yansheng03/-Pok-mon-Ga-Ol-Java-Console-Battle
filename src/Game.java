import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
//The main class representing the Pokemon game
public class Game {
	
	private Player player;
	private Screen screen;
	private Controller controller;
	private Battle battle;
	private Database player_database;
	private Database enemy_database;
	private ScoreManager scoreManager;
    protected static Pokemon playerpnow;
	protected static Pokemon enemypnow;
	protected static PokeBall userBall;
	private Random rand;
	private boolean running = true;
	
	private ArrayList<Pokemon> wildpokemons;
	private ArrayList<Pokemon> enemypokemons;
	
	// Constructor for the Game class
	public Game() {
		screen = new Screen();
		controller = new Controller();
		player_database = new Database();
		enemy_database = new Database();
		scoreManager = new ScoreManager("score.txt");
		enemypokemons = new ArrayList<Pokemon>();
		rand = new Random();
	}
	
	//setter & getter
	public Pokemon getPlayerpnow() {
		return playerpnow;
	}
	
	public void setPlayerpnow(Pokemon playerpnow) {
		this.playerpnow = playerpnow;
	}
	
	public Pokemon getEnemypnow() {
		return enemypnow;
	}
	
	public void setEnemypnow(Pokemon enemypnow) {
		this.enemypnow = enemypnow;
	}
	
	

	// Main Loop Method
	public void run() {
		player= new Player(displayMenu());
		screen.displayMessageLine("\nHello, " + player.getName()+"!!");
		
		while (running) {
			displaywildPokemon();
			screen.printline();
			choosePokemon();
			controller.enterEnterKey();
			screen.printline();
			
			if(battlestart()) {
				controller.enterEnterKey();
				screen.printline();
				screen.displayMessageLine("\nNow is Pokemon Catch Time !");
				catchpokemon(enemypokemons.get(0));
				screen.printline();
				catchpokemon(enemypokemons.get(1));
				for (Pokemon pokemon : player.getPlayer_pokemons()) {
					pokemon.resetHp();
				}
				convertDisk();
		        scoreManager.updateScore(player.getName(), player.getScore());
				scoreManager.displayTopScores();
				running = false;
			}
			else {
				screen.displayMessageLine("Game Over!!!!");
		        scoreManager.updateScore(player.getName(), player.getScore());
				scoreManager.displayTopScores();
				running = false;
			}
		}
	}
	
	// let user choose the pokemon that might appear in the battle
	public void displaywildPokemon() {
		screen.displayMessageLine("\nFive wild Pokémon have appeared!");
		screen.displayMessageLine("Choose the Pokemon you want to meet");
		screen.displayMessageLine("It might appear in the battle!");
		wildpokemons = enemy_database.generaterandomPokemon(5);
		printPokemons(wildpokemons);
		int number = controller.getValidInt("Which Pokemon you want to choose (input number): ", 1, 5);
		int chosenPokemonIndex = number - 1;
		//make the chosenpokemon might appear
		enemypokemons.add(wildpokemons.get(chosenPokemonIndex));
		enemy_database.getPokemons().remove(enemypokemons.get(0));
		for (int i=0;i<3;i++) {
			enemypokemons.add(enemy_database.generaterandomPokemon(1).get(0));
		}	
		//random choose
		Collections.shuffle(enemypokemons);
		enemypokemons.remove(enemypokemons.size()-1);
		enemypokemons.remove(enemypokemons.size()-1);
	}	
	
	// Method for the player to choose a Pokemon
	public void choosePokemon() {
		screen.displayMessageLine("Before the battle, you can get a Pokemon");
		screen.displayMessageLine("You can use it in the battle");
		screen.displayMessageLine("Choose a Pokémon to add to your backpack!!");
		wildpokemons = player_database.generaterandomPokemon(3);
		printPokemons(wildpokemons);
	    int number = controller.getValidInt("Choose the Pokemon you want to catch: ", 1, 3);
	    player.addPokemon(wildpokemons.get(number - 1));
	 }
	
	// Method to display the game menu and get the player's name
	public String displayMenu() {
		screen.displayMessageLine("Welcome to Pokemon Ga-Ole!!\n");
		screen.displayMessage("Please enter your name : ");
		return controller.getString();
	}
	
	// Method to initiate and control the battle sequence
	public boolean battlestart() {
	 	screen.displayMessageLine("The Pokemon around you heard your Pokemon's roar!");
	    screen.displayMessageLine("There are two wild Pokemons! Battle to catch them!");
	    printPokemons(enemypokemons);
		controller.enterEnterKey();
		screen.printline();
		getsupportpokemon();
		controller.enterEnterKey();
		screen.printline();
	    screen.displayMessageLine("Your First Enemy Pokemon to battle is\n " + enemypokemons.get(0));
	    setEnemypnow(enemypokemons.get(0));
	    Database.drawPokemon(enemypnow);
		controller.enterEnterKey();
	    screen.displayMessageLine("\n\nWhich Pokemon you want to send up (1/2): ");
	    screen.displayMessageLine(String.format("1 - %s\n2 - %s\n",player.getPlayer_pokemons().get(0).getName(),player.getPlayer_pokemons().get(1).getName()));
	    int num = controller.getValidInt("", 1, 2);
	    setPlayerpnow(player.getPlayer_pokemons().get(num-1));
	    screen.displayMessageLine("Your First Pokemon is " + playerpnow.getName());
	    Database.drawPokemon(playerpnow);
	    screen.displayMessageLine("\n\nBattle Start!!");
	    //player 1 vs enemy 1
	    battle = new Battle(playerpnow,enemypnow);
		
	    //(p1 vs e1) player pokemon 1 win
		if(battle.execute()==1) {
			player.addScore(enemypnow.getGrade()*1000+enemypnow.getAttackvalue()*10);
			screen.displayMessageLine("Congratulations , you defeated the first enemy pokemon!!");
			screen.displayMessageLine("Your current score is "+player.getScore());
			//change to enemy 2
			setEnemypnow(enemypokemons.get(1));
			controller.enterEnterKey();
			screen.displayMessageLine(String.format("\nYour Second Enemy Pokemon to battle is \n %s", enemypnow));
		    Database.drawPokemon(enemypnow);
			battle = new Battle(playerpnow,enemypnow);
			Database.drawPokemon(playerpnow);
			//(p1 vs e2) player pokemon 1 win
			if(battle.execute()==1) {
				player.addScore(enemypnow.getGrade()*1000+enemypnow.getAttackvalue()*10);
				screen.displayMessageLine("Congratulations , you win the battle !!!");
				screen.displayMessageLine("Your current score is "+player.getScore());
				return true;
			}
			//(p1 vs e2) player pokemon 1 lose
			else {
				player.pokemondead(playerpnow);
				//change to player pokemon 2
				setPlayerpnow(player.getPlayer_pokemons().get(0));
				controller.enterEnterKey();
				screen.displayMessageLine(String.format("This is your second Pokemon ",playerpnow));
			    player_database.drawPokemon(playerpnow);
				battle = new Battle(playerpnow,enemypnow);
				//(p2 vs e2) player pokemon 2 win
				if (battle.execute()==1) {
					player.addScore(enemypnow.getGrade()*1000+enemypnow.getAttackvalue()*10);
					screen.displayMessageLine("Congratulations , you win the battle !!!");
					screen.displayMessageLine("Your current score is "+player.getScore());
					return true;
				}
				//(p2 vs e2) player pokemon 2 lose
				else {
					screen.displayMessageLine("You Lose !!!");
					screen.displayMessageLine("Your current score is "+player.getScore());
					return false;
				}
			}
		}
		
		//(p1 vs e1) player pokemon 1 lose
		else {
			player.pokemondead(playerpnow);
			//change to player pokemon 2
			setPlayerpnow(player.getPlayer_pokemons().get(0));
			controller.enterEnterKey();
			screen.displayMessageLine(String.format("This is your second Pokemon\n "+ playerpnow));
			Database.drawPokemon(playerpnow);
			battle = new Battle(playerpnow,enemypnow);
			//(p2 vs e1) player pokemon 2 win
			if (battle.execute()==1) {
				player.addScore(enemypnow.getGrade()*1000+enemypnow.getAttackvalue()*10);
				screen.displayMessageLine("\nCongratulations , you defeated the first enemy Pokemon !!");
				screen.displayMessageLine("Your current score is "+player.getScore());
				//change to enemy pokemon 2
				setEnemypnow(enemypokemons.get(1));
				controller.enterEnterKey();
				screen.displayMessageLine("Your Second Enemy Pokemon to battle is\n"+ enemypnow);
				Database.drawPokemon(enemypnow);
				battle = new Battle(playerpnow,enemypnow);
				
				//(p2 vs e2) player pokemon 2 win
				if (battle.execute()==1) {
					player.addScore(enemypnow.getGrade()*1000+enemypnow.getAttackvalue()*10);
					screen.displayMessageLine("\nCongratulations , you win the battle !!!");
					screen.displayMessageLine("Your current score is "+player.getScore());
					return true;
				}
				
				//(p2 vs e2) player pokemon 2 lose
				else{
					screen.displayMessageLine("Your current score is "+player.getScore());
					return false;
				}
			}
		}
		return false;
	}
	
	// Method to print a list of Pokemon
	public void printPokemons(ArrayList<Pokemon> ps) {
		screen.displayMessageLine("--------------------------------");
		for (int i =0;i<ps.size();i++) {
			screen.displayMessageLine(String.format("%d) \n %s",i+1,ps.get(i)));
			screen.displayMessageLine("--------------------------------");
		}
	}
	
	// Method to simulate catching a Pokemon
	public void catchpokemon(Pokemon target) {
		screen.displayMessageLine("Try to catch "+target.getName());
		userBall = player_database.generaterandomBall();
		screen.displayMessageLine("\nYour Pokeball is " + userBall.getName());
		//draw Pokeball
		player_database.drawPokeball();
		screen.displayMessageLine("Press t to throw PokeBall");
		
		if (controller.getNext().equals("t")) {
			//pokeball grade > enemy grade -> successful
			if (userBall.getGrade() > target.getGrade()){
				player.addPokemon(target);
				screen.displayMessageLine("Congratulations, you've caught this Pokémon!");
			}
			//pokeball grade < enemy grade -> unsuccessful
			else if (userBall.getGrade() < target.getGrade()){
				screen.displayMessageLine("Fail!");
			}
			
			//pokeball grade = enemy grade -> 50% probability
			else {
				if (rand.nextDouble()>0.5) {
					player.addPokemon(target);
					screen.displayMessageLine("Congratulations, you've caught this Pokémon!");	
				}
				else {
					screen.displayMessageLine("Fail!");
				}
			}
		}
		else {
			screen.displayMessageLine("Fail!");
		}
	}
	
	public void convertDisk() {
		screen.displayMessageLine("This is your Pokemon list:");
		printPokemons(player.getPlayer_pokemons());
		int num = controller.getValidInt("\nWould you like to convert these pokemon into Ga ole disk? \n1. Yes \n2. No\n",1,2);
		if(num==1) {
			System.out.println(String.format("That will be %s tokens. Thank you for playing.", 5*player.getPlayer_pokemons().size()));
		}
		else if (num==2) {
			System.out.println("Thank you for playing.");
		}
	}
	

	// Method to provide the player with a support Pokemon (randomly)
	public void getsupportpokemon() {
	    screen.displayMessageLine("You have randomly obtained a support Pokémon to assist you!");
	    player_database.getPokemons().remove(player.getPlayer_pokemons().get(0));
	    player.getPlayer_pokemons().add(player_database.generaterandomPokemon(1).get(0));
	    screen.displayMessageLine("Here is your Pokemon list now");
	    printPokemons(player.getPlayer_pokemons());
	}
	
	
}

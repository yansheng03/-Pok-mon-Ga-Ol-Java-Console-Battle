import java.util.ArrayList;
import java.util.Collections;
//The Database class manages the collection of Pokemon and PokeBall objects
public class Database {
	private ArrayList<Pokemon> pokemons;
	private ArrayList<Pokemon> returnlist;
	private ArrayList<PokeBall> pokeballs;
	
   // Constructor initializes the Pokemon and PokeBall collections with predefined values
	public Database() {
		pokemons = new ArrayList<Pokemon>();
       // Initialize the Pokemon database with Fire type Pokemon
		pokemons.add(new FirePokemon("Entei","Fire", 5, 4000, 220, 100));
		pokemons.add(new FirePokemon("Arcanine","Fire",  4, 3500, 170, 90));
		pokemons.add(new FirePokemon("Charizard","Fire",  3, 3000, 150, 80));
		pokemons.add(new FirePokemon("Cyndaquil","Fire",  2, 2500, 120, 70));
		pokemons.add(new FirePokemon("Flareon","Fire",  1, 2300, 110, 60));
		
       // Initialize the Pokemon database with Water type Pokemon
		pokemons.add(new WaterPokemon("Gyarados","Water", 5, 3200, 250, 110));
		pokemons.add(new WaterPokemon("Milotic","Water", 4, 2800, 210, 100));
		pokemons.add(new WaterPokemon("Lapras","Water", 3, 2400, 200, 100));
		pokemons.add(new WaterPokemon("Squirtle","Water", 2, 2000, 180, 60));
		pokemons.add(new WaterPokemon("Golduck","Water", 1, 1700, 120, 50));
		
       // Initialize the Pokemon database with Electric type Pokemon
		pokemons.add(new ElectricPokemon("Zapdos","Electric", 5, 4000, 200, 100));
		pokemons.add(new ElectricPokemon("Raikou","Electric", 4, 3700, 200, 90));
		pokemons.add(new ElectricPokemon("Pikachu","Electric", 3, 2200, 150, 70));
		pokemons.add(new ElectricPokemon("Electabuzz","Electric", 2, 2000, 140, 50));
		pokemons.add(new ElectricPokemon("Manectric","Electric", 1, 1500, 130, 30));
		
       // Initialize the PokeBall database
		pokeballs = new ArrayList<PokeBall>();
		pokeballs.add(new PokeBall("Master Ball", 5));
		pokeballs.add(new PokeBall("Ultra Ball", 4));
		pokeballs.add(new PokeBall("Great Ball", 3));
		pokeballs.add(new PokeBall("Poke Ball", 2));
	}
	//setter & getter
	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
	public void setPokemons(ArrayList<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}
	
   // Method to generate a list of random Pokemon
	public ArrayList<Pokemon> generaterandomPokemon(int num) {
		returnlist = new ArrayList<Pokemon>();
       // Shuffle the Pokemon database and add the first 'num' Pokemon to the return list
		Collections.shuffle(pokemons);
		for (int i =0 ;i<num;i++) {
			returnlist.add(pokemons.get(i));
		}
		return returnlist;
	}
	
	
   // Method to generate a random PokeBall
	public PokeBall generaterandomBall() {
       // Shuffle the PokeBall database and return the first PokeBall
		Collections.shuffle(pokeballs);
		return pokeballs.get(0);
	}
	
   // Method to draw the image of the Pokemon based on its name
   // Method to draw the image of the Pokemon based on its name
	public static void drawPokemon(Pokemon p) {
       // Implementation to draw Pokemon images based on the comparison of the Pokemon's name
		if (comparePokemon(p,"Entei")){
			Art.drawEntei();
			}
		else if (comparePokemon(p,"Arcanine")){
			Art.drawArcanine();
		}
		else if (comparePokemon(p,"Charizard")){
			Art.drawCharizard();
		}
		else if (comparePokemon(p,"Cyndaquil")){
			Art.drawCyndaquil();
		}
		else if (comparePokemon(p,"Flareon")){
			Art.drawFlareon();
		}
		else if (comparePokemon(p,"Gyarados")) {
			Art.drawGyarados();
		}
		else if (comparePokemon(p,"Milotic")){
			Art.drawMilotic();
		}
		else if (comparePokemon(p,"Lapras")){
			Art.drawLapras();
		}
		else if (comparePokemon(p,"Squirtle")){
			Art.drawSquirtle();
		}
		else if (comparePokemon(p,"Golduck")){
			Art.drawGolduck();
		}
		else if (comparePokemon(p,"Zapdos")){
			Art.drawZapdos();
		}
		else if (comparePokemon(p,"Raikou")){
			Art.drawRaikou();
		}
		else if (comparePokemon(p,"Pikachu")){
			Art.drawPikachu();
		}
		else if (comparePokemon(p,"Electabuzz")){
			Art.drawElectabuzz();
		}
		else if (comparePokemon(p,"Manectric")){
			Art.drawManectric();
		}
		
	}
	
	
	public void drawPokeball() {
		if (compareBall("Poke Ball")){
			Art.drawPokeBall();
		}
		else if (compareBall("Great Ball")){
			Art.drawGreatBall();
		}
		else if (compareBall("Ultra Ball")){
			Art.drawUltraBall();
		}
		else if (compareBall("Master Ball")){
			Art.drawMasterBall();
		}
	}
	
	public static boolean comparePokemon(Pokemon p,String p_name) {
		return (p.getName().equals(p_name));
	}
	
	public boolean compareBall(String pokeball_name) {
		return Game.userBall.getName().equals(pokeball_name);
	}


}

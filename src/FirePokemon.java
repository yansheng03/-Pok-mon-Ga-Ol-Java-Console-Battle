// Fire_pokemon class is a specific type of Pokemon that specializes in fire attacks

public class FirePokemon extends Pokemon {
    // Constructor for creating a Fire_pokemon with specified attributes
	public FirePokemon(String name,String type, int grade, int hp, int attackvalue, int defendvalue) {
        // Call the constructor of the superclass (Pokemon) with the provided attributes
		super(name, type ,grade, hp, attackvalue, defendvalue);
		// TODO Auto-generated constructor stub
	}

    // Override the attack method to implement specialized attack behavior for Fire_pokemon
	@Override
	public void attack (Pokemon enemy) {
        // Check if the enemy is either a Fire_pokemon or a Water_pokemon, increase the attack value by 250
		if (enemy instanceof FirePokemon || enemy instanceof WaterPokemon) {
			increaseAttackvalue(250);
		}
	}

	

}
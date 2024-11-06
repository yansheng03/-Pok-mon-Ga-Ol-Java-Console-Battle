// Electric_pokemon class is a specific type of Pokemon that specializes in electric attacks

public class ElectricPokemon extends Pokemon {
	
    // Constructor for creating an Electric_pokemon with specified attributes
	public ElectricPokemon(String name,String type,int grade, int hp, int attackvalue, int defendvalue) {
        // Call the constructor of the superclass (Pokemon) with the provided attributes
		super(name,type, grade, hp, attackvalue, defendvalue);
		// TODO Auto-generated constructor stub
	}

    // Override the attack method to implement specialized attack behavior for Electric_pokemon
	@Override
	public void attack (Pokemon enemy) {
        // Check if the enemy is a Water_pokemon, increase the attack value by 500
		if (enemy instanceof WaterPokemon) {
			increaseAttackvalue(500);
		}
        // Check if the enemy is another Electric_pokemon, increase the attack value by 250
		if (enemy instanceof ElectricPokemon) {
			increaseAttackvalue(250);
		}
	}


}
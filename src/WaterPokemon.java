/**
 * The Water_pokemon class represents a Water-type Pokemon in the Pokemon game.
 * It extends the Pokemon class and provides specific behavior for Water-type Pokemon.
 */
public class WaterPokemon extends Pokemon {
    // Constructor for creating a new Water-type Pokemon with the given attributes
	public WaterPokemon(String name, String type, int grade, int hp, int attackvalue, int defendvalue) {
		super(name, type, grade, hp, attackvalue, defendvalue);
		// TODO Auto-generated constructor stub
	}
    // Override the attack method to provide specific behavior for Water-type Pokemon
	@Override
	public void attack (Pokemon enemy) {
        // If the enemy is a Fire-type Pokemon, increase attack value significantly
		if (enemy instanceof FirePokemon) {
			increaseAttackvalue(500);
		}
        // If the enemy is an Electric-type Pokemon, increase attack value moderately
		if (enemy instanceof WaterPokemon) {
			increaseAttackvalue(250);
		}
	}

	
}
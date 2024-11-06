/**
 * The Pokemon class represents a Pokemon in the Pokemon game.
 * It holds information such as the Pokemon's name, type, grade, HP, attack value, and defend value.
 */

public class Pokemon {
    // The name of the Pokemon
    private String name;
    // The type of the Pokemon
    private String type;
    // The grade or level of the Pokemon
    private int grade;
    // The current HP of the Pokemon
    private int hp;
    // The initial HP of the Pokemon (used for resetting)
    private final int starthp;
    // The attack value of the Pokemon
    private int attackvalue;
    // The defend value of the Pokemon
    private int defendvalue;
    
       
	//Constructor
	public Pokemon(String name, String type, int grade, int hp, int attackvalue, int defendvalue) {
		this.name = name;
		this.type = type;
		this.grade = grade;
		this.starthp = hp;
		this.hp = hp;
		this.attackvalue = attackvalue;
		this.defendvalue = defendvalue;
	}

	//Setter & Getter
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public int getGrade() {
		return grade;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getAttackvalue() {
		return attackvalue;
	}

	public int getDefendvalue() {
		return defendvalue;
	}
	
	public void setAttackvalue(int attackvalue) {
		this.attackvalue = attackvalue;
	}

	public void setDefendvalue(int defendvalue) {
		this.defendvalue = defendvalue;
	}
	


    // Increase the Pokemon's attack value by the specified value
	public void increaseAttackvalue(int value) {
		setAttackvalue(getAttackvalue()+value);
	}
	
    // Reduce the Pokemon's HP by the specified damage value
	public void receiveDamage(int damage) {
		setHp(getHp()-damage);;
	}
	
    // Reset the Pokemon's HP to its initial value
	public void resetHp() {
		setHp(starthp);
	}
	
    // Check if the Pokemon is dead (HP less than 0)
	public boolean isdead() {
        return hp < 0;
	}
	
    // Override the toString method to provide a formatted string representation of the Pokemon
	@Override
	public String toString() {
		return String.format("Pokemon Name : %s \n Type : %s \n Grade : %s \n HP : %s \n AttackValue : %s \n Defendvalue : %s",getName(),getType(),getGrade(),getHp(),getAttackvalue(),getDefendvalue());
	}
	
    // Placeholder method for the attack action (to be overridden by subclasses)
	public void attack(Pokemon enemy) {
		//do nothing....
	}
	
	
	
	

	

}
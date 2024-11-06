import java.util.concurrent.ThreadLocalRandom;

//The Battle class represents a battle between two Pokemon
public class Battle {
    private Pokemon playerpokemon;
    private Pokemon enemypokemon;
    private Screen screen;
    private Controller controller;
    private final double MIN_VALUE = 0.3;
    private final double MAX_VALUE = 0.99;
    private int playerAttackValue;
    private int enemyAttackValue;

    // Constructor
    public Battle(Pokemon playerpokemon, Pokemon enemypokemon) {
        this.playerpokemon = playerpokemon;
        this.enemypokemon = enemypokemon;
        this.screen = new Screen();
        this.controller = new Controller();
    }

    // Getter and setter
    public int getPlayerAttackValue() {
        return playerAttackValue;
    }

    public void setPlayerAttackValue(int playerAttackValue) {
        this.playerAttackValue = playerAttackValue;
    }

    public int getEnemyAttackValue() {
        return enemyAttackValue;
    }

    public void setEnemyAttackValue(int enemyAttackValue) {
        this.enemyAttackValue = enemyAttackValue;
    }

    // Main method to execute the battle
    public int execute() {
        // Initialize the battle by comparing initial attacks
        startAttackCompare(playerpokemon, enemypokemon);
        startAttackCompare(enemypokemon, playerpokemon);

        // Continue the battle until one of the Pokemon is defeated
        while ((!playerpokemon.isdead()) && (!enemypokemon.isdead())) {
        	displayhp();
            if (!playerTurn()) {
                break;
            }
            // Player wins
            if (enemypokemon.isdead()) {
                screen.displayMessage("\n\nEnemy Pokemon is dead\n");
                return 1;
            }
            displayhp();
            if (!enemyTurn()) {
                break;
            }
            // Enemy wins
            if (playerpokemon.isdead()) {
                screen.displayMessage("\n\nYour Pokemon is dead\n");
                return 2;
            }
        }

        return 3;
    }

    // Method to compare initial attacks when the battle starts
    private void startAttackCompare(Pokemon attacker, Pokemon defender) {
        attacker.attack(defender);
    }

    // Method to display the HP of both the player and enemy Pokemon
    private void displayhp() {
        screen.displayMessageLine(String.format("\nPlayer hp : %d   Enemy hp : %d", playerpokemon.getHp(), enemypokemon.getHp()));
    }

    // Method for the player's turn in the battle
    private boolean playerTurn() {
        if (controller.getValidInt("\nIt's your turn to attack (type 1 to attack)", 1, 1)!=1) {
            return false;
        }
        // Calculate player's attack value and apply damage to the enemy Pokemon
        setPlayerAttackValue((int) (playerpokemon.getAttackvalue() / ThreadLocalRandom.current().nextDouble(MIN_VALUE, MAX_VALUE)));
        screen.displayMessageLine("\nYour Pokemon Attack Value is " + getPlayerAttackValue());
        screen.displayMessageLine("Enemy Pokemon Defend Value is " + enemypokemon.getDefendvalue());
        enemypokemon.receiveDamage(getPlayerAttackValue() - enemypokemon.getDefendvalue());

        return true;
    }

    // Method for the enemy's turn in the battle
    private boolean enemyTurn() {
        screen.displayMessageLine("\nIt's now the enemy's turn to attack");
        // Calculate enemy's attack value and apply damage to the player's Pokemon
        setEnemyAttackValue((int) (enemypokemon.getAttackvalue() / ThreadLocalRandom.current().nextDouble(MIN_VALUE, MAX_VALUE)));
        screen.displayMessageLine("Enemy Pokemon Attack Value is " + getEnemyAttackValue());
        screen.displayMessageLine("Your Pokemon Defend Value is " + playerpokemon.getDefendvalue());
        playerpokemon.receiveDamage(getEnemyAttackValue() - playerpokemon.getDefendvalue());

        return true;
    }
}
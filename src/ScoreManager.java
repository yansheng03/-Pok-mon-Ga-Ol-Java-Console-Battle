import java.io.*;
import java.util.*;

public class ScoreManager {
	// Map to store player names and their scores
    private Map<String, Integer> scores;
    // File name where scores are stored
    private String fileName;

    // Constructor - Initializes the ScoreManager with the specified file name
    // Load scores from the file when the ScoreManager is created
    public ScoreManager(String fileName) {
        this.fileName = fileName;
        scores = new HashMap<>();
        loadScoresFromFile(); 
    }
    
    // Get the score for a specific player; returns 0 if the player is not found
    public int getScore(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }

    // Update the score for a player and write scores to the file whenever they are updated
    public void updateScore(String playerName, int score) {
        scores.put(playerName, score);
        writeScoresToFile(); 
    }

    // Display all scores in the console
    public void displayScores() {
        System.out.println("----- Scores -----");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("-------------------");
    }
    
    // Display the top 5 scores in the console
    public void displayTopScores() {
        System.out.println("\n\n----- Top 5 Scores -----");
        List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
        sortedScores.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedScores) {
            if (count >= 5) {
                break;
            }
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
        }
        System.out.println("------------------------");
    }
    
    // Load scores from the file when the ScoreManager is created
    private void loadScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    scores.put(playerName, score);
                }
            }
        } catch (FileNotFoundException e) {
            // If the file is not found, create a new file
            createFileIfNotExists();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); 
        }
    }
    
    // Write scores to the file whenever they are updated
    private void writeScoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    // Create a new file if it does not exist
    private void createFileIfNotExists() {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.err.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
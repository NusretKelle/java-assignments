package SentimentAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sentimentAnalysisTest {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        File file = new File("sentiments.txt");

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\t");
                    if (parts.length == 2) {
                        map.put(parts[0].toLowerCase(), Integer.parseInt(parts[1]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading sentiments.txt: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        int total = 0, count = 0;
        String prev = "";

        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) break;

            String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String word : words) {
                count++;
                String phrase = prev + " " + word;
                Integer sentiment = map.get(phrase.trim());
                if (sentiment != null) {
                    total += sentiment;
                } else {
                    sentiment = map.get(word);
                    if (sentiment != null) total += sentiment;
                }
                prev = word;
            }
        }

        double average = count == 0 ? 0 : (double) total / count;
        System.out.printf("Words: %d\nSentiment: %d\nOverall: %.2f\n", count, total, average);
    }
}

package SpellChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheck {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: dictionary file not specified.");
            return;
        }

        File dictFile = new File(args[0]);
        if (!dictFile.exists()) {
            System.out.println("Error: file not found.");
            return;
        }

        BinarySearchTree<String> bst = new BinarySearchTree<>();

        try (Scanner dictScan = new Scanner(dictFile)) {
            while (dictScan.hasNext()) {
                bst.insert(dictScan.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading dictionary file.");
            return;
        }

        System.out.println("Loaded the words into a tree with height = " + bst.height());

        Scanner input = new Scanner(System.in);
        System.out.println("Enter words to check (type END to quit):");

        while (true) {
            String word = input.next();
            if (word.equals("END")) break;
            if (bst.contains(word))
                System.out.println(word + " is spelled correctly.");
            else
                System.out.println(word + " is spelled wrong!");
        }
    }
}

package com.CSC161.ArrayAndLinkedList;

import java.io.*;
import java.util.*;

public class Playlist {
    private static MyDoubleLinkedList<Song> playlist = new MyDoubleLinkedList<>();
    private static Random rand = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("🎵 Playlist Manager 🎵");
        System.out.println("Commands: add, remove, count, play, shuffle, reverse, save, load, quit");

        while (running) {
            System.out.print("> ");
            String cmd = sc.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "add":
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();
                    playlist.add(new Song(title, artist));
                    break;

                case "remove":
                    System.out.print("Enter title: ");
                    String rTitle = sc.nextLine();
                    System.out.print("Enter artist: ");
                    String rArtist = sc.nextLine();
                    if (playlist.remove(new Song(rTitle, rArtist)))
                        System.out.println("Removed.");
                    else
                        System.out.println("Not found.");
                    break;

                case "count":
                    System.out.println("Songs in playlist: " + playlist.count());
                    break;

                case "play":
                    if (playlist.isEmpty()) System.out.println("Playlist empty.");
                    else for (Song s : playlist) System.out.println(s);
                    break;

                case "shuffle":
                    ArrayList<Song> arr = playlist.toArrayList();
                    Collections.shuffle(arr, rand);
                    playlist.fromArrayList(arr);
                    System.out.println("Playlist shuffled.");
                    break;

                case "reverse":
                    playlist.reverse();
                    System.out.println("Playlist reversed.");
                    break;

                case "save":
                    saveToFile("playlist.txt");
                    break;

                case "load":
                    loadFromFile("playlist.txt");
                    break;

                case "quit":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Unknown command.");
            }
        }
        sc.close();
    }

    private static void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Song s : playlist) {
                pw.println(s.getTitle() + "|" + s.getArtist());
            }
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private static void loadFromFile(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            ArrayList<Song> arr = new ArrayList<>();
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split("\\|");
                if (parts.length == 2) arr.add(new Song(parts[0], parts[1]));
            }
            playlist.fromArrayList(arr);
            System.out.println("Loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}

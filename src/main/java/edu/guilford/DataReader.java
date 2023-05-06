package edu.guilford;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class DataReader {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanFile = null;
        File dataLocation = null; // what's our data file?
        ArrayList<String[]> values = null; // where are we putting the data when we get it?
        String fileName = "data2.txt";
        PriorityQueue<String> pq = new PriorityQueue<String>();
        ArrayList<Word> words = new ArrayList<Word>();
        // open the file and read the data
        try {
            dataLocation = new File(DataReader.class.getResource("/" + fileName).getFile());
            scanFile = new Scanner(dataLocation); // so that we can read the data line by line
            values = readData(scanFile);
            for (String[] row : values) {
                for (String value : row) {
                    // logic goes here
                    if (value.matches("^[a-zA-Z]*$")) {
                        pq.add(value);
                    }
                }
            }
            String current = "";
            while (!pq.isEmpty()) {
                String next = pq.poll();
                if (next.equals(current)) {
                    int index = words.size() - 1;
                    words.get(index).setCount(words.get(index).getCount() + 1);
                } else {
                    words.add(new Word(next, 1));
                    current = next;
                }
            }
            Collections.sort(words);
            // while (!pq.isEmpty()) {
            // System.out.println(pq.poll());
            // }
            // for (int i = 0; i < 10; i++) {
            //     System.out.println(pq.poll());
            // }
        } catch (FileNotFoundException | NullPointerException e) { // | allow us to catch multiple exception
            // types and do the same basic thing with any of them
            e.printStackTrace();
        }
        System.out.println("Enter a word");
        String word = scan.nextLine();
        // linear iteration thorugh the array list
        for (Word w : words) {
            if (w.getName().equals(word)) {
                System.out.println("count for " + word + ": " + w.getCount());
            }
        }
        // write the data to a file
        scan.close();
        FileWriter writer = null;
        try {
            writer = new FileWriter("output3.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // write each word to the file
        for (Word w : words) {
            try {
                writer.write(w.getName() + " " + w.getCount() + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // while (!pq.isEmpty()) {
        //     try {
        //         writer.write(pq.poll() + "\n");
        //     } catch (IOException ex) {
        //         ex.printStackTrace();
        //     }

        // close the file writer
        try {
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String[]> readData(Scanner scan) {
        // returns an ArrayList of String arrays, where each String array is a sentence
        ArrayList<String[]> sentences = new ArrayList<String[]>();
        String line = null;

        // try reading the data from the file, catching any exceptions that take place
        try {
            while (scan.hasNextLine()) {
                line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] words = line.split("\\s+");
                    for (int i = 0; i < words.length; i++) {
                        String word = words[i];
                        if (Character.isUpperCase(word.charAt(0))) {
                            word = word.toLowerCase();
                        }
                        words[i] = word;
                    }
                    sentences.add(words);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sentences;
    }

}

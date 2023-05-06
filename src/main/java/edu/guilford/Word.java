package edu.guilford;

public class Word implements Comparable<Word> {
    //attributes
    private String name;
    private int count;

    //constructor
    public Word(String name, int count) {
        this.name = name;
        this.count = count;
    }

    //getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //compareTo by count and then alphabetical order
    public int compareTo(Word other) {
        if (this.count > other.count) {
            return -1;
        } else if (this.count < other.count) {
            return 1;
        } else {
            return this.name.compareTo(other.name);
        }
    }

}

package org.itstep.task2;

import java.util.ArrayList;

public class Word implements Comparable<Word> {

    private String word;
    private ArrayList<String> translations;
    private int requests = 0;

    public Word(String word, ArrayList<String> translations) {
        this.word = word;
        this.translations = translations;
    }

    public Word(String word, ArrayList<String> translations, int requests) {
        this.word = word;
        this.translations = translations;
        this.requests = requests;
    }

    public String getWord() {
        return this.word;
    }

    public ArrayList<String> getTranslations() {
        return this.translations;
    }

    public int getRequests() {
        return this.requests;
    }

    @Override
    public int compareTo(Word other) {
        return Integer.compare(this.requests, other.requests);
    }
}

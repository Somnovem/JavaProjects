package org.itstep;

import org.itstep.task2.WordDictionary;

public class Main {
    public static void main(String[] args) {
        Runnable app = new WordDictionary();
        app.run();
    }
}
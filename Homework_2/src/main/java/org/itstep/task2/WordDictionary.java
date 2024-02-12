package org.itstep.task2;

import java.util.*;

public class WordDictionary implements Runnable {

    private ArrayList<Word> dict = new ArrayList<>();
    private int initWords = 3;

    private void init() {
        for (int i = 0; i < initWords; i++) {
            addWord();
        }
    }

    private boolean contains(String word) {
        boolean contains = false;
        for (Word dictWord: dict) {
            if(Objects.equals(dictWord.getWord(), word)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private boolean isOneWord(String input) {
        String[] words = input.split("\\s+");
        return words.length == 1 && !words[0].isEmpty();
    }

    private Word getWord(String word) {
        for (Word dictWord: dict) {
            if(dictWord.getWord().equals(word)) return dictWord;
        }
        return null;
    }

    private void getTranslations() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to translate: ");
        String word = scanner.nextLine();
        scanner.close();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
            return;
        }

        Word dictWord = getWord(word);
        dict.remove(dictWord);
        dict.add(new Word(word,dictWord.getTranslations(),dictWord.getRequests() + 1));
        System.out.print("Word: " + word + "\nTranslations: ");
        for(String translation : dictWord.getTranslations()) {
            System.out.print(translation + " ");
        }
        System.out.println();
    }

    private void findTranslation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the translation to search for: ");
        String translation = scanner.nextLine();
        scanner.close();

        for (Word dictWord : dict) {
            for (String dictTranslation : dictWord.getTranslations()) {
                if (dictTranslation.equals(translation)) {
                    String word = dictWord.getWord();
                    dict.remove(dictWord);
                    dict.add(new Word(word,dictWord.getTranslations(),dictWord.getRequests() + 1));
                    System.out.print("Word: " + word + "\nTranslations: ");
                    for(String foundTranslation : dictWord.getTranslations()) {
                        System.out.print(foundTranslation + " ");
                    }
                    System.out.println();
                    return;
                }
            }
        }
        System.out.println("No such translation is present in the dictionary");
    }

    private void addTranslations() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to add a translation to: ");
        String word = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
            return;
        }

        System.out.print("Enter the translation you want to add, separated by commas: ");
        String translations = scanner.nextLine();
        scanner.close();

        Word dictWord = getWord(word);

        ArrayList<String> presentTranslations = dictWord.getTranslations();

        presentTranslations.addAll(Arrays.stream(translations.split(",")).toList());

        dict.remove(dictWord);

        dict.add(new Word(word,presentTranslations, dictWord.getRequests()));
        System.out.println("Translation added.");
    }

    private void editTranslations() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to add a translation to: ");
        String word = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
            return;
        }

        System.out.print("Enter the translation to replace the existing, separated by commas: ");
        ArrayList<String> newTranslations = new ArrayList<>
                    (Arrays.stream(scanner.nextLine().split(",")).toList());
        scanner.close();

        Word dictWord = getWord(word);
        dict.remove(dictWord);
        dict.add(new Word(word,newTranslations, dictWord.getRequests()));
        System.out.println("Translations replaced.");

    }

    private void deleteTranslation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to add a translation to: ");
        String word = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
            return;
        }

        System.out.print("Enter the translation you want to delete: ");
        String translation = scanner.nextLine();

        scanner.close();

        Word dictWord = getWord(word);
        ArrayList<String> newTranslations = new ArrayList<>();

        for (String dictTranslation: dictWord.getTranslations()) {
            if (!dictTranslation.equals(translation)) newTranslations.add(dictTranslation);
        }

        dict.remove(dictWord);
        dict.add(new Word(word,newTranslations,dictWord.getRequests()));
        System.out.println("Translation removed.");
    }

    private void addWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to add: ");
        String word = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(contains(word)){
            System.out.println("Word already present in the dictionary. Action declined.");
            return;
        }

        System.out.print("Enter translations of the word, separated by commas: ");
        String[] input = scanner.nextLine().split(",");
        scanner.close();
        ArrayList<String> translations = new ArrayList<>();
        for (String inputWord : input) {
            translations.add(inputWord.trim());
        }

        dict.add(new Word(word, translations));
        System.out.println("Word successfully added!");
    }

    private void editWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to edit: ");
        String word = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
        }

        System.out.print("Enter the new spelling: ");
        String newWord = scanner.nextLine();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(contains(word)){
            System.out.println("Word already present in the dictionary. Action declined.");
        }

        scanner.close();

        Word presentWord = getWord(word);
        dict.remove(presentWord);
        dict.add(new Word(newWord, presentWord.getTranslations(),presentWord.getRequests()));
        System.out.println("Word edited.");
    }

    private void deleteWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you want to delete: ");
        String word = scanner.nextLine();
        scanner.close();

        if (!isOneWord(word)) {
            System.out.println("Input not a word. Action declined.");
            return;
        }

        if(!contains(word)){
            System.out.println("Word not present in the dictionary. Action declined.");
        }

        for (Word presentWord : dict) {
            if (Objects.equals(presentWord.getWord(), word)) {
                dict.remove(presentWord);
                System.out.println("Word successfully deleted!");
                return;
            }
        }
    }

    private void getTop10PopularWords() {
        ArrayList<Word> tempDict = new ArrayList<>(dict);
        Arrays.sort(tempDict.toArray());
        int displaySize = Integer.compare(tempDict.size(),10) >= 0 ? 10 : tempDict.size();
        for (int i = 0; i < displaySize; i++){
            Word dictWord = tempDict.get(i);
            System.out.print("Word: " + dictWord.getWord() + "\nRequests: " + dictWord.getRequests() +
                                                                            "\nTranslations: ");
            for(String translation : dictWord.getTranslations()) {
                System.out.print(translation + " ");
            }
            System.out.println();
            System.out.println("----------------------------------------");
        }
    }

    private void getTop10UnpopularWords() {
        ArrayList<Word> tempDict = new ArrayList<>(dict);
        Arrays.sort(tempDict.toArray());
        List<Word> reversedList = tempDict.reversed();
        int displaySize = Integer.compare(tempDict.size(),10) >= 0 ? 10 : tempDict.size();
        for (int i = 0; i < displaySize; i++){
            Word dictWord = tempDict.get(i);
            System.out.print("Word: " + dictWord.getWord() + "\nRequests: " + dictWord.getRequests() +
                    "\nTranslations: ");
            for(String translation : dictWord.getTranslations()) {
                System.out.print(translation + " ");
            }
            System.out.println();
            System.out.println("----------------------------------------");
        }
    }

    private void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            System.out.println("Choose an option:");
            System.out.println("    1 - Get translation of a word");
            System.out.println("    2 - Find word by translation");
            System.out.println("    3 - Add a word");
            System.out.println("    4 - Edit a word");
            System.out.println("    5 - Delete a word");
            System.out.println("    6 - Add translations");
            System.out.println("    7- Edit translations");
            System.out.println("    8 - Delete a transaltion");
            System.out.println("    9 - Get Top 10 most popular");
            System.out.println("    10 - Get Top 10 least popular");
            System.out.println("    11 - Exit");
            switch (choice) {
                case 1:
                    getTranslations();
                    break;
                case 2:
                    findTranslation();
                    break;
                case 3:
                    addWord();
                    break;
                case 4:
                    editWord();
                    break;
                case 5:
                    deleteWord();
                    break;
                case 6:
                    addTranslations();
                    break;
                case 7:
                    editTranslations();
                    break;
                case 8:
                    deleteTranslation();
                    break;
                case 9:
                    getTop10PopularWords();
                    break;
                case 10:
                    getTop10UnpopularWords();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    @Override
    public void run() {
        init();
        mainLoop();
    }
}

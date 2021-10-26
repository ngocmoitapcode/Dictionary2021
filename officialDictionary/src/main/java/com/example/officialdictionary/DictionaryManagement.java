package com.example.officialdictionary;

import java.io.*;
import java.util.*;

public class DictionaryManagement {
    Dictionary dictionary = new Dictionary();
    ArrayList<Word> list = dictionary.wordList;
    Scanner input = new Scanner(System.in);

    void insertFromCommandline() {
        System.out.print("Nhập vào số lượng từ vựng: ");
        int numOfWords = input.nextInt();
        for (int i = 0; i < numOfWords; i++) {
            Word newWord = new Word();
            System.out.println("No " + (i + 1) + ": ");
            if (i == 0) {
                String begin = input.nextLine();
            }
            System.out.print("Word_target: ");
            newWord.setWord_target(input.nextLine());
            System.out.print("Word_explain: ");
            newWord.setWord_explain(input.nextLine());
            list.add(newWord);
        }
    }

    void insertFromFile() {
        try {
            File file = new File("dictionaries.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) {
                addToList(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addToList(String line) {
        StringTokenizer string = new StringTokenizer(line, "\t");
        while (string.hasMoreTokens()) {
            String word_target = string.nextToken();
            String word_explain = string.nextToken();
            Word newWord = new Word(word_target, word_explain);
            list.add(newWord);
        }
    }

    void showAllWords() {
        System.out.printf("%-8s%-24s%-24s\n", "No", "|English", "|Vietnamese");
        String target, explain;
        int index;
        for (int i = 0; i < list.size(); i++) {
            index = i + 1;
            target = list.get(i).getWord_target();
            explain = list.get(i).getWord_explain();
            System.out.printf("%-8s%-24s%-24s\n", index, "|" + target, "|" + explain);
        }
    }

    void dictionaryLookup() {
        String inputCommandString = input.nextLine();
        String inputCommand = null, inputTarget = null;
        StringTokenizer string = new StringTokenizer(inputCommandString, "\t");
        while (string.hasMoreTokens()) {
            inputCommand = string.nextToken();
            inputTarget = string.nextToken();
        }

        if (!Objects.equals(inputCommand, "lookup")) {
            return;
        }

        System.out.printf("%-8s%-24s%-24s\n", "No", "|English", "|Vietnamese");
        String target, explain;
        int index;

        for (int i = 0; i < list.size(); i++) {
            target = list.get(i).getWord_target();
            if (Objects.equals(target, inputTarget)) {
                index = i + 1;
                explain = list.get(i).getWord_explain();
                System.out.printf("%-8s%-24s%-24s\n", index, "|" + target, "|" + explain);
            }
        }
    }

    void dictionaryAdd() {
        String command = null;
        String target = null;
        String explain = null;
        Word newWord = new Word();
        String addNewWord = input.nextLine();
        StringTokenizer string = new StringTokenizer(addNewWord, "\t", false);
        while (string.hasMoreTokens()) {
            command = string.nextToken();
            if (!command.equals("add")) {
                return;
            }
            target = string.nextToken();
            explain = string.nextToken();
            newWord.setWord_target(target);
            newWord.setWord_explain(explain);
        }
        Boolean checkSameWord = false;
        for (int i = 0; i < dictionary.wordList.size(); i++) {
            if (newWord.getWord_target().equals(dictionary.wordList.get(i).getWord_target())) {
                checkSameWord = true;
            }
        }
        if (!checkSameWord) {
            dictionary.wordList.add(newWord);
        }
        dictionaryExportToFile();
    }

    void dictionaryEdit() {
        String inputCommandString = input.nextLine();
        String inputCommand = null, inputTarget = null, inputExplain = null;
        StringTokenizer string = new StringTokenizer(inputCommandString, "\t");
        while (string.hasMoreTokens()) {
            inputCommand = string.nextToken();
            inputTarget = string.nextToken();
            inputExplain = string.nextToken();
        }

        if (!Objects.equals(inputCommand, "edit")) {
            return;
        }

        String target;
        for (int i = 0; i < list.size(); i++) {
            target = list.get(i).getWord_target();
            if (Objects.equals(target, inputTarget)) {
                list.get(i).setWord_explain(inputExplain);
            }
        }
        dictionaryExportToFile();
    }

    void dictionaryDelete() {
        String inputCommandString = input.nextLine();
        String inputCommand = null, inputTarget = null;
        StringTokenizer string = new StringTokenizer(inputCommandString, "\t");
        while (string.hasMoreTokens()) {
            inputCommand = string.nextToken();
            inputTarget = string.nextToken();
        }

        if (!Objects.equals(inputCommand, "delete")) {
            return;
        }

        String target;
        for (int i = 0; i < list.size(); i++) {
            target = list.get(i).getWord_target();
            if (Objects.equals(target, inputTarget)) {
                list.remove(i);
            }
        }
        dictionaryExportToFile();
    }

    void dictionaryExportToFile() {
        try {
            FileWriter writer = new FileWriter("src/version1/dictionaries.txt");
            String line;
            String target, explain;
            for (int i = 0; i < list.size(); i++) {
                target = list.get(i).getWord_target();
                explain = list.get(i).getWord_explain();
                line = target + "\t" + explain + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


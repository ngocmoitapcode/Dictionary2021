package com.example.officialdictionary;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dictManagement = new DictionaryManagement();
    ArrayList<Word> list = dictManagement.dictionary.wordList;

    void dictionaryBasic() {
        dictManagement.insertFromCommandline();
        dictManagement.showAllWords();
    }

    void dictionaryAdvanced() {
        dictManagement.insertFromFile();
        dictManagement.showAllWords();
        dictManagement.dictionaryLookup();
        dictManagement.dictionaryAdd();
        dictManagement.dictionaryEdit();
        dictManagement.dictionaryDelete();
    }

    void dictionarySearcher() {
        Scanner input = new Scanner(System.in);
        String beginLetters = input.nextLine();

        System.out.printf("%-8s%-24s%-24s\n", "No", "|English", "|Vietnamese");
        String target, explain;
        int index;

        for (int i = 0; i < list.size(); i++) {
            target = list.get(i).getWord_target();
            if (target.startsWith(beginLetters)) {
                index = i + 1;
                explain = list.get(i).getWord_explain();
                System.out.printf("%-8s%-24s%-24s\n", index, "|" + target, "|" + explain);
            }
        }
    }

    /*public static void main(String[] args) {
        DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
        dictionaryCommandLine.dictManagement.insertFromFile();
        dictionaryCommandLine.dictManagement.showAllWords();
        dictionaryCommandLine.dictionarySearcher();
    }*/
}

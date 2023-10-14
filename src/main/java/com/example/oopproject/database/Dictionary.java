package com.example.oopproject.database;

import java.util.Set;
import java.util.TreeSet;
import org.jsoup.*;

public class Dictionary {
    private Set<String> wordList = new TreeSet<>();

    public Set<String> getWordList() {
        return wordList;
    }

    public void setWordList(Set<String> wordList) {
        this.wordList = wordList;
    }

    public void add(String word) {
        wordList.add(word);
    }

    public void remove(String word) {
        wordList.remove(word);
    }

    public static String htmlToText(String html) {
        return Jsoup.parse(html).wholeText();
    }
}

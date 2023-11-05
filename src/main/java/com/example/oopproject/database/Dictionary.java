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
        String source = Jsoup.parse(html).wholeText();
//        String[] s = source.split("\n", -1);
//        String explain = "";
//        for (int i = 0; i < s.length; i++) {
//            if (s[i].startsWith("@")) {
//                explain = explain + s[i] + "\n";
//            } else if (s[i].startsWith("*")) {
//                explain = explain + "  " + s[i] + "\n";
//            } else if (s[i].startsWith("-")) {
//                explain = explain + "     " + s[i] + "\n";
//            }
//        }
        //System.out.println(meaning);
        return source;
    }
}

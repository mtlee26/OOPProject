package com.example.oopproject.dictionary;

import org.jsoup.Jsoup;

import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private Map<String, String> wordList = new TreeMap<String, String>();
    public void setWordList(Map<String, String> wordList) {
        this.wordList = wordList;
    }
    public Map<String, String> getWordList() {
        return wordList;
    }

    public void add(Word word) {
        wordList.put(word.getWordTarget(), word.getWordExplain());
    }

    public void remove(String word) {
        wordList.remove(word);
    }

    public String getMeaning(String wordTarget) {
        return htmlToText(wordList.get(wordTarget));
    }

    public int size() {
        return wordList.size();
    }
    public static String htmlToText(String html) {
        String source = Jsoup.parse(html).wholeText();
        return source;
    }
}

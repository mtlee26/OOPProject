package com.example.oopproject.dictionary;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    //private String content;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) {
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return false;
            }
        }
        return current.isEndOfWord;
    }

//    public boolean delete(String word) {
//
//    }


//    private Map<Character, Trie> children;
//    private String content;
//    private boolean isEndOfWord;

//    private Trie(String content) {
//        this.content = content;
//        children = new HashMap<Character, Trie>();
//    }


//////////
//    protected final Map<Character, Trie> children;
//    protected String content;
//    protected boolean terminal = false;
//
//    public Trie() {
//        this(null);
//    }
//
//    private Trie(String content) {
//        this.content = content;
//        children = new HashMap<Character, Trie>();
//    }
//
//    protected void add(char character) {
//        String s;
//        if (this.content == null) s = Character.toString(character);
//        else s = this.content + character;
//        children.put(character, new Trie(s));
//    }
//
//    public void insert(String diagnosis) {
//        if (diagnosis == null) throw new IllegalArgumentException("Null diagnoses entries are not valid.");
//        Trie node = this;
//        for (char c : diagnosis.toCharArray()) {
//            if (!node.children.containsKey(c)) node.add(c);
//            node = node.children.get(c);
//        }
//        node.terminal = true;
//    }

//    public List<String> autoComplete(String prefix) {
//        Trie trieNode = this;
//        for (char c : prefix.toCharArray()) {
//            if (!trieNode.children.containsKey(c)) return null;
//            trieNode = trieNode.children.get(c);
//        }
//        return trieNode.allPrefixes();
//    }

//    protected List<String> allPrefixes() {
//        List<String> diagnosisResults = new ArrayList<String>();
//        if (this.terminal) diagnosisResults.add(this.content);
//        for (Map.Entry<Character, Trie> entry : children.entrySet()) {
//            Trie child = entry.getValue();
//            Collection<String> childPrefixes = child.allPrefixes();
//            diagnosisResults.addAll(childPrefixes);
//        }
//        return diagnosisResults;
//    }

}

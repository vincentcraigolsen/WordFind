package com.vincentcraigolsen.wordfind.objects;

public class WordTrieNode {

    boolean isEndOfAWord;
    boolean hasNoChildren;
    WordTrieNode[] children;

    public WordTrieNode() {
        this.children = new WordTrieNode[26];
    }
}

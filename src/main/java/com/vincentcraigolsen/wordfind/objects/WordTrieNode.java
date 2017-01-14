package com.vincentcraigolsen.wordfind.objects;

import java.io.Serializable;

public class WordTrieNode implements Serializable {

    boolean isEndOfAWord;
    boolean hasNoChildren;
    WordTrieNode[] children;

    public WordTrieNode() {
        this.children = new WordTrieNode[26];
    }
}

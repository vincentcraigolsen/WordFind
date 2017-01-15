package com.vincentcraigolsen.wordfind.objects;

public class WordTrie {

    private WordTrieNode root;

    public WordTrie() {
        root = new WordTrieNode();
    }

    public void insert(String word) {
        WordTrieNode parent = root;

        for (int charPosition = 0; charPosition < word.length(); charPosition++) {

            char letter = word.charAt(charPosition);
            int childIndex = positionInTheAlphabetOf(letter);

            if (parent.children[childIndex] == null) {
                WordTrieNode temp = new WordTrieNode();
                parent.children[childIndex] = temp;
                parent = temp;
            } else {
                parent = parent.children[childIndex];
            }
        }
        parent.isEndOfAWord = true;
    }

    public boolean contains(String word) {
        WordTrieNode endOfWordNode = searchNode(word);

        if (endOfWordNode == null) {
            return false;
        } else if (endOfWordNode.isEndOfAWord) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsPrefixOf(String prefix) {
        WordTrieNode endOfPrefixNode = searchNode(prefix);
        if (endOfPrefixNode == null) {
            return false;
        } else {
            return true;
        }
    }

    private WordTrieNode searchNode(String letterSequence) {
        WordTrieNode parent = root;

        for (int charPosition = 0; charPosition < letterSequence.length(); charPosition++) {
            char letter = letterSequence.charAt(charPosition);
            int childIndex = positionInTheAlphabetOf(letter);

            if (parent.children[childIndex] != null) {
                parent = parent.children[childIndex];
            } else {
                return null;
            }
        }

        if (parent == root) {
            return null;
        }

        return parent;
    }

    private int positionInTheAlphabetOf(char c) {
        return c - 'a';
    }
}

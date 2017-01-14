package com.vincentcraigolsen.wordfind;

import java.util.Set;

public class DisplaySolution {

    public void printOut(Set<String> foundWords) {
        System.out.println(foundWords.size());
        for (String word : foundWords) {
            System.out.println(word);
        }
    }
}

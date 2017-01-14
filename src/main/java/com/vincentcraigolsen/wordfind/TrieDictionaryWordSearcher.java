package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.util.Set;
import java.util.TreeSet;

public class TrieDictionaryWordSearcher {

    private WordTrie dictionary;
    private String[][] letterGrid;
    private int numOfRows;
    private int numOfColumns;
    protected boolean[][] parentGrid;

    TrieDictionaryWordSearcher(WordTrie dictionary, String[][] letterGrid) {
        this.dictionary = dictionary;
        this.letterGrid = letterGrid;
        numOfRows = letterGrid.length;
        numOfColumns = letterGrid[0].length;
        parentGrid = new boolean[numOfRows][numOfColumns]; //initializes as all false
    }

    public Set<String> trieToFindWords() {

        Set<String> foundWords = new TreeSet<>();

        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColumns; col++) {
                foundWords.addAll(appendAllNeighbors(letterGrid[row][col], row, col, foundWords));
            }
        }

        return foundWords;
    }

    private Set<String> appendAllNeighbors(String currentCombo, int gridRow, int gridCol, Set<String> foundWords) {

        parentGrid[gridRow][gridCol] = true;

        for (int neighborRow = gridRow - 1; neighborRow <= gridRow + 1; neighborRow++) {
            for (int neighborCol = gridCol - 1; neighborCol <= gridCol + 1; neighborCol++) {
                if (isValidCell(neighborRow, neighborCol)) {
                    String newCombo = currentCombo + letterGrid[neighborRow][neighborCol];
                    if (dictionary.contains(newCombo)) {
                        foundWords.add(newCombo);
                        appendAllNeighbors(newCombo, neighborRow, neighborCol, foundWords);
                    } else if (dictionary.containsPrefixOf(newCombo)) {
                        appendAllNeighbors(newCombo, neighborRow, neighborCol, foundWords);
                    }
                }
            }
        }
        parentGrid[gridRow][gridCol] = false;

        return foundWords;
    }

    protected Boolean isValidCell(int row, int col) {
        return (row >= 0
                && row < numOfRows
                && col >= 0
                && col < numOfColumns
                && !parentGrid[row][col]);
    }
}

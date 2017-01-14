package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.io.FileNotFoundException;
import java.util.Set;

public class WordFind {

    private static final String DICTIONARY_FILE_NAME = "WordFind/englishwords.txt";
    private static final String GRID_FILENAME = "WordFind/grid.txt";

    public static void main(String[] args) throws FileNotFoundException {

        FileDao fileDao = new FileDao();
        WordTrie dictionaryTrie = fileDao.getDictionaryIntoTrie(DICTIONARY_FILE_NAME);
        String[][] letterGrid = fileDao.getGrid(GRID_FILENAME);

        TrieDictionaryWordSearcher wordSearcher = new TrieDictionaryWordSearcher(dictionaryTrie, letterGrid);
        Set<String> foundWords = wordSearcher.trieToFindWords();

        DisplaySolution display = new DisplaySolution();
        display.printOut(foundWords);
    }
}

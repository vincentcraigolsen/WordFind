package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileDao {

    public WordTrie getDictionaryIntoTrie(String englishWordsFileName) throws IOException {
        WordTrie words = new WordTrie();

        words = createDictionaryTrieWithBufferedReaderFrom(englishWordsFileName, words);

        return words;
    }

    public String[][] getGrid(String gridFileName) throws FileNotFoundException {
        Scanner gridFileScanner = new Scanner(new File(gridFileName));
        Integer rows = 0, columns = 0;

        if (gridFileScanner.hasNext()) {
            rows = Integer.valueOf(gridFileScanner.next());
        }
        if (gridFileScanner.hasNext()) {
            columns = Integer.valueOf(gridFileScanner.next());
        }

        String[][] grid = new String[rows][columns];
        int row = 0;
        while (gridFileScanner.hasNext()) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = gridFileScanner.next().toLowerCase();
            }
            row++;
        }

        return grid;
    }

    private WordTrie createDictionaryTrieWithBufferedReaderFrom(String englishWordsFileName, WordTrie words) throws FileNotFoundException, IOException {
        BufferedReader dictionaryFileReader = new BufferedReader(new FileReader(englishWordsFileName));

        while (dictionaryFileReader.ready()) {
            words.insert(dictionaryFileReader.readLine()); 
        }

        return words;
    }
}

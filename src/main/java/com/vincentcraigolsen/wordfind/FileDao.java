package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileDao {
    
    public WordTrie getDictionaryIntoTrie(String dictionaryFileName) throws FileNotFoundException {
        Scanner dictionaryFileScanner = new Scanner(new File(dictionaryFileName));

        WordTrie words = new WordTrie();

        while (dictionaryFileScanner.hasNext()) {
                words.insert(dictionaryFileScanner.next().trim().toLowerCase());
        }

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
                grid[row][col] = gridFileScanner.next().trim().toLowerCase();
            }
            row++;
        }

        return grid;
    }
}

package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class FileDao {

    public WordTrie getDictionaryIntoTrie(String englishWordsFileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        WordTrie words = new WordTrie();
        File dictionaryTrieFile = new File("WordFind/englishWordsInATrie");

        words = createDictionaryTrieWithBufferedReaderFrom(englishWordsFileName, words);
//        if (dictionaryTrieFile.isFile()) {
//
//            words = readDictionaryTrieFrom(dictionaryTrieFile, words);
//
//        } else {
//
//            words = createDictionaryTrieFrom(englishWordsFileName, words);
//
//            saveDictionaryTrieTo(dictionaryTrieFile, words);
//
//        }

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

    private WordTrie readDictionaryTrieFrom(File dictionaryTrieFile, WordTrie words) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(dictionaryTrieFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            words = (WordTrie) objectInputStream.readObject();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return words;
    }

    private WordTrie createDictionaryTrieFrom(String englishWordsFileName, WordTrie words) throws FileNotFoundException {
        Scanner dictionaryFileScanner = new Scanner(new File(englishWordsFileName));

        while (dictionaryFileScanner.hasNext()) {
            words.insert(dictionaryFileScanner.next().trim().toLowerCase());
        }

        return words;
    }
    
    private WordTrie createDictionaryTrieWithBufferedReaderFrom(String englishWordsFileName, WordTrie words) throws FileNotFoundException, IOException {
        BufferedReader dictionaryFileReader = new BufferedReader(new FileReader(englishWordsFileName));

        while (dictionaryFileReader.ready()) {
            dictionaryFileReader.lines().forEach(word -> words.insert(word));
        }

        return words;
    }    

    private void saveDictionaryTrieTo(File dictionaryTrieFile, WordTrie words) throws IOException {
        dictionaryTrieFile.createNewFile();
        FileOutputStream fileOutPutStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutPutStream = new FileOutputStream(dictionaryTrieFile);
            bufferedOutputStream = new BufferedOutputStream(fileOutPutStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(words);
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }
}

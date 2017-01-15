package com.vincentcraigolsen.wordfind;

import com.vincentcraigolsen.wordfind.objects.WordTrie;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

public class TrieDictionaryWordSearcherTest {

    WordTrie testDictionary = createDictionary();
    String[][] testLetterGrid = createLetterGrid();
    TrieDictionaryWordSearcher trieDictionaryWordSearcher = new TrieDictionaryWordSearcher(testDictionary, testLetterGrid);

    @Test
    public void when_grid_has_one_word_that_matches_the_dictionary_then_trieToFindWords_returns_that_match() {
        String[][] grid = new String[][]{
            {"r", "a"},
            {"c", "k"}};

        WordTrie dictionary = new WordTrie();
        dictionary.insert("rack");
        dictionary.insert("car");

        trieDictionaryWordSearcher = new TrieDictionaryWordSearcher(dictionary, grid);

        Set<String> expectedMatches = new TreeSet<>();
        expectedMatches.add("rack");
        expectedMatches.add("car");

        Assert.assertEquals(expectedMatches, trieDictionaryWordSearcher.trieToFindWords());
    }

    @Test
    public void when_grid_has_eight_words_that_match_the_dictionary_then_trieToFindWords_returns_ordered_set_of_those_matches() {

        Set<String> expectedMatches = createExpectedMatches();

        Assert.assertEquals(expectedMatches, trieDictionaryWordSearcher.trieToFindWords());
    }

    @Test
    public void when_coordinates_are_within_the_grid_and_not_a_parent_then_isValid_returns_true() {
        Assert.assertTrue(trieDictionaryWordSearcher.isValidCell(2, 1));
    }

    @Test
    public void when_is_a_parent_then_isValidCell_returns_false() {
        trieDictionaryWordSearcher.parentGrid[0][2] = true;
        Assert.assertFalse(trieDictionaryWordSearcher.isValidCell(0, 2));
    }

    @Test
    public void when_coordinates_are_outside_the_grid_then_isValidCell_returns_false() {
        Assert.assertFalse(trieDictionaryWordSearcher.isValidCell(99, 99));
    }

    private Set<String> createExpectedMatches() {
        Set<String> expectedMatches = new TreeSet<>();
        expectedMatches.add("dog");
        expectedMatches.add("cat");
        expectedMatches.add("rat");
        expectedMatches.add("god");
        expectedMatches.add("bat");
        expectedMatches.add("tab");
        expectedMatches.add("art");
        expectedMatches.add("tar");

        return expectedMatches;
    }

    private WordTrie createDictionary() {
        WordTrie dictionary = new WordTrie();

        dictionary.insert("dog");
        dictionary.insert("cat");
        dictionary.insert("rat");
        dictionary.insert("god");
        dictionary.insert("bat");
        dictionary.insert("tab");
        dictionary.insert("art");
        dictionary.insert("tar");
        dictionary.insert("act");
        dictionary.insert("zzz");
        dictionary.insert("asd");
        dictionary.insert("qwe");
        dictionary.insert("ghj");
        dictionary.insert("bnm");

        return dictionary;
    }

    private String[][] createLetterGrid() {
        return new String[][]{
            {"c", "a", "t"},
            {"c", "b", "r"},
            {"d", "o", "g"}
        };
    }

}

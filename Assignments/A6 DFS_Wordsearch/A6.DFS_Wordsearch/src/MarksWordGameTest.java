import org.junit.Assert;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class MarksWordGameTest {

    // Initialize test fixtures
    String[] fileNames = {null, "", "words_small.txt", "words_medium.txt",
                            "words.txt", "OWL.txt", "CSW12.txt"};

    @Test
    public void testLoadLexicon() {
        MarksWordGame testObj = new MarksWordGame();
        // Test null handling
        try {
            testObj.loadLexicon(fileNames[0]);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        // Test processing small file
        testObj.loadLexicon(fileNames[2]);
        // Test processing file with definitions on each line
        testObj.loadLexicon(fileNames[5]);
        System.out.println("HOLY LONG LEXICON");
    }

    @Test
    public void testSetBoard() {
        MarksWordGame testObj = new MarksWordGame();
        // Test setting up board with a null array
        try {
            testObj.setBoard(null);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        // Test setting up the board with a square array
        String[] squareArrayStrings = {"A", "B", "C", "D"};
        try {
            testObj.setBoard(squareArrayStrings);
        } catch (IllegalArgumentException e) {
            Assert.assertFalse("Exception thrown when it should not have!", false);
        }
        // Test setting up the board with a non-square array
        String[] notSquareArrayStrings = {"A", "B", "C", "D", "E", "F"};
        try {
            testObj.setBoard(notSquareArrayStrings);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testGetBoard() {
        MarksWordGame testObj = new MarksWordGame();
        // Test displaying the default board
        System.out.println(testObj.getBoard());
    }

    @Test
    public void testGetAllValidWords() {
        MarksWordGame testObj = new MarksWordGame();
        testObj.loadLexicon("words.txt");
        System.out.println(testObj.getAllValidWords(5).toString());
        Object o = testObj.getAllValidWords(30);
        Assert.assertEquals(null, o);
        // Test with a board of length 1
        String[] oneByOneArray = {"A"};
        testObj.setBoard(oneByOneArray);
        SortedSet<String> actualWords = testObj.getAllValidWords(3);
        if (actualWords == null) System.out.println("NULL");
        else System.out.println("TEST FAILED");
        String[] twentyByTwentyBoard = {"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B","G","U","P","Y","H","R","R","X","R","E","F","H","D","H","T","K","X","K","O","Z","F","W","Y","H","Y","T","C","H","M","V","P","R","T","A","K","N","E","S","I","B","T","M","V","Y","Q","E","U","O","E","F","A","K","J","C","W","I","K","I","U","K","T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G","D","P","P","P","G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K","H","K","G","B","B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z","R","G","A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T","J","K","J","F","C","I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C","V","P","P","D","S","Q","E","W","O","C","R","L","V","L","P","T","A","T","N","O","R","M","W","K","O","D","O","U","O","V","F","M","H","V","V","S","I","X","Z","L","O","T","Z","L","B","R","G","F","Q","P","A","Y","P","D","L","B","K","S","N","C","H","O","P","Y","K","H","C","R","R","I","C","S","B","J","X","R","F","I","Y","R","H","B","Z","I","P","C","K","I","N","O","E","C","C","U","C","P","I","J","R","E","Y","E","Z","U","R","R","M","F","S","M","R","N","J","I","B","T","Q","O","C","V","R","O","T","X","H","C","R","W","S","A","V","T","N","U","I","O","W","X","C","O","R","X","Q","A","S","A","S","S","E","M","B","L","Y","O","Z","F","P","L","S","C","I","T","L","U","M","O","N","I","T","O","R","J","W","I","N","L","L","L","E","L","J","R","R","E","M","M","O","B","D","X","I","J","D","S","R","L","C","H","S","H","Y","U","L","P","M","O","U","S","E","C","B","I","I","U","I"};
        testObj.setBoard(twentyByTwentyBoard);
        testObj.loadLexicon(fileNames[3]);
        System.out.println(testObj.getBoard());
        System.out.println(testObj.getAllValidWords(10).toString());
    }

    @Test
    public void testGetScoreForWords() {
        MarksWordGame testObj = new MarksWordGame();
        testObj.loadLexicon("words.txt");
        int expected = 31;
        int actual = testObj.getScoreForWords(testObj.getAllValidWords(5), 5);
        Assert.assertEquals(expected, actual);
        System.out.println("Total word score is: " + actual);
    }

    @Test
    public void testIsValidWord() {
        MarksWordGame testObj = new MarksWordGame();
        testObj.loadLexicon("words_small.txt");
        Assert.assertTrue(testObj.isValidWord("hangout"));
    }

    @Test
    public void testIsValidPrefix() {
        MarksWordGame testObj = new MarksWordGame();
        testObj.loadLexicon("words_small.txt");
        Assert.assertTrue(testObj.isValidPrefix("de"));
    }

    @Test
    public void testIsOnBoard() {
        MarksWordGame testObj = new MarksWordGame();
        testObj.loadLexicon("words.txt");
        System.out.println(testObj.isOnBoard("PEACE"));
    }

    @Test
    public void testGetLexiconData() {
        MarksWordGame testObj = new MarksWordGame();
        Assert.assertEquals(0, testObj.getLexiconData().size());
    }

    @Test
    public void testGetBoardWidth() {
        MarksWordGame testObj = new MarksWordGame();
        Assert.assertEquals(4, testObj.getBoardWidth());
    }
}
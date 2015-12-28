import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Implementation class of WordSearchGame interface.
 *
 * @author  Mark Gallagher Jr (mag0038@auburn.edu)
 * @version 11.11.2014
 */
public class MarksWordGame implements WordSearchGame {

    // Declare class fields
    private static final String[] DEFAULT_BOARD = {"E", "E", "C", "A",
                                                   "A", "L", "E", "P",
                                                   "H", "N", "B", "O",
                                                   "Q", "T", "T", "Y"};
    private TreeSet<String> lexiconData;
    private String[][] gameBoard;
    private int boardWidth; // For tracking the "width" of the board's sides

    /**
     * Constructor - initializes class fields
     */
    public MarksWordGame(){
        lexiconData = new TreeSet<String>();
        setBoard(DEFAULT_BOARD);
    }

    /**
     * Loads the lexicon into a data structure for later use.
     *
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null or cannot be opened.
     */
    @Override
    public void loadLexicon(String fileName) {
        // Start of file error prevention code
        Scanner fileScan;
        try {
            fileScan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Lexicon file not found");
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException("Lexicon file name cannot be null!");
        }
        // End of file error prevention code

        // Start loading lexicon information from file
        Scanner wordScan;
        while (fileScan.hasNextLine()) {
            wordScan = new Scanner(fileScan.nextLine());
            lexiconData.add(wordScan.next().toUpperCase());
        }
        // End of loading lexicon information from file

        // Close file scanner stream
        fileScan.close();
    }

    /**
     * Stores the incoming array of Strings in a data structure that will
     * make it convenient to find words.
     *
     * @param letterArray This array of length N^2 stores the contents of the
     *                    game board in row-major order. Thus, index 0 stores the contents
     *                    of board position (0,0) and index length-1 stores the contents
     *                    of board position (N-1,N-1). Note that the board must be square
     *                    and that the strings inside may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is
     *                                  not square.
     */
    @Override
    public void setBoard(String[] letterArray) {
        // Argument error checking code
        if (letterArray == null) throw new IllegalArgumentException("Board array cannot be null");
        if(Math.round(Math.sqrt(letterArray.length)) != Math.sqrt(letterArray.length)) {
            throw new IllegalArgumentException("Board array is not square!");
        }

        // Set up board side width value and initialize game board to a square 2D array of that size
        boardWidth = (int)Math.sqrt(letterArray.length);
        gameBoard = new String[boardWidth][boardWidth];
        int i = 0; // Keeps track of where in the letterArray method is
        for (int row = 0; row < boardWidth; row++) { // Iterates through rows of board
            for (int col = 0; col < boardWidth; col++) { // Iterates through columns of board
                gameBoard[row][col] = letterArray[i].toUpperCase();
                i++;
            }
        }
    }

    /**
     * Creates a String representation of the board, suitable for
     * printing to standard out. Note that this method can always be
     * called since implementing classes should have a default board.
     */
    @Override
    public String getBoard() {
        String ret = "";
        for (String[] rowElement : gameBoard) {
            for (String colElement : rowElement) {
                ret += colElement;
                ret += "\t";
            }
            ret += "\n";
        }

        return ret;
    }

    /**
     * Retrieves all valid words on the game board, according to the
     * stated game rules.
     *
     * @param minimumWordLength The minimum allowed length (i.e., number
     *                          of characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum
     * length found on the game board and in the lexicon. If no words can
     * be found, return null.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    @Override
    public SortedSet<String> getAllValidWords(int minimumWordLength) {
        // Argument error checking code
        if (minimumWordLength < 1) throw new IllegalArgumentException("Minimum word length cannot be < 1");
        if (lexiconData.isEmpty()) throw new IllegalStateException("No lexicon loaded!");
        TreeSet<String> returnItems = new TreeSet<String>();
        // Use a stack to track where the search has already been
        Stack<String> coordStack;
        for (int row = 0; row < boardWidth; row++) {
            for (int col = 0; col < boardWidth; col++) {
                coordStack = new Stack<String>();
                Stack<String> validWords = new Stack<String>();
                validWords = recursiveDFS(row, col, coordStack, minimumWordLength, gameBoard[row][col],
                        validWords, null); // Last parameter not used in this method
                returnItems.addAll(validWords);
            }
        }

        if (returnItems.isEmpty()) return null;
        return returnItems;
    }

    /**
     * Recursive method that performs Depth-First Search (DFS) to find
     * all valid words from a given point.
     *
     * @param row START: the starting row of search RECURSIVE: the current row of search
     * @param col START: the starting column of search RECURSIVE: the current column of search
     * @param coordStack Stack that stores the coordinates of all places visited
     * @param minimumWordLength Minimum valid word length, given by the method caller
     * @param word START: the String held in the original row, col RECURSIVE: stores the word as it forms
     * @param wordsFound Stack that contains all of the words found by the algorithm
     * @param pathsFound Used only in isOnBoard - passes an ArrayList to store paths to words
     * @return All valid words found by the algorithm
     */
    private Stack<String> recursiveDFS(int row, int col, Stack<String> coordStack, int minimumWordLength, String word,
                               Stack<String> wordsFound, ArrayList<WordPathTracker> pathsFound) {
        coordStack.push(Integer.toString(row) + " " + Integer.toString(col));
        // If word meets specifications, add it to the words found stack
        if (word.length() >= minimumWordLength && isValidWord(word)) {
            if (pathsFound != null) { // Path tracker only used in isOnBoard method
                pathsFound.add(new WordPathTracker(word, coordStack)); // Adds to list of all paths with their words
            }
            if (wordsFound != null) {
                wordsFound.push(word);
            }
        }
        if (!isValidPrefix(word)) {
            coordStack.pop();
            return wordsFound; // No prefix means no words here
        }

        // Ensure that only VALID moves are taken and NOT out of bounds
        if (row - 1 >= 0 && col - 1 >= 0 &&
                !coordStack.contains(Integer.toString(row - 1) + " " + Integer.toString(col - 1))) {
            recursiveDFS(row - 1, col - 1, coordStack, minimumWordLength, word + gameBoard[row - 1][col - 1],
                    wordsFound, pathsFound);
        }
        if (row - 1 >= 0 && !coordStack.contains(Integer.toString(row - 1) + " " + Integer.toString(col))) {
            recursiveDFS(row - 1, col, coordStack, minimumWordLength, word + gameBoard[row - 1][col],
                    wordsFound, pathsFound);
        }
        if (row - 1 >= 0 && col + 1 < boardWidth &&
                !coordStack.contains(Integer.toString(row - 1) + " " + Integer.toString(col + 1))) {
            recursiveDFS(row - 1, col + 1, coordStack, minimumWordLength, word + gameBoard[row - 1][col + 1],
                    wordsFound, pathsFound);
        }
        if (col - 1 >= 0 && !coordStack.contains(Integer.toString(row) + " " + Integer.toString(col - 1))) {
            recursiveDFS(row, col - 1, coordStack, minimumWordLength, word + gameBoard[row][col - 1],
                    wordsFound, pathsFound);
        }
        if (col + 1 < boardWidth && !coordStack.contains(Integer.toString(row) + " " + Integer.toString(col + 1))) {
            recursiveDFS(row, col + 1, coordStack, minimumWordLength, word + gameBoard[row][col + 1],
                    wordsFound, pathsFound);
        }
        if (row + 1 < boardWidth && col - 1 >= 0 &&
                !coordStack.contains(Integer.toString(row + 1) + " " + Integer.toString(col - 1))) {
            recursiveDFS(row + 1, col - 1, coordStack, minimumWordLength, word + gameBoard[row + 1][col - 1],
                    wordsFound, pathsFound);
        }
        if (row + 1 < boardWidth && !coordStack.contains(Integer.toString(row + 1) + " " + Integer.toString(col))) {
            recursiveDFS(row + 1, col, coordStack, minimumWordLength, word + gameBoard[row + 1][col],
                    wordsFound, pathsFound);
        }
        if (row + 1 < boardWidth && col + 1 < boardWidth &&
                !coordStack.contains(Integer.toString(row + 1) + " " + Integer.toString(col + 1))) {
            recursiveDFS(row + 1, col + 1, coordStack, minimumWordLength, word + gameBoard[row + 1][col + 1],
                    wordsFound, pathsFound);
        }

        coordStack.pop();
        return wordsFound;
    }

    /**
     * Computes the cumulative score for the scorable words in the given set.
     * To be scorable, a word must (1) have at least the minimum number of characters,
     * (2) be in the lexicon, and (3) be on the board. Each scorable word is
     * awarded one point for the minimum number of characters, and one point for
     * each character beyond the minimum number.
     *
     * @param words             The set of words that are to be scored.
     * @param minimumWordLength The minimum number of characters required per word
     * @return the cumulative score of all scorable words in the set
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    @Override
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        // Argument error checking code
        if (minimumWordLength < 1) throw new IllegalArgumentException("Minimum word length cannot be < 1");
        if (lexiconData.isEmpty()) throw new IllegalStateException("No lexicon loaded!");

        // Begin tallying up total word score
        int totalScore = 0;
        for (String currentWord : words) {
            if (isOnBoard(currentWord) != null && currentWord.length() >= minimumWordLength) {
                totalScore++;
                if (currentWord.length() > minimumWordLength) {
                    String extraPts = currentWord.substring(minimumWordLength);
                    totalScore += extraPts.length();
                }
            }
        }

        return totalScore;
    }

    /**
     * Determines if the given word is in the lexicon.
     *
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    @Override
    public boolean isValidWord(String wordToCheck) {
        // Argument error checking code
        if (wordToCheck == null) throw new IllegalArgumentException("Word to check cannot be null");
        if (lexiconData.isEmpty()) throw new IllegalStateException("No lexicon loaded!");

        return lexiconData.contains(wordToCheck.toUpperCase());
    }

    /**
     * Determines if there is at least one word in the lexicon with the
     * given prefix.
     *
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    @Override
    public boolean isValidPrefix(String prefixToCheck) {
        // Argument error checking code
        if (prefixToCheck == null) throw new IllegalArgumentException("Prefix to check cannot be null");
        if (lexiconData.isEmpty()) throw new IllegalStateException("No lexicon loaded!");

        if (lexiconData.higher(prefixToCheck.toUpperCase()) != null) {
            return lexiconData.higher(prefixToCheck.toUpperCase()).startsWith(prefixToCheck.toUpperCase());
        }
        else {
            return false;
        }
    }

    /**
     * Determines if the given word is in on the game board. If so,
     * it returns the path that makes up the word.
     *
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with
     * the path that makes up the word on the game board. If word
     * is not on the game board, return null. Positions on the board are
     * numbered from zero top to bottom, left to right (i.e., in row-major
     * order). Thus, on an NxN board, the upper left position is numbered
     * 0 and the lower right position is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException    if loadLexicon has not been called.
     */
    @Override
    public List<Integer> isOnBoard(String wordToCheck) {
        // Argument error checking code
        if (wordToCheck == null) throw new IllegalArgumentException("Word to check cannot be null");
        if (lexiconData.isEmpty()) throw new IllegalStateException("No lexicon loaded!");

        // Reusing the recursiveDFS method
        ArrayList<WordPathTracker> allPossiblePaths = new ArrayList<WordPathTracker>();
        Stack<String> coordStack;
        for (int row = 0; row < boardWidth; row++){
            for (int col = 0; col < boardWidth; col++) {
                // Only perform the recursion IF the start point is the prefix to the desired word
                if (wordToCheck.startsWith(gameBoard[row][col])) {
                    ArrayList<WordPathTracker> recursivePathTrack = new ArrayList<WordPathTracker>();
                    coordStack = new Stack<String>();
                    recursiveDFS(row, col, coordStack, wordToCheck.length(), gameBoard[row][col],
                            null, recursivePathTrack);
                    allPossiblePaths.addAll(recursivePathTrack);
                }
            }
        }

        // If a possible path was found, locate the desired word in the array and return the path track
        if (allPossiblePaths.size() > 0) {
            for (WordPathTracker currentPathTrack : allPossiblePaths) {
                if (currentPathTrack.getWord().equals(wordToCheck)) {
                    return currentPathTrack.getPathTrack();
                }
            }
        }

        return null;
    }

    /**
     * Getter for lexicon data (used for testing purposes).
     *
     * @return TreeSet containing the loaded lexicon data.
     */
    public TreeSet<String> getLexiconData() {
        return lexiconData;
    }

    /**
     * Getter for the width of the game board (used for testing purposes).
     *
     * @return int representing the length of one side of the game board.
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /*************************************************************************************************************/

    /**
     * Nested class to keep track of word path values.
     */
    public class WordPathTracker {

        // Declare class fields
        private String word;
        private ArrayList<Integer> pathTrack;

        /**
         *  Constructor takes in the word and its path in the coordinate stack.
         *
         * @param value The word that is being stored.
         * @param coordStack The stack that stores coordinates to the word, stored in "ROW COL" form.
         */
        public WordPathTracker(String value, Stack<String> coordStack) {
            word = value;
            pathTrack = new ArrayList<Integer>();
            for (String coordSet : coordStack) {
                int spaceLoc = coordSet.indexOf(' ');
                int row = Integer.parseInt(coordSet.substring(0, spaceLoc));
                int col = Integer.parseInt(coordSet.substring(spaceLoc + 1, coordSet.length()));
                int path = (row * boardWidth) + col; // Converts into row-major form
                pathTrack.add(path);
            }
        }

        /**
         * Getter for the word stored in this object.
         *
         * @return value of word stored.
         */
        public String getWord() {
            return word;
        }

        /**
         * Getter for the path tracking to the word stored in this object.
         *
         * @return ArrayList of ints representing the coordinates to a word in row-major form.
         */
        public ArrayList<Integer> getPathTrack() {
            return pathTrack;
        }
    }
}

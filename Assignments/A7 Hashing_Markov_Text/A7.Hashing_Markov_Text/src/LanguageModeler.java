import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Creates an order K Markov model for a text sample.
 *
 */
public class LanguageModeler {

    // Declare class fields
    private int kValue;                             // Stores K-value for this model
    private HashMap<String, String> langModel;      // Stores the language model formed at initialization
    private String originalText;                    // Stores the original text used for processing
    private Random randomGen = new Random();        // Seed the random number generator

    // create an order K model for text
    public LanguageModeler(int K, File text) {
        // Call second constructor using file's text as the input String
        this(K, fileProcessor(text));
    }

    // Private method to process incoming files for the constructor
    private static String fileProcessor(File text) {
        // Start file error prevention code
        Scanner fileScan;
        try {
            fileScan = new Scanner(text);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Source file not found");
        }
        // End file error prevention code

        // Begin reading in text from file
        String fileText = "";
        while (fileScan.hasNextLine()) {
            String currentLine = fileScan.nextLine();
            // Removal of "Byte Order Mark" put in by Notepad (#WindowsProblems)
            if (currentLine.startsWith(String.valueOf(Character.toChars(65279)))) {
                currentLine = currentLine.substring(1);
            }
            fileText += currentLine;
            if (fileText.charAt(fileText.length() - 1) != ' ') { // Add a space at line ends, if not already there
                fileText += " ";
            }
        }

        fileText = fileText.trim(); // Remove final space added

        // Close file stream and return file's contents
        fileScan.close();
        return fileText;
    }

    // create an order K model for text
    public LanguageModeler(int K, String text) {
        kValue = K;
        langModel = new HashMap<String, String>();
        originalText = text;
        int textLength = text.length();

        for (int i = 0; i + K <= textLength; i++) {
            String kKey = text.substring(i, i + K);
            String nextChar = " ";
            if (i + K != textLength) { // Get char after K-seed
                nextChar = String.valueOf(text.charAt(i + K));
            }

            if (langModel.containsKey(kKey)) { // See if key already exists
                String existingChars = langModel.get(kKey);
                existingChars += nextChar; // Add next char into list of existing chars
                langModel.put(kKey, existingChars);
            }
            else { // If key does not already exist, add it
                langModel.put(kKey, nextChar);
            }
        }

    }

    // return the first K characters of the sample text
    public String firstSeed() {
        return originalText.substring(0, kValue);
    }

    // return K consecutive characters from the sample text,
    // selected uniformly at random
    public String randomSeed() {
        // Find a random int between 0 and a value K away from the text's length
        int startIndex = randomGen.nextInt(originalText.length() - kValue + 1);
        return originalText.substring(startIndex, startIndex + kValue);
    }

    // return a character that follows seed in the sample text,
    // selected according to the probability distribution of all
    // characters that follow seed in the sample text
    public char nextChar(String seed) {
        String allNextChars = langModel.get(seed);
        if (allNextChars == null) return ' '; // Seed was not valid, return a space

        // Find a random int between 0 and the amount of chars available
        int randomCharIndex = randomGen.nextInt(allNextChars.length());
        // Return char at the random index
        return allNextChars.charAt(randomCharIndex);
    }

}

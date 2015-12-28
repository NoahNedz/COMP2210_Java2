import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * MarkovMonkey.java. Produces output text from an order K Markov model
 * of provided sample text.
 */
public class MarkovMonkey {

    /**
     * Main method for running the MarkovMonkey algorithms.
     *
     * @param args Must contain 4 valid arguments: k, length, source, result.
     */
    public static void main(String[] args) {
        // START OF ERROR CHECKING ARGUMENTS
        // Check for 4 arguments
        if (args.length != 4) {
            System.out.println("User must provide 4 arguments for program to run: "
                                + "k-value, length of output, source file, result file.\nTerminating...");
            return;
        }
        // Check for negative values for k and length
        int kInput = Integer.parseInt(args[0]);
        int lengthInput = Integer.parseInt(args[1]);
        if (kInput <= 0 || lengthInput <= 0) {
            System.out.println("Values for k and length must be non-negative and greater than 0."
                                + "\nTerminating...");
            return;
        }
        // Check to ensure that the source file exists and contains more than k characters
        File sourceFile = new File(args[2]);
        if (!sourceFile.exists() || !sourceFile.canRead()) {
            System.out.println("Specified source file does not exist or cannot be read from.\nTerminating...");
            return;
        }
        try {
            if (!fileHasEnoughChars(kInput, sourceFile)) {
                System.out.println("Source file does not have more than K characters.\nTerminating...");
                return;
            }
        } catch (IOException e) {
            System.out.println("Specified source file does not exist or cannot be read from.\nTerminating...");
            return;
        }
        // END OF ARGUMENT ERROR CHECKING

        // Create language modeler based on the input file
        LanguageModeler langModel = new LanguageModeler(kInput, sourceFile);
        // Begin language modeling, store result into a string that will write to the output later
        String seed = langModel.firstSeed();
        String result = "";
        result += seed;
        int i = kInput;

        while (i <= lengthInput) {
            result += langModel.nextChar(seed);
            i++;
            seed = result.substring(i - kInput);
        }

        // Open a file stream to the output file
        try {
            File outputFile = new File(args[3]);
            // Create a new file (overwrite existing)
            if (outputFile.exists()) {
                outputFile.delete();
            }
            outputFile.createNewFile();
            // Create a buffered writer to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(result);
            writer.close(); // Close file once writing is complete

        } catch (IOException e) {
            System.out.println("Error occurred during output file writing.\nTerminating...");
        }

    }

    /**
     * Checks to see if a file has enough characters to be used in the language modeler.
     *
     * @param k             K-value to compare against.
     * @param inputFile     Input file to count number of valid characters.
     * @return              True if valid characters in file equals or exceeds K-value.
     * @throws IOException  if file is not found or valid.
     */
    private static boolean fileHasEnoughChars(int k, File inputFile) throws IOException {
        FileReader readFile = new FileReader(inputFile);
        char[] cbuf = new char[1];
        int n = 0;

        while (readFile.read(cbuf) != -1) {
            if (cbuf[0] != '\r' || cbuf[0] != '\n') n++;
        }
        readFile.close();

        return k <= n;
    }
}

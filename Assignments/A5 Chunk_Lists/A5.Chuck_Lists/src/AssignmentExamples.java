/**
 * AssignmentExamples.java
 * Generates the DnaStrand examples in the assignment handout.
 *
 * @author   Dean Hendrix <dh@auburn.edu>
 * @version  2014-10-13
 *
 */
public class AssignmentExamples {
   public static void main(String[] args) {
      DnaStrand dna = new ArrayStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
      String enzyme = "GAATTC";
      String splice = "XXYY";
      System.out.println("DnaStrand method cutWith() - ArrayStrand Implementation");
      System.out.println("======================");
      System.out.println("Before: " + dna);
      DnaStrand ret = dna.cutWith(enzyme);
      System.out.println("After:  " + dna);
      System.out.println("Return: " + ret);
      System.out.println();


      dna = new LinkedStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
      enzyme = "GAATTC";
      splice = "XXYY";
      System.out.println("DnaStrand method cutWith() - LinkedStrand Implementation");
      System.out.println("======================");
      System.out.println("Before: " + dna);
      ret = dna.cutWith(enzyme);
      System.out.println("After:  " + dna);
      System.out.println("Return: " + ret);
      System.out.println();
   
      dna = new ArrayStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
      enzyme = "GAATTC";
      splice = "XXYY";
      System.out.println("DnaStrand method cutAndSplice() - ArrayStrand implementation");
      System.out.println("======================");
      System.out.println("Before: " + dna);
      ret = dna.cutAndSplice(enzyme, splice);
      System.out.println("After:  " + dna);
      System.out.println("Return: " + ret);
      System.out.println();

       dna = new LinkedStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
       enzyme = "GAATTC";
       splice = "XXYY";
       System.out.println("DnaStrand method cutAndSplice() - LinkedStrand implementation");
       System.out.println("======================");
       System.out.println("Before: " + dna);
       ret = dna.cutAndSplice(enzyme, splice);
       System.out.println("After:  " + dna);
       System.out.println("Return: " + ret);
       System.out.println();

       dna = new LinkedStrand("GATGATCTGAT");
       enzyme = "GAT";
       splice = "XXYYZZ";
       System.out.println("DnaStrand method cutAndSplice() - LinkedStrand implementation");
       System.out.println("======================");
       System.out.println("Before: " + dna);
       ret = dna.cutAndSplice(enzyme, splice);
       System.out.println("After:  " + dna);
       System.out.println("Return: " + ret);
       System.out.println("Size: " + ret.size());
       System.out.println();
   }
}
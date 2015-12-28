/**
 * TimingCode.java
 * Illustrates basic approach to measuring a method's
 * running time.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-08
 *
 */
 public class TimingCode {

   public static void main(String[] args) {
     long start, elapsedTime;
     double avgTime = 0d;
     int NUM_RUNS = 10;
     for (int i = 0; i < NUM_RUNS; i++) {
       start = System.nanoTime();
       foo();
       elapsedTime = System.nanoTime() - start;
       avgTime += elapsedTime;
     }
     avgTime = avgTime / NUM_RUNS;
     avgTime = avgTime / 1_000_000_000d; // convert to seconds
     System.out.printf("%s%4.3f%s\n", "Method foo's running time: ", avgTime, " seconds");
   }


   // Something that will hopefully take time >= 0.001 seconds
   // so that the program output looks better.
   private static void foo() {
      for (int i = 0; i < 100_000; i++) {
         String s1 = "War";
         String s2 = "Eagle";
         String s3 = s1 + s2;
         s1 = null;
         s2 = null;
         s3 = null;
      }
   }

 }
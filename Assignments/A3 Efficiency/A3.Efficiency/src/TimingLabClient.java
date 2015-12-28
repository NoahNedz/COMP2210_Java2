import java.lang.Math;

/**
 * TimingLabClient.java. Provides a simple example
 * client of TimingLab.java.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @author    Ben Cyr
 * @author    Mark Gallagher
 * @version   2014-09-13
 *
 */
public class TimingLabClient {

   public static void main(String[] args) {

      // some useful variables that you will need in
      // your own code
      double BILLION = 1_000_000_000d; // nanoseconds to seconds
      double start = 0;         // start time of the current run
      double elapsedTime = 0;   // elapsed time of current run
      double prevTime = 0;      // elapsed time of previous run
      double ratio = 1;         // currentTime / prevTime
      double lgratio = 0;       // log base 2 of ratio
      int N = 8;                // problem size parameter
      int key = 25;             // selects internal method of RunningTime

      TimingLab tl = new TimingLab(key);

      // measure elapsed time for multiple calls to timeTrial
      // with increasing N values
      System.out.print("Timing multiple calls to timeTrial(N) ");
      System.out.println("with increasing N values.");
      System.out.println("N\t  Time\t\tR\t\tk");
      for (int i = 0; i < 7; i++) {
         start = System.nanoTime();
         tl.timeTrial(N);
         elapsedTime = (System.nanoTime() - start) / BILLION;
         System.out.print(N + "\t");
         System.out.printf("%7.3f\t\t", elapsedTime);
         if (prevTime == 0) System.out.print("-\t\t-\n");
         else {
             ratio = elapsedTime / prevTime;
             lgratio = Math.log10(ratio) / Math.log10(2);
             System.out.printf("%4.2f\t%4.2f\n", ratio, lgratio);
         }
         prevTime = elapsedTime;
         N *= 2;
      }

   }

}

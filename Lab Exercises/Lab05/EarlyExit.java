import java.util.List;
import java.util.LinkedList;
import java.util.Random;

/**
 * EarlyExit.java
 * Illustrates the average case running time difference
 * between a linear search with an "early exit" and one
 * without.
 *
 * Note: Average case is defined as a search for a list
 * element that is at an arbitrary list location, other
 * than the first or last.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-08
 *
 */
 public class EarlyExit {

   public static void main(String[] args) {
     int N        = 1_000_000;     // size of the list to be searched
     int NUM_RUNS = 100;           // number of timing runs
     int SECONDS  = 1_000_000_000; // 1.0E9 ns per second

     // generate timing data for "average case" searches
     long start, elapsedTime;
     double avgTimeA = 0d;
     double avgTimeB = 0d;
     Integer target = 42;

     for (int i = 0; i < NUM_RUNS; i++) {
       List<Integer> list = getRandomListWithSeed(N, 42);

       start = System.nanoTime();
       EarlyExit.searchA(list, target);
       elapsedTime = System.nanoTime() - start;
       avgTimeA += elapsedTime;

       start = System.nanoTime();
       EarlyExit.searchB(list, target);
       elapsedTime = System.nanoTime() - start;
       avgTimeB += elapsedTime;
     }

     avgTimeA = (avgTimeA / NUM_RUNS) / SECONDS;
     avgTimeB = (avgTimeB / NUM_RUNS) / SECONDS;

     System.out.printf("%s%4.3f%s\n","Without early exit: ", avgTimeA, " seconds");
     System.out.printf("%s%4.3f%s\n","With early exit:    ", avgTimeB, " seconds");
   }

   // exits only after examining the entire list
   private static <T> boolean searchA(List<T> list, T target){
     boolean found = false;
     for (T element : list) {
       if (element.equals(target)) {
         found = true;
       }
     }
     return found;
   }

   // exits as soon as it knows the status of the search
   private static <T> boolean searchB(List<T> list, T target){
     for (T element : list) {
       if (element.equals(target)) {
         return true;
       }
     }
     return false;
   }

   // fills a list with N random integer values, then places the
   // specified seed value at a random index i, 0 < i < N.
   private static List<Integer> getRandomListWithSeed(int N, int seed) {
     Random rng = new Random();
     List<Integer> list = new LinkedList<Integer>();
     for (int i = 0; i < N; i++) {
       list.add(i, new Integer(rng.nextInt()));
     }
     int index = rng.nextInt(N - 1) + 1;
     list.set(index, seed);
     return list;
   }

 }
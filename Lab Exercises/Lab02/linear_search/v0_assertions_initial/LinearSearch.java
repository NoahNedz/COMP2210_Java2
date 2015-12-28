/**
 * LinearSearch.java
 * Implements a simple linear search method on arrays of ints.
 *
 * @author   YOUR NAME (you@auburn.edu)
 * @author:  Dean Hendrix (dh@auburn.edu)
 * @version: 2014-08-18
 *
 */

 public final class LinearSearch {

   // No public constructor
   private LinearSearch() { }

   /**
    * This method uses the linear search algorithm to return
    * the index of the first occurence of target in a.
    *
    * @param   a  the array to be searched through
    * @param   target  the value to be searched for
    * @return  the index of the first occurence of target in a
    *          or -1 if a does not contain target
    */
   public static int search(int[] a, int target) {
     int i = 0;
     while ((a[i] != target) && (i < a.length)) {
       i++;
     }
     if (a[i] == target) {
       return i;
     }
     else {
       return -1;
     }
   }

 }

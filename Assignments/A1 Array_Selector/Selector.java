import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Mark Gallagher (mag0038@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2014-08-20
*
*/
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


/**
 * Selects the minimum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 *
 * @param a     the array to be searched through
 * @return      minimum value in a
 * @throws      IllegalArgumentException if a is null or zero-length
 *
 */
   public static int min(int[] a) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      
      int minimum = a[0];
      
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] < minimum)
         {
            minimum = a[i];
         }
      }
      
      return minimum;
   }


/**
 * Selects the maximum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 *
 * @param a     the array to be searched through
 * @return      maximum value in a
 * @throws      IllegalArgumentException if a is null or zero-length
 *
 */
   public static int max(int[] a) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      
      int maximum = a[0];
      
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] > maximum)
         {
            maximum = a[i];
         }
      }
      
      return maximum;
   }


/**
 * Selects the kth minimum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth minimum value. Note that there is no kth
 * minimum value if k < 1, k > a.length, or if k is larger than
 * the number of distinctk values in the array. The array a is not
 * changed by this method.
 *
 * @param a     the array to be searched through
 * @param k     the k-selection value
 * @return      kth minimum value in a
 * @throws      IllegalArgumentException as specified above
 *
 */
   public static int kmin(int[] a, int k) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      if (k < 1 || k > a.length)
      {
         throw new IllegalArgumentException("K value out of array bounds");
      }
      
      int[] sortedArray = Arrays.copyOf(a, a.length);
      Arrays.sort(sortedArray);
      int[] dupsRemoved = duplicateRemover(sortedArray);
      
      if (k > dupsRemoved.length)
      {
         throw new IllegalArgumentException("K value greater than distinct values in array.");
      }
      
      return dupsRemoved[k-1];
   }


/**
 * Selects the kth maximum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth maximum value. Note that there is no kth
 * maximum value if k < 1, k > a.length, or if k is larger than
 * the number of distinctk values in the array. The array a is not
 * changed by this method.
 *
 * @param a     the array to be searched through
 * @param k     the k-selection value
 * @return      kth maximum value in a
 * @throws      IllegalArgumentException as specified above
 *
 */
   public static int kmax(int[] a, int k) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      if (k < 1 || k > a.length)
      {
         throw new IllegalArgumentException("K value out of array bounds");
      }
      
      int[] sortedArray = Arrays.copyOf(a, a.length);
      Arrays.sort(sortedArray);
      int[] dupsRemoved = duplicateRemover(sortedArray);
      
      if (k > dupsRemoved.length)
      {
         throw new IllegalArgumentException("K value greater than distinct values in array.");
      }
      
      return dupsRemoved[dupsRemoved.length - k];
   }


/**
 * Returns an array containing all the values in a in the
 * range [low..high]; that is, all the values that are greater
 * than or equal to low and less than or equal to high,
 * including duplicate values. The length of the returned array
 * is the same as the number of values in the range [low..high].
 * If there are no qualifying values, this method returns a
 * zero-length array. Note that low and high do not have
 * to be actual values in a. This method thows an
 * IllegalArgumentException if a is null or has zero length.
 * The array a is not changed by this method.
 *
 * @param a     the array to be searched through
 * @param low   the lower bound value of the range
 * @param high  the upper bound value of the range
 * @return      an array of elements i
 * @throws      IllegalArgumentException as specified above
 *
 */
   public static int[] range(int[] a, int low, int high) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      
      int[] rangeOfValues = new int[a.length];
      int counter = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] >= low && a[i] <= high)
         {
            rangeOfValues[counter] = a[i];
            counter++;
         }
      }
      
      int[] result = Arrays.copyOf(rangeOfValues, counter);
      
      return result;
   }


/**
 * Returns the smallest value in a that is greater than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 *
 * @param a     the array to be searched through
 * @param key   the reference value
 * @return      the next greater value in a
 * @throws      IllegalArgumentException as specified above
 *
 */
   public static int ceiling(int[] a, int key) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      
      int[] eligibleValues = new int[a.length];
      int counter = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] >= key)
         {
            eligibleValues[counter] = a[i];
            counter++;
         }
      }
      
      // In event of there not being an eligible value, the trimmed array will
      // be of length 0, which will throw the IllegalArgumentException in the 
      // min() function.
      
      int[] eligibleValuesTrim = Arrays.copyOf(eligibleValues, counter);
      
      return min(eligibleValuesTrim);
   }


/**
 * Returns the largest value in a that is less than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 *
 * @param a     the array to be searched through
 * @param key   the reference value
 * @return      the next smaller value in a
 * @throws      IllegalArgumentException as specified above
 *
 */
   public static int floor(int[] a, int key) 
   {
      if (a == null || a.length == 0)
      {
         throw new IllegalArgumentException("Array is null or has length zero.");
      }
      
      int[] eligibleValues = new int[a.length];
      int counter = 0;
      for (int i = 0; i < a.length; i++)
      {
         if (a[i] <= key)
         {
            eligibleValues[counter] = a[i];
            counter++;
         }
      }
      
      // In event of there not being an eligible value, the trimmed array will
      // be of length 0, which will throw the IllegalArgumentException in the 
      // max() function.
      
      int[] eligibleValuesTrim = Arrays.copyOf(eligibleValues, counter);
      
      return max(eligibleValuesTrim);
   }
   
   /**
    * Removes duplicates in a sorted array for use with the kmin and kmax methods.
    * 
    * @param a    sorted array that will have duplicate values removed from.
    * @return     array with only unique values.
    */
   private static int[] duplicateRemover(int[] a) {
      int[] temp = new int[a.length];
      int counter = 0;      
      
      for (int i = 0; i < a.length; i++)
      {
         if (!valueExists(temp, a[i]))
         {
            temp[counter] = a[i];
            counter++;
         }
      }
      
      int[] result = Arrays.copyOf(temp, counter);
      
      return result;
      
   }
   
   /**
    * Searches through an array to see if a value is already in specified array. 
    * If value is found, returns true.
    * 
    * @param a       array to be searched through.
    * @param target  value to find
    * @return        true if value found, false otherwise
    */
   private static boolean valueExists(int[] a, int target) {
      for (int i = 0; i < a.length; i++)
      {
         if (target == a[i])
         {
            return true;
         }
      }
      
      return false;
   } 

}

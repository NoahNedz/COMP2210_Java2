import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Defines a library of selection methods
* on Collections.
*
* @author   Dean Hendrix (dh@auburn.edu)
* @author   Mark Gallagher (mag0038@auburn.edu)
* @version  2014-08-29
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
 * Selects the minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if c is empty. The Collection c is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      minimum value in c
 * @throws      IllegalArgumentException if either c or comp is null
 * @throws      NoSuchElementException if c is empty
 *
 */
   public static <T> T min(Collection<T> c, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty()) throw new NoSuchElementException("Empty Collection");

       // Set up Iterator and grab first element
       Iterator<T> itr = c.iterator();
       T minimum = itr.next();

       // Run comparisons to find minimum
       while (itr.hasNext()) {
           T element = itr.next();
           if (comp.compare(minimum, element) > 0) {
               minimum = element;
           }
       }

       return minimum;
   }


/**
 * Selects the maximum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if c is empty. The Collection c is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      maximum value in c
 * @throws      IllegalArgumentException if either c or comp is null
 * @throws      NoSuchElementException if c is empty
 *
 */
   public static <T> T max(Collection<T> c, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty()) throw new NoSuchElementException("Empty Collection");

       // Set up Iterator and grab first element
       Iterator<T> itr = c.iterator();
       T maximum = itr.next();

       // Run comparisons to find maximum
       while (itr.hasNext()) {
           T element = itr.next();
           if (comp.compare(maximum, element) < 0) {
               maximum = element;
           }

       }

       return maximum;
   }


/**
 * Selects the kth minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if either c is empty or if there is no kth minimum value. Note that there
 * is no kth minimum value if k < 1, k > c.size(), or if k is larger than
 * the number of distinct values in c. The Collection c is not changed by
 * this method.
 *
 * @param c     the Collection to be searched through
 * @param k     the k-selection value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      kth minimum value in c
 * @throws      IllegalArgumentException as specified above
 * @throws      NoSuchElementException as specified above
 *
 */
   public static <T> T kmin(Collection<T> c, int k, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty() || k < 1 || k > c.size()) throw new NoSuchElementException("Empty collection or invalid k");

       // Create sorted ArrayList
       ArrayList<T> sortedList = new ArrayList<T>(c);
       java.util.Collections.sort(sortedList, comp);

       int distinctCounter = c.size();      // Create counter to track the distinct values in ArrayList
       int kCounter = 1;                    // Create counter to track unique k values found
       T kthMinimum = sortedList.get(0);    // Grab first element for comparisons
       // Iteration loop, end condition: when kCounter is greater than k - 1 (means value should be found)
       for (int i = 1; kCounter < k; i++) {
           if (comp.compare(kthMinimum, sortedList.get(i)) == 0) { // Determines if duplicate value found
               distinctCounter--;
               if (distinctCounter < k) throw new NoSuchElementException();
           }
           else {
               // On each iteration, assigns current element to return value
               // On loop termination, return value will contain the kth minimum
               kthMinimum = sortedList.get(i);
               kCounter++;
           }
       }

       return kthMinimum;
   }


/**
 * Selects the kth minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if either c is empty or if there is no kth minimum value. Note that there
 * is no kth minimum value if k < 1, k > c.size(), or if k is larger than
 * the number of distinct values in c. The Collection c is not changed by
 * this method.
 *
 * @param c     the Collection to be searched through
 * @param k     the k-selection value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      kth minimum value in c
 * @throws      IllegalArgumentException as specified above
 * @throws      NoSuchElementException as specified above
 *
 */
   public static <T> T kmax(Collection<T> c, int k, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty() || k < 1 || k > c.size()) throw new NoSuchElementException("Empty collection or invalid k");

       // Create sorted ArrayList
       ArrayList<T> sortedList = new ArrayList<T>(c);
       java.util.Collections.sort(sortedList, comp);

       int distinctCounter = c.size();                  // Create counter to track the distinct values in ArrayList
       int kCounter = 1;                                // Create counter to track unique k values found
       T kthMaximum = sortedList.get(c.size() - 1);     // Grab last element for comparisons
       // Iteration loop, end condition: when counter is than c.size() - k (means value should be found)
       for (int i = c.size() - 2; kCounter < k; i--) {
           if (comp.compare(kthMaximum, sortedList.get(i)) == 0) { // Determines if duplicate value found
               distinctCounter--;
               if (distinctCounter < k) throw new NoSuchElementException();
           }
           else {
               // On loop termination, return value will contain the kth maximum
               kthMaximum = sortedList.get(i);
               kCounter++;
           }
       }

       return kthMaximum;
   }


/**
 * Returns a Collection containing all the values in the supplied
 * Collection c that are in the range [low..high]; that is, all the
 * values that are greater than or equal to low and less than or
 * equal to high, including duplicate values, as determined by the
 * supplied Comparator comp. The returned Collection contains only
 * values in the range [low..high], and no others. Note that low and
 * high do not have to be actual values in c. If there are no
 * qualifying values, including the case where c is empty, this method
 * throws a NoSuchElementException. This method throws an
 * IllegalArgumentException if either c or comp is null. The Collection c
 * is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param low   the lower bound value of the range
 * @param high  the upper bound value of the range
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      a Collection of elements in the range [low..high]
 * @throws      IllegalArgumentException as specified above
 * @throws      NoSuchElementException as specified above
 *
 */
   public static <T> Collection<T> range(Collection<T> c, T low, T high,
                                         Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty()) throw new NoSuchElementException("Empty Collection");

       // Set up resultant ArrayList
       ArrayList<T> result = new ArrayList<T>();

       // Iteration loop for each element in c, end condition: reach end of Collection
       for (T element : c) {
           // Run comparison to determine if element fits the desired range
           if (comp.compare(low, element) <= 0 && comp.compare(high, element) >= 0) {
               result.add(element);
           }
       }

       if (result.size() == 0) throw new NoSuchElementException("Range not found");

       return result;
   }


/**
 * Returns the smallest value in the Collection c that is greater than
 * or equal to the given key, as defined by the supplied Comparator
 * comp. This method throws an IllegalArgumentException if either c
 * or comp is null, and throws a NoSuchElementException if c is empty
 * or if there is no qualifying value. Note that key does not have to
 * be an actual value in c.
 *
 * @param c     the Collection to be searched through
 * @param key   the reference value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      the next greater value in c
 * @throws      IllegalArgumentException as specified above
 * @throws      NoSuchElementException as specified above
 *
 */
   public static <T> T ceiling(Collection<T> c, T key, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty()) throw new NoSuchElementException("Empty Collection");

       // Set up Iterator and grab first element for comparisons
       Iterator<T> itr = c.iterator();
       T result = itr.next();

       // Iteration loop, end condition: when end of Collection is reached
       while (itr.hasNext()) {
           T nextElement = itr.next(); // Grab next element for comparisons
           if (comp.compare(nextElement, key) >= 0) { // Determine if next element is possible value
               // First see if result is NOT a possible value, then see if next element is smaller than result
               if (comp.compare(result, key) < 0 || comp.compare(nextElement, result) < 0) {
                   result = nextElement;
               }
           }
       }

       if (comp.compare(result, key) < 0) throw new NoSuchElementException("Value not found");

       return result;
   }


/**
 * Returns the largest value in the Collection c that is greater than
 * or equal to the given key, as defined by the supplied Comparator
 * comp. This method throws an IllegalArgumentException if either c
 * or comp is null, and throws a NoSuchElementException if c is empty
 * or if there is no qualifying value. Note that key does not have to
 * be an actual value in c.
 *
 * @param c     the Collection to be searched through
 * @param key   the reference value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      the next smaller value in c
 * @throws      IllegalArgumentException as specified above
 * @throws      NoSuchElementException as specified above
 *
 */
   public static <T> T floor(Collection<T> c, T key, Comparator<T> comp) {
       if (c == null || comp == null) throw new IllegalArgumentException("Null arguments");
       if (c.isEmpty()) throw new NoSuchElementException("Empty Collection");

       // Set up Iterator and grab first element for comparisons
       Iterator<T> itr = c.iterator();
       T result = itr.next();

       // Iteration loop, end condition: when end of Collection is reached
       while (itr.hasNext()) {
           T nextElement = itr.next(); // Grab next element for comparisons
           if (comp.compare(nextElement, key) <= 0) { // Determine if next element is possible value
               // First see if result is NOT a possible value, then see if next element is smaller than result
               if (comp.compare(result, key) > 0 || comp.compare(nextElement, result) > 0) {
                   result = nextElement;
               }
           }
       }

       if (comp.compare(result, key) > 0) throw new NoSuchElementException("Value not found");

       return result;
   }

}

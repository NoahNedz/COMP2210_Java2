/**
 * Lab3AGenerics.java
 * Used to illustrate basic principles of generic types
 * and type safety in Java.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-08-28
 *
 */
 public class Lab3AGenerics {

   // Make this method generic. Use a type variable named
   // T that will allow you to search through an array
   // of any type and return the location of target in a
   // or -1 if it doesn't exist.
   public static <T> int search(T[] a, T target) {
       int i = 0;
       while ((i < a.length) && !(a[i].equals(target))) {
          i++;
       }
       if (i < a.length)
          return i;
       else
          return -1;
    }

    // Make this method generic and type safe. Use a
    // type variable named T that will allow you to
    // find the minimum element of an array of any
    // type of mutually-comparable values.
    public static <T extends Comparable<T>> T min(T[] a) {
      T min = a[0];
      for (T val : a) {
        if (val.compareTo(min) < 0) {
          min = val;
        }
      }
      return min;
    }

    public static void main(String[] args) {
       // You'll need to change these statements once
       // you make the search method generic.
       Integer[] a1 = {4, 10, 2, 8, 6};
       int i = Lab3AGenerics.search(a1, 8);
       System.out.println(i);

       Integer[] a2 = {4, 10, 2, 8, 6};
       String[] a3 = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};

       // You'll need to change some of these
       // statements once you make the min
       // method generic.
       Integer min1 = Lab3AGenerics.<Integer>min(a2);
       System.out.println(min1);
       String min2 = Lab3AGenerics.<String>min(a3);
       System.out.println(min2);
    }

 }
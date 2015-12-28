import org.junit.*;

/**
 * LinearSearchTestWithJUnit.java
 * Tests LinearSearch.search using JUnit.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-08-18
 *
 */

 public final class LinearSearchTest {

   // test fixtures
   int[] a1  = {2};                     // length 1
   int[] a2u = {2, 4};                  // length 2 no duplicates
   int[] a2d = {2, 2};                  // length 2 with duplicates
   int[] atu = {2, 4, 6, 8, 10};        // typical length no duplicates
   int[] atd = {2, 4, 6, 8, 10, 2, 8};  // typical length with duplicates

   int target;
   int expected;
   int actual;
   StringBuilder feedback;

/********************************************************************/
   // test cases
   @Test
   public void testSearch_lengthN_unique_found_front() {
     target = 2;
     expected = 0;
     coreTest(atu, target, expected);
   }

   @Test
   public void testSearch_lengthN_unique_found_mid() {
     target = 6;
     expected = 2;
     coreTest(atu, target, expected);
   }

   @Test
   public void testSearch_lengthN_unique_found_rear() {
     target = 10;
     expected = 4;
     coreTest(atu, target, expected);
   }




/********************************************************************/


   // core test
   private void coreTest(int[] a, int target, int expected) {
     feedback = new StringBuilder();
     actual = LinearSearch.search(a, target);
     feedback.append("a = " + toString(a) + " target = " + target);
     feedback.append(" expected = " + expected + " actual = " + actual);
     Assert.assertEquals(feedback.toString(), expected, actual);
   }

   // return a string representation of an array
   private String toString(int[] a) {
     StringBuilder s = new StringBuilder();
     s.append("[");
     for (int i : a) {
       s.append(i + ",");
     }
     s.delete(s.length()-1, s.length());
     s.append("]");
     return s.toString();
   }

 }

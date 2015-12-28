import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Defines tests for the Selector class.
 * 
 * @author Mark Gallagher (mag0038@auburn.edu)
 * @version 8/22/2014
 * 
 */

public class SelectorTest {

   // Test fixtures
   int[] aNull = null;                  // null array
   int[] aZero = new int[0];            // Length of 0
   int[] a1 = {2};                      // Length of 1
   int[] a2u = {2, 4};                  // Unique and length of 2
   int[] a2d = {3, 3};                  // Duplicates and length of 2
   int[] atu = {10, 3, 4, 6, 12};       // Unique and typical length
   int[] atd = {2, 9, 2, 4, 9};         // Duplicates and typical length
   
   int expected;
   int actual;
   int[] expectedArray;
   int[] actualArray;
   
   
// Tests for min(int[] a) function.

   // Test for null array handling.
   @Test
   public void testmin_illegal_array_null() {
      try {
         Selector.min(aNull);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testmin_array_length_zero() {
      try {
         Selector.min(aZero);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in an array of length 1.
   @Test
   public void testmin_array_length_one() {
      expected = 2;
      actual = Selector.min(a1);
      Assert.assertEquals("Did not find minimum in array of length 1" , expected, actual);
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testmin_array_length_two_unique() {
      expected = 2;
      actual = Selector.min(a2u);
      Assert.assertEquals("Did not find minimum in unique array of length 2", expected, actual);
   }
      
   // Test for finding value in array with duplicates of length 2.
   @Test
   public void testmin_array_length_two_duplicate() {
      expected = 3;
      actual = Selector.min(a2d);
      Assert.assertEquals("Did not find minimum in array of duplicates of length 2", expected, actual);
   }
   
   // Test for finding value in typical size unique array.
   @Test
   public void testmin_array_typical_unique() {
      expected = 3;
      actual = Selector.min(atu);
      Assert.assertEquals("Did not find minimum in unique typical array", expected, actual);
   }
   
   // Test for finding value in typical size array with duplicates.
   @Test
   public void testmin_array_typical_duplicate() {
      expected = 2;
      actual = Selector.min(atd);
      Assert.assertEquals("Did not find minimum in typical array of duplicates", expected, actual);
   }
   
// Tests for max(int[] a) function.
   
   // Test for null array handling.
   @Test
   public void testmax_illegal_array_null() {
      try {
         Selector.max(aNull);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testmax_array_length_zero() {
      try {
         Selector.max(aZero);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in an array of length 1.
   @Test
   public void testmax_array_length_one() {
      expected = 2;
      actual = Selector.max(a1);
      Assert.assertEquals("Did not find maximum in array of length 1" , expected, actual);
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testmax_array_length_two_unique() {
      expected = 4;
      actual = Selector.max(a2u);
      Assert.assertEquals("Did not find maximum in unique array of length 2", expected, actual);
   }
      
   // Test for finding value in array with duplicates of length 2.
   @Test
   public void testmax_array_length_two_duplicate() {
      expected = 3;
      actual = Selector.max(a2d);
      Assert.assertEquals("Did not find maximum in array of duplicates of length 2", expected, actual);
   }
   
   // Test for finding value in typical size unique array.
   @Test
   public void testmax_array_typical_unique() {
      expected = 12;
      actual = Selector.max(atu);
      Assert.assertEquals("Did not find maximum in unique typical array", expected, actual);
   }
   
   // Test for finding value in typical size array with duplicates.
   @Test
   public void testmax_array_typical_duplicate() {
      expected = 9;
      actual = Selector.max(atd);
      Assert.assertEquals("Did not find maximum in typical array of duplicates", expected, actual);
   }
   
// Tests for kmin(int[] a, int k) function.

   // Test for null array handling.
   @Test
   public void testkmin_illegal_array_null() {
      try {
         Selector.kmin(aNull, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testkmin_array_length_zero() {
      try {
         Selector.kmin(aZero, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
        Assert.assertTrue(true);
      }
   }
   
   // Test for a value of k < 1 being passed.
   @Test
   public void testkmin_k_less_than_1() {
      try {
         Selector.kmin(atu, 0);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for a value of k > a.length being passed.
   @Test
   public void testkmin_k_greater_than_a_length() {
      try {
         Selector.kmin(atu, 6);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of length 1.
   @Test
   public void testkmin_array_length_one() {
      expected = 2; 
      actual = Selector.kmin(a1, 1);
      Assert.assertEquals("Did not find 1st minimum value in array of one.", expected, actual);
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testkmin_array_length_two_unique() {
      expected = 4;
      actual = Selector.kmin(a2u, 2);
      Assert.assertEquals("Did not find 2nd minimum value in unique array of two.", expected, actual);
   }
   
   // Test for finding value in array of duplicates of length 2.
   @Test
   public void testkmin_array_length_two_duplicates() {
      expected = 3;
      actual = Selector.kmin(a2d, 1);
      Assert.assertEquals("Did not find minimum value in array of two identical numbers.", expected, actual);
   }
   
   // Test for finding value in typical unique array. (k = 2)
   @Test
   public void testkmin_array_typical_unique_k2() {
      expected = 4;
      actual = Selector.kmin(atu, 2);
      Assert.assertEquals("Did not find 2nd minimum in typical unique array.", expected, actual);  
   }
   
   // Test for finding value in typical unique array. (k = 3)
   @Test
   public void testkmin_array_typical_unique_k3() {
      expected = 6;
      actual = Selector.kmin(atu, 3);
      Assert.assertEquals("Did not find 3rd minimum in typical unique array.", expected, actual);
   }
   
   // Test for finding value in typical array of duplicates. (k = 2)
   @Test 
   public void testkmin_array_typical_duplicate_k2() {
      expected = 4;
      actual = Selector.kmin(atd, 2);
      Assert.assertEquals("Did not find 2nd minimum in typical duplicate array.", expected, actual);
   }
   
   // Test for finding value in typical array of duplicates. (k = 3)
   @Test
   public void testkmin_array_typical_duplicate_k3() {
      expected = 9;
      actual = Selector.kmin(atd, 3);
      Assert.assertEquals("Did not find 3rd minimum in typical duplicate array.", expected, actual);
   }
   
   // Test for when k is larger than distinct values in array. (k = 4)
   @Test
   public void testkmin_array_typical_duplicate_k4() {
      try {
         Selector.kmin(atd, 4);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
// Tests for kmax(int[] a, int k) function.
   
   // Test for null array handling.
   @Test
   public void testkmax_illegal_array_null() {
      try {
         Selector.kmax(aNull, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testkmax_array_length_zero() {
      try {
         Selector.kmax(aZero, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
        Assert.assertTrue(true);
      }
   }
   
   // Test for a value of k < 1 being passed.
   @Test
   public void testkmax_k_less_than_1() {
      try {
         Selector.kmax(atu, 0);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for a value of k > a.length being passed.
   @Test
   public void testkmax_k_greater_than_a_length() {
      try {
         Selector.kmax(atu, 6);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of length 1.
   @Test
   public void testkmax_array_length_one() {
      expected = 2; 
      actual = Selector.kmax(a1, 1);
      Assert.assertEquals("Did not find 1st maximum value in array of one.", expected, actual);
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testkmax_array_length_two_unique() {
      expected = 2;
      actual = Selector.kmax(a2u, 2);
      Assert.assertEquals("Did not find 2nd maximum value in unique array of two.", expected, actual);
   }
   
   // Test for finding value in array of duplicates of length 2.
   @Test
   public void testkmax_array_length_two_duplicates() {
      expected = 3;
      actual = Selector.kmax(a2d, 1);
      Assert.assertEquals("Did not find maximum value in array of two identical numbers.", expected, actual);
   }
   
   // Test for finding value in typical unique array. (k = 2)
   @Test
   public void testkmax_array_typical_unique_k2() {
      expected = 10;
      actual = Selector.kmax(atu, 2);
      Assert.assertEquals("Did not find 2nd maximum in typical unique array.", expected, actual);  
   }
   
   // Test for finding value in typical unique array. (k = 3)
   @Test
   public void testkmax_array_typical_unique_k3() {
      expected = 6;
      actual = Selector.kmax(atu, 3);
      Assert.assertEquals("Did not find 3rd maximum in typical unique array.", expected, actual);
   }
   
   // Test for finding value in typical array of duplicates. (k = 2)
   @Test 
   public void testkmax_array_typical_duplicate_k2() {
      expected = 4;
      actual = Selector.kmax(atd, 2);
      Assert.assertEquals("Did not find 2nd maximum in typical duplicate array.", expected, actual);
   }
   
   // Test for finding value in typical array of duplicates. (k = 3)
   @Test
   public void testkmax_array_typical_duplicate_k3() {
      expected = 2;
      actual = Selector.kmax(atd, 3);
      Assert.assertEquals("Did not find 3rd maximum in typical duplicate array.", expected, actual);
   }
   
   // Test for when k is larger than distinct values in array. (k = 4)
   @Test
   public void testkmax_array_typical_duplicate_k4() {
      try {
         Selector.kmax(atd, 4);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
// Tests for range(int[] a, int low, int high) function.

   // Test for null array handling.
   @Test
   public void testrange_illegal_array_null() {
      try {
         Selector.range(aNull, 1, 3);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testrange_array_length_zero() {
      try {
         Selector.range(aZero, 1, 3);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
        Assert.assertTrue(true);
      }
   }
   
   // Test for finding range when array has 1 value.
   @Test
   public void testrange_array_length_one_found() {
      expectedArray = new int[1];
      expectedArray[0] = 2;
      actualArray = Selector.range(a1, 0, 5);
      Assert.assertArrayEquals("Proper range was not found.", expectedArray, actualArray);
   }
   
   
   // Test for not finding a specified range when array has 1 value.
   @Test
   public void testrange_array_length_one_not_found() {
      expectedArray = new int[0];
      actualArray = Selector.range(a1, 3, 4);
      Assert.assertArrayEquals("Non-zero array was returned when invalid range entered.", 
                                 expectedArray, actualArray);
   }
   
   // Test for finding range in unique array of two values.
   @Test
   public void testrange_array_length_two_unique_found() {
      expectedArray = new int[1];
      expectedArray[0] = 4;
      actualArray = Selector.range(a2u, 3, 5);
      Assert.assertArrayEquals("Proper range was not found.", expectedArray, actualArray);
   }
   
   // Test for not finding range in unique array of two values.
   @Test
   public void testrange_array_length_two_unique_not_found() {
      expectedArray = new int[0];
      actualArray = Selector.range(a2u, 5, 6);
      Assert.assertArrayEquals("Non-zero array was returned when invalid range entered.",
                                 expectedArray, actualArray);
   }
   
   // Test for finding range in array of duplicates with length two.
   @Test
   public void testrange_array_length_two_duplicates_found() {
      expectedArray = new int [2];
      expectedArray[0] = 3;
      expectedArray[1] = 3;
      actualArray = Selector.range(a2d, 0, 5);
      Assert.assertArrayEquals("Proper range was not found.", expectedArray, actualArray);
   }
   
   // Test for not finding range in array of duplicates with length two.
   @Test
   public void testrange_array_length_two_duplicates_not_found() {
      expectedArray = new int[0];
      actualArray = Selector.range(a2d, 0, 1);
      Assert.assertArrayEquals("Non-zero array was returned when invalid range entered.", 
                                 expectedArray, actualArray);
   }
   
   // Test for finding range in typical array of unique values.
   @Test
   public void testrange_array_typical_unique_found() {
      expectedArray = new int[2];
      expectedArray[0] = 3;
      expectedArray[1] = 4;
      actualArray = Selector.range(atu, 0, 5);
      Assert.assertArrayEquals("Proper range was not found.", expectedArray, actualArray);
   }
   
   // Test for not finding range in typical array of unique values.
   @Test
   public void testrange_array_typical_unique_not_found() {
      expectedArray = new int[0];
      actualArray = Selector.range(atu, 0, 1);
      Assert.assertArrayEquals("Non-zero array was returned when invalid range entered.", 
                                 expectedArray, actualArray);
   }
   
   // Test for finding range in typical array of duplicate values.
   @Test
   public void testrange_array_typical_duplicate_found() {
      expectedArray = new int[3];
      expectedArray[0] = 2;
      expectedArray[1] = 2;
      expectedArray[2] = 4;
      actualArray = Selector.range(atd, 0, 5);
      Assert.assertArrayEquals("Proper range was not found.", expectedArray, actualArray);
   }
   
   // Test for not finding range in typical array of duplicate values.
   @Test
   public void testrange_array_typical_duplicate_not_found() {
      expectedArray = new int[0];
      actualArray = Selector.range(atd, 0, 1);
      Assert.assertArrayEquals("Non-zero array was returned when invalid range entered.", 
                                 expectedArray, actualArray);
   }
   
// Tests for ceiling(int[] a, int key) function.

   // Test for null array handling.
   @Test
   public void testceiling_illegal_array_null() {
      try {
         Selector.ceiling(aNull, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testceiling_array_length_zero() {
      try {
         Selector.ceiling(aZero, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
        Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of length 1.
   @Test
   public void testceiling_array_length_one() {
      expected = 2;
      actual = Selector.ceiling(a1, 1);
      Assert.assertEquals("Value not found in array of length 1.", expected, actual);
   }
   
   // Test for not finding value in array of length 1.
   @Test
   public void testceiling_array_length_one_not_found() {
      try {
         Selector.ceiling(a1, 4);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testceiling_array_length_two_unique() {
      expected = 2;
      actual = Selector.ceiling(a2u, 1);
      Assert.assertEquals("Value not found in unique array of length 2.", expected, actual);
   }
   
   // Test for not finding value in unique array of length 2.
   @Test
   public void testceiling_array_length_two_unique_not_found() {
      try {
         Selector.ceiling(a2u, 5);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of duplicates length 2.
   @Test
   public void testceiling_array_length_two_duplicate() {
      expected = 3;
      actual = Selector.ceiling(a2d, 2);
      Assert.assertEquals("Value not found in array of duplicates of length 2.", expected, actual);
   }
   
   // Test for finding value in unique array of typical length.
   @Test
   public void testceiling_array_typical_unique() {
      expected = 10;
      actual = Selector.ceiling(atu, 10);
      Assert.assertEquals("Value not found in unique typical array.", expected, actual);
   }
   
   // Test for not finding value in unique array of typical length.
   @Test
   public void testceiling_array_typical_unique_not_found() {
      try {
         Selector.ceiling(atu, 14);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of duplicates of typical length.
   @Test public void testceiling_array_typical_duplicate() {
      expected = 9;
      actual = Selector.ceiling(atd, 8);
      Assert.assertEquals("Value not found in typical array of duplicates.", expected, actual);
   }
   
// Tests for floor(int[] a, int key) function.

   // Test for null array handling.
   @Test
   public void testfloor_illegal_array_null() {
      try {
         Selector.floor(aNull, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for an array of length zero being passed.
   @Test
   public void testfloor_array_length_zero() {
      try {
         Selector.floor(aZero, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
        Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of length 1.
   @Test
   public void testfloor_array_length_one() {
      expected = 2;
      actual = Selector.floor(a1, 3);
      Assert.assertEquals("Value not found in array of length 1.", expected, actual);
   }
   
   // Test for not finding value in array of length 1.
   @Test
   public void testfloor_array_length_one_not_found() {
      try {
         Selector.floor(a1, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in unique array of length 2.
   @Test
   public void testfloor_array_length_two_unique() {
      expected = 4;
      actual = Selector.floor(a2u, 5);
      Assert.assertEquals("Value not found in unique array of length 2.", expected, actual);
   }
   
   // Test for not finding value in unique array of length 2.
   @Test
   public void testfloor_array_length_two_unique_not_found() {
      try {
         Selector.floor(a2u, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of duplicates length 2.
   @Test
   public void testfloor_array_length_two_duplicate() {
      expected = 3;
      actual = Selector.floor(a2d, 4);
      Assert.assertEquals("Value not found in array of duplicates of length 2.", expected, actual);
   }
   
   // Test for finding value in unique array of typical length.
   @Test
   public void testfloor_array_typical_unique() {
      expected = 4;
      actual = Selector.floor(atu, 4);
      Assert.assertEquals("Value not found in unique typical array.", expected, actual);
   }
   
   // Test for not finding value in unique array of typical length.
   @Test
   public void testfloor_array_typical_unique_not_found() {
      try {
         Selector.floor(atu, 1);
         Assert.fail("Exception was not thrown when it needed to be.");
      }
      catch (IllegalArgumentException e) {
         Assert.assertTrue(true);
      }
   }
   
   // Test for finding value in array of duplicates of typical length.
   @Test public void testfloor_array_typical_duplicate() {
      expected = 4;
      actual = Selector.floor(atd, 5);
      Assert.assertEquals("Value not found in typical array of duplicates.", expected, actual);
   }
   
}

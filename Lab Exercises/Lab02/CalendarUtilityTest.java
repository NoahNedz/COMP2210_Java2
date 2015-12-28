import org.junit.*;

/**
 * CalendarUtility.java
 * Implements basic utility functions on dates, months, etc.
 *
 * @author   Mark Gallagher (mag0038@auburn.edu)
 * @version  2014-08-21
 *
 */
 
public class CalendarUtilityTest {
   
   int expected_value;
   
   @Test (expected=IllegalArgumentException.class)
   public void outOfRangeTest1()
   {

       expected_value = CalendarUtility.earliest(0, 1, 4);
   }
   
   @Test (expected=IllegalArgumentException.class)
   public void outOfRangeTest2()
   {

       expected_value = CalendarUtility.earliest(3, 13, 4);
   }
   
   @Test (expected=IllegalArgumentException.class)
   public void outOfRangeTest3()
   {

       expected_value = CalendarUtility.earliest(0, 13, 5);
   }
   
   @Test (expected=IllegalArgumentException.class)
   public void outOfRangeTest4()
   {
      expected_value = CalendarUtility.earliest(0, 13, 20);
   }
   
   @Test 
   public void earliestTest1()
   {
      coreTest(1, 3, 6, 1);
   }
   
   @Test 
   public void earliestTest2()
   {
      coreTest(5, 3, 7, 3);
   }
   
   @Test 
   public void earliestTest3()
   {
      coreTest(7, 4, 2, 2);
   }
   
   @Test 
   public void earliestTest4()
   {
      coreTest(12, 12, 12, 12);
   }
   
   @Test 
   public void earliestTest5()
   {
      coreTest(3, 11, 2, 2);
   }
   
   // core test
   private void coreTest(int m1, int m2, int m3, int expected)
   {
      int actual = CalendarUtility.earliest(m1, m2, m3);
      
      Assert.assertEquals(expected, actual);
   }
}

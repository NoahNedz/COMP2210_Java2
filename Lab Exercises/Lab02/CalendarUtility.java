/**
 * CalendarUtility.java
 * Implements basic utility functions on dates, months, etc.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-08-18
 *
 */

 public final class CalendarUtility {

   // No public constructor
   private CalendarUtility() { }

   /**
    * This method returns the month parameter that is earliest
    * in the calendar year.
    *
    * @param   m1 a month
    * @param   m2 a month
    * @param   m3 a month
    * @return  the earliest in the year of m1, m2, m3
    * @throws  IllegalArgumentException if m1, m2, or m3 is invalid
    */
    public static int earliest(int m1, int m2, int m3) {
      if (!valid(m1) || !valid(m2) || !valid(m3)) {
        throw new IllegalArgumentException("Month values must be in the range 1..12.");
      }
      if ((m1 < m2) && (m1 < m3)) {
        return m1;
      }
      if ((m2 < m1) && (m2 < m3)) {
        return m2;
      }
      return m3;
    }


    // validate a month
    private static boolean valid(int m) {

        return (m >= 1) && (m <=12);
    }


 }
 
/**
 * Student.java
 * A class to represent student data, for the
 * purpose of illustrating order by Comparable
 * and Comparator.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-02
 *
 */
 public class Student implements Comparable<Student>{
   private String fname;
   private String lname;
   private int    section;

   public Student(String fn, String ln, int sec) {
     fname   = fn;
     lname   = ln;
     section = sec;
   }

   public String getFirstName() {
     return fname;
   }

   public String getLastName() {
     return lname;
   }

   public int getSection() {
     return section;
   }

   /**
    * Implement compareTo so that students are ordered in the
    * following way: in ascending order of last name, then in
    * ascending order of first name, and then in ascending order
    * of section.
    */
   @Override
   public int compareTo(Student s) {
     int cmp = this.getLastName().compareTo(s.getLastName());
	 
	 if (cmp == 0) {
		cmp = this.getFirstName().compareTo(s.getFirstName());
		if (cmp == 0) {
			cmp = this.getSection() - s.getSection();
		}
	 }
	 
     return cmp;
   }

   @Override
   public String toString() {
     return section + ", " + lname + ", " + fname;
   }
 }
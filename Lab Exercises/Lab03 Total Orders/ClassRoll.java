import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;

/**
 * ClassRoll.java.
 * A class to illustrate storing and sorting
 * a list of Students.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-02
 *
 */
 public class ClassRoll {

   public static void main(String[] args) {
     List<Student> roll = new LinkedList<Student>();
     buildRoll(roll);
     printRoll(roll, "Original");

     // Use the Collections.shuffle() method to
     // arrange the Students in the roll in some
     // random order.
	 Collections.<Student>shuffle(roll);	 
     printRoll(roll, "Shuffled");

     Collections.<Student>sort(roll);
     printRoll(roll, "Sorted in natural order");

     Comparator<Student> orderBySection = new CompareStudentsBySection();
     Collections.<Student>sort(roll, orderBySection);
     printRoll(roll, "Sorted by section");

     // Use the Collections.reverseOrder() method to
     // set the following Comparator to the reverse of
     // CompareStudentsBySection; that is, a comparator
     // for descending order of section.
     Comparator<Student> reverseOrderBySection = Collections.reverseOrder(new CompareStudentsBySection());
	 Collections.<Student>sort(roll, reverseOrderBySection);
     printRoll(roll, "Sorted in reverse order of section");
   }

   private static void buildRoll(List<Student> roll) {
     roll.add(new Student("Alan", "Turing", 1));
     roll.add(new Student("John", "von Neumann", 1));
     roll.add(new Student("Alonzo", "Church", 1));
     roll.add(new Student("Don", "Knuth", 2));
     roll.add(new Student("John", "McCarthy", 2));
     roll.add(new Student("Tony", "Hoare", 2));
     roll.add(new Student("Edsger", "Dijkstra", 2));
   }

   private static void printRoll(List<Student> roll, String header) {
     StringBuilder output = new StringBuilder(header + "\n");
     for (Student s : roll) {
       output.append(s + "\n");
     }
     System.out.println(output.toString());
   }

   /**
    * Implement this Comparator so that Student objects are compared
    * in ascending order of section. That is, compare(s1, s2) must
    * return a negative integer if s1's section is less than s2's section,
    * zero if s1 and s2 have the same section, and a positive integer
    * if s1's section is greater than s2's section.
    */
   private static class CompareStudentsBySection implements Comparator<Student> {
     public int compare(Student s1, Student s2) {
       return s1.getSection() - s2.getSection();
     }
   }

 }
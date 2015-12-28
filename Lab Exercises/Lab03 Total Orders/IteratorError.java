import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * IteratorError.java
 * Illustrates a common error when using Iterators.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-02
 *
 */
 public class IteratorError {

   /**
    * Identify and eliminate the error regarding the use
    * of the iterator itr in the following method.
    */
   public static <T> int search(List<T> list, T target) {
     int i = 0;
     Iterator<T> itr = list.iterator();
     while ((itr.hasNext()) && (!itr.next().equals(target))) {
       i++;
       //itr.next();
     }
     if (itr.hasNext()) {
       return i;
     }
     else {
       return -1;
     }
   }

   public static void main(String[] args) {
     List<Integer> ilist = new ArrayList<Integer>();
     ilist.add(2); ilist.add(4); ilist.add(6);
     ilist.add(8); ilist.add(10);

     int loc = IteratorError.<Integer>search(ilist, 8);
     System.out.println(loc);
     loc = IteratorError.<Integer>search(ilist, 5);
     System.out.println(loc);
   }

 }
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Lab3BGenerics.java
 * Used to illustrate basic principles of generic types
 * and type safety in Java.
 *
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-08-28
 *
 */

 ////////////////////////////////////////////////
 //
 // Add appropriate type parameters and arguments
 // to eliminate all unchecked warnings. That is,
 // make this code type safe.
 //
 ////////////////////////////////////////////////

 public class Lab3BGenerics<T> {

   private List<T> al;

   public Lab3BGenerics() {
     al = new ArrayList<T>();
   }

   public void addAll(Collection<T> c) {
     for (T o : c) {
       al.add(o);
     }
   }

   public String toString() {
     StringBuilder s = new StringBuilder();
     Iterator itr = al.iterator();
     while (itr.hasNext()) {
       s.append(itr.next());
       s.append(" ");
     }
     return s.toString();
   }

   public static void main(String[] args) {
     Collection<Integer> c = new ArrayList<Integer>();
     for (int i = 1; i < 12; i +=2) {
       c.add(i);
     }

     Lab3BGenerics<Integer> lab = new Lab3BGenerics<Integer>();
     lab.addAll(c);
     System.out.println(lab.toString());
   }


 }
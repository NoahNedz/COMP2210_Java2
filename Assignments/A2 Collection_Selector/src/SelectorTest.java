// Copyright (c) 2014 Mark A. Gallagher Jr (mag0038@auburn.edu).

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Comparator;

/**
 * Test cases for Selector class.
 *
 * @author Mark Gallagher Jr
 * @version 2014-08-29
 */

public class SelectorTest {

    // Test fixtures
    List<Integer> iNull = null;
    List<Integer> i1;
    List<Integer> i2;
    List<Integer> i3;
    List<String> s1;

// Tests for IllegalArgumentException throwing

    @Test(expected = IllegalArgumentException.class)
    public void test_min_IllegalArgumentException() {
        Selector.min(iNull, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_max_IllegalArgumentException() {
        Selector.max(iNull, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_kmin_IllegalArgumentException() {
        Selector.kmin(iNull, 1, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_kmax_IllegalArgumentException() {
        Selector.kmax(iNull, 1, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_range_IllegalArgumentException() {
        Selector.range(iNull, 1, 10, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ceiling_IllegalArgumentException() {
        Selector.ceiling(iNull, 1, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_floor_IllegalArgumentException() {
        Selector.floor(iNull, 1, new CompareIntegerAscending());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_min_IllegalArgumentException2() {
        populate_i1();
        Selector.min(i1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_max_IllegalArgumentException2() {
        populate_i1();
        Selector.max(i1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_kmin_IllegalArgumentException2() {
        populate_i1();
        Selector.kmin(i1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_kmax_IllegalArgumentException2() {
        populate_i1();
        Selector.kmax(i1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_range_IllegalArgumentException2() {
        populate_i1();
        Selector.range(i1, 1, 10, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ceiling_IllegalArgumentException2() {
        populate_i1();
        Selector.ceiling(i1, 1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_floor_IllegalArgumentException2() {
        populate_i1();
        Selector.floor(i1, 1, null);
    }

// Tests for min method

    // Test to find value in Collection of Integer
    @Test
    public void test_min_Integer_Collection1() {
        populate_i1();
        int actual = Selector.min(i1, new CompareIntegerAscending());
        int expected = 2;
        Assert.assertEquals("Minimum not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_min_Integer_Collection2() {
        populate_i2();
        int actual = Selector.min(i2, new CompareIntegerDescending());
        int expected = 9;
        Assert.assertEquals("Minimum not found in descending comparator", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_min_Integer_Collection3() {
        populate_i3();
        int actual = Selector.min(i3, new CompareIntegerAscending());
        int expected = 4;
        Assert.assertEquals("Minimum not found", expected, actual);
    }

    // Test to find value in Collection of String
    @Test
    public void test_min_String_Collection1() {
        populate_s1();
        String actual = Selector.min(s1, new CompareStringAscending());
        String expected = "A";
        Assert.assertEquals("Minimum not found", expected, actual);
    }

// Tests for max method

    // Test to find value in Collection of Integer
    @Test
    public void test_max_Integer_Collection1() {
        populate_i1();
        int actual = Selector.max(i1, new CompareIntegerAscending());
        int expected = 8;
        Assert.assertEquals("Maximum not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_max_Integer_Collection2() {
        populate_i2();
        int actual = Selector.max(i2, new CompareIntegerDescending());
        int expected = 1;
        Assert.assertEquals("Maximum not found in descending comparator", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_max_Integer_Collection3() {
        populate_i3();
        int actual = Selector.max(i3, new CompareIntegerAscending());
        int expected = 8;
        Assert.assertEquals("Maximum not found", expected, actual);
    }

    // Test to find value in Collection of String
    @Test
    public void test_max_String_Collection1() {
        populate_s1();
        String actual = Selector.max(s1, new CompareStringAscending());
        String expected = "E";
        Assert.assertEquals("Maximum not found", expected, actual);
    }

// Tests for kmin method

    // Test to find value in Collection of Integer
    @Test
    public void test_kmin_Integer_Collection1() {
        populate_i1();
        int actual = Selector.kmin(i1, 1, new CompareIntegerAscending());
        int expected = 2;
        Assert.assertEquals("1st minimum not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_kmin_Integer_Collection2() {
        populate_i2();
        int actual = Selector.kmin(i2, 2, new CompareIntegerDescending());
        int expected = 7;
        Assert.assertEquals("2nd minimum in descending comparator not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_kmin_Integer_Collection3() {
        populate_i3();
        int actual = Selector.kmin(i3, 3, new CompareIntegerAscending());
        int expected = 6;
        Assert.assertEquals("3rd minimum not found", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_kmin_Integer_Collection4() {
        List<Integer> i4 = new ArrayList<Integer>();
        i4.add(2);
        i4.add(2);
        int actual = Selector.kmin(i4, 1, new CompareIntegerAscending());
        int expected = 2;
        Assert.assertEquals("Failed in duplicate, size 2 list", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_kmin_Integer_Collection5() {
        List<Integer> i5 = new ArrayList<Integer>();
        i5.add(3);
        i5.add(7);
        i5.add(3);
        i5.add(3);
        i5.add(1);
        i5.add(9);
        i5.add(1);
        i5.add(1);
        i5.add(1);
        i5.add(5);
        int actual = Selector.kmin(i5, 2, new CompareIntegerAscending());
        int expected = 3;
        Assert.assertEquals("Failed in duplicate, size 10 list", expected, actual);
    }

    // Test to find value in Collection of String
    @Test
    public void test_kmin_String_Collection1() {
        populate_s1();
        String actual = Selector.kmin(s1, 4, new CompareStringAscending());
        String expected = "D";
        Assert.assertEquals("4th minimum not found (strings)", expected, actual);
    }

// Tests for kmax method

    // Test to find value in Collection of Integer
    @Test
    public void test_kmax_Integer_Collection1() {
        populate_i1();
        int actual = Selector.kmax(i1, 1, new CompareIntegerAscending());
        int expected = 8;
        Assert.assertEquals("1st maximum not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_kmax_Integer_Collection2() {
        populate_i2();
        int actual = Selector.kmax(i2, 2, new CompareIntegerDescending());
        int expected = 3;
        Assert.assertEquals("2nd maximum in descending comparator not found", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_kmax_Integer_Collection3() {
        populate_i3();
        int actual = Selector.kmax(i3, 3, new CompareIntegerAscending());
        int expected = 6;
        Assert.assertEquals("3rd maximum not found", expected, actual);
    }

    @Test
    public void test_kmax_Integer_Collection4() {
        List<Integer> i4 = new ArrayList<Integer>();
        i4.add(2);
        i4.add(2);
        int actual = Selector.kmax(i4, 1, new CompareIntegerAscending());
        int expected = 2;
        Assert.assertEquals("Failed in duplicate, size 2 list", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_kmax_Integer_Collection5() {
        List<Integer> i5 = new ArrayList<Integer>();
        i5.add(3);
        i5.add(7);
        i5.add(3);
        i5.add(3);
        i5.add(1);
        i5.add(9);
        i5.add(1);
        i5.add(1);
        i5.add(1);
        i5.add(5);
        int actual = Selector.kmax(i5, 5, new CompareIntegerAscending());
        int expected = 1;
        Assert.assertEquals("Failed in duplicate, size 10 list", expected, actual);
    }

    // Test to find value in Collection of String
    @Test
    public void test_kmax_String_Collection1() {
        populate_s1();
        String actual = Selector.kmax(s1, 4, new CompareStringAscending());
        String expected = "B";
        Assert.assertEquals("4th maximum not found (strings)", expected, actual);
    }

// Tests for range method

    // Test to find value in Collection of Integer
    @Test
    public void test_range_Integer_Collection1() {
        populate_i1();
        Collection<Integer> actual = Selector.range(i1, 1, 5, new CompareIntegerAscending());
        Collection<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        Assert.assertEquals("Did not find range correctly", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_range_Integer_Collection2() {
        populate_i2();
        Collection<Integer> actual = Selector.range(i2, 3, 5, new CompareIntegerAscending());
        Collection<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        expected.add(3);
        Assert.assertEquals("Did not find range correctly", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_range_Integer_Collection3() {
        populate_i2();
        Collection<Integer> actual = Selector.range(i2, 5, 3, new CompareIntegerDescending());
        Collection<Integer> expected = new ArrayList<Integer>();
        expected.add(5);
        expected.add(3);
        Assert.assertEquals("Did not find range correctly", expected, actual);
    }

    // Test to find value in Collection of Integer
    @Test
    public void test_range_Integer_Collection4() {
        populate_i3();
        Collection<Integer> actual = Selector.range(i3, 4, 8, new CompareIntegerAscending());
        Collection<Integer> expected = new ArrayList<Integer>();
        expected.add(8);
        expected.add(7);
        expected.add(6);
        expected.add(5);
        expected.add(4);
        Assert.assertEquals("Did not find range correctly", expected, actual);
    }

    // Test to find value in Collection of String
    @Test
    public void test_range_String_Collection1() {
        populate_s1();
        Collection<String> actual = Selector.range(s1, "B", "C", new CompareStringAscending());
        Collection<String> expected = new ArrayList<String>();
        expected.add("B");
        expected.add("C");
        Assert.assertEquals("Did not find range correctly", expected, actual);
    }

// Tests for ceiling method

    // Test for finding value in Collection of Integer
    @Test
    public void test_ceiling_Integer_Collection1() {
        populate_i1();
        int actual = Selector.ceiling(i1, 1, new CompareIntegerAscending());
        int expected = 2;
        Assert.assertEquals("Did not find ceiling value", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_ceiling_Integer_Collection2() {
        populate_i2();
        int actual = Selector.ceiling(i2, 7, new CompareIntegerDescending());
        int expected = 7;
        Assert.assertEquals("Did not find ceiling value", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_ceiling_Integer_Collection3() {
        populate_i3();
        int actual = Selector.ceiling(i3, 0, new CompareIntegerAscending());
        int expected = 4;
        Assert.assertEquals("Did not find ceiling value", expected, actual);
    }

    // Test for finding value in Collection of String
    @Test
    public void test_ceiling_String_Collection1() {
        populate_s1();
        String actual = Selector.ceiling(s1, "B", new CompareStringAscending());
        String expected = "B";
        Assert.assertEquals("Did not find ceiling value", expected, actual);
    }

// Tests for floor method

    // Test for finding value in Collection of Integer
    @Test
    public void test_floor_Integer_Collection1() {
        populate_i1();
        int actual = Selector.floor(i1, 6, new CompareIntegerAscending());
        int expected = 4;
        Assert.assertEquals("Did not find floor value", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_floor_Integer_Collection2() {
        populate_i2();
        int actual = Selector.floor(i2, 1, new CompareIntegerDescending());
        int expected = 1;
        Assert.assertEquals("Did not find floor value", expected, actual);
    }

    // Test for finding value in Collection of Integer
    @Test
    public void test_floor_Integer_Collection3() {
        populate_i3();
        int actual = Selector.floor(i3, 9, new CompareIntegerAscending());
        int expected = 8;
        Assert.assertEquals("Did not find floor value", expected, actual);
    }

    // Test for finding value in Collection of String
    @Test
    public void test_floor_String_Collection1() {
        populate_s1();
        String actual = Selector.floor(s1, "F", new CompareStringAscending());
        String expected = "E";
        Assert.assertEquals("Did not find floor value", expected, actual);
    }

/******************************************************/

    // Repopulates test fixture
    private void populate_i1() {
        i1 = new ArrayList<Integer>();
        i1.add(2);
        i1.add(8);
        i1.add(7);
        i1.add(3);
        i1.add(4);
    }

    // Repopulates test fixture
    private void populate_i2() {
        i2 = new ArrayList<Integer>();
        i2.add(5);
        i2.add(9);
        i2.add(1);
        i2.add(7);
        i2.add(3);
    }

    // Repopulates test fixture
    private void populate_i3() {
        i3 = new ArrayList<Integer>();
        i3.add(8);
        i3.add(7);
        i3.add(6);
        i3.add(5);
        i3.add(4);
    }

    // Repopulates test fixture
    private void populate_s1() {
        s1 = new ArrayList<String>();
        s1.add("A");
        s1.add("B");
        s1.add("C");
        s1.add("D");
        s1.add("E");
    }

    // Comparator for ascending order of Integers
    private static class CompareIntegerAscending implements Comparator<Integer> {

        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    // Comparator for descending order of Integers
    private static class CompareIntegerDescending implements Comparator<Integer> {

        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    }

    // Comparator for ascending order of Strings
    private static class CompareStringAscending implements Comparator<String> {

        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }
}
package algorithms;

import java.util.Arrays;
import java.util.Random;

public class HeapSortTest {

    public static void main(String[] args) {
        System.out.println("=== Running Heap Sort Tests ===");

        boolean allTestsPassed = true;

        allTestsPassed &= testEmptyArray();
        allTestsPassed &= testSingleElement();
        allTestsPassed &= testSortedArray();
        allTestsPassed &= testReverseSortedArray();
        allTestsPassed &= testDuplicateElements();
        allTestsPassed &= testRandomArray();
        allTestsPassed &= testAllSameElements();
        allTestsPassed &= testNullArray();

        System.out.println("\n=== " + (allTestsPassed ? "ALL TESTS PASSED! ✅" : "SOME TESTS FAILED! ❌") + " ===");
    }

    static boolean testEmptyArray() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {};
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("Empty array test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Empty array test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testSingleElement() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {5};
            sorter.sort(arr);
            boolean passed = Arrays.equals(arr, new int[]{5});
            System.out.println("Single element test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Single element test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testSortedArray() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {1, 2, 3, 4, 5};
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("Sorted array test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Sorted array test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testReverseSortedArray() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {5, 4, 3, 2, 1};
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("Reverse sorted test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Reverse sorted test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testDuplicateElements() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5};
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("Duplicate elements test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Duplicate elements test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testRandomArray() {
        try {
            HeapSort sorter = new HeapSort();
            Random rand = new Random(42);
            int[] arr = new int[1000];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rand.nextInt(10000);
            }
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("Random array test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("Random array test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testAllSameElements() {
        try {
            HeapSort sorter = new HeapSort();
            int[] arr = {5, 5, 5, 5, 5};
            sorter.sort(arr);
            boolean passed = sorter.isSorted(arr);
            System.out.println("All same elements test: " + (passed ? "PASS ✅" : "FAIL ❌"));
            return passed;
        } catch (Exception e) {
            System.out.println("All same elements test: FAIL ❌ - " + e.getMessage());
            return false;
        }
    }

    static boolean testNullArray() {
        try {
            HeapSort sorter = new HeapSort();
            sorter.sort(null);
            System.out.println("Null array test: FAIL ❌ - Should have thrown exception");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Null array test: PASS ✅ - Correctly threw exception");
            return true;
        } catch (Exception e) {
            System.out.println("Null array test: FAIL ❌ - Wrong exception type: " + e.getMessage());
            return false;
        }
    }
}
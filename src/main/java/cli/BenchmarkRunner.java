package cli;

import algorithms.HeapSort;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CLI Benchmark Runner for Heap Sort performance testing
 * Supports multiple input sizes and distributions with CSV export
 */
public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        switch (args[0]) {
            case "benchmark":
                runFullBenchmark();
                break;
            case "correctness":
                testCorrectness();
                break;
            case "demo":
                runDemo();
                break;
            default:
                printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("Heap Sort Benchmark Runner");
        System.out.println("Usage:");
        System.out.println("  benchmark  - Run full benchmark suite with CSV export");
        System.out.println("  correctness - Test algorithm correctness");
        System.out.println("  demo       - Run demonstration on small arrays");
    }

    /**
     * Full benchmark with multiple sizes and distributions
     */
    private static void runFullBenchmark() {
        System.out.println("=== Heap Sort Full Benchmark ===");

        int[] sizes = {100, 1000, 10000, 100000};
        String[] distributions = {"random", "sorted", "reverse_sorted", "nearly_sorted"};

        try (FileWriter csvWriter = new FileWriter("benchmark_results.csv")) {
            // CSV header
            csvWriter.write("Size,Distribution,TimeMs,Comparisons,Swaps,ArrayAccesses,MemoryKB\n");

            System.out.println("\nSize\tDistribution\tTime(ms)\tComparisons\tSwaps");
            System.out.println("----\t------------\t--------\t-----------\t-----");

            for (int size : sizes) {
                for (String distribution : distributions) {
                    BenchmarkResult result = runSingleBenchmark(size, distribution);

                    // Console output
                    System.out.printf("%d\t%-12s\t%.3f\t\t%d\t\t%d\n",
                            size, distribution, result.timeMs, result.comparisons, result.swaps);

                    // CSV output
                    csvWriter.write(String.format("%d,%s,%.3f,%d,%d,%d,%d\n",
                            size, distribution, result.timeMs, result.comparisons,
                            result.swaps, result.arrayAccesses, result.memoryKB));
                }
                System.out.println();
            }

            System.out.println("✅ Benchmark completed! Results saved to benchmark_results.csv");

        } catch (IOException e) {
            System.err.println("❌ Error writing CSV file: " + e.getMessage());
        }
    }

    /**
     * Single benchmark run
     */
    private static BenchmarkResult runSingleBenchmark(int size, String distribution) {
        int[] arr = generateArray(size, distribution);
        HeapSort sorter = new HeapSort();

        sorter.sort(arr);

        return new BenchmarkResult(
                sorter.getMetrics().getTimeNano() / 1_000_000.0,
                sorter.getMetrics().getComparisons(),
                sorter.getMetrics().getSwaps(),
                sorter.getMetrics().getArrayAccesses(),
                sorter.getMetrics().getMemoryUsed() / 1024
        );
    }

    /**
     * Algorithm correctness testing
     */
    private static void testCorrectness() {
        System.out.println("=== Heap Sort Correctness Tests ===");

        int[][] testCases = {
                {},
                {1},
                {1, 2},
                {2, 1},
                {5, 5, 5},
                {3, 1, 4, 1, 5, 9, 2, 6},
                {9, 8, 7, 6, 5, 4, 3, 2, 1}
        };

        String[] descriptions = {
                "Empty array",
                "Single element",
                "Sorted array",
                "Reverse sorted",
                "All duplicates",
                "Random with duplicates",
                "Large reverse sorted"
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i].clone();
            HeapSort sorter = new HeapSort();

            System.out.printf("\nTest %d: %s\n", i + 1, descriptions[i]);
            System.out.println("Input:  " + Arrays.toString(testCases[i]));

            sorter.sort(arr);
            boolean isCorrect = sorter.isSorted(arr);

            System.out.println("Output: " + Arrays.toString(arr));
            System.out.println("Result: " + (isCorrect ? "✅ PASS" : "❌ FAIL"));

            if (!isCorrect) allTestsPassed = false;
        }

        System.out.println("\n=== " + (allTestsPassed ? "✅ ALL TESTS PASSED" : "❌ SOME TESTS FAILED") + " ===");
    }

    /**
     * Demonstration on small arrays
     */
    private static void runDemo() {
        System.out.println("=== Heap Sort Demonstration ===");

        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        System.out.println("Original array: " + Arrays.toString(arr));

        HeapSort sorter = new HeapSort();
        sorter.sort(arr);

        System.out.println("Sorted array:   " + Arrays.toString(arr));
        System.out.println("Correctly sorted: " + sorter.isSorted(arr));

        System.out.println("\nPerformance Metrics:");
        sorter.getMetrics().printMetrics();
    }

    /**
     * Array generators
     */
    private static int[] generateArray(int size, String type) {
        Random rand = new Random(42); // Fixed seed for reproducibility

        switch (type) {
            case "random":
                int[] randomArr = new int[size];
                for (int i = 0; i < size; i++) {
                    randomArr[i] = rand.nextInt(size * 10);
                }
                return randomArr;

            case "sorted":
                int[] sortedArr = new int[size];
                for (int i = 0; i < size; i++) {
                    sortedArr[i] = i;
                }
                return sortedArr;

            case "reverse_sorted":
                int[] reverseArr = new int[size];
                for (int i = 0; i < size; i++) {
                    reverseArr[i] = size - i;
                }
                return reverseArr;

            case "nearly_sorted":
                int[] nearlyArr = new int[size];
                for (int i = 0; i < size; i++) {
                    nearlyArr[i] = i;
                }
                // Randomly swap 10% of elements
                for (int i = 0; i < size / 10; i++) {
                    int idx1 = rand.nextInt(size);
                    int idx2 = rand.nextInt(size);
                    int temp = nearlyArr[idx1];
                    nearlyArr[idx1] = nearlyArr[idx2];
                    nearlyArr[idx2] = temp;
                }
                return nearlyArr;

            default:
                throw new IllegalArgumentException("Unknown distribution: " + type);
        }
    }

    /**
     * Data class for benchmark results
     */
    private static class BenchmarkResult {
        final double timeMs;
        final long comparisons;
        final long swaps;
        final long arrayAccesses;
        final long memoryKB;

        BenchmarkResult(double timeMs, long comparisons, long swaps, long arrayAccesses, long memoryKB) {
            this.timeMs = timeMs;
            this.comparisons = comparisons;
            this.swaps = swaps;
            this.arrayAccesses = arrayAccesses;
            this.memoryKB = memoryKB;
        }
    }
}
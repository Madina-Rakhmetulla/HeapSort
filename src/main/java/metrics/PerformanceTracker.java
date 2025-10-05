package metrics;

/**
 * Tracks performance metrics for sorting algorithms
 */
public class PerformanceTracker {
    private String algorithmName;
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long startTime;
    private long endTime;
    private long memoryBefore;
    private long memoryAfter;

    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        reset();
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
        memoryBefore = 0;
        memoryAfter = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
        memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
        memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses(int count) {
        arrayAccesses += count;
    }

    // Getters
    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getTimeNano() { return endTime - startTime; }
    public long getMemoryUsed() { return memoryAfter - memoryBefore; }

    public void printMetrics() {
        System.out.println("=== " + algorithmName + " Metrics ===");
        System.out.println("Time: " + getTimeNano() / 1_000_000.0 + " ms");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Array Accesses: " + arrayAccesses);
        System.out.println("Memory Used: " + getMemoryUsed() / 1024 + " KB");
    }
}
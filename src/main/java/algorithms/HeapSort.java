package algorithms;

import metrics.PerformanceTracker;

public class HeapSort {
    private PerformanceTracker metrics;

    public HeapSort() {
        this.metrics = new PerformanceTracker("HeapSort");
    }

    public PerformanceTracker getMetrics() {
        return metrics;
    }

    public void sort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        metrics.reset();
        metrics.startTimer();

        int n = arr.length;
        buildMaxHeap(arr, n);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            metrics.incrementSwaps();
            heapify(arr, i, 0);
        }

        metrics.stopTimer();
    }

    private void buildMaxHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        metrics.incrementComparisons();
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        metrics.incrementComparisons();
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            metrics.incrementSwaps();
            heapify(arr, n, largest);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incrementArrayAccesses(4);
    }

    public boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
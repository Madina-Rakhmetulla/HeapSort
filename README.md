Heap Sort – Algorithmic Analysis & Benchmark

Project Overview

This project is part of Assignment 2: Algorithmic Analysis and Peer Code Review.
It implements, tests, benchmarks, and analyzes the Heap Sort algorithm according to the assignment requirements.
The implementation focuses on algorithmic correctness, asymptotic complexity, empirical performance measurement, and comprehensive peer analysis.


Algorithm Description

Heap Sort is a comparison-based sorting algorithm based on a binary heap data structure.
It works in two main stages:
	1.	Heap Construction – builds a max-heap from the input array.
	2.	Extraction Phase – repeatedly removes the largest element from the heap and places it at the end of the array.

Time complexity:
	•	Best: Θ(n log n)
	•	Average: Θ(n log n)
	•	Worst: Θ(n log n)

Space complexity: O(1), since the algorithm sorts the array in place.


Project Structure
assignment2-heap-sort/
├── src/
│   ├── main/java/
│   │   ├── algorithms/
│   │   │   ├── HeapSort.java
│   │   │   └── HeapSortTest.java
│   │   ├── metrics/
│   │   │   └── PerformanceTracker.java
│   │   └── cli/
│   │       └── BenchmarkRunner.java
│   └── test/java/
│       └── algorithms/
│           └── HeapSortTest.java
├── docs/
│   ├── analysis-report.pdf
│   └── performance-plots/
├── benchmark_results.csv
├── README.md
└── pom.xml
How to Run the Project

1. Compile the project
   mvn -q -DskipTests=true package
2. Run the CLI (3 modes)
Demonstration mode (small example):
  java -cp target/assignment2-heap-sort-1.0-SNAPSHOT.jar cli.BenchmarkRunner demo
Correctness tests (edge cases):
 java -cp target/assignment2-heap-sort-1.0-SNAPSHOT.jar cli.BenchmarkRunner correctness
Full benchmark (CSV export):
 java -cp target/assignment2-heap-sort-1.0-SNAPSHOT.jar cli.BenchmarkRunner benchmark

Features Implemented
	•	In-place Heap Sort implementation with max-heap construction.
	•	Metrics tracking for comparisons, swaps, array accesses, execution time, and memory usage.
	•	CLI benchmark runner with support for different input sizes and array distributions.
	•	CSV export of benchmark results for further analysis and visualization.
	•	Comprehensive unit tests covering all important edge cases.
	•	Performance analysis including theoretical and empirical comparison.


Benchmark Distributions

The algorithm was tested on several input types:
	•	Random: completely random integers.
	•	Sorted: already sorted input.
	•	Reverse Sorted: decreasing order input.
	•	Nearly Sorted: mostly sorted with a small percentage of random swaps.

Performance Measurement

During benchmarking, the program collects the following metrics: execution time, number of comparisons, number of swaps, number of array accesses, and memory usage.
All results are saved into the benchmark_results.csv file, which can be used to generate performance graphs and analyze how the algorithm scales with input size.

Report and Analysis

The complete theoretical and empirical analysis of Heap Sort is provided in the following files:
	•	docs/analysis-report.pdf – contains a full written report with theoretical derivations and complexity analysis.
	•	docs/performance-plots/ – contains graphs and performance measurements showing how runtime and operations grow with input size.

Conclusion

This project demonstrates the full process of algorithm engineering: from clean implementation and correctness testing to performance benchmarking and complexity validation.
It satisfies all the requirements of Assignment 2 and provides a complete framework for analyzing the Heap Sort algorithm from both theoretical and practical perspectives.

Author: Rakhmetulla Madina
Course: Design and Analysis of Algorithms
Institution: Astana IT University

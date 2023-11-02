//Mini-Project

import java.util.Arrays;
import java.lang.Thread;

class Main {
    // Function to merge two sorted subarrays
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = Arrays.copyOfRange(arr, left, left + n1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, mid + 1 + n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Single-threaded Merge Sort
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Multithreaded Merge Sort
    private static void threadedMergeSort(int[] arr, int left, int right, int depth) {
        if (depth == 0) {
            mergeSort(arr, left, right);
            return;
        }

        if (left < right) {
            int mid = left + (right - left) / 2;

            Thread leftThread = new Thread(() -> threadedMergeSort(arr, left, mid, depth - 1));
            Thread rightThread = new Thread(() -> threadedMergeSort(arr, mid + 1, right, depth - 1));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(arr, left, mid, right);
        }
    }

    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        // Measure time for single-threaded Merge Sort
       // long startTimeSingleThread = System.currentTimeMillis();
        long startTimeSingleThread =System.nanoTime();
        mergeSort(arr, 0, n - 1);
      //  long endTimeSingleThread = System.currentTimeMillis();
        long endtTimeSingleThread =System.nanoTime();

        System.out.print("Sorted array using single-threaded merge sort: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Time taken for single-threaded Merge Sort: " + (endtTimeSingleThread - startTimeSingleThread) + " nanoseconds");

        // Reset the array for multithreaded merge sort
        arr = new int[]{12, 11, 13, 5, 6, 7};

        // Measure time for multithreaded Merge Sort (2 threads)
      //  long startTimeMultiThread = System.currentTimeMillis();
        long startTimeMultiThread =System.nanoTime();
        threadedMergeSort(arr, 0, n - 1, 2);
      //  long endTimeMultiThread = System.currentTimeMillis();
        long endTimeMultiThread =System.nanoTime();

        System.out.print("Sorted array using multithreaded merge sort: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Time taken for multithreaded Merge Sort: " + (endTimeMultiThread - startTimeMultiThread) + " nanoseconds");
    }  
    
    
}
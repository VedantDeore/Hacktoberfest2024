import java.util.Arrays;

public class RadixSort {

    // A utility function to get the maximum value in an array
    public static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // A function to perform counting sort on arr[] according to the digit represented by exp
    public static void countingSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // Output array to store sorted numbers
        int[] count = new int[10]; // Count array for digits (0-9)

        // Initialize count array as 0
        Arrays.fill(count, 0);

        // Store count of occurrences of digits in arr[] based on exp (current digit)
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / exp) % 10;
            count[index]++;
        }

        // Change count[i] so that it now contains the actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array by placing elements in the correct order based on the digit
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copy the output array to arr[], so that arr[] now contains the sorted numbers
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // The main function that implements Radix Sort
    public static void radixSort(int[] arr, int n) {
        // Find the maximum number to know the number of digits
        int max = getMax(arr, n);

        // Perform counting sort for every digit. Instead of passing digit number, exp is passed.
        // exp is 10^i where i is the current digit number (1's place, 10's place, etc.)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, n, exp);
        }
    }

    // A utility function to print an array
    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main function to test the Radix Sort
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;

        System.out.println("Original array:");
        printArray(arr, n);

        radixSort(arr, n);

        System.out.println("Sorted array using Radix Sort:");
        printArray(arr, n);
    }
}

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Arrays;

public class SortArray {
    private int[] array;
    //Initializer
    SortArray(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String[] values = line.split(",");

            array = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                array[i] = Integer.parseInt(values[i].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Selection Sort
    public int[] simpleSort(boolean isSorted) {
        if(!isSorted){
            return this.array;
        }
        int result[] = this.array.clone();
        int n = result.length;
        for (int i = 0; i < n-1; i++){
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (result[j] < result[min_idx])
                    min_idx = j;
            }
            int temp = result[min_idx];
            result[min_idx] = result[i];
            result[i] = temp;
        }
        return result;
    }
    // Merge Sort
    public int[] efficientSort(boolean isSorted){
        if(!isSorted){
            return this.array;
        }
        int result[] = this.array.clone();
        mergeSort(result, 0, result.length - 1);
        return result;
    }
    private void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subArrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    private void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    // Counting Sort
    public int[] nonComparisonSort(boolean isSorted) {
        if(!isSorted){
            return this.array;
        }
        int result[] = this.array.clone();
        if (result == null || result.length <= 1) {
            return result;
        }
        // Find the maximum value in the array
        int max = result[0];
        for (int i = 1; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
            }
        }
        // Create a count array to store the count of each unique element
        int[] count = new int[max + 1];
        // Calculate the count of each element in the input array
        for (int i = 0; i < result.length; i++) {
            count[result[i]]++;
        }
        // Modify the count array to store the actual position of each element in the sorted array
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Create a temporary output array
        int[] output = new int[result.length];

        // Build the output array using the count array
        for (int i = result.length - 1; i >= 0; i--) {
            output[count[result[i]] - 1] = result[i];
            count[result[i]]--;
        }
        // Copy the sorted elements from the output array to the original array
        System.arraycopy(output, 0, result, 0, result.length);
        return result;
    }
     //MAX-HEAPIFY :
    public static void maxHeapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, n);
        }
    }

    //BUILD-MAX-HEAP procedure:
    public static void buildMaxHeap(int[] arr, int n) {
        for (int i = n/2; i >= 0; i--) {
            maxHeapify(arr, i, n);
        }
    }

    //HEAPSORT:
    public static int[] heapSort(int[] arr) {
        int n = arr.length;
        buildMaxHeap(arr, n);
        for (int i = n-1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, 0, i);
        }
        return arr;
    }

    //MAX-HEAP-INSERT and HEAP-EXTRACT-MAX :
    public static void maxHeapInsert(int[] arr, int key, int n) {
        n++;
        arr[n-1] = key;
        int i = n-1;
        while (i > 0 && arr[i] > arr[(i-1)/2]) {
            int temp = arr[i];
            arr[i] = arr[(i-1)/2];
            arr[(i-1)/2] = temp;
            i = (i-1)/2;
        }
    }

    public static int heapExtractMax(int[] arr, int n) {
        if (n < 1) {
            return -1;
        }
        int maxVal = arr[0];
        arr[0] = arr[n-1];
        n--;
        maxHeapify(arr, 0, n);
        return maxVal;
    }
    
    
    public int[] getArray() {
        return array;
    }
}

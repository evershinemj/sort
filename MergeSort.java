package wxm.algorithm.sort;

import java.util.List;
import java.util.ArrayList;

import java.util.Arrays;

public class MergeSort {
    // public static void sort(List list) {
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        // int size = list.size();
        // int middle = size / 2;
        // left = list.subList(0, middle);
        // right = list.subList(middle, size);
        // while (true) {
        //     // condition to jump out of recursion
        //     if (size <= 1) {
        //         break;
        //     }
        //     sort(left);
        //     sort(right);
    }

    public static void mergeSort(int[] arr, int low, int high) {

    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 19, 17, 5, 3, 13, 7};
        System.out.println(Arrays.toString(arr));
        MergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

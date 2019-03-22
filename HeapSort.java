package wxm.algorithm.sort;

import java.util.Arrays;
import java.util.logging.Logger;

public class HeapSort {
    private int[] array;
    private int size;
    private Logger logger = Logger.getLogger("mylogger");

    public HeapSort(int[] array) {
        this.array = array;
        this.size = array.length;
    }

    public int getLeftChildIndex(int index) { return index * 2 + 1; }
    public int getRightChildIndex(int index) { return index * 2 + 2; }
    public int getParentIndex(int index) { return (index - 1) / 2; }

    public int leftChild(int index) { return array[getLeftChildIndex(index)]; }
    public int rightChild(int index) { return array[getRightChildIndex(index)]; }
    public int parent(int index) { return array[getParentIndex(index)]; }

    // public boolean hasLeftChild(int index) { return getLeftChildIndex(index) <= size; }
    // public boolean hasRightChild(int index) { return getRightChildIndex(index) <= size; }
    // BUG FOUND:
    // index should be < size, not <= size
    public boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
    public boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
    public boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    // private int[] array;
    // private int size;

    // private class Node {
    //     private int data;
    //     private Node left;
    //     private Node right;
    //     public Node(data) {
    //         this.data = data;
    //     }

        /**
         * this is the logic for binary search tree, not a heap
         */
        // public void insert(int data) {
        //     if (data < this.data) {
        //         if (this.left != null) this.left.insert(data);
        //         else {
        //             left = new Node(data);
        //         }
        //     } else {
        //         if (this.right != null) this.right.insert(data);
        //         else {
        //             right = new Node(data);
        //         }
        //     }
        // }
    

    // private Node root;

    // public void insert(int data) {
    //     if ()
    // }

    // build max heap in place
    // public void buildMaxHeap(int[] array) {
    public void buildMaxHeap() {
        // for (int i : array) {
        for (int i = 0; i < array.length; i++) {
            heapifyUp(i);
        }
    }

    // public void heapSort(int[] array) {
    public void heapSort() {
        // buildMaxHeap(array);
        buildMaxHeap();
        logger.info("After building max heap:");
        logger.info(Arrays.toString(array));
        for (int i = array.length - 1; i > 0; i--) {
            // BUG FOUND:
            // MUST DECREMENT SIZE, SO THAT SORTED ELEMENTS AT THE END REMAIN UNCHANGED 
            size--;
            logger.warning("\033[32mswapping before heapifyDown " + array[0] + " and " + array[i] + "\033[0m");
            swap(array, 0, i);
            logger.warning("\033[32mafter swapping: " + Arrays.toString(array) + "\033[0m");
            logger.warning("\033[31msize = " + size + "\033[0m");
            heapifyDown(0);
        }
    }
    // public void ensureExtraCapacity(int[] array) {
    // }

    // public void heapifyUp(int[] array, int index) {
    public void heapifyUp(int index) {
        // array = new int[array.length + 1];
        if (!hasParent(index) || array[index] <= parent(index)) {
            return;
        } else {
            swap(array, index, getParentIndex(index));
            index = getParentIndex(index);
            // heapifyUp(array, index);
            heapifyUp(index);
        }
    }

    // public void heapifyDown(int[] array, int index) {
    public void heapifyDown(int index) {
        logger.info("calling heapifyDown, index = " + index);
        // if (!hasLeftChild(index)) return;
        if (!hasLeftChild(index)) {
            logger.warning("\033[31mno left child!\033[0m");
            return;
        }
        else {
            if (hasLeftChild(index) && !hasRightChild(index)) {
                // if (array[index] >= getLeftChildIndex(index)) return;
                // if (array[index] >= getLeftChildIndex(index)) {
                // BUG FOUND:
                // getLeftChildIndex(index) should be leftChild(index)!
                if (array[index] >= leftChild(index)) {
                    logger.warning("\033[34marray[index] = " + array[index] + "\033[0m");
                    // logger.warning("\033[34mgetLeftChildIndex(index) = " + getLeftChildIndex(index) + "\033[0m");
                    logger.warning("\033[34mleftChild(index) = " + leftChild(index) + "\033[0m");
                    logger.warning("\033[34mbigger than left child and no right child!\033[0m");
                }
                else {
                    logger.info("swapping " + array[index] + " and " + array[getLeftChildIndex(index)]);
                    swap(array, index, getLeftChildIndex(index));
                    logger.info("after swaping: " + Arrays.toString(array));//{{{
                    logger.info("preparing for a recursive call of heapifyDown");//}}}
                    index = getLeftChildIndex(index);
                    // heapifyDown(array, index);
                    heapifyDown(index);
                }
            } else {
                int biggerChildIndex = getLeftChildIndex(index);
                if (rightChild(index) > leftChild(index)) {
                    biggerChildIndex = getRightChildIndex(index);
                }
                if (array[index] >= array[biggerChildIndex]) return;
                else {
                    // logger.info("swapping " + index + " and " + biggerChildIndex);{{{
                    logger.info("swapping inside heapifyDown " + array[index] + " and " + array[biggerChildIndex]);//}}}
                    swap(array, index, biggerChildIndex);
                    index = biggerChildIndex;
                    logger.info("after swaping: " + Arrays.toString(array));//{{{
                    logger.info("preparing for a recursive call of heapifyDown");//}}}
                    heapifyDown(index);
                }
            }
        }
        logger.info("end of recursive call of heapifyDown");
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        int[] array = {11, 2, 7, 19, 29, 23, 3, 5, 31, 17};
        System.out.println(Arrays.toString(array));
        HeapSort heapSort = new HeapSort(array);
        heapSort.heapSort();
        System.out.println(Arrays.toString(array));
    }

    // public void heapSort(int[] arr) {
    //     // heapSort(arr, 0, arr.length - 1);
    //     int biggest = arr[0];
    //     for (int i : arr) {
    //         if (i > biggest) {
    //             biggest = i;
    //         }
    //     }
    //     Node root = new Node(biggest);
    // }

    // private void heapSort(int[] arr, int low, int high) {

    // }

}

// package xwm.algorithm.sort;{{{
// typo}}}
package wxm.algorithm.sort;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;

public class QuickSort {
    private static Object pivot; 
    private static int pivotIndex;
    private static int leftIndex;
    private static int rightIndex;
    private static int compareTimes = 0;
    private static int size;
    private static List list;
    private static List left;
    private static List right;
    private static List rightBackup;
    private static Logger logger = Logger.getLogger("logger");
    private static Logger majorLogger = Logger.getLogger("majorLogger");
    static {
        logger.setLevel(Level.WARNING);
        // logger.setLevel(Level.INFO);
    }
    public static void sort(List list) {
        // BUG FOUND:
        // compareTimes must be reset in each calling of sort!
        compareTimes = 0;
        QuickSort.list = list;        // this.list = list;
        pivot = list.get(0); 
        logger.info("setting the pivot");
        logger.info(pivot.toString());
        size = list.size();        // int size = list.size();
        logger.info("list size: " + size);
        leftIndex = 0;
        // IndexOutOfBoundsException because rightIndex should be size - 1 !
        // rightIndex = size;
        rightIndex = size - 1;
        logger.info("rightIndex: " + rightIndex);
        // BUG FOUND:
        // updating pivotIndex should be after grouping left and right!
        //
        pivotIndex = list.indexOf(pivot);
        // divide into two groups according to the pivot{{{ // for (int i = size - 1; i > 0; i--) {
        // List container = new ArrayList(size);
        // for (int i = 0; i < size; i++) {
        // for (int i = size - 1; i > 1; i--) {
        // while (rightIndex - leftIndex <= 2) {}}}
        compareWithRight();
        logger.info("after one round of sorting, list becomes : " + list.toString());
        majorLogger.info("after one round of sorting, list becomes : " + list.toString());
        // }{{{
            // if (list.get(i) > pivot) {
            // pivot must be compared with elements of index rightIndex or leftIndex
            // if (list.get(rightIndex) >= pivot) {
                // container.get(rightIndex) = list.get(i);
                // rightIndex--;
            // } else {
                // container.get(leftIndex) = list.get(i);
                // swap(pivot, list.get(rightIndex));
                // leftIndex++;
            // }
        // update list to make it refer to the newly created List
            // list = container;
        // int pivotIndex = list.indexOf(pivot);}}}
        logger.info("pivotIndex = " + pivotIndex);
        left = list.subList(0, pivotIndex);
        // logger.info("left: " + left.toString());
        majorLogger.info("left: " + left.toString());
        /**
         *   ___________________________________________________
         *  /\                                                  \
         *  \_| // BUG FOUND:                                   |
         *    | // right is updated after QuickSort.sort(left)! |
         *    |   ______________________________________________|_
         *     \_/________________________________________________/
         */
        right = list.subList(pivotIndex + 1, size);
        rightBackup = right;
        // logger.info("right: " + right.toString());
        majorLogger.info("right: " + right.toString());
        majorLogger.info("rightBackup: " + right.toString());
        // }{{{
        // condition to terminate recursion
        // while (left.size() > 1 && right.size() > 1)}}}
        while (left.size() > 1 || right.size() > 1)
        // calling sort on the two groups
        {
            logger.info("calling QuickSort.sort(left)");
            majorLogger.warning("QuickSort.sort(" + left.toString() + ")");
            QuickSort.sort(left);
            right = rightBackup;
            logger.info("right: " + right.toString());
            logger.info("calling QuickSort.sort(right)");
            majorLogger.warning("QuickSort.sort(" + right.toString() + ")");
            QuickSort.sort(right);
        }
    }
    
    // public static void compareWithLeft() {
    private static void compareWithLeft() {
        // BUG FOUND:
        // compareTimes++ should be called after the test condition!
        // compareTimes++;
        if (compareTimes >= size - 1) return;
        compareTimes++;
        // if (list.get(leftIndex) <= pivot) {{{{
        // if ((Comparable) list.get(leftIndex) <= (Comparable) pivot) {
        // the type conversion is checked at runtime
        // if ((Integer) list.get(leftIndex) <= (Integer) pivot) {}}}
        if ((Integer) list.get(leftIndex) < (Integer) pivot) {
            logger.info("testing: if ((Integer) list.get(leftIndex) < (Integer) pivot) SUCCEEDED");
            logger.info("list.get(leftIndext) = " + list.get(leftIndex) + "; pivot = " + pivot);
            leftIndex++;
            logger.info("executed leftIndex++, leftIndex now becomes " + leftIndex);
            // recursive call
            compareWithLeft();
        } else {
            logger.info("testing: if ((Integer) list.get(leftIndex) < (Integer) pivot) FAILED");
            logger.info("list.get(leftIndext) = " + list.get(leftIndex) + "; pivot = " + pivot);
            // swap(pivot, list.get(leftIndex));
            Collections.swap(list, leftIndex, pivotIndex);
            logger.info("calling Collections.swap(list, leftIndex, pivotIndex)");
            logger.info("swapped " + list.get(leftIndex) + " and " +  list.get(pivotIndex));
            // BUG FOUND:
            // forgot to update pivot after swapping!
            pivotIndex = leftIndex;
            pivot = list.get(leftIndex);
            rightIndex--;
            logger.info("executed rightIndex--, rightIndex now becomes " + rightIndex);
            compareWithRight();
        }
        // forgot to increment compareTimes resulted in StackOverflowError!{{{
        // the below statement is never executed! 
        // compareTimes++;}}}
    }

    // public static void compareWithRight() {
    private static void compareWithRight() {
        // BUG FOUND:
        // compareTimes++ should be called after the test condition!
        // compareTimes++;
        if (compareTimes >= size - 1) return;
        compareTimes++;
        // if (list.get(rightIndex) >= pivot) {{{{
        // if ((Comparable) list.get(rightIndex) >= (Comparable) pivot) {
        // if ((Integer) list.get(rightIndex) >= (Integer) pivot) {}}}
        if ((Integer) list.get(rightIndex) > (Integer) pivot) {
            logger.info("testing: if ((Integer) list.get(rightIndex) > (Integer) pivot) SUCCEEDED");
            logger.info("list.get(rightIndext) = " + list.get(rightIndex) + "; pivot = " + pivot);
            rightIndex--;
            logger.info("executed rightIndex--, rightIndex now becomes " + rightIndex);
            logger.info("leftIndex = " + leftIndex);
            compareWithRight();            // recursive call
        } else {
            logger.info("testing: if ((Integer) list.get(rightIndex) > (Integer) pivot) FAILED");
            logger.info("list.get(rightIndext) = " + list.get(rightIndex) + "; pivot = " + pivot);
            logger.info("leftIndex = " + leftIndex);
            Collections.swap(list, rightIndex, pivotIndex);            // swap(pivot, list.get(rightIndex));
            logger.info("calling Collections.swap(list, rightIndex, pivotIndex)");
            logger.info("swapped " + list.get(rightIndex) + " and " + list.get(pivotIndex));
            // BUG FOUND:
            // forgot to update pivot after swapping!
            pivotIndex = rightIndex;
            pivot = list.get(rightIndex);
            leftIndex++;
            // BUG FOUND:
            // logger.info("executed leftIndex++, leftIndex now becomes " + rightIndex);
            logger.info("executed leftIndex++, leftIndex now becomes " + leftIndex);
            compareWithLeft();
        }
        // forgot to increment compareTimes resulted in StackOverflowError!{{{
        // the below statement is never executed! 
        // compareTimes++;}}}
    }

    // public static void swap(T t1, T t2) {{{{
    //     T temp = t1;
    //     t1 = t2;
    //     t2 = temp;
    // }

    // public static void swap(Object o1, Object o2) {
    //     Object temp = o1;
    //     o1 = o2;
    //     o2 = temp;
    // }
//}}}
    public static void main(String[] args) {
        // ArrayList<Integer> array = Arrays.asList({{{
        //     2, 5, 7, 19, 13, 17, 3, 11
        // );}}}
        ArrayList<Integer> array = new ArrayList<>();
        array.add(17);
        array.add(13);
        array.add(19);
        array.add(23);
        array.add(7);

        array.add(71);
        array.add(67);
        array.add(87);
        array.add(27);
        array.add(37);

        array.add(72);
        array.add(47);
        array.add(57);
        array.add(26);
        array.add(29);

        System.out.println("before sorting: " + array);
        QuickSort.sort(array);
        System.out.println("after sorting: " + array);
    }
}


// public class QuickSort<T> {{{{
//     private T pivot; 
//     private int leftIndex;
//     private int rightIndex;
//     private Collection left;
//     private Collection right;
//     public static void sort(Collection<T> collection) {
//         pivot = collection.get(0); 
//         int size = collection.size();
//         leftIndex = 0;
//         rightIndex = size;
//         // divide into two groups according to the pivot
//         // for (int i = size - 1; i > 0; i--) {
//         Collection container = new Collection(size);
//         for (int i = 0; i < size; i++) {
//             if (collection.get(i) > pivot) {
//                 collection.get(rightIndex) = collection.get(i);
//                 rightIndex--;
//             } else {
//                 collection.get(leftIndex) = collection.get(i);
//                 leftIndex++;
//             }
//         // update collection to make it refer to the newly created Collection
//             collection = container;
//             int pivotIndex = collection.indexOf(pivot);
//             left = collection.
//         }
//         // condition to terminate recursion
//         while (left.size() > 1 && right.size() > 1)
//         // calling sort on the two groups
//         {
//             QuickSort.sort(left);
//             QuickSort.sort(right);
//         }
//     }}}}

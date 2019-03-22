package wxm.algorithm.sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class SelectSort {
    public static void sort(List<Integer> list) {
        int size = list.size();
        int lastIndex = size - 1;
        Logger logger = Logger.getLogger("mylogger");
        for (int i = 0; i < lastIndex; i++) {
            for (int j = i + 1; j <= lastIndex; j++) {
                if (list.get(i) > list.get(j)) {
                    // Swap.swap(list.get(i), list.get(j));
                    // Swap.swap((Integer) list.get(i), (Integer) list.get(j));
                    //
                    // Collections.sort(list, i, j);
                    // Collections.<Integer>sort(list, i, j);
                    // typo
                    Collections.swap(list, i, j);
                    logger.info("called Collections.swap(list, i, j)");
                    // logger.info("swapped " + i + " and " + j);
                    logger.info("swapped " + list.get(i) + " and " + list.get(j));
                }
            }
        }
    }

    public static void main(String[] args) {
        // List<Integer> list = SampleCollection.sampleList;
        List<Integer> list = CollectionFactory.getListOfInteger();
        System.out.println(list);
        sort(list);
        System.out.println(list);
    }
}

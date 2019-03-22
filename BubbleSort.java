package wxm.algorithm.sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class BubbleSort {
    public static void sort(List<Integer> list) {
        int size = list.size();
        int lastIndex = size - 1;
        Logger logger = Logger.getLogger("mylogger");
        for (int i = lastIndex; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Collections.swap(list, i, j);
                    // BUG FOUND:
                    // should swap j and j + 1, not i and j!
                    Collections.swap(list, j, j + 1);
                    // logger.info("called Collections.swap(list, i, j))");
                    logger.info("called Collections.swap(list, j, j + 1))");
                    // logger.info("swapped " + j + " and " + (j + 1));
                    logger.info("swapped " + list.get(j) + " and " + list.get(j + 1));
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

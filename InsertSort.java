package wxm.algorithm.sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

public class InsertSort {
    public static void sort(List<Integer> list) {
        int size = list.size();
        int lastIndex = size - 1;
        for (int i = 0; i <= lastIndex; i++) {    
            int j = i;
            while (j > 0 && list.get(j) < list.get(j - 1)) {
                // BUG FOUND:
                // should swap j and j - 1, not i and j!
                // Collections.swap(list, i, j);
                Collections.swap(list, j, j - 1);
                // Logger logger = Logger.getLogger(InsertSort.class);
                Logger logger = Logger.getLogger("mylogger");
                // logger.info("called Collections.swap(list, i, j)");
                logger.info("called Collections.swap(list, j, j - 1)");
                    // logger.info("swapped " + j + " and " + (j - 1));
                    logger.info("swapped " + list.get(j) + " and " + list.get(j - 1));
                j--;
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

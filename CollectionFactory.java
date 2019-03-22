package wxm.algorithm.sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CollectionFactory {
    // private static List<Integer> listOfInteger;
    public static List<Integer> getListOfInteger() {
        List<Integer> listOfInteger = new ArrayList<Integer>();
        listOfInteger.add(7);
        listOfInteger.add(3);
        listOfInteger.add(11);
        listOfInteger.add(13);
        listOfInteger.add(2);
        return listOfInteger;
    }
    // public static List<Integer> sampleList = new ArrayList<>();
    // public static List<Integer> sampleList = new ArrayList<>();
    // static {
    //     sampleList.add(3);
    //     sampleList.add(2);
    //     sampleList.add(11);
    //     sampleList.add(13);
    //     sampleList.add(7);
    // }
}


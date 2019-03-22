package wxm.algorithm.sort;

// public class Swap {
class Swap {
    // typo
    // static void sort(Object o1, Object o2) {
    public static void swap(Object o1, Object o2) {
        Object temp = o1;
        o1 = o2;
        o2 = temp;
    }

    public static void main(String[] args) {
        String[] words = new String[] {
            "I", "like", "bunnies"
        };
        System.out.println(words);
        for (String s : words) {
            System.out.print(s + " ");
        }
        System.out.println();
        swap(words[0], words[2]);
        System.out.println(words);
        for (String s : words) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}

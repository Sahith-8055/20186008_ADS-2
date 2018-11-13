import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Unused Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        Scanner scan = new Scanner(System.in);
        TST<Integer> t = new TST<Integer>();
        String s = scan.nextLine();
        int j = 0;
        for (String word : words) {
            SuffixArray sa = new SuffixArray(word);
            for (int i = 0; i < word.length(); i++) {
                t.put(sa.select(i), j++);
            }
        }
        for (String str : t.keysWithPrefix(s)) {
            System.out.println(str);
        }
    }

    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
import java.util.Scanner;
import java.util.TreeSet;
/**
 * Class for solution.
 */
public class Solution {
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
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * {Method to read files}.
     *
     * @param      file  The file
     *
     * @return     {Array of string}
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     {Symbol Table}
     */
    public static BinarySearchST<String, Integer> loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] tokens = toReadFile(file);
        int length = tokens.length;
        for(int i = 0; i < length; i++) {
            String str = tokens[i].toLowerCase();
            if (!st.contains(str)) {
                st.put(str, 1);
            } else {
                st.put(str, st.get(str) + 1);
            }
        }
        return st;
    }
}

/**
 * Class for t9.
 */
class T9 {
    /**
     * {Ternary Search Trie}.
     */
    private TST<Integer> tst;

    /**
     * Constructs the object.
     *
     * @param      st    {Symbol Table Object}
     */
    public T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        this.tst = new TST<Integer>();
        for (String word: st.keys()) {
            tst.put(word, st.get(word));
        }
    }


    /**
     * get all the prefixes that match with given prefix.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }

    /**
     * {Method to get the potential words}.
     *
     * @param      t9Signature  The t9 signature
     *
     * @return     {Iterable}
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        TreeSet<String> set = new TreeSet<String>();
        for (String word : tst.keys()) {
            String[] tokens1 = word.split("");
            String format = "";
            for (String str : tokens1) {
                if (str.equals("a") || str.equals("b") || str.equals("c")) {
                    format += "2";
                } else if (str.equals("d") || str.equals("e") || str.equals("f")) {
                    format += "3";
                } else if (str.equals("g") || str.equals("h") || str.equals("i")) {
                    format += "4";
                } else if (str.equals("j") || str.equals("k") || str.equals("l")) {
                    format += "5";
                } else if (str.equals("m") || str.equals("n") || str.equals("o")) {
                    format += "6";
                } else if (str.equals("p") || str.equals("q") || str.equals("r") || str.equals("s")) {
                    format += "7";
                } else if (str.equals("t") || str.equals("u") || str.equals("v")) {
                    format += "8";
                } else if (str.equals("w") || str.equals("x") || str.equals("y") || str.equals("z")) {
                    format += "9";
                }
            }
            if (format.equals(t9Signature)) {
                set.add(word);
            }
        }
        return set;
    }


    /**
     * return all possibilities(words).
     * find top k with highest frequency.
     *
     * @param      words  The words
     * @param      k      {Limit}
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words, final int k) {
        // your code goes here
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        for (String word : words) {
            pq.insert(tst.get(word));
        }
        TreeSet<String> set = new TreeSet<String>();
        for (int i = 0; i < k; i++) {
            int frequency = pq.delMax();
            for (String word : words) {
                if (frequency == tst.get(word)) {
                    set.add(word);
                }
            }
        }
        return set;
    }


    /**
     * {Final method}.
     *
     * @param      t9Signature  The t9 signature
     * @param      k            {Integer}
     *
     * @return     {Iterable}
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;
public class CircularSuffixArray {
    private class Node implements Comparable<Node> {
        private String str;
        private int value;
        Node(final String s, final int val) {
            this.str = s;
            this.value = val;
        }
        public int compareTo(final Node that) {
            int len = str.length();
            for (int i = 0; i < len; i++) {
                char c = str.charAt((i + this.value) % len);
                char d = str.charAt((i + that.value) % len);
                if (c > d) {
                    return 1;
                }
                if (c < d) {
                    return -1;
                }
            }
            return 0;
        }
        public int getValue() {
            return this.value;
        }
    }

    private int[] indices;
    private int length;

    public CircularSuffixArray(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        this.length = s.length();
        this.indices = new int[length];
        Node[] substrings = new Node[length];
        for (int i = 0; i < length; i++) {
            substrings[i] = new Node(s, i);
        }
        Arrays.sort(substrings);
        for (int j = 0; j < substrings.length; j++) {
            indices[j] = substrings[j].getValue();
        }
    }

    public int length() {
        return this.length;
    }

    /**
     * returns index of ith sorted suffix.
     *
     * @param i the index of the ith sorted suffix
     * @return returns index of ith sorted suffix
     */
    public int index(final int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException("Input index is invalid.");
        }
        return indices[i];
    }
}
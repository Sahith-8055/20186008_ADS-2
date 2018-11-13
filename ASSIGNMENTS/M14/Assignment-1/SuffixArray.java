import java.util.Arrays;

/**
 * Class for suffix array.
 */
public final class SuffixArray {
    /**
     * {suffixes}.
     */
    private Suffix[] suffixes;

    /**
     * Initializes a suffix array for the given {@code text} string.
     * @param text the input string
     */
    SuffixArray(final String text) {
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text, i);
        }
        Arrays.sort(suffixes);
    }

    /**
     * Class for suffix.
     */
    private class Suffix implements Comparable<Suffix> {
        /**
         * {String text}.
         */
        private String text;
        /**
         * {Index}.
         */
        private int index;

        /**
         * Constructs the object.
         *
         * @param      text1   The text
         * @param      index1  The index
         */
        private Suffix(final String text1, final int index1) {
            this.text = text1;
            this.index = index1;
        }
        /**
         * {Length of the suffix array}.
         *
         * @return     {Integer}
         */
        private int length() {
            return text.length() - index;
        }
        /**
         * {Method to find the position of the character}.
         *
         * @param      i     {Integer}
         *
         * @return     {Character}
         */
        private char charAt(final int i) {
            return text.charAt(index + i);
        }

        /**
         * {compareTo method}.
         *
         * @param      that  The that
         *
         * @return     {Integer}
         */
        public int compareTo(final Suffix that) {
            if (this == that) {
                return 0;
            }
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) {
                    return -1;
                }
                if (this.charAt(i) > that.charAt(i)) {
                    return +1;
                }
            }
            return this.length() - that.length();
        }

        /**
         * Returns a string representation of the object.
         *
         * @return     String representation of the object.
         */
        public String toString() {
            return text.substring(index);
        }
    }

    /**
     * Returns the length of the input string.
     * @return the length of the input string
     */
    public int length() {
        return suffixes.length;
    }


    /**
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string
     * of the <em>i</em>th smallest suffix
     */
    public int index(final int i) {
        return suffixes[i].index;
    }


    /**
     * @param i an integer between 1 and <em>n</em>-1
     * @return the length of the longest common prefix.
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     */
    public int lcp(final int i) {
        return lcpSuffix(suffixes[i], suffixes[i - 1]);
    }


    /**
     * {longest common prefix of s and t}.
     *
     * @param      s     {Suffix}
     * @param      t     {Suffix}
     *
     * @return     {Integer}
     */
    private int lcpSuffix(final Suffix s, final Suffix t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        return n;
    }

    /**
     * Returns the <em>i</em>th smallest suffix as a string.
     * @param i the index
     * @return the <em>i</em> smallest suffix as a string
     */
    public String select(final int i) {
        return suffixes[i].toString();
    }

    /**
     * @param query the query string
     * @return the number of suffixes strictly less than {@code query}
     */
    public int rank(final String query) {
        int lo = 0, hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, suffixes[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }


    /**
     * {compare query string to suffix}.
     *
     * @param      query   The query
     * @param      suffix  The suffix
     *
     * @return     {Integer}
     */
    private int compare(final String query, final Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }
}
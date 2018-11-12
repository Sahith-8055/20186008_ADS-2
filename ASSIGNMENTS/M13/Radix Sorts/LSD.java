/**
 * Class for lsd.
 */
public final class LSD {
    /**
     * {Bits per byte}.
     */
    private static final int BITS_PER_BYTE = 8;

    /**
     * Constructs the object.
     */
    protected LSD() {
        // do not instantiate.
    }

    /**
      * Rearranges the array of W-character strings in ascending order.
      *
      * @param a the array to be sorted
      * @param w the number of characters per string
      */
    public void sort(final String[] a, final int w) {
        final int x = 256;
        int n = a.length;
        int radix = x;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[radix + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
    /**
     * Returns a string representation of the object.
     *
     * @param      a     {String array}
     */
    public void toString(final String[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < a.length - 1; i++) {
            sb.append(a[i] + ", ");
        }
        sb.append(a[a.length - 1] + "]");
        System.out.println(sb.toString());
    }
}

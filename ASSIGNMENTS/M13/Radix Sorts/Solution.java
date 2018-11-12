import java.util.Scanner;
class LSD {
    private static final int BITS_PER_BYTE = 8;

    public LSD() {
        // do not instantiate.
    }

    /**
      * Rearranges the array of W-character strings in ascending order.
      *
      * @param a the array to be sorted
      * @param w the number of characters per string
      */
    public void sort(final String[] a, final int w) {
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
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
      * Rearranges the array of 32-bit integers in ascending order.
      * This is about 2-3x faster than Arrays.sort().
      *
      * @param a the array to be sorted
      */
    public void sort(final int[] a) {
        final int BITS = 32;                 // each int is 32 bits
        final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int MASK = R - 1;              // 0xFF
        final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = R / 2; r < R; r++) {
                    count[r] -= shift2;
                }
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
    public void toString(final String[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i;
        for (i = 0; i < a.length - 1; i++) {
            sb.append(a[i] + ", ");
        }
        sb.append(a[i] + "]");
        System.out.println(sb.toString());
    }
}

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
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberofLines = Integer.parseInt(scan.nextLine());
        String[] array = new String[numberofLines];
        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextLine();
        }
        LSD l = new LSD();
        l.sort(array, 6);
        l.toString(array);
    }
}
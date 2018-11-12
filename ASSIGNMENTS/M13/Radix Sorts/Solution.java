import java.util.Scanner;

/**
 * Class for msd.
 */
class MSD {
    /**
     * {Bits per byte}.
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * {each Java int is 32 bits}.
     */
    private static final int BITS_PER_INT = 32;
    /**
     * {extended ASCII alphabet size}.
     */
    private static final int R = 256;
    /**
     * {cutoff to insertion sort}.
     */
    private static final int CUTOFF = 15;

    /**
     * Constructs the object.
     */
    public MSD() {
        // do not instantiate.
    }

    /**
      * Rearranges the array of extended ASCII strings in ascending order.
      * @param a the array to be sorted
      */
    public void sort(final String[] a) {
        int n = a.length;
        String[] aux = new String[n];
        sort(a, 0, n - 1, 0, aux);
    }


    /**
     * {return dth character of s, -1 if d = length of string}.
     *
     * @param      s     {String}
     * @param      d     {Integer}
     *
     * @return     {Integer}
     */
    private int charAt(final String s, final int d) {
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }


    /**
     * {sort from a[lo] to a[hi], starting at the dth character}.
     *
     * @param      a     {String array}
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     {Integer}
     * @param      aux   The auxiliary
     */
    private void sort(final String[] a, final int lo,
                      final int hi, final int d, final String[] aux) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // compute frequency counts
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c + 1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }


        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
        }
    }

    /**
     * {insertion sort a[lo..hi], starting at dth character}
     *
     * @param      a     {String array}
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     {Integer}
     */
    private void insertion(final String[] a,
                           final int lo, final int hi, final int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }


    /**
     * {exchange a[i] and a[j]}.
     *
     * @param      a     {String array}
     * @param      i     {Index i}
     * @param      j     {Index j}
     */
    private void exch(final String[] a, final int i, final int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     * {is v less than w, starting at character d}.
     *
     * @param      v     {String}
     * @param      w     {String}
     * @param      d     {Position of character}
     *
     * @return     {Boolean}
     */
    private boolean less(final String v, final String w, final int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }

    // exchange a[i] and a[j]
    private void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
        MSD l = new MSD();
        l.sort(array);
        l.toString(array);
    }
}
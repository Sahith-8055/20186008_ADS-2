import java.util.Scanner;
class Percolation {
    /**
     * {Declaring an object of weighted quick union}.
     */
    private Graph uf;
    /**
     * {variable n}.
     */
    private int n;
    /**
     * {Variables size, first, last, count}.
     */
    private int size, first, last, count;
    /**
     * {Declaring an integer array of type boolean}.
     */
    private boolean[] connected;
    /**
     * Constructs the object.
     *
     * @param      n1    The n1
     */
    Percolation(final int n1) {
        this.n = n1;
        this.size = n1 * n1;
        this.first = size;
        this.last = size + 1;
        this.count = 0;
        connected = new boolean[size];
        uf = new Graph(size + 2);
        for (int i = 0; i < n; i++) {
            uf.addEdge(first, i);
            uf.addEdge(last, size - i - 1);
        }
    }
    /**
     * Searches for the first match.
     *
     * @param      i     {row}
     * @param      j     {column}
     *
     * @return     {index value for 1-Dimensional Array}
     */
    private int indexOf(final int i, final int j) {
        return (n * (i - 1)) + (j - 1);
    }
    /**
     * Links open sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void linkOpenSites(final int row, final int col) {
        if (connected[col] && !uf.hasEdge(row, col)) {
            uf.addEdge(row, col);
        }
    }
    /**
     * {Method to open site (row, col) if it is not open already}.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        int index = indexOf(row, col);
        connected[index] = true;
        count++;
        int top = index - n;
        int bottom = index + n;
        if (n == 1) {
            uf.addEdge(first, index);
            uf.addEdge(first, index);
        }
        if (bottom < size) {
            linkOpenSites(index, bottom);
        }
        if (top >= 0) {
            linkOpenSites(index, top);
        }
        if (col == 1) {
            if (col != n) {
                linkOpenSites(index, index + 1);
            }
            return;
        }
        if (col == n) {
            linkOpenSites(index, index - 1);
            return;
        }
        linkOpenSites(index, index + 1);
        linkOpenSites(index, index - 1);
    }
    /**
     * Determines if the site is open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return connected[indexOf(row, col)];
    }
    /**
     * {Method to determine the number of open sites}.
     *
     * @return     {Number of open sites}
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * {Method to determine does the system percolate?}.
     *
     * @return     {Boolean value}
     */
    public boolean percolates() {
        CC cc = new CC(uf);
        return cc.connected(first, last);
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Unused Constructor.
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = Integer.parseInt(scan.nextLine());
        Percolation p = new Percolation(number);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            p.open(a, b);
        }
        System.out.println(p.percolates() && p.numberOfOpenSites() != 0);
    }
}

import java.util.Scanner;

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Digraph d = new Digraph(vertices);
        while (edges > 0) {
            String[] tokens = scan.nextLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            d.addEdge(a, b);
            edges--;
        }
        DirectedCycle dc = new DirectedCycle(d);
        if (dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}

import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Unused Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        Edge e = null;
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices, edges);
        while (vertices > 0) {
            String[] tokens = scan.nextLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);
            e = new Edge(a, b, weight);
            ewg.addEdge(e);
            vertices--;
        }
        LazyPrimMST l = new LazyPrimMST(ewg);
        System.out.printf("%.5f\n", l.weight());
    }
}
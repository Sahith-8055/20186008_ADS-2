import java.util.Scanner;
//import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Empty Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokens = scan.nextLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        String[] stations = scan.nextLine().split(" ");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
        LinearProbingHashST<String, Integer> hash;
        hash = new LinearProbingHashST<String, Integer>();
        for (int i = 0; i < stations.length; i++) {
            hash.put(stations[i], i);
        }
        while (m > 0) {
            String[] routes = scan.nextLine().split(" ");
            ewg.addEdge(new Edge(
                            hash.get(routes[0]), hash.get(routes[1]),
                            Double.parseDouble(routes[2])));
            m--;
        }
        int q = Integer.parseInt(scan.nextLine());
        while (q > 0) {
            String[] queries = scan.nextLine().split(" ");
            int a = hash.get(queries[0]);
            DijkstraUndirectedSP dusp = new DijkstraUndirectedSP(ewg, a);
            if (dusp.hasPathTo(hash.get(queries[1]))) {
                System.out.println((int) dusp.distTo(hash.get(queries[1])));
            }
            q--;
        }
    }
}

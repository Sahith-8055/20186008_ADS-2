import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
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
        int cities = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(cities);
        int roadLines = Integer.parseInt(scan.nextLine());
        DijkstraUndirectedSP dusp;
        while (roadLines > 0) {
            String[] tokens = scan.nextLine().split(" ");
            int cityA = Integer.parseInt(tokens[0]);
            int cityB = Integer.parseInt(tokens[1]);
            int distance = Integer.parseInt(tokens[2]);
            ewg.addEdge(new Edge(cityA, cityB, distance));
            roadLines--;
        }
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(ewg);
            //Print the Graph Object.
            break;

        case "DirectedPaths":
            String[] tokens1 = scan.nextLine().split(" ");
            int a = Integer.parseInt(tokens1[0]);
            int b = Integer.parseInt(tokens1[1]);
            dusp = new DijkstraUndirectedSP(ewg, a);
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            if (!dusp.hasPathTo(b)) {
                System.out.println("No Path Found.");
            } else {
                System.out.println(dusp.distTo(b));
            }
            break;

        case "ViaPaths":
            String[] tokens3 = scan.nextLine().split(" ");
            int source = Integer.parseInt(tokens3[0]);
            int viaPath = Integer.parseInt(tokens3[1]);
            int destination = Integer.parseInt(tokens3[2]);
            dusp = new DijkstraUndirectedSP(ewg, source);
            DijkstraUndirectedSP dusp1 = new DijkstraUndirectedSP(ewg, viaPath);
            if (!(dusp.hasPathTo(viaPath) && dusp1.hasPathTo(destination))) {
                System.out.println("No Path Found.");
            } else {
                String str = "";
                for (int p : dusp.pathTo(viaPath)) {
                    str += p + " ";
                }
                for (int q : dusp1.pathTo(destination)) {
                    str += q + " ";
                }
                str += destination;
                System.out.println(dusp.distTo(viaPath) + dusp1.distTo(destination));
                System.out.println(str);
            }
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via
            // is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;
        default:
            break;
        }

    }
}

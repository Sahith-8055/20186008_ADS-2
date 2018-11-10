import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
    private Solution() {
        //Unused Constructor.
    }
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
            if (dusp.hasPathTo(destination)) {
                Queue<Integer> queue = new Queue<Integer>();
                for (Edge each : dusp.pathTo(viaPath)) {
                    String[] cities1 = each.toString().split(" ");
                    String[] array = cities1[0].split("-");
                    int count = 0;
                    int flag = 0;
                    for (int eachInt: queue) {
                        if (Integer.parseInt(array[1]) == eachInt) {
                            count++;
                        }
                        if (Integer.parseInt(array[1]) == eachInt) {
                            flag++;
                        }
                    }
                    if (flag == 0) {
                        queue.enqueue(Integer.parseInt(array[1]));
                    }
                    if (count == 0) {
                        queue.enqueue(Integer.parseInt(array[0]));
                    }
                }
                DijkstraUndirectedSP dusp1 = new DijkstraUndirectedSP(ewg, viaPath);
                for (Edge eachEdge : dusp1.pathTo(destination)) {
                    String[] cities2 = eachEdge.toString().split(" ");
                    String[] array1 = cities2[0].split("-");
                    int count1 = 0;
                    int flag1 = 0;
                    for (Integer eachInt1 : queue) {
                        if (Integer.parseInt(array1[0]) == eachInt1) {
                            count1++;
                        }
                        if (Integer.parseInt(array1[1]) == eachInt1) {
                            flag1++;
                        }
                    }
                    if (count1 == 0) {
                        queue.enqueue(Integer.parseInt(array1[0]));
                    }
                    if (flag1 == 0) {
                        queue.enqueue(Integer.parseInt(array1[1]));
                    }
                }
                System.out.println(
                    dusp.distTo(viaPath) + dusp1.distTo(destination));
                while (!queue.isEmpty()) {
                    System.out.print(queue.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;
        default:
            break;
        }

    }
}
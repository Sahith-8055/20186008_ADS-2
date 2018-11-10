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
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokens = scan.nextLine().split(" ");
        int vertices = Integer.parseInt(tokens[0]);
        int edges = Integer.parseInt(tokens[1]);
        if (edges == 0) {
            System.out.println(edges);
        } else {
            Graph g = new Graph(vertices + 1);
            while (scan.hasNext()) {
                String[] tokens1 = scan.nextLine().split(" ");
                g.addEdge(Integer.parseInt(tokens1[0]),
                            Integer.parseInt(tokens1[1]));
            }
            CC connected = new CC(g);
            int count1 = 0;
            int count2 = 0;
            int[] array = connected.idArray();
            for (int i = 0; i < g.V(); i++) {
                if (g.hasParallelEdges(i)) {
                    count1++;
                }
                int idcount = 0;
                int id = array[i];
                for (int j = 0; j < array.length; j++) {
                    if (id == array[j]) {
                        idcount++;
                    }
                }
                if (count2 < idcount) {
                    count2 = idcount;
                }
            }
            System.out.println(count1 + count2);
        }
    }
}
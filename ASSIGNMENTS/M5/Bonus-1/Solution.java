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
        Graph g = new Graph(vertices);
        while (edges > 0) {
            String[] connections = scan.nextLine().split(" ");
            int a = Integer.parseInt(connections[0]);
            int b = Integer.parseInt(connections[1]);
            g.addEdge(a - 1, b - 1);
            edges--;
        }
        CC connected = new CC(g);
        int m = connected.count();
        Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int j = 0; j < g.V(); j++) {
            components[connected.id(j)].enqueue(j);
        }
        int count = 0;
        for (int k = 0; k < m; k++) {
            if (count <= components[k].size()) {
                count = components[k].size();
            }
        }
        System.out.println(count);
    }
}
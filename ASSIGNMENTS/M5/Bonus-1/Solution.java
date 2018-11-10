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
        Graph g = new Graph(vertices + 1);
        while (edges > 0) {
            String[] connections = scan.nextLine().split(" ");
            int a = Integer.parseInt(connections[0]);
            int b = Integer.parseInt(connections[1]);
            g.addEdge(a - 1, b - 1);
            edges--;
        }
        CC connected = new CC(g);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int[] array = connected.idArray();
        for (int i = 0; i < g.V(); i++) {
            if (g.hasParallelEdges(i)) {
                count1++;
            }
            int count4 = 0;
            count3 = array[i];
            for (int j = 0; j < array.length; j++) {
                if (count3 == array[j]) {
                    count4++;
                }
            }
            if (count2 < count4) {
                count2 = count4;
            }
        }
        System.out.println(count1 + count2);
    }
}
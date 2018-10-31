import java.util.Scanner;
public class Solution {
    public Solution() {
        // Constructor.
    }
    public static void main(String[] args) {
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
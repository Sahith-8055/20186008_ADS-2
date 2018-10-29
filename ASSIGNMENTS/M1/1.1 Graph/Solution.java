import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {

    /**
     * no of vertices.
     *
     * @return    integer
     */
    int vertices();

    /**
     * no of edges.
     *
     * @return    integer
     */
    int edges();

    /**
     * Adds an edge between two vertices.
     *
     * @param      v     {Integer}
     * @param      w     {Integer}
     */
    void addEdge(int v, int w);

    /**
     * adds two vertices.
     *
     * @param      v     {Integer}
     *
     * @return     {Iterable}
     */
    Iterable<Integer> adj(int v);

    /**
     * Determines if it has edge.
     *
     * @param      v     {Integer}
     * @param      w     {Integer}
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}

/**
 * Class for graph theory.
 */
class GraphADT implements Graph {
    /**
     * vertices.
     */
    private int v;
    /**
     * edges.
     */
    private int e;
    /**
     * array of bag type.
     */
    private Bag<Integer>[] adj;

    /**
     * Constructs the object.
     *
     * @param      v1    The v1
     */
    GraphADT(final int v1) {
        this.v = v1;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * returns number of vertices.
     *
     * @return    no of vertices
     */
    public int vertices() {
        return v;
    }

    /**
     * returns number of edges.
     *
     * @return    no of edges
     */
    public int edges() {
        return e;
    }

    /**
     * Adds an edge.
     *
     * @param      v1    integer
     * @param      w1    integer
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 == w1) {
            return;
        }
        if (!hasEdge(v1, w1)) {
            e++;
        }
        adj[v1].add(w1);
        adj[w1].add(v1);
    }

    /**
     * returns all the values in list.
     *
     * @param      v     integer
     *
     * @return   list.
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }

    /**
     * Determines if it has edge.
     *
     * @param      v     integer
     * @param      w     integer
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int k : adj[v]) {
                if (k == w) {
                    return true;
                }
        }
        return false;
    }

    /**
     * display function of adjacency list.
     *
     * @param      v1         The v1
     * @param      e1         The e1
     * @param      tokens     The tokens
     *
     * @throws     Exception  {Exception}
     */
    public void displayList(final int v1, final int e1,
        final String[] tokens) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            for (int i = 0; i < tokens.length; i++) {
            String str = "";
            str = tokens[i] + ": ";
            for (int k : adj(i)) {
                str = str + tokens[k] + " ";
            }
            System.out.println(str);
            }
        }
    }
    /**
     * display function of adjacency matrix list.
     *
     * @param      v1         The v1
     * @param      e1         The e1
     *
     * @throws     Exception  No edges
     */
    public void displayMatrix(final int v1, final int e1) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertices() + " vertices" + ", "
                + edges() + " edges");
            int[][] disp = new int[v1][v1];
            for (int i = 0; i  < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
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
        //Unused Constructor.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int v = Integer.parseInt(sc.nextLine());
        int e = Integer.parseInt(sc.nextLine());
        String keynames = sc.nextLine();
        String[] tokens = keynames.split(",");
        GraphADT g = new GraphADT(v);
        while (sc.hasNext()) {
            String connect = sc.nextLine();
            String[] connections = connect.split(" ");
            g.addEdge(Integer.parseInt(connections[0]),
                Integer.parseInt(connections[1]));
        }
        switch (input) {
            case "List":
            try {
                g.displayList(v, e, tokens);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                g.displayMatrix(v, e);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            default:
            break;
        }
    }
}

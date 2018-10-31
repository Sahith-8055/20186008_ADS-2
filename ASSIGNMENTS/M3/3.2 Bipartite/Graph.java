/**
 * Class for graph.
 */
public class Graph {
    /**
     * {Vertex}.
     */
    private int vertices;
    /**
     * {Edges}.
     */
    private int edges;
    /**
     * {Declaring a bag array of type integer}.
     */
    private Bag<Integer>[] adj;

    /**
     * Create an empty graph with V vertices.
     */
    public Graph(final int v) {
        if (v < 0) {
            throw new RuntimeException(
                "Number of vertices must be nonnegative");
        }
        this.vertices = v;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

   /**
     * Create a random graph with V vertices and E edges.
     * Expected running time is proportional to V + E.
     */
    public Graph(final int V, final int E) {
        this(V);
        if (E < 0) {
            throw new RuntimeException(
                "Number of edges must be nonnegative");
        }
        for (int i = 0; i < edges; i++) {
            int v = (int) (Math.random() * vertices);
            int w = (int) (Math.random() * vertices);
            addEdge(v, w);
        }
    }

   /**
     * Return the number of vertices in the graph.
     */
    public int vertices() {
        return vertices;
    }

   /**
     * Return the number of edges in the graph.
     */
    public int edges() {
        return edges;
    }

   /**
     * Add the edge v-w to graph.
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }

   /**
     * Return the list of neighbors of vertex v as in Iterable.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
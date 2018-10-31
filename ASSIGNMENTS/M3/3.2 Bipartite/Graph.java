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
     * @param     v     {Vertex}
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
     * Return the number of vertices in the graph.
     * @return      {Integer}
     */
    public int vertices() {
        return vertices;
    }

    /**
     * Return the number of edges in the graph.
     * @return      {Integer}
     */
    public int edges() {
        return edges;
    }

    /**
     * Add the edge v-w to graph.
     * @param      v       {Vertex v}
     * @param       w      {Vertex w}
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Return the list of neighbors of vertex v as in Iterable.
     * @param      v    {Vertex}
     * @return     {Iterable}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
}

public class EdgeWeightedGraph {
    private int vertices;
    private int edges;
    private Bag<Edge>[] adj;

    /**
     * Initializes a random edge-weighted graph.
     *
     * @param  ver the number of vertices
     * @param  e the number of edges
     */
    public EdgeWeightedGraph(final int ver, final int e) {
        for (int i = 0; i < e; i++) {
            int v = StdRandom.uniform(ver);
            int w = StdRandom.uniform(ver);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            Edge ed = new Edge(v, w, weight);
            addEdge(ed);
        }
    }
    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int V() {
        return this.vertices;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int E() {
        return this.edges;
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edges++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v}
     */
    public Iterable<Edge> adj(final int v) {
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     */
    public int degree(final int v) {
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int j = 0; j < vertices; j++) {
            int selfLoops = 0;
            for (Edge e : adj(j)) {
                if (e.other(j) > j) {
                    list.add(e);
                } else if (e.other(j) == j) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }
}

/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * {Vertices}.
     */
    private int vertices;
    /**
     * {Edges}.
     */
    private int edges;
    /**
     * {Bag array of type Edge}.
     */
    private Bag<Edge>[] adj;

    /**
     * Initializes an empty edge-weighted graph with {@code v}
     * vertices and 0 edges.
     * @param  v the number of vertices
     * @throws IllegalArgumentException if {@code v < 0}
     */
    public EdgeWeightedGraph(final int v) {
        this.vertices = v;
        this.edges = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int vertices() {
        return this.vertices;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int edge() {
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

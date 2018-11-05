/**
 * Class for lazy primitive mst.
 */
public class LazyPrimMST {
    /**
     * {total weight of MST}.
     */
    private double weight;
    /**
     * {edges in the MST}.
     */
    private Queue<Edge> mst;
    /**
     * {marked[v] = true iff v on tree}.
     */
    private boolean[] marked;
    /**
     * {edges with one endpoint in tree}.
     *
     */
    private MinPQ<Edge> pq;

    /**
     * Compute a minimum spanning tree (or forest).
     * of an edge-weighted graph.
     * @param g the edge-weighted graph
     */
    LazyPrimMST(final EdgeWeightedGraph g) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                prim(g, v);
            }
        }
    }

    /**
     * {Private Accessor method of prim}.
     *
     * @param      g     {EdgeWeightedGraph}
     * @param      s     {Vertex}
     */
    private void prim(final EdgeWeightedGraph g,
                      final int s) {
        scan(g, s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) {
                scan(g, v);
            }
            if (!marked[w]) {
                scan(g, w);
            }
        }
    }

    /**
     * {add all edges e incident to v onto pq if the.
     * other endpoint has not yet been scanned}
     *
     * @param      g     {EdgeWeightedGraph}
     * @param      v     {Vertex}
     */
    private void scan(final EdgeWeightedGraph g,
                      final int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest)
     *  as an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * @return the sum of the edge weights.
     * in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }
}

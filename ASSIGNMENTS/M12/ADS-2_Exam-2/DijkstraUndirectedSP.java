/**
 * Class for dijkstra undirected sp.
 */
public class DijkstraUndirectedSP {
    /**
     * {distTo[v] = distance  of shortest s->v path}.
     */
    private double[] distTo;
    /**
     * {edgeTo[v] = last edge on shortest s->v path}.
     */
    private Edge[] edgeTo;
    /**
     * {priority queue of vertices}.
     */
    private IndexMinPQ<Double> pq;

    /**
     *
     * @param  g the edge-weighted digraph
     * @param  s the source vertex
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph g, final int s) {
        this.distTo = new double[g.vertices()];
        this.edgeTo = new Edge[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(g.vertices());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v)) {
                relax(e, v);
            }
        }
    }

    /**
     * {relax edge e and update pq if changed}.
     *
     * @param      e     {Edge object}
     * @param      v     {Source vertex}
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     *
     * @param  v the destination vertex.
     * @return the length of a shortest path between the source vertex
     * and the vertex.
     *
     */
    public double distTo(final int v) {
        return distTo[v];
    }

    /**
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path between vertex
     * {@code s} to vertex {@code v};
     * {@code false} otherwise
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * Returns a shortest path between the source vertex {@code s}.
     * and vertex {@code v}.
     * @param  v the destination vertex
     * @return a shortest path between the source vertex {@code s}
     * and vertex {@code v};
     * {@code null} if no such path
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
}

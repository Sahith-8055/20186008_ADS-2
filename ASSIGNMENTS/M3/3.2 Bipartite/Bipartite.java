/**
 * Class for bipartite.
 */
public class Bipartite {
    /**
     * {is the graph bipartite?}.
     */
    private boolean isBipartite;
    /**
     * {color[v] gives vertices on one side of bipartition}.
     */
    private boolean[] color;
    /**
     * {marked[v] = true iff v has been visited in DFS}.
     */
    private boolean[] marked;
    /**
     * {edgeTo[v] = last edge on path to v}.
     */
    private int[] edgeTo;
    /**
     * {odd-length cycle}.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param  graph the graph
     */
    Bipartite(final Graph graph) {
        isBipartite = true;
        color  = new boolean[graph.vertices()];
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.vertices()];

        for (int v = 0; v < graph.vertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    /**
     * {Method for Depth First Search}.
     * Time complexity of this method is O(V + E).
     * @param      graph     {Graph}
     * @param      v     {Source Vertex}
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            // short circuit if odd-length cycle found
            if (cycle != null) {
                return;
            }
            // found uncolored vertex, so recur
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(graph, w);
                // if v-w create an odd-length cycle, find it
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                // don't need this unless you want to include start vertex twice
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    /**
     * Returns true if the graph is bipartite.
     *
     * @return {@code true} if the graph is bipartite; {@code false} otherwise
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * {Method to check whether the vertex is colored (or) not}.
     *
     * @param      v     {Vertex}
     *
     * @return     {Boolean}
     */
    public boolean color(final int v) {
        if (!isBipartite) {
            throw new UnsupportedOperationException("graph is not bipartite");
        }
        return color[v];
    }

    /**
     * Returns an odd-length cycle if the graph is not bipartite, and
     * {@code null} otherwise.
     *
     * @return an odd-length cycle if the graph is not bipartite
     *         (and hence has an odd-length cycle), and {@code null}
     *         otherwise
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }
}

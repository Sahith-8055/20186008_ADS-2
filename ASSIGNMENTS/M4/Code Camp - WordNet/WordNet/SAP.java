/**
 * Class for sap.
 */
public class SAP {
    /**
     * {Declaring a digraph object}.
     */
    private Digraph digraph;
    /**
     * {Id of ancestor}.
     */
    private int ancesId;

    /**
     * Constructs the object.
     *
     * @param      di     {Digraph}
     */
    public SAP(final Digraph di) {
        this.digraph = new Digraph(di);
        this.ancesId = 0;
    }
    /**
     * Gets the graph.
     *
     * @return     The graph.
     */
    public Digraph getGraph() {
        return this.digraph;
    }

    /**
     * {Length of ancestral path}.
     *
     * @param      v     {Iterable}
     * @param      w     {Iterable}
     *
     * @return     {Integer}
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsp;
        bfsp = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsp1;
        bfsp1 = new BreadthFirstDirectedPaths(digraph, w);
        int a = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsp.hasPathTo(i) && bfsp1.hasPathTo(i)) {
                int dist = bfsp.distTo(i) + bfsp1.distTo(i);
                if (a == -1 || dist < a) {
                    a = dist;
                    ancesId = i;
                }
            }
        }
        return a;
    }

    /**
     * {a common ancestor that participates in shortest ancestral path}.
     *
     * @param      v     {Iterable}
     * @param      w     {Iterable}
     *
     * @return     {Integer}
     */
    public int ancestor(final Iterable<Integer> v, final Iterable<Integer> w) {
        return ancesId;
    }
}
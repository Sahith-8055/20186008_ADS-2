public class SAP {
    private Digraph digraph;
    int ancesId;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(final Digraph G) {
        this.digraph = new Digraph(G);
        this.ancesId = 0;
    }
    public Digraph getGraph() {
        return this.digraph;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsp = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsp1 = new BreadthFirstDirectedPaths(digraph, w);
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

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(final Iterable<Integer> v, final Iterable<Integer> w) {
        return ancesId;
    }
}
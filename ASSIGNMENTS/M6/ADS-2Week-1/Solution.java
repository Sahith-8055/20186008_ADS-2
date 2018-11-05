/**
 * Class for page rank.
 */
class PageRank {
    /**
     * {Digraph object}.
     */
    private Digraph digraph;
    /**
     * {doubleArray}.
     */
    private double[] doubleArray;
    /**
     * {newArray}.
     */
    private double[][] newArray;
    /**
     * {Variable of type integer}.
     */
    private int k;

    /**
     * Constructs the object.
     *
     * @param      d     {Digraph}
     */
    PageRank(final Digraph d) {
        this.digraph = d;
        this.k = 0;
        final int x = 1001;
        this.doubleArray = new double[digraph.V()];
        this.newArray = new double[x][digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            doubleArray[i] = (1.0 / digraph.V());
        }
        newArray[0] = doubleArray;
        checkCorner();
        computeRank();
    }
    /**
     * {Method to check the corner cases}.
     */
    public void checkCorner() {
        for (int i = 0; i < digraph.V(); i++) {
            if (digraph.outdegree(i) == 0) {
                for (int j = 0; j < digraph.V(); j++) {
                    if (i != j) {
                        digraph.addEdge(i, j);
                    }
                }
            }
        }
    }
    /**
     * Calculates the rank.
     */
    public void computeRank() {
        int vertex = 0;
        int outdegree = 0;
        double[] pageranking = null;
        double[] pageranker = null;
        for (int k = 1; k <= 1000; k++) {
            pageranking = new double[digraph.V()];
            for (int i = 0; i < digraph.V(); i++) {
                double pagerank = 0.0;
                pageranker = new double[digraph.V()];
                pageranker = newArray[k - 1];
                for (int j : digraph.reverse().adj(i)) {
                    vertex = j;
                    outdegree = digraph.outdegree(vertex);
                    pagerank += (pageranker[vertex] / outdegree);
                }
                pageranking[i] = pagerank;
            }
            newArray[k] = pageranking;
        }
    }
    /**
     * Gets the pr.
     *
     * @param      v     {Vertex}
     *
     * @return     The pr.
     */
    public double getPR(final int v) {
        final int y = 1000;
        doubleArray = newArray[y];
        return doubleArray[v];
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < digraph.V(); i++) {
            str += i + " - " + getPR(i) + "\n";
        }
        return str;
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
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        int vertices = StdIn.readInt();
        Digraph dg = new Digraph(vertices);
        // read the first line of the input to get the number of vertices
        while (vertices + 1 > 0) {
            String[] array = StdIn.readLine().split(" ");
            //int vertex = Integer.parseInt(array[0]);
            for (int i = 1; i < array.length; i++) {
                dg.addEdge(
                    Integer.parseInt(array[0]), Integer.parseInt(array[i]));
            }
            vertices--;
        }
        System.out.println(dg);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        PageRank pg = new PageRank(dg);
        System.out.println(pg);
        // Create page rank object and pass the graph object to the constructor

        // print the page rank object

        // This part is only for the final test case
        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
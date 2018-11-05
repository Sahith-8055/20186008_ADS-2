import java.util.ArrayList;
class PageRank {
    private Digraph digraph;
    private double[] doubleArray;
    private double[][] newArray;
    private int k;
    PageRank(final Digraph d) {
        this.digraph = d;
        this.k = 0;
        this.doubleArray = new double[digraph.V()];
        this.newArray = new double[1001][digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            doubleArray[i] = (1.0 / digraph.V());
        }
        newArray[0] = doubleArray;
        checkCorner();
        computeRank();
    }
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
    public double getPR(final int v) {
        doubleArray = newArray[1000];
        return doubleArray[v];
    }
    public String toString() {
        String str = "";
        for (int i = 0; i < digraph.V(); i++) {
            str += i + " - " + getPR(i) + "\n";
        }
        return str;
    }
}

public class Solution {
    private Solution() {
        //Unused Constructor.
    }
    public static void main(String[] args) {
        int vertices = StdIn.readInt();
        Digraph dg = new Digraph(vertices);
        // read the first line of the input to get the number of vertices
        while (vertices + 1 > 0) {
            String[] array = StdIn.readLine().split(" ");
            //int vertex = Integer.parseInt(array[0]);
            for (int i = 1; i < array.length; i++) {
                dg.addEdge(Integer.parseInt(array[0]), Integer.parseInt(array[i]));
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
import java.util.ArrayList;
class PageRank {
    private Digraph digraph;
    private double[] doubleArray;
    PageRank(final Digraph d) {
        this.digraph = d;
        this.doubleArray = new double[digraph.V()];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = (1.0 / digraph.V());
        }
    }
    public double getPR(final int v) {
        return 0.0;

    }
    public String toString() {
        return null;
    }
}

class WebSearch {
    private PageRank pr;
    private String filename;
    private LinearProbingHashST<String, ArrayList<Integer>> hash;
    //private ArrayList<Integer> list;
    private int count;
    WebSearch(final PageRank p, final String name) {
        this.hash = new LinearProbingHashST<String, ArrayList<Integer>>();
        //this.list = new ArrayList<Integer>();
        In in = new In(name);
        while (!in.isEmpty()) {
            this.count++;
            String[] tokens = in.readLine().split(":");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 0; i < tokens[1].length(); i++) {
                ArrayList<Integer> list1;
                if (hash.contains(tokens[i])) {
                    list1 = hash.get(tokens[i]);
                    list1.add(id);
                } else {
                    list1 = new ArrayList<Integer>();
                    list1.add(id);
                }
                hash.put(tokens[i], list1);
            }
        }
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

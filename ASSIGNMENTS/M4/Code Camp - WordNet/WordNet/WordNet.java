import java.util.Arrays;
public class WordNet {

    // constructor takes the name of the two input files
    public WordNet(final String synsets, final String hypernyms) {
        //String[] arr = readFile(synsets);
        readFile(synsets, hypernyms);

    }

    // returns all WordNet nouns
    // public Iterable<String> nouns() {

    // }

    // // is the word a WordNet noun?
    // public boolean isNoun(final String word) {

    // }

    // // distance between nounA and nounB (defined below)
    // public int distance(final String nounA, final String nounB) {

    // }

    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(final String nounA, final String nounB) {

    // }
    public void readFile(final String s, final String hypernyms) {
        In in = new In("./Files" + "/" + s);
        String[] syn1 = null;
        int id;
        int count = 0;
        while (!in.isEmpty()) {
            //System.out.println(count + "count");
            count++;
            String[] tokens = in.readString().split(",");
            //System.out.println(Arrays.toString(tokens));
            id = Integer.parseInt(tokens[0]);
            syn1 = tokens[1].split(" ");
        }
        Digraph digraphObj = new Digraph(count);
        readHyperNyms(hypernyms, digraphObj);
    }
    public void readHyperNyms(String s, Digraph d) {
        In in = new In("./Files/" + s);
        String[] tokens1 = null;
        while (!in.isEmpty()) {
            tokens1 = in.readString().split(",");
            int a = Integer.parseInt(tokens1[0]);
            int b = Integer.parseInt(tokens1[1]);
            d.addEdge(a, b);
        }
        System.out.println(d);
    }

    // do unit testing of this class

}

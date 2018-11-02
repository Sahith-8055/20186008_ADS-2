import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class WordNet {
    private LinearProbingHashST<String, ArrayList<Integer>> hash;
    private ArrayList<String> synsetsList = new ArrayList<String>();
    private SAP sap;
    private int count;
    private Digraph digraphObj;

    // constructor takes the name of the two input files
    public WordNet(final String synsets, final String hypernyms) {
        hash = new LinearProbingHashST<String, ArrayList<Integer>>();
        this.count = 0;
        readSynsets(synsets, hypernyms);
        sap = new SAP(digraphObj);
    }

    //returns all WordNet nouns
    public Iterable<String> nouns() {
        return hash.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(final String word) {
        return hash.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(final String nounA, final String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        Iterable<Integer> a = hash.get(nounA);
        Iterable<Integer> b = hash.get(nounB);
        return sap.length(a, b);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(final String nounA, final String nounB) {
        Iterable<Integer> a = hash.get(nounA);
        Iterable<Integer> b = hash.get(nounB);
        return synsetsList.get(sap.ancestor(a, b));
    }

    public void readSynsets(final String s, final String hypernyms) {
        In in = new In("./Files" + "/" + s);
        // String[] syn1 = null;
        int id;
        while (!in.isEmpty()) {
            this.count++;
            String[] tokens = in.readLine().split(",");
            id = Integer.parseInt(tokens[0]);
            synsetsList.add(id, tokens[1]);
            String[] syn1 = tokens[1].split(" ");
            for (int i = 0; i < syn1.length; i++) {
                ArrayList<Integer> list;
                // System.out.println(syn1[i]);
                if (hash.contains(syn1[i])) {
                    list = hash.get(syn1[i]);
                    list.add(id);
                } else {
                    list = new ArrayList<Integer>();
                    list.add(id);
                }
                hash.put(syn1[i], list);
            }
        }
        digraphObj = new Digraph(count);
        readHyperNyms(hypernyms, digraphObj, count);
    }
    public void readHyperNyms(final String s, final Digraph d, int count) {
        In in = new In("./Files/" + s);
        String[] tokens1 = null;
        while (!in.isEmpty()) {
            tokens1 = in.readString().split(",");
            for (int i = 1; i < tokens1.length; i++) {
                d.addEdge(Integer.parseInt(tokens1[0]), Integer.parseInt(tokens1[i]));
            }
        }
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (d.outdegree(i) == 0) {
                flag++;
            }
            if (flag > 1) {
                throw new IllegalArgumentException("Multiple roots");
            }
        }
        DirectedCycle dc = new DirectedCycle(d);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        }
    }
    public Digraph getDigraph() {
        return digraphObj;
    }
}
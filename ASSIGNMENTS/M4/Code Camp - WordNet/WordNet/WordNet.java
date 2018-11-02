import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * {Declaring an object for LinearProbingHashing}.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> hash;
    /**
     * {ArrayList declaration}.
     */
    private ArrayList<String> synsetsList;
    /**
     * {Creating a object of SAP.java}.
     */
    private SAP sap;
    /**
     * {Count of vertices}.
     */
    private int count;
    /**
     * {Creating a digraph object}.
     */
    private Digraph digraphObj;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        hash = new LinearProbingHashST<String, ArrayList<Integer>>();
        synsetsList = new ArrayList<String>();
        this.count = 0;
        readSynsets(synsets, hypernyms);
        sap = new SAP(digraphObj);
    }


    /**
     * {returns all WordNet nouns}.
     *
     * @return     {Iterable}
     */
    public Iterable<String> nouns() {
        return hash.keys();
    }

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return hash.contains(word);
    }

    /**
     * {distance between nounA and nounB (defined below)}.
     *
     * @param      nounA  The nouna
     * @param      nounB  The nounb
     *
     * @return     {Integer}
     */
    public int distance(final String nounA, final String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        Iterable<Integer> a = hash.get(nounA);
        Iterable<Integer> b = hash.get(nounB);
        return sap.length(a, b);
    }


    /**
     * {Shortest Ancestral Path}.
     *
     * @param      nounA  The nouna
     * @param      nounB  The nounb
     *
     * @return     {String}
     */
    public String sap(final String nounA, final String nounB) {
        Iterable<Integer> a = hash.get(nounA);
        Iterable<Integer> b = hash.get(nounB);
        return synsetsList.get(sap.ancestor(a, b));
    }

    /**
     * Reads synsets.
     *
     * @param      s          {String}
     * @param      hypernyms  The hypernyms
     */
    public void readSynsets(final String s, final String hypernyms) {
        In in = new In("./Files" + "/" + s);
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
    /**
     * Reads hyper nyms.
     *
     * @param      s      {String}
     * @param      d      {Digraph}
     * @param      count  The count of vertices
     */
    public void readHyperNyms(final String s, final Digraph d, final int count) {
        int count1 = count;
        In in = new In("./Files/" + s);
        String[] tokens1 = null;
        while (!in.isEmpty()) {
            tokens1 = in.readString().split(",");
            for (int i = 1; i < tokens1.length; i++) {
                d.addEdge(
                    Integer.parseInt(tokens1[0]),
                    Integer.parseInt(tokens1[i]));
            }
        }
        int flag = 0;
        for (int i = 0; i < count1; i++) {
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
    /**
     * Gets the digraph.
     *
     * @return     The digraph.
     */
    public Digraph getDigraph() {
        return digraphObj;
    }
}
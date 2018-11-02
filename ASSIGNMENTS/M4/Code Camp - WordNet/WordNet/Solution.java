/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // Unused Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsetsFileName = StdIn.readLine();
        String hypernymnsFileName = StdIn.readLine();
        String token = StdIn.readLine();
        try {
            WordNet wordNet = new WordNet(
                synsetsFileName, hypernymnsFileName);
            switch (token) {
            case "Graph":
                System.out.println(wordNet.getDigraph());
                break;
            case "Queries":
                while (StdIn.hasNextLine()) {
                    String[] querys = StdIn.readLine().split(" ");
                    if (querys[0].equals("null") || querys[1].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    } else {
                        System.out.println(
                            "distance = " + wordNet.distance(querys[0], querys[1]) +
                            ", ancestor = " + wordNet.sap(querys[0], querys[1]));
                    }
                }
                break;
            default:
                break;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
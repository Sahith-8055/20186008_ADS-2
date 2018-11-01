public class Solution {
    public Solution() {
        // Constructor.
    }
    public static void main(String[] args) {
        String synsetsFileName = StdIn.readString();
        String hypernymnsFileName = StdIn.readString();
        String token = StdIn.readString();
        switch (token) {
        case "Graph":
            WordNet wordNet = new WordNet(synsetsFileName, hypernymnsFileName);
            break;
        case "Queries":
            String[] a = StdIn.readString().split(" ");
            if (a[0].equals("null") || a[1].equals("null")) {
                System.out.println("IllegalArgumentException");
            }
            break;
        default:
            break;
        }
    }
}
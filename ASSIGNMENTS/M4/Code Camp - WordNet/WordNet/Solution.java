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
            break;
        default:
            break;
        }
    }
}
import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * {TrieST object which consists of values as Integers}.
     */
    private TrieST<Integer> dictionaryTrie;
    /**
     * {Set object whose type is String}.
     */
    private Set<String> validWords;
    /**
     * {Marked array which is of type boolean}.
     */
    private boolean[][] marked1;

    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    BoggleSolver(final String[] dictionary) {
        this.dictionaryTrie = new TrieST<Integer>();
        this.validWords = new TreeSet<String>();
        final int x = 11;
        int[] points = {0, 0, 0, 1, 1, 2, (2 + 1), (2 + 2 + 1), x};
        for (String word : dictionary) {
            if (word.length() >= 2 + 2 + 2 + 2) {
                dictionaryTrie.put(word, points[points.length - 1]);
            } else {
                dictionaryTrie.put(word, points[word.length()]);
            }
        }
    }

    /**
     * Gets all valid words which are iterable.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        marked1 = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String sb = appendCharacter("", board.getLetter(i, j));
                dfs(board, marked1, i, j, sb);
            }
        }
        return validWords;
    }
    /**
     * Appends a character to the string.
     *
     * @param      sb    {String}
     * @param      c     {Character}
     *
     * @return     {String}
     */
    private String appendCharacter(final String sb, final char c) {
        String str = sb;
        if (c == 'Q') {
            str += "QU";
            return str;
        } else {
            str += c;
            return str;
        }
    }
    /**
     * Determines if valid word.
     *
     * @param      word  The word
     *
     * @return     True if valid word, False otherwise.
     */
    private boolean isValidWord(final String word) {
        if (word.length() <= 2) {
            return false;
        }
        return dictionaryTrie.contains(word);
    }

    /**
     * {Method of traversal and finding the valid word}.
     *
     * @param      board   The board
     * @param      marked  The marked
     * @param      rows    The rows
     * @param      cols    The cols
     * @param      word    The word
     */
    public void dfs(final BoggleBoard board, final boolean[][] marked,
                    final int rows, final int cols,
                    final String word) {
        if (!dictionaryTrie.hasPrefix(word)) {
            return;
        }
        if (isValidWord(word)) {
            validWords.add(word);
        }
        marked[rows][cols] = true;
        for (int i = rows - 1; i <= rows + 1; i++) {
            for (int j = cols - 1; j <= cols + 1; j++) {
                if (isValidRowColumn(i, j, board) && !marked[i][j]) {
                    String sequence = appendCharacter(
                                          word, board.getLetter(i, j));
                    dfs(board, marked, i, j, sequence);
                }
            }
        }
        marked[rows][cols] = false;
    }

    /**
     * Determines if valid row column.
     *
     * @param      row    The row
     * @param      col    The col
     * @param      board  The board
     *
     * @return     True if valid row column, False otherwise.
     */
    private boolean isValidRowColumn(final int row, final int col,
                                     final BoggleBoard board) {
        return (row >= 0 && col >= 0
                && row < board.rows() && col < board.cols());
    }


    /**
     * {Returns the score of the given word.
     * if it is in the dictionary, zero otherwise.}
     *
     * @param      word  The word
     *
     * @return     {Integer}
     */
    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (dictionaryTrie.contains(word)) {
            return dictionaryTrie.get(word);
        }
        return 0;
    }
}
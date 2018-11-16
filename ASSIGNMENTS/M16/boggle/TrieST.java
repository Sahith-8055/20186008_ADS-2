/**
 * Class for trie st.
 *
 * @param      <Value>  The value
 */
public class TrieST<Value> {
    /**
     * {extended ASCII}.
     */
    private static final int RADIX = 26;
    /**
     * {root of trie}.
     */
    private Node root;
    /**
     * {number of keys in trie}.
     */
    private int n;

    /**
     * Class for node.
     */
    private static class Node {
        /**
         * {Value}.
         */
        private Object val;
        /**
         * {next of type node}.
         */
        private Node[] next = new Node[RADIX];
    }

    /**
      * Initializes an empty string symbol table.
      */
    public TrieST() {
        //Unused Constructor.
    }


    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to get() is null");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * {Private method for get}.
     *
     * @param      x     {Node}
     * @param      key   The key
     * @param      d     {Integer}
     *
     * @return     {Node}
     */
    private Node get(final Node x, final String key, final int d) {
        final int y = 65;
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - y], key, d + 1);
    }

    /**
     * Determines if it has prefix.
     *
     * @param      str   The string
     *
     * @return     True if has prefix, False otherwise.
     */
    public boolean hasPrefix(final String str) {
        Node node = get(root, str, 0);
        return node != null;
    }

    /**
     * Inserts the key-value pair into the symbol table.
     * with the new value if the key is already in the symbol table.
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(final String key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException(
                "first argument to put() is null");
        }
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }

    /**
     * {Private method for put}.
     *
     * @param      x     {Node}
     * @param      key   The key
     * @param      val   The value
     * @param      d     {Integer}
     *
     * @return     {Node}
     */
    private Node put(final Node x, final String key,
                     final Value val, final int d) {
        Node x1 = x;
        final int z = 65;
        if (x1 == null) {
            x1 = new Node();
        }
        if (d == key.length()) {
            if (x1.val == null) {
                n++;
            }
            x1.val = val;
            return x1;
        }
        char c = key.charAt(d);
        x1.next[c - z] = put(x1.next[c - z], key, val, d + 1);
        return x1;
    }

    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to delete() is null");
        }
        root = delete(root, key, 0);
    }

    /**
     * {Private method for delete}.
     *
     * @param      x     {Node}
     * @param      key   The key
     * @param      d     {Integer}
     *
     * @return     {Node}
     */
    private Node delete(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                n--;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < RADIX; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}

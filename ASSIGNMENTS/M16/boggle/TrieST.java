/**
 * Class for trie st.
 *
 * @param      <Value>  The value
 */
public class TrieST<Value> {
    /**
     * {extended ASCII}.
     */
    private static final int R = 26;
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
        private Node[] next = new Node[R];
    }

    /**
      * Initializes an empty string symbol table.
      */
    public TrieST() {
    }


    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
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
     *     {@code false} otherwise
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
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - 65], key, d + 1);
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
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
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
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                n++;
            }
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c - 65] = put(x.next[c - 65], key, val, d + 1);
        return x;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
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
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}
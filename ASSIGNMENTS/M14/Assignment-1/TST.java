/**
 * Class for tst.
 *
 * @param      <Value>  The value
 */
public class TST<Value> {
    /**
     * {size}.
     */
    private int n;
    /**
     * {root of TST}.
     */
    private Node<Value> root;

    /**
     * Class for node.
     *
     * @param      <Value>  The value
     */
    private class Node<Value> {
        /**
         * {character}.
         */
        private char c;
        /**
         * {left, middle, and right subtries}.
         */
        private Node<Value> left, mid, right;
        /**
         * {value associated with string}.
         */
        private Value val;
    }

    /**
     * Initializes an empty string symbol table.
     */
    TST() {
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     */
    public boolean contains(final String key) {
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key
     * is in the symbol table
     * and {@code null} if the key is not in the symbol table
     */
    public Value get(final String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }


    /**
     * {return subtrie corresponding to given key}.
     *
     * @param      x     {Node}
     * @param      key   The key
     * @param      d     {Integer}
     *
     * @return     {Node}
     */
    private Node<Value> get(final Node<Value> x,
                            final String key, final int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left,  key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    /**
     * Inserts the key-value pair into the symbol table.
     * overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null},
     * this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     */
    public void put(final String key, final Value val) {
        if (!contains(key)) {
            n++;
        }
        root = put(root, key, val, 0);
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
    private Node<Value> put(final Node<Value> x, final String key,
                            final Value val, final int d) {
        char c = key.charAt(d);
        Node<Value> x1 = x;
        if (x1 == null) {
            x1 = new Node<Value>();
            x1.c = c;
        }
        if (c < x1.c) {
            x1.left  = put(x1.left, key, val, d);
        } else if (c > x1.c) {
            x1.right = put(x1.right, key, val, d);
        } else if (d < key.length() - 1) {
            x1.mid = put(x1.mid, key, val, d + 1);
        } else {
            x1.val = val;
        }
        return x1;
    }

    /**
     * Returns the string in the symbol table that is.
     * the longest prefix of {@code query},
     * or {@code null}, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is
     * the longest prefix of {@code query},
     * or {@code null} if no such string
     */
    public String longestPrefixOf(final String query) {
        if (query.length() == 0) {
            return null;
        }
        int length = 0;
        Node<Value> x = root;
        int i = 0;
        while (x != null && i < query.length()) {
            char c = query.charAt(i);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                i++;
                if (x.val != null) {
                    length = i;
                }
                x = x.mid;
            }
        }
        return query.substring(0, length);
    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     * as an iterable
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> queue = new Queue<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return queue;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }


    /**
     * {all keys in subtrie rooted at x with given prefix}.
     *
     * @param      x       {Node}
     * @param      prefix  The prefix
     * @param      queue   The queue
     */
    private void collect(final Node<Value> x,
                         final StringBuilder prefix,
                         final Queue<String> queue) {
        if (x == null) {
            return;
        }
        collect(x.left,  prefix, queue);
        if (x.val != null) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid, prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }


    /**
     * Returns all of the keys in the symbol table that match {@code pattern},
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match {@code pattern},
     * as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    /**
     * {Method to collect}.
     *
     * @param      x        {Node}
     * @param      prefix   The prefix
     * @param      i        {Integer}
     * @param      pattern  The pattern
     * @param      queue    The queue
     */
    private void collect(final Node<Value> x, final StringBuilder prefix,
                         final int i, final String pattern,
                         final Queue<String> queue) {
        if (x == null) {
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) {
            collect(x.left, prefix, i, pattern, queue);
        }
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) {
                queue.enqueue(prefix.toString() + x.c);
            }
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) {
            collect(x.right, prefix, i, pattern, queue);
        }
    }
}

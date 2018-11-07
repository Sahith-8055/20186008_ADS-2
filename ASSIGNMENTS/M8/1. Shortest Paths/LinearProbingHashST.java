/**
 * Class for linear probing hash symbol table.
 *
 * @param      <Key>    The key
 * @param      <Value>  The value
 */
public class LinearProbingHashST<Key, Value> {
    /**
     * {number of key-value pairs in the symbol table}.
     */
    private int n;
    /**
     * {size of linear probing table}.
     */
    private int m;
    /**
     * {the keys}.
     */
    private Key[] keys;
    /**
     * {the values}.
     */
    private Value[] vals;

    /**
     * {Initial Capacity}.
     */
    private static final int INIT_CAPACITY = 4;

    /**
     * Constructs the object.
     */
    LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param capacity the initial capacity
     */
    LinearProbingHashST(final int capacity) {
        this.m = capacity;
        this.n = 0;
        this.keys = (Key[])   new Object[m];
        this.vals = (Value[]) new Object[m];
    }

    /**
     * @return the number of key-value pairs.
     */
    public int size() {
        return n;
    }

    /**
     * @return {@code true} if this symbol table is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param  key the key.
     * @return {@code true} if this symbol table contains {@code key};
     * {@code false} otherwise
     */
    public boolean contains(final Key key) {
        return get(key) != null;
    }


    /**
     * {hash function for keys.
     * returns value between 0 and M-1}.
     *
     * @param      key   The key
     *
     * @return     {Integer}
     */
    private int hash(final Key key) {
        final int y = 0x7fffffff;
        return (key.hashCode() & y) % m;
    }

    /**
     * {resizes the hash table to the given capacity}.
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        LinearProbingHashST<Key, Value> temp;
        temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    /**
     * Inserts the specified key-value pair into the symbol table.
     * overwriting the old value with the new value if the
     * symbol table already contains the specified key.
     * Deletes the specified key (and its associated value)
     * from this symbol table
     * if the specified value is {@code null}.
     * @param  key the key
     * @param  val the value
     */
    public void put(final Key key, final Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        // double table size if 50% full
        if (n >= m / 2) {
            resize(2 * m);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with {@code key};
     * {@code null} if no such value
     */
    public Value get(final Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * Removes the specified key and its.
     * associated value from symbol table
     * @param  key the key
     */
    public void delete(final Key key) {
        if (!contains(key)) {
            return;
        }
        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        // delete key and associated value
        keys[i] = null;
        vals[i] = null;
        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;
        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m / (2 + 2 + 2 + 2)) {
            resize(m / 2);
        }
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }
}

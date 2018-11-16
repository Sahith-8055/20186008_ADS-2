import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * {number of elements in bag}.
     */
    private int n;
    /**
     * {beginning of bag}.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * {Item}.
         */
        private Item item;
        /**
         * {next of type node}.
         */
        private Node next;
    }

    /**
      * Create an empty stack.
      */
    Bag() {
        this.first = null;
        this.n = 0;
    }

    /**
      * Is the BAG empty?
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**
      * Return the number of items in the bag.
      */
    public int size() {
        return n;
    }

    /**
      * Add the item to the bag.
      */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    /**
      * Return an iterator that iterates over the items in the bag.
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * {Current node}.
         */
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * {Method for next}.
         *
         * @return     {Item}
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
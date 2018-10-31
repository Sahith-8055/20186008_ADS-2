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
         * {Next of type node}.
         */
        private Node next;
    }

    /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Is the BAG empty?
     * @return    {Boolean}
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the bag.
     * @return     {Integer}.
     */
    public int size() {
        return n;
    }

    /**
     * {Add the item to the bag}.
     *
     * @param      item  The item
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
     * @return     {Iterator}.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * {Current Node}.
         */
        private Node current = first;

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * {Method to remove}.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * {Next Method}.
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

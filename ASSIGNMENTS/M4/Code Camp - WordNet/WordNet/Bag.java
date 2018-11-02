import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> {
    /**
     * {beginning of bag}.
     */
    private Node<Item> first;
    /**
     * {number of elements in bag}.
     */
    private int n;

    /**
     * Class for node.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**
         * {Item}.
         */
        private Item item;
        /**
         * {Next of type node}.
         */
        private Node<Item> next;
    }

    /**
     * Initializes an empty bag.
     */
    Bag() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this bag is empty.
     *
     * @return {@code true} if this bag is empty
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
}

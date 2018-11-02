import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List of Stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * {top of stack}.
     */
    private Node<Item> first;
    /**
     * {size of the stack}.
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
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "Stack underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }


    /**
     * Returns (but does not remove) the item most
     * recently added to this stack.
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                "Stack underflow");
        }
        return first.item;
    }

    /**
     * Returns an iterator to this stack that iterates
     * through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates
     * through the items in LIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    /**
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**
         * {Current Node}.
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         *
         * @param      first1  The first1
         */
        ListIterator(final Node<Item> first1) {
            current = first1;
        }

        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * {Remove}.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * {Next}.
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

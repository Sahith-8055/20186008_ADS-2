import java.util.Iterator;

/**
 * List of Stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * {first item}.
     */
    private Node<Item> first;
    /**
     * {Size of the stack}.
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
         * {next of type node}.
         */
        private Node<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    Stack() {
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
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }


    /**
     * Returns (but does not remove) the item most recently
     * added to this stack.
     *
     * @return the item most recently added to this stack
     */
    public Item peek() {
        return first.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
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
         * {Current node}.
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         *
         * @param      first1  The first
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
         * {Remove method}.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * {Next method}.
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

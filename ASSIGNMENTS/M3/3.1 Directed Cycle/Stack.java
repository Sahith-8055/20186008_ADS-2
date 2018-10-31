import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List of Stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**
     * {size of the stack}.
     */
    private int n;
    /**
     * {top of stack}.
     */
    private Node first;

    /**
     * helper linked list class.
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
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * Is the stack empty?
     * @return     {Boolean}.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the stack.
     * @return     {Integer}.
     */
    public int size() {
        return n;
    }

    /**
     * {Method to push into a stack}.
     *
     * @param      item  The item
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Delete and return the item most recently added to the stack.
     * Throw an exception if no such item exists because the stack is empty.
     * @return      {Item}
     */
    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }


    /**
     * Return the item most recently added to the stack.
     * Throw an exception if no such item exists because the stack is empty.
     * @return      {Item}
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return first.item;
    }

    /**
     * Return string representation.
     * @return     {String}.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }


    /**
     * Return an iterator to the stack.
     * that iterates through the items in LIFO order.
     * @return     {Iterator}.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Class for list iterator.
     * an iterator, doesn't implement remove() since it's optional.
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



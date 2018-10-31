/**
 * List of Stack.
 *
 * @param      <Item>  The item
 */
public class Stack<Item> {
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
     *
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     *
     */
    public Item peek() {
        return first.item;
    }
}

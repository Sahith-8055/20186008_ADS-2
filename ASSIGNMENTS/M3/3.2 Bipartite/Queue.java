/**
 * List of Queue.
 *
 * @param      <Item>  The item
 */
public class Queue<Item> {
    /**
     * {number of elements on queue}.
     */
    private int n;
    /**
     * {beginning of queue}.
     */
    private Node first;
    /**
     * {end of queue}.
     */
    private Node last;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * {Item}.
         */
        private Item item;
        /**
         * {Node of type next}.
         */
        private Node next;
    }

    /**
     * Create an empty queue.
     */
    Queue() {
        first = null;
        last  = null;
    }

    /**
     * Is the queue empty?
     * @return     {Boolean}
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the queue.
     * @return     {Integer}
     */
    public int size() {
        return n;
    }

    /**
     * Return the item least recently added to the queue.
     * Throw an exception if the queue is empty.
     * @return     {Item}
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.item;
    }


    /**
     * {Add the item to the queue}.
     *
     * @param      item  The item
     */
    public void enqueue(final Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        n++;
    }

    /**
     * Remove and return the item on the queue least recently added.
     * Throw an exception if the queue is empty.
     * @return     {Item}
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException(
                "Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        // to avoid loitering
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
}



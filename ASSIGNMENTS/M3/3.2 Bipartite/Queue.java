import java.util.NoSuchElementException;

public class Queue<Item> {
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

   /**
     * Create an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
    }

   /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the queue.
     */
    public int size() {
        return N;
    }

    /**
     * Return the item least recently added to the queue.
     * Throw an exception if the queue is empty.
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.item;
    }

    /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        N++;
    }

    /**
     * Remove and return the item on the queue least recently added.
     * Throw an exception if the queue is empty.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException(
                "Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        N--;
        // to avoid loitering
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
}

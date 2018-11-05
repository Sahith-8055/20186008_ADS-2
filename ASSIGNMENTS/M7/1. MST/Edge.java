/**
 * Class for edge.
 */
public class Edge implements Comparable<Edge> {
    /**
     * {Vertex v}.
     */
    private final int v;
    /**
     * {Vertex w}.
     */
    private final int w;
    /**
     * {Weight}.
     */
    private final double weight;

    /**
     * Initializes an edge between vertices
     * {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     */
    Edge(final int v,
        final int w, final double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is.
     * different from the given vertex
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        }
        else if (vertex == w) {
            return v;
        }
        else {
            throw new IllegalArgumentException(
                "Illegal endpoint");
        }
    }

    /**
     * Compares two edges by weight.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer.
     */
    @Override
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
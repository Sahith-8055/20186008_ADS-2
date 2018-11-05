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
     * @param  v1 one vertex
     * @param  w1 the other vertex
     * @param  weight the weight of this edge
     */
    Edge(final int v1,
        final int w1, final double weight1) {
        this.v = v1;
        this.w = w1;
        this.weight = weight1;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return this.weight;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return this.v;
    }

    /**
     * Returns the endpoint of this edge that is.
     * different from the given vertex
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     * endpoints of this edge
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
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
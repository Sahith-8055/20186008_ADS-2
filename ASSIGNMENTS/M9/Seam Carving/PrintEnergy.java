/**
 * Class for print energy.
 */
public final class PrintEnergy {
    /**
     * Constructs the object.
     */
    private PrintEnergy() {

    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Picture picture = new Picture(args[0]);
        StdOut.printf(
            "image is %d pixels wide by %d pixels high.\n",
            picture.width(), picture.height());

        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++) {
                StdOut.printf("%9.0f ", sc.energy(col, row));
            }
            StdOut.println();
        }
    }
}


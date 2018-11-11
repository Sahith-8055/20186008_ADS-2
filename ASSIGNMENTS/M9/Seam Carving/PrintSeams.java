/**
 * Class for print seams.
 */
public final class PrintSeams {
    /**
     * {Horizontal}.
     */
    private static final boolean HORIZONTAL = true;
    /**
     * {Vertical}.
     */
    private static final boolean VERTICAL = false;

    /**
     * Constructs the object.
     */
    private PrintSeams() {

    }

    /**
     * {Method to Print the Seam}.
     *
     * @param      carver     The carver
     * @param      seam       The seam
     * @param      direction  The direction
     */
    private static void printSeam(final SeamCarver carver,
                                  final int[] seam, final boolean direction) {
        double totalSeamEnergy = 0.0;

        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction == HORIZONTAL && row == seam[col]) ||
                        (direction == VERTICAL   && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                StdOut.printf("%7.2f%s ", energy, marker);
            }
            StdOut.println();
        }
        // StdOut.println();
        StdOut.printf("Total energy = %f\n", totalSeamEnergy);
        StdOut.println();
        StdOut.println();
    }

    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        Picture picture = new Picture(args[0]);
        StdOut.printf("%s (%d-by-%d image)\n", args[0],
                      picture.width(), picture.height());
        StdOut.println();
        StdOut.println("The table gives the dual-gradient energies of each pixel.");
        StdOut.println(
            "The asterisks denote a minimum energy vertical or horizontal seam.");
        StdOut.println();

        SeamCarver carver = new SeamCarver(picture);

        StdOut.printf("Vertical seam: { ");
        int[] verticalSeam = carver.findVerticalSeam();
        for (int x : verticalSeam) {
            StdOut.print(x + " ");
        }
        StdOut.println("}");
        printSeam(carver, verticalSeam, VERTICAL);

        StdOut.printf("Horizontal seam: { ");
        int[] horizontalSeam = carver.findHorizontalSeam();
        for (int y : horizontalSeam) {
            StdOut.print(y + " ");
        }
        StdOut.println("}");
        printSeam(carver, horizontalSeam, HORIZONTAL);
    }
}
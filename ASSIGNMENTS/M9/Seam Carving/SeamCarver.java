import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * {Border Energy Value}.
     */
    private static final double BORDER_VALUE = 1000;
    /**
     * {Picture Object}.
     */
    private Picture pic;

    /**
     * Constructs the object.
     *
     * @param      picture  The picture
     */
    SeamCarver(final Picture picture) {
        if (picture == null) {
            throw new java.lang.IllegalArgumentException("picture is null");
        }
        this.pic = picture;
    }


    /**
     * {current picture}.
     *
     * @return     {Picture}
     */
    public Picture picture() {
        return this.pic;
    }

    /**
     * {width of current picture}.
     *
     * @return     {Integer}
     */
    public int width() {
        return this.pic.width();
    }

    /**
     * {height of current picture}.
     *
     * @return     {Integer}
     */
    public int height() {
        return this.pic.height();
    }


    /**
     * {energy of pixel at column x and row y}.
     *
     * @param      x     {Column}
     * @param      y     {Row}
     *
     * @return     {Double}
     */
    public  double energy(final int x, final int y) {
        int w = width() - 1, h = height() - 1;
        if (x < 0 || x > w || y < 0 || y > h) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        if (x == 0 || x == w ||  y == 0 || y == h) {
            return BORDER_VALUE;
        }
        return internalEnergy(x, y);
    }


    /**
     * {energy of pixel at column x and row y not on border}.
     *
     * @param      x     {Column}
     * @param      y     {Row}
     *
     * @return     {Double}
     */
    private double internalEnergy(final int x, final int y) {
        Color left = this.pic.get(x - 1, y);
        Color right = this.pic.get(x + 1, y);
        Color up = this.pic.get(x, y - 1);
        Color down = this.pic.get(x, y + 1);
        return Math.sqrt(gradient(left, right) + gradient(up, down));
    }

    /**
     * {Method to find the gradient}.
     *
     * @param      one   One
     * @param      two   Two
     *
     * @return     {Double}
     */
    private double gradient(final Color one, final Color two) {
        double red = one.getRed() - two.getRed();
        double green = one.getGreen() - two.getGreen();
        double blue = one.getBlue() - two.getBlue();
        return red * red + green * green + blue * blue;
    }

    /**
     * Gets the energy matrix.
     *
     * @return     The energy matrix.
     */
    private double[][] getEnergyMatrix() {
        double[][] energies = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energies[i][j] = energy(j, i);
            }
        }
        return energies;
    }


    /**
     * {pass through an array and mark the.
     * shorthest distance from top to entry}
     *
     * @param      array  The array
     */
    private void topologicalSort(final double[][] array) {
        int h = array.length;
        int w = array[0].length;
        for (int row = 1; row < h; row++) {
            for (int col = 0; col < w; col++) {
                double temp = array[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, array[row - 1][col - 1]);
                }

                if (col != (w - 1)) {
                    min = Math.min(min, array[row - 1][col + 1]);
                } else {
                    min = min;
                }
                array[row][col] += min;
            }
        }

    }
    /**
     * {Method to transpose the grid}.
     *
     * @param      array  The array
     *
     * @return     {2-D Double Array}
     */
    private double[][] transposeGrid(final double[][] array) {
        int h = array.length;
        int w = array[0].length;
        double[][] tempArray = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                tempArray[col][row] = array[row][col];
            }
        }
        return tempArray;
    }

    /**
     * {Method to find the minimum vertical path}.
     *
     * @param      array  The array
     *
     * @return     {1-D Integer array}
     */
    private int[] minVerticalPath(final double[][] array) {
        int h = array.length, w = array[0].length;
        int[] path = new int[h];

        topologicalSort(array);

        path[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (array[h - 1][i] < array[h - 1][path[h - 1]]) {
                path[h - 1] = i;
            }
        }
        for (int row = h - 2; row >= 0; row--) {
            int col = path[row + 1];
            path[row] = col;
            if (col > 0 && array[row][col - 1] < array[row][path[row]]) {
                path[row] = col - 1;
            }
            if (col < (w - 2) && array[row][col + 1] < array[row][path[row]]) {
                path[row] = col + 1;
            }
        }
        return path;
    }

    /**
     * {sequence of indices for horizontal seam}.
     *
     * @return     {1-D Integer array}
     */
    public int[] findHorizontalSeam() {
        double[][] transposeEnergies = transposeGrid(getEnergyMatrix());
        return minVerticalPath(transposeEnergies);
    }

    /**
     * {sequence of indices for vertical seam}.
     *
     * @return     {1-D Integer array}
     */
    public int[] findVerticalSeam() {
        double[][] normalEnergies = getEnergyMatrix();
        return minVerticalPath(normalEnergies);
    }

    /**
     * Removes a horizontal seam.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        if (height() <= 1 || !isValid(seam, width(), height() - 1)) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        Picture picture1 = new Picture(width(), height() - 1);
        for (int w = 0; w < width(); w++) {
            for (int h = 0; h < seam[w]; h++) {
                picture1.set(w, h, this.pic.get(w, h));
            }

            for (int h = seam[w] + 1; h < height(); h++) {
                picture1.set(w, h - 1, this.pic.get(w, h));
            }
        }
        this.pic = picture1;
    }


    /**
     * Removes a vertical seam.
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {
        if (width() <= 1 || !isValid(seam, height(), width())) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        Picture pic = new Picture(width() - 1, height());
        for (int h = 0; h < height(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                pic.set(w, h, this.pic.get(w, h));
            }
            for (int w = seam[h] + 1; w < width(); w++) {
                pic.set(w - 1, h, this.pic.get(w, h));
            }
        }
        this.pic = pic;
    }

    /**
     * Determines if valid.
     *
     * @param      a      {Integer array}
     * @param      len    The length
     * @param      range  The range
     *
     * @return     True if valid, False otherwise.
     */
    private boolean isValid(final int[] a,
                            final int len, final int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1)
                    || a[i] > Math.min(range, a[i - 1] + 1)) {
                return false;
            }
        }
        return true;
    }
}
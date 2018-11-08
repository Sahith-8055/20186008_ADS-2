import java.awt.Color;
public class SeamCarver {
    // create a seam carver object based on the given picture
    private final int BORDER_VALUE = 1000;
    private Picture pic;
    SeamCarver(final Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.pic = picture;
    }
    // current picture
    public Picture picture() {
        return this.pic;
    }
    // width of current picture
    public int width() {
        return this.pic.width();
    }

    // height of current picture
    public int height() {
        return this.pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(final int x, final int y) {
        if(x == 0 || x == this.pic.width() - 1 || y == 0 || y == this.pic.height() - 1) {
            return BORDER_VALUE;
        }
        return Math.sqrt(gradX(x, y) + gradY(x, y));
    }
    private double gradX(int x, int y) {
        Color left = this.pic.get(x - 1, y);
        Color right = this.pic.get(x  + 1, y);
        int redDiff = right.getRed() - left.getRed();
        int blueDiff = right.getBlue() - left.getBlue();
        int greenDiff = right.getGreen() - left.getGreen();
        return (redDiff * redDiff) + (greenDiff * greenDiff) + (blueDiff * blueDiff);
    }
    private double gradY(int x, int y) {
        Color bottom = this.pic.get(x, y + 1);
        Color top = this.pic.get(x, y - 1);
        int redDiff = bottom.getRed() - top.getRed();
        int blueDiff = bottom.getBlue() - top.getBlue();
        int greenDiff = bottom.getGreen() - top.getGreen();
        return (redDiff * redDiff) + (greenDiff * greenDiff) + (blueDiff * blueDiff);
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}
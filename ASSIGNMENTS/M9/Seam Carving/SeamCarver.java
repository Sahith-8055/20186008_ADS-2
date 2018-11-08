
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
		double a = gradX(x, y);
		double b = gradY(x, y);
		return Math.sqrt(a * a + b * b);
	}
	private double gradX(int x, int y) {
		int left = this.pic.getRGB(x - 1, y);
		int right = this.pic.getRGB(x  + 1, y);
		int[] left1 = new int[]{(left>>16) & 0xff, (left>>8) & 0xff, (left) & 0xff};
		int[] right1 = new int[]{(right>>16) & 0xff, (right>>8) & 0xff, (right) & 0xff};
		int redDiff = right1[0] - left1[0];
		int greenDiff = right1[1] - left1[1];
		int blueDiff = right1[2] - left1[2];
		return (redDiff ^ 2) + (greenDiff ^ 2) + (blueDiff ^ 2);
	}
	private double gradY(int x, int y) {
		int bottom = this.pic.getRGB(x, y - 1);
		int top = this.pic.getRGB(x, y + 1);
		int[] top1 = new int[]{(top>>16) & 0xff, (top>>8) & 0xff, (top) & 0xff};
		int[] bottom1 = new int[]{(bottom>>16) & 0xff, (bottom>>8) & 0xff, (bottom) & 0xff};
		int redDiff = top1[0] - bottom1[0];
		int greenDiff = top1[1] - bottom1[1];
		int blueDiff = top1[2] - bottom1[2];
		return (redDiff ^ 2) + (greenDiff ^ 2) + (blueDiff ^ 2);
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
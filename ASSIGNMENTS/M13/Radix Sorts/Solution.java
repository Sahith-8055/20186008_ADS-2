import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {

    /**
     * Constructs the object.
     */
    private Solution() {
        //Unused Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberofLines = Integer.parseInt(scan.nextLine());
        String[] array = new String[numberofLines];
        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextLine();
        }
        LSD l = new LSD();
        l.sort(array, array[0].length());
        l.toString(array);
    }
}

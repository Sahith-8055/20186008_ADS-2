import java.util.LinkedList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {
    private static final int RADIX = 256;
    public static void encode() {
        String input = BinaryStdIn.readString();
        char[] array = input.toCharArray();
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < RADIX; i++) {
            list.add(i);
        }
        for (int j = 0; j < array.length; j++) {
            int index = list.indexOf((int) array[j]);
            BinaryStdOut.write((char) index, 8);
            int x = list.remove(index);
            list.add(0, x);
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        String input1 = BinaryStdIn.readString();
        char[] array1 = input1.toCharArray();
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for (int k = 0; k < RADIX; k++) {
            list1.add(k);
        }
        for (int l = 0; l < array1.length; l++) {
            int y = list1.remove((int) array1[l]);
            list1.add(0, y);
            BinaryStdOut.write((char) y, 8);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }
}

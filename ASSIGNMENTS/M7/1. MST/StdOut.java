import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * {Class for StdOut}.
 */
public final class StdOut {

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String UTF8 = "UTF-8";

    // assume language = English, country = US for consistency with StdIn
    private static final Locale US_LOCALE = new Locale("en", "US");

    // send output here
    private static PrintWriter out;

    // this is called before invoking any methods
    static {
        try {
            out = new PrintWriter(
                new OutputStreamWriter(System.out, UTF8), true);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }

    /**
     * Constructs the object.
     */
    private StdOut() {
        //singleton pattern - can't instantiate.
    }

    /**
     * Close standard output.
     */
    public static void close() {
        out.close();
    }

    /**
     * Terminate the current line by printing the line separator string.
     */
    public static void println() {
        out.println();
    }

    /**
     * Print an object to standard output and then terminate the line.
     */
    public static void println(final Object x) {
        out.println(x);
    }

   /**
     * Print a boolean to standard output and then terminate the line.
     */
    public static void println(boolean x) {
        out.println(x);
    }

   /**
     * Print a char to standard output and then terminate the line.
     */
    public static void println(char x) {
        out.println(x);
    }

   /**
     * Print a double to standard output and then terminate the line.
     */
    public static void println(double x) {
        out.println(x);
    }

   /**
     * Print a float to standard output and then terminate the line.
     */
    public static void println(float x) {
        out.println(x);
    }

   /**
     * Print an int to standard output and then terminate the line.
     */
    public static void println(int x) {
        out.println(x);
    }

   /**
     * Print a long to standard output and then terminate the line.
     */
    public static void println(long x) {
        out.println(x);
    }

   /**
     * Print a short to standard output and then terminate the line.
     */
    public static void println(short x) {
        out.println(x);
    }

   /**
     * Print a byte to standard output and then terminate the line.
     */
    public static void println(byte x) {
        out.println(x);
    }

   /**
     * Flush standard output.
     */
    public static void print() {
        out.flush();
    }

   /**
     * Print an Object to standard output and flush standard output.
     */
    public static void print(final Object x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a boolean to standard output and flush standard output.
     */
    public static void print(boolean x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a char to standard output and flush standard output.
     */
    public static void print(char x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a double to standard output and flush standard output.
     */
    public static void print(double x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a float to standard output and flush standard output.
     */
    public static void print(float x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print an int to standard output and flush standard output.
     */
    public static void print(int x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a long to standard output and flush standard output.
     */
    public static void print(long x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a short to standard output and flush standard output.
     */
    public static void print(short x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a byte to standard output and flush standard output.
     */
    public static void print(byte x) {
        out.print(x);
        out.flush();
    }

   /**
     * Print a formatted string to standard output using the specified
     * format string and arguments, and flush standard output.
     */
    public static void printf(
        final String format, final Object... args) {
        out.printf(US_LOCALE, format, args);
        out.flush();
    }

   /**
     * Print a formatted string to standard output using the specified
     * locale, format string, and arguments, and flush standard output.
     */
    public static void printf(final Locale locale,
        final String format, final Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }
}

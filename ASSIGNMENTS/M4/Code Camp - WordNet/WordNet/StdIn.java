import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * {class of StdIn}.
 */
public final class StdIn {

    /**
     * {assume Unicode UTF-8 encoding}.
     */
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * {assume language = English, country = US for consistency}.
     */
    private static final Locale LOCALE = Locale.US;


    /**
     * {the default token separator}.
     * we maintain the invariant that this value}.
     */
    private static final Pattern WHITESPACE_PATTERN =
        Pattern.compile("\\p{javaWhitespace}+");

    /**
     * {makes whitespace significant}.
     */
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    /**
     * {used to read the entire input}.
     */
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private static Scanner scanner;

    /**
     * Constructs the object.
     */
    private StdIn() {

    }
    /**
     * Returns true if standard input
     * is empty (except possibly for whitespace).
     * Use this method to know whether
     * the next call to {@link #readString()},
     * {@link #readDouble()}, etc will succeed.
     *
     * @return {@code true}
     * if standard input is empty (except possibly
     *         for whitespace); {@code false} otherwise
     */
    public static boolean isEmpty() {
        return !scanner.hasNext();
    }

    /**
     * Returns true if standard input has a next line.
     * Use this method to know whether the
     * next call to {@link #readLine()} will succeed.
     * This method is functionally equivalent to {@link #hasNextChar()}.
     *
     * @return {@code true} if standard input
     * has more input (including whitespace);
     *         {@code false} otherwise
     */
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }


    /**
     * Reads and returns the next line, excluding the line separator if present.
     *
     * @return the next line, excluding the line separator if present;
     *         {@code null} if no such line
     */
    public static String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
    /**
     * {Static Method}.
     */
    static {
        resync();
    }

    /**
     * If StdIn changes, use this to reinitialize the scanner.
     */
    private static void resync() {
        setScanner(
            new Scanner(
                new java.io.BufferedInputStream(
                    System.in), CHARSET_NAME));
    }

    private static void setScanner(Scanner scanner) {
        StdIn.scanner = scanner;
        StdIn.scanner.useLocale(LOCALE);
    }
}

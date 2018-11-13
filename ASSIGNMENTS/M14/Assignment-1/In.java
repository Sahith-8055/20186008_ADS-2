import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * {class of In}.
 */
public final class In {

    /**
     * {assume Unicode UTF-8 encoding}.
     */
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * {assume language = English,country = US}.
     */
    private static final Locale LOCALE = Locale.US;

    /**
     * {the default token separator}.
     */
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile(
                "\\p{javaWhitespace}+");

    /**
     * {makes whitespace characters significant}.
     */
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    /**
     * {used to read the entire input source}.
     */
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    /**
     * {Scanner object}.
     */
    private Scanner scanner;


    /**
     * Initializes an input stream from a filename or web page name.
     * @param  name the filename or web page name
     * @throws IllegalArgumentException if {@code name} is {@code null}
     */
    In(final String name) {
        try {
            // first try to read file from local file system
            File file = new File(name);
            if (file.exists()) {
                // for consistency with StdIn,
                // wrap with BufferedInputStream instead of use
                // file as argument to Scanner
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(
                    new BufferedInputStream(fis), CHARSET_NAME);
                scanner.useLocale(LOCALE);
                return;
            }
            // next try for files included in jar
            URL url = getClass().getResource(name);
            // try this as well
            if (url == null) {
                url = getClass().getClassLoader().getResource(name);
            }
            // or URL from web
            if (url == null) {
                url = new URL(name);
            }

            URLConnection site = url.openConnection();

            InputStream is = site.getInputStream();
            scanner = new Scanner(
                new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(
                "Could not open " + name, ioe);
        }
    }

    /**
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     */
    public String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }

    /**
     * Reads all remaining tokens from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining tokens in this input stream, as an array of strings
     */
    public String[] readAllStrings() {
        // we could use readAll.trim().split(), but that's not consistent
        // since trim() uses characters 0x00..0x20 as whitespace
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            decapitokens[i] = tokens[i + 1];
        }
        return decapitokens;
    }
}

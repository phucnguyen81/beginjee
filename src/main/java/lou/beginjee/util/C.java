package lou.beginjee.util;

/**
 * Consolidate constants and their usage in the application.
 */
public interface C {

    /** Name of the main persistence unit */
    public static final String PU = "mainPU";

    /** JNDI of the main data */
    public static final String DS_JNDI = "java:global/jdbc/mainDS";

    /** Name of the main driver */
    public static final String DS_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    /** URL of the main database */
    public static final String DS_URL = "jdbc:derby:memory:mainDB";

    /** Name of packaged application */
    public static final String APP_NAME = "beginjee";

    /** The starting port to try open */
    public static final int PORT_START = 8180;

    /** How many ports to try for */
    public static final int PORT_TRIES = 1000;

}

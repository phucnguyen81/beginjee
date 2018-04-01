package lou.beginjee.util;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Base class for integration tests.
 */
public class BaseIT {

    protected static int HTTP_PORT = 0;

    private static GlassFishRuntime runtime;

    private static GlassFish gf;

    @BeforeClass
    public static void startServer() throws Exception {
        if (HTTP_PORT <= 0) HTTP_PORT = Util.findFreePort();

        /*
         * for details on using embedded glassfish API see Embedded Glassfish
         * Guide https://docs.oracle.com/cd/E18930_01/html/821-2424/gjldt.html#
         */
        runtime = GlassFishRuntime.bootstrap();
        GlassFishProperties prop = new GlassFishProperties();
        prop.setPort("http-listener", HTTP_PORT);
        gf = runtime.newGlassFish(prop);
        gf.start();

        Path warFile = Paths.get("target", C.APP_NAME + ".war").toAbsolutePath();
        assertTrue("War file not found at " + warFile, Files.exists(warFile));

        String result = gf.getDeployer().deploy(warFile.toFile());
        if (result == null)
            throw new IllegalStateException("Deployment failed");
    }

    @AfterClass
    public static void stopServer() throws Exception {
        gf.stop();
        gf.dispose();
        runtime.shutdown();
    }

}

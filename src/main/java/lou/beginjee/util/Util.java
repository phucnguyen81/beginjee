package lou.beginjee.util;

import java.io.IOException;
import java.net.ServerSocket;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Util implements C {

    /**
     * Tries to alocate a free local port.
     * 
     * @return a free local port number.
     * @throws IllegalStateException if it fails for {@link #PORT_TRIES}
     * attempts
     */
    public static int findFreePort() throws IllegalStateException {
        IOException e = null;
        for (int i = 0; i < PORT_TRIES; i++) {
            try (ServerSocket socket = new ServerSocket(PORT_START + i)) {
                // quickly close the socket and free it so it can be used
                socket.setSoTimeout(1);
                socket.setReuseAddress(true);
                return socket.getLocalPort();
            } catch (IOException ex) {
                e = ex;
            }
        }

        int start = PORT_START, end = PORT_START + PORT_TRIES;
        String msg = "Failed to find a free port from %d to %d";
        msg = String.format(msg, start, end);
        throw new IllegalStateException(msg, e);
    }

    /**
     * Looks up local bean whose name is the same as their no-interface view
     * name.
     */
    public static <T> T lookupLocalBean(Class<T> type) throws NamingException {
        String name = String.format("java:global/%s/%s!%s",
            APP_NAME, type.getSimpleName(), type.getName());
        Object instance = new InitialContext().lookup(name);
        return type.cast(instance);
    }

}

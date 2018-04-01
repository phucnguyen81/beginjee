package lou.beginjee.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures the rest services.
 * 
 * Redirects requests sent to /rs path to the rest provider (Jersey in this
 * application).
 */
@ApplicationPath("rs")
public class RestAppConfig extends Application {

    private final Set<Class<?>> classes;

    public RestAppConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(BookRestService.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
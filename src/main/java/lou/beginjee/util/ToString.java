package lou.beginjee.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Helps generate output for toString.
 */
public class ToString {

    // class of the client object
    private final Class<?> type;

    // positional attributes
    private final ArrayList<Object> posAttrs;

    // keyword attributes
    private final LinkedHashMap<String, Object> kwAttrs;

    public ToString() {
        this(null);
    }

    public ToString(Class<?> type) {
        this.type = type;
        this.kwAttrs = new LinkedHashMap<>();
        this.posAttrs = new ArrayList<>();
    }

    public ToString add(Object value) {
        posAttrs.add(value);
        return this;
    }

    public ToString add(String attribute, Object value) {
        kwAttrs.put(attribute, value);
        return this;
    }

    public String render() {
        String typeStr = type == null ? "" : type.getSimpleName();
        ArrayList<Object> attrs = new ArrayList<>();
        if (!posAttrs.isEmpty()) attrs.add(posAttrs);
        if (!kwAttrs.isEmpty()) attrs.add(kwAttrs);
        return String.format("%s%s", typeStr, attrs);
    }

    @Override
    public String toString() {
        return new ToString(ToString.class)
            .add("type", type)
            .add("attrs", kwAttrs)
            .render();
    }

}

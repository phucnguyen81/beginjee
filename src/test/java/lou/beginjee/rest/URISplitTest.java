package lou.beginjee.rest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class URISplitTest {

    @Test
    public void getLastPathComponent() {
        String uri = "http://localhost:8080/chapter15-service-1.0/rs/book/33";
        String id = uri.split("/")[6];
        assertEquals("33", id);
    }
}

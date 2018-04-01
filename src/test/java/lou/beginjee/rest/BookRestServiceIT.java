package lou.beginjee.rest;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lou.beginjee.util.BaseIT;
import lou.beginjee.util.C;

/**
 * Integration test for the book rest service.
 */
public class BookRestServiceIT extends BaseIT {

    private static final URI SERVICE_URI = UriBuilder
        .fromUri("http://localhost/" + C.APP_NAME + "/rs/book")
        .port(HTTP_PORT)
        .build();

    private Client client;

    @Before
    public void createClient() {
        client = ClientBuilder.newClient();
    }

    @After
    public void closeClient() {
        client.close();
    }

    @Test
    public void shouldMarshallABook() throws JAXBException {
        BookXml book = new BookXml("The Hitchhiker's Guide to the Galaxy",
            12.5F, "Science fiction comedy book", "1-84023-742-2", 354, false);
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(BookXml.class);
        Marshaller m = context.createMarshaller();
        m.marshal(book, writer);

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><description>Science fiction comedy book</description><illustrations>false</illustrations><isbn>1-84023-742-2</isbn><nbOfPage>354</nbOfPage><price>12.5</price><title>The Hitchhiker's Guide to the Galaxy</title></book>";
        assertEquals(xml, writer.toString());
    }

    @Test
    public void shouldMarshallAListOfBooks() throws JAXBException {
        BooksXml books = new BooksXml();
        books.add(new BookXml("The Hitchhiker's Guide to the Galaxy", 12.5F,
            "Science fiction comedy book", "1-84023-742-2", 354, false));
        books.add(new BookXml("The Hitchhiker's Guide to the Galaxy", 12.5F,
            "Science fiction comedy book", "1-84023-742-2", 354, false));
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(BooksXml.class);
        Marshaller m = context.createMarshaller();
        m.marshal(books, writer);
    }

    @Test
    public void shouldCreateAndDeleteABook() throws JAXBException {
        BookXml book = new BookXml("The Hitchhiker's Guide to the Galaxy",
            12.5F, "Science fiction comedy book", "1-84023-742-2", 354, false);

        // POSTs a Book
        Response response = client.target(SERVICE_URI).request()
            .post(Entity.entity(book, MediaType.APPLICATION_XML));
        assertEquals(Response.Status.CREATED.getStatusCode(),
            response.getStatusInfo().getStatusCode());
        URI bookURI = response.getLocation();

        // With the location, GETs the Book
        response = client.target(bookURI).request().get();
        book = response.readEntity(BookXml.class);
        assertEquals(Response.Status.OK.getStatusCode(),
            response.getStatusInfo().getStatusCode());
        assertEquals("The Hitchhiker's Guide to the Galaxy", book.getTitle());

        // Gets the book isbn and DELETEs it
        String bookIsbn = bookURI.toString().split("/")[6];
        response = client.target(SERVICE_URI).path(bookIsbn).request().delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
            response.getStatusInfo().getStatusCode());

        // GETs the Book and checks it has been deleted
        response = client.target(bookURI).request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
            response.getStatusInfo().getStatusCode());

    }

    @Test
    public void shouldNotCreateANullBook() throws JAXBException {
        // POSTs a Null Book
        Response response = client.target(SERVICE_URI).request()
            .post(Entity.entity(null, MediaType.APPLICATION_XML));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
            response.getStatusInfo().getStatusCode());
    }

    @Test
    public void shouldNotFindTheBookID() throws JAXBException {
        // GETs a Book with an unknown ID
        Response response = client.target(SERVICE_URI).path("invalidISBN")
            .request()
            .get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
            response.getStatusInfo().getStatusCode());
    }
}

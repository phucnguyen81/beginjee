package lou.beginjee.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lou.beginjee.core.Book;
import lou.beginjee.ejb.RepositoryService;

@Path("/book")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Stateless
public class BookRestService {

    @Inject
    private RepositoryService repo;

    @Context
    private UriInfo uriInfo;

    /**
     * <pre>
     * curl -X POST --data-binary "<book><description>Science fiction comedy book</description><illustrations>false</illustrations><isbn>1-84023-742-2</isbn><nbOfPage>354</nbOfPage><price>12.5</price><title>The Hitchhiker's Guide to the Galaxy</title></book>" -H "Content-Type: application/xml" http://localhost:8080/chapter15-service-1.0/rs/book -v
     * curl -X POST --data-binary "{\"description\":\"Science fiction comedy book\",\"illustrations\":false,\"isbn\":\"1-84023-742-2\",\"nbOfPage\":354,\"price\":12.5,\"title\":\"The Hitchhiker's Guide to the Galaxy\"}" -H "Content-Type: application/json" http://localhost:8080/chapter15-service-1.0/rs/book -v
     * </pre>
     */
    @POST
    public Response createBook(BookXml bookXml) {
        if (bookXml == null) throw new BadRequestException();
        Book book = bookXml.getBook();
        repo.save(book);
        URI bookUri = uriInfo.getAbsolutePathBuilder()
            .path(book.getIsbn().toString())
            .build();
        return Response.created(bookUri).build();
    }

    @PUT
    public Response updateBook(BookXml bookXml) {
        if (bookXml == null) throw new BadRequestException();
        repo.updateByIsbn(bookXml.getBook());
        return Response.ok().build();
    }

    /**
     * <pre>
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/chapter15-service-1.0/rs/book/1 -v
     * XML  : curl -X GET -H "Accept: application/xml" http://localhost:8080/chapter15-service-1.0/rs/book/1 -v
     * </pre>
     */
    @GET
    @Path("{isbn}")
    public Response getBook(@PathParam("isbn") String isbn) {
        Book book = repo.findBookByIsbn(isbn);
        if (book == null) throw new NotFoundException();
        return Response.ok(new BookXml(book)).build();
    }

    /**
     * <pre>
     * curl -X DELETE http://localhost:8080/chapter15-service-1.0/rs/book/1 -v
     * </pre>
     */
    @DELETE
    @Path("{isbn}")
    public Response deleteBook(@PathParam("isbn") String isbn) {
        Book book = repo.findBookByIsbn(isbn);
        if (book == null)
            throw new NotFoundException();
        repo.remove(book);
        return Response.noContent().build();
    }

    /**
     * <pre>
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/chapter15-service-1.0/rs/book -v 
     * XML : curl -X GET -H "Accept: application/xml" http://localhost:8080/chapter15-service-1.0/rs/book -v
     * </pre>
     */
    @GET
    public Response getAllBooks() {
        List<Book> books = repo.findAll(Book.class);
        BooksXml booksXml = new BooksXml(books);
        return Response.ok(booksXml).build();
    }
}
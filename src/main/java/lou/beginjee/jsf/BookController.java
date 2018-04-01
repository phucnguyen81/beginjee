package lou.beginjee.jsf;

import java.net.MalformedURLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lou.beginjee.core.Book;
import lou.beginjee.ejb.RepositoryService;

@Named
@RequestScoped
public class BookController {

    @Inject
    private RepositoryService repo;

    @Inject
    private FacesContext context;

    private Book book = new Book();

    public Book getBook() {
        return book;
    }

    public void createBook() {
        repo.save(book);
        context.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
                "The book" + book.getTitle() + " has been created with id="
                    + book.getId()));
    }

    public void findById() {
        book = repo.findById(Book.class, book.getId());
    }

    public List<Book> findAll() {
        return repo.findAll(Book.class);
    }

    /**
     * TODO check if the image resource exists, if not, returns a default image
     * resource.
     */
    public String findImageName() throws MalformedURLException {
        String imgName = "" + book.getIsbn() + ".gif";
        String imgPath = "/resources/images/" + imgName;
        ExternalContext ctx = context.getExternalContext();
        boolean hasImage = (ctx.getResource(imgPath) != null);
        return hasImage ? imgName : "no-image-available.jpg";
    }

}

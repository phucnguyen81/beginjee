package lou.beginjee.core;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lou.beginjee.util.ToString;

@Entity
@NamedQuery(
    name = "findAllBooks",
    query = "SELECT b FROM Book b ORDER BY b.title DESC")
public class Book extends Item {

    private String isbn;

    @Temporal(TemporalType.DATE)
    private Date published;

    private Integer nbOfPage;

    private Boolean illustrations;

    public Book() {
        // default constructor required for entity bean
        super();
    }

    public Book(String title, Float price, String description, String isbn,
        Integer nbOfPage, Boolean illustrations) {
        super(title, price, description);
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return new ToString(Book.class)
            .add(super.toString())
            .add("isbn", isbn)
            .add("published", published)
            .add("nbOfPage", nbOfPage)
            .render();
    }
}
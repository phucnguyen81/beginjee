package lou.beginjee.rest;

import javax.xml.bind.annotation.XmlRootElement;

import lou.beginjee.core.Book;
import lou.beginjee.util.ToString;

@XmlRootElement(name = "book")
public class BookXml {

    private String title;
    private Float price;
    private String description;
    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;

    public BookXml() {}

    public BookXml(String title, Float price, String description,
        String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    public BookXml(Book book) {
        this(book.getTitle(), book.getPrice(), book.getDescription(),
            book.getIsbn(), book.getNbOfPage(), book.getIllustrations());
    }

    public Book getBook() {
        return new Book(
            title, price, description,
            isbn, nbOfPage, illustrations);
    }

    @Override
    public String toString() {
        return new ToString(BookXml.class).add("title", title).add("isbn", isbn)
            .render();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

}

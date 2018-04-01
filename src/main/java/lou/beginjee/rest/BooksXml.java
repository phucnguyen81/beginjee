package lou.beginjee.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lou.beginjee.core.Book;

@XmlRootElement
@XmlSeeAlso(BookXml.class)
public class BooksXml extends ArrayList<BookXml> {

    private static final long serialVersionUID = 1L;

    public BooksXml(List<Book> books) {
        this(toXML(books));
    }

    private static List<BookXml> toXML(List<Book> books) {
        ArrayList<BookXml> booksXml = new ArrayList<>(books.size());
        for (Book book : books)
            booksXml.add(new BookXml(book));
        return booksXml;
    }

    public BooksXml() {
        super();
    }

    public BooksXml(Collection<? extends BookXml> c) {
        super(c);
    }

    @XmlElement(name = "book")
    public List<BookXml> getBooks() {
        return this;
    }

    public void setBooks(List<BookXml> books) {
        this.addAll(books);
    }
}
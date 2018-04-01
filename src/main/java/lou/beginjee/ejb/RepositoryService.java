package lou.beginjee.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import lou.beginjee.core.Book;
import lou.beginjee.core.CD;
import lou.beginjee.interceptor.LoggingEnabled;

@Stateless
@LoggingEnabled
public class RepositoryService {

    @Inject
    private EntityManager em;

    /**
     * Updates the book in the database whose isbn is equal to the isbn of the
     * given book. Returns the updated book.
     */
    public Book updateByIsbn(Book book) {
        Book theBook = findBookByIsbn(book.getIsbn());
        if (theBook == null) return null;
        book.setId(theBook.getId());
        return em.merge(book);
    }

    public Book findBookByIsbn(String isbn) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = '"
            + isbn + "'", Book.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * TODO use setFirstResult and setMaxResult for pagination.
     */
    public <T> List<T> findAll(Class<T> type) {
        if (type == Book.class)
            return em.createNamedQuery("findAllBooks", type).getResultList();
        else if (type == CD.class)
            return em.createNamedQuery("findAllCds", type).getResultList();
        else {
            String selectAll = "SELECT t FROM " + type.getSimpleName() + " t";
            return em.createQuery(selectAll, type).getResultList();
        }
    }

    public <T> T findById(Class<T> type, Long id) {
        return em.find(type, id);
    }

    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    public void remove(Book book) {
        em.remove(book);
    }

    public Book merge(Book book) {
        return em.merge(book);
    }

    public CD save(CD cd) {
        em.persist(cd);
        return cd;
    }

}

package lou.beginjee.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import lou.beginjee.core.Book;
import lou.beginjee.core.CD;
import lou.beginjee.ejb.RepositoryService;
import lou.beginjee.util.BaseIT;
import lou.beginjee.util.Util;

public class RepositoryIT extends BaseIT {

    @Test
    public void initialData() throws Exception {
        RepositoryService repo = Util.lookupLocalBean(RepositoryService.class);
        assertNotNull(repo);

        List<Book> books = repo.findAll(Book.class);
        assertEquals("Wrong number of books", 3, books.size());

        List<CD> cds = repo.findAll(CD.class);
        assertEquals("Wrong number of cds", 1, cds.size());
    }

}

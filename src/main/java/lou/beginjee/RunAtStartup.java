package lou.beginjee;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import lou.beginjee.core.Book;
import lou.beginjee.core.CD;
import lou.beginjee.ejb.RepositoryService;

/**
 * Run some tests on initial state of the application. Exceptions at this stage
 * will stop the application from being deployed.
 */
@Startup
@Singleton
public class RunAtStartup {

    @Inject
    Logger log;

    @Inject
    RepositoryService repo;

    @PostConstruct
    public void testRepository() {
        List<Book> books = repo.findAll(Book.class);
        log.info("Number of books: " + books.size());

        List<CD> cds = repo.findAll(CD.class);
        log.info("Numer of CDs: " + cds.size());
    }

}

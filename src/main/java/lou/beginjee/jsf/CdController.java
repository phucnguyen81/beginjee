package lou.beginjee.jsf;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lou.beginjee.core.CD;
import lou.beginjee.ejb.RepositoryService;

@Named
@RequestScoped
public class CdController {

    @Inject
    private RepositoryService repo;

    @Inject
    private FacesContext context;

    private CD cd = new CD();

    public CD getCd() {
        return cd;
    }

    public void createCd() {
        repo.save(cd);
        context.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "CD created",
                "The cd" + cd.getTitle() + " has been created with id="
                    + cd.getId()));
    }

    public void findById() {
        cd = repo.findById(CD.class, cd.getId());
    }

    public List<CD> findAll() {
        return repo.findAll(CD.class);
    }

}

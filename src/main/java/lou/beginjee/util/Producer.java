package lou.beginjee.util;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Makes various beans available for direct CDI injection.
 */
@Dependent
public class Producer {

    /**
     * Creates entity-managers from the main persistence unit.
     */
    @PersistenceContext(unitName = C.PU)
    private EntityManager em;

    @Produces
    public EntityManager entityManager() {
        return em;
    }

    /**
     * Creates a logger for the instance that declares the injection point.
     */
    @Produces
    public Logger logger(InjectionPoint injectionPoint) {
        return Logger.getLogger(
            injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    public FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @RequestScoped
    public ExternalContext externalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

}

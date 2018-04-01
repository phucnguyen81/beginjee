package lou.beginjee.core;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
    name = "findAllCds",
    query = "SELECT cd FROM CD cd ORDER BY cd.title DESC")
public class CD extends Item {

    private Integer numberOfCDs;
    private Integer totalDuration;
    private String genre;

    public CD() {}

    public CD(String title, Float price, String description,
        Integer numberOfCDs, Integer totalDuration,
        String genre) {
        super(title, price, description);
        this.numberOfCDs = numberOfCDs;
        this.totalDuration = totalDuration;
        this.genre = genre;
    }

    public Integer getNumberOfCDs() {
        return numberOfCDs;
    }

    public void setNumberOfCDs(Integer numberOfCDs) {
        this.numberOfCDs = numberOfCDs;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
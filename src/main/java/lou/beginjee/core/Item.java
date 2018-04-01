package lou.beginjee.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lou.beginjee.util.ToString;

/**
 * Root entity of the inheritance hierachy. For ease of use, use table-per
 * concrete-class stragegy.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
        message = "{Item.title.size.message}",
        min = 4,
        max = 50)
    @Column(nullable = false)
    private String title;

    private Float price;

    @Column(length = 2000)
    private String description;

    public Item() {}

    public Item(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new ToString(Item.class)
            .add("id", id)
            .add("title", title)
            .add("price", price)
            .add("description", description)
            .render();
    }

}
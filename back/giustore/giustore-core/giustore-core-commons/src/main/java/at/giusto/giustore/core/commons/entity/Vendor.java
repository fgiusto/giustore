package at.giusto.giustore.core.commons.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * The type Vendor.
 */
@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Integer vendorId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "vendor")
    private List<Item> items;

    // getters and setters

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setVendorId(Integer id) {
        this.vendorId = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}

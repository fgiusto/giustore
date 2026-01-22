package at.giusto.giustore.core.commons.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Item.
 */
@Entity
@Table(name = "item",
        indexes = {
                @Index(name = "idx_item_category", columnList = "category_id"),
                @Index(name = "idx_item_vendor", columnList = "vendor_id")
        })
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_category"))
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_vendor"))
    private Vendor vendor;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // getters and setters

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setItemId(Long id) {
        this.itemId = id;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets vendor.
     *
     * @return the vendor
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * Sets vendor.
     *
     * @param vendor the vendor
     */
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

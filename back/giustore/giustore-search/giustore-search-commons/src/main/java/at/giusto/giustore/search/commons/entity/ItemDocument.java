package at.giusto.giustore.search.commons.entity;

import at.giusto.giustore.search.commons.record.CategoryDocument;
import at.giusto.giustore.search.commons.record.OwnerDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * The type Item document.
 */
@Document(indexName = "items")
public class ItemDocument {
    @Id
    private String id;
    @Field(type = FieldType.Object)
    private CategoryDocument category;
    @Field(type = FieldType.Object)
    private OwnerDocument owner;
    private String title;
    private String description;

    /**
     * Instantiates a new Item document.
     */
    public ItemDocument() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public CategoryDocument getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(CategoryDocument category) {
        this.category = category;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public OwnerDocument getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(OwnerDocument owner) {
        this.owner = owner;
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
}
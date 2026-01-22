package at.giusto.giustore.core.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.List;

/**
 * The type Category.
 */
@Entity
@Table(
        name = "category",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_category_name_parent",
                        columnNames = {"parent_category_id", "name"})
        })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "parent_category_id",
            foreignKey = @ForeignKey(name = "fk_parent_category"))
    private Category parentCategory;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> subCategories;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }

    /**
     * Gets parent category.
     *
     * @return the parent category
     */
    public Category getParentCategory() {
        return parentCategory;
    }

    /**
     * Sets parent category.
     *
     * @param parentCategory the parent category
     */
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
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
     * Gets child categories.
     *
     * @return the child categories
     */
    public List<Category> getSubCategories() {
        return subCategories;
    }

    /**
     * Sets child categories.
     *
     * @param childCategories the child categories
     */
    public void setSubCategories(List<Category> childCategories) {
        this.subCategories = childCategories;
    }
}

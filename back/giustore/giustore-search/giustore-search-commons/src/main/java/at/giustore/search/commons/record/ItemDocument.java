package at.giustore.search.commons.record;

/**
 * The type Item document.
 */
public record ItemDocument(
        Long itemId,
        CategoryDocument category,
        OwnerDocument owner,
        String title,
        String description
) {
}
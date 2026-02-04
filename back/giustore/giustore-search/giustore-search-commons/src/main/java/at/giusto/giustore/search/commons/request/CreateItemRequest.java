package at.giusto.giustore.search.commons.request;

/**
 * The type Create item request.
 */
public record CreateItemRequest(
        String title,
        String description,
        String videoUrl
) {
}

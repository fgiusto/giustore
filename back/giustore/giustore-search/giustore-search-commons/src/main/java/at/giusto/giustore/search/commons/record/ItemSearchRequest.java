package at.giusto.giustore.search.commons.record;

public record ItemSearchRequest(
        String text,
        String categoryId,
        String ownerId,
        Integer page,
        Integer size
) {
}

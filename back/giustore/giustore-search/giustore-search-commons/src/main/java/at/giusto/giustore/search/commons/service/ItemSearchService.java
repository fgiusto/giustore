package at.giusto.giustore.search.commons.service;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import at.giusto.giustore.search.commons.repository.ItemDocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * The type Search service.
 */
@Service
public class ItemSearchService {
    private final ItemDocumentRepository itemDocumentRepository;

    /**
     * Instantiates a new Item search service.
     *
     * @param itemDocumentRepository the item document repository
     */
    public ItemSearchService(ItemDocumentRepository itemDocumentRepository) {
        this.itemDocumentRepository = itemDocumentRepository;
    }

    public Page<ItemDocument> search(String query) {
        return itemDocumentRepository.searchSimilar(new ItemDocument(query, query), null, PageRequest.of(0, 10));
    }
}

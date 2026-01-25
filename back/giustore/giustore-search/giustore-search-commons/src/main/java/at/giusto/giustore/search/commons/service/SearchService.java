package at.giusto.giustore.search.commons.service;

import at.giusto.giustore.search.commons.repository.ItemDocumentRepository;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.stereotype.Service;

import static at.giusto.giustore.search.commons.constant.MeiliConstant.ITEM_INDEX_NAME;

/**
 * The type Search service.
 */
@Service
public class SearchService {

    private final ItemDocumentRepository itemDocumentRepository;

    /**
     * Instantiates a new Search service.
     *
     * @param itemDocumentRepository the item document repository
     */
    public SearchService(ItemDocumentRepository itemDocumentRepository) {
        this.itemDocumentRepository = itemDocumentRepository;
    }

    public SearchResult search(String query) {
        return itemDocumentRepository.search(ITEM_INDEX_NAME, query);
    }
}

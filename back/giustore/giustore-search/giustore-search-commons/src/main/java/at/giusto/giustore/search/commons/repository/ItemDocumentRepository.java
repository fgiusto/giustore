package at.giusto.giustore.search.commons.repository;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * The interface Item document repository.
 */
public interface ItemDocumentRepository extends ElasticsearchRepository<ItemDocument, String> {
}

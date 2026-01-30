package at.giusto.giustore.search.commons.repository;

import at.giusto.giustore.search.commons.entity.ItemDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * The interface Item document repository.
 */
public interface ItemDocumentRepository extends ElasticsearchRepository<ItemDocument, String> {

    @Query("""
            {
              "multi_match": {
                "query": "?0",
                "fields": ["title", "description"],
                "fuzziness": "AUTO"
              }
            }
            """)
    List<ItemDocument> fuzzySearch(String text);

}

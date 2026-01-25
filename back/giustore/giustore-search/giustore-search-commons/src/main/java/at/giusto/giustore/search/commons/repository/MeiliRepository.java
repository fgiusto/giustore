package at.giusto.giustore.search.commons.repository;

import com.meilisearch.sdk.model.SearchResult;

import java.util.List;

/**
 * The interface Meili repository.
 *
 * @param <T> the type parameter
 */
public interface MeiliRepository<T> {
    /**
     * Save.
     *
     * @param indexName the index name
     * @param document  the document
     */
    void save(String indexName, T document);

    /**
     * Save all.
     *
     * @param indexName the index name
     * @param documents the documents
     */
    void saveAll(String indexName, List<T> documents);

    /**
     * Search search result.
     *
     * @param indexName the index name
     * @param query     the query
     * @return the search result
     */
    SearchResult search(String indexName, String query);

    /**
     * Delete.
     *
     * @param indexName the index name
     * @param id        the id
     */
    void delete(String indexName, String id);
}

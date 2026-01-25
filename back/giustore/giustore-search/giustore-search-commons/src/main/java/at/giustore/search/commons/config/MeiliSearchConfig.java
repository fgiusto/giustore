package at.giustore.search.commons.config;

import at.giustore.search.commons.record.ItemDocument;
import at.giustore.search.commons.repository.MeiliRepository;
import at.giustore.search.commons.repository.MeiliRepositoryImpl;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeiliSearchConfig {

    @Bean
    public Client meiliClient() {
        return new Client(new Config("http://localhost:7700", "changeme"));
    }

    @Bean
    public MeiliRepository<ItemDocument> itemRepository(Client client) {
        return new MeiliRepositoryImpl<>(client);
    }

}

package at.giusto.giustore.search.commons.config;

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

}

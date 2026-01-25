package at.giusto.giustore.search.app.config;

import at.giusto.giustore.search.commons.config.GiustoreSearchCommonsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GiustoreSearchCommonsConfig.class)
@ComponentScan("at.giusto.giustore.search.app")
public class GiustoreSearchAppConfig {
}

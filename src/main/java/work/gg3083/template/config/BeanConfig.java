package work.gg3083.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Gimi
 * @date 2019/8/20 14:45
 */
@Configuration
public class BeanConfig {
    @Autowired
    private RestTemplateBuilder builder;
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}

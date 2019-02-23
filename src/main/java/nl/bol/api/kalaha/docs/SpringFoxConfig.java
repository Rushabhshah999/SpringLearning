package nl.bol.api.kalaha.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket docket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kalaha-api")
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo)
                .select().paths(regex("/kalaha/.*"))
                .build();
    }
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kalaha API")
                .description("API For Kalaha Game")
                .version("1.0.0")
                .build();
    }
 }
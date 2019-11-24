package ru.customs.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.customs.dao.ClaimsDao;
import ru.customs.dao.DeclarantDao;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DaoConfig {
    @Bean
    public ClaimsDao claimsDao() {
        return new ClaimsDao();
    }

    @Bean
    public DeclarantDao declarantDao(){
        return new DeclarantDao();
    }
}
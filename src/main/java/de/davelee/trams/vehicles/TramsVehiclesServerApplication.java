package de.davelee.trams.vehicles;

import com.google.common.base.Predicate;
import de.davelee.trams.vehicles.admin.ui.AdditionalFeatures;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;


/**
 * This class represents the Spring Boot application for TraMS Vehicle Management.
 * @author Dave Lee
 */
@SpringBootApplication
@EnableSwagger2
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories("de.davelee.trams.vehicles.repository")
public class TramsVehiclesServerApplication {

    /**
     * Main method to start the application.
     * @param args a <code>String</code> array of arguments which are not presently used.
     */
    public static void main ( String[] args ) {
        SpringApplication.run(TramsVehiclesServerApplication.class, args);
    }

    @Bean
    /**
     * Configure Swagger to display appropriate information.
     * @return a <code>Docket</code> object containing the configured information.
     */
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("operations")
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    /**
     * Only map urls starting with vehicle.
     * @return a <code>Predicate</code> object containing the configuration of limited urls.
     */
    private Predicate<String> paths() {
        return or (
                regex("/vehicle.*")
        );
    }

    /**
     * Return an API Info object with the configured information for the Swagger UI.
     * @return a <code>ApiInfo</code> object with the configured information for the Swagger UI.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TraMS Vehicle Rest API")
                .description("Rest API for TraMS Vehicle")
                .termsOfServiceUrl("http://www.davelee.de")
                .contact("Dave Lee")
                .license("TraMS Website")
                .licenseUrl("http://www.davelee.de")
                .version("0.1")
                .build();
    }

    @Bean
    /**
     * This method returns the FeatureProvider which Togglz uses to determine which features can be
     * activated.
     * @return a <code>FeatureProvider</code> containing the Feature List for Togglz.
     */
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(AdditionalFeatures.class);
    }

}

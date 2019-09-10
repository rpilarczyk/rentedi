package pl.rentedi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"pl.rentedi.controllers", "pl.rentedi.services"})
@EnableJpaRepositories("pl.rentedi.repository")
@EntityScan("pl.rentedi.domains")
@EnableSwagger2
public class RentediApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RentediApplication.class, args);
    }

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RentediApplication.class);
	}
}

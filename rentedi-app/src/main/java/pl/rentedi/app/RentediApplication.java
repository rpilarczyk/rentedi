package pl.rentedi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"pl.rentedi.controllers", "pl.rentedi.services"})
@EnableJpaRepositories("pl.rentedi.repository")
@EntityScan("pl.rentedi.domains")
public class RentediApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentediApplication.class, args);
    }
}

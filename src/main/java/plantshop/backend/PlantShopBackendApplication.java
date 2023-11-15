package plantshop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;

@SpringBootApplication
@PropertySource("classpath:secure.properties")
public class PlantShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantShopBackendApplication.class, args);
    }

}

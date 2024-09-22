package capstone.capstone01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Capstone01Application {

    public static void main(String[] args) {
        SpringApplication.run(Capstone01Application.class, args);
    }

}

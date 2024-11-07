package org.baas.baascore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BaascoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaascoreApplication.class, args);
    }

}

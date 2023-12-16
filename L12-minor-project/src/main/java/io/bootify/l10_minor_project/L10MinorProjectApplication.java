package io.bootify.l10_minor_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class L10MinorProjectApplication {

    public static void main(final String[] args) {
        SpringApplication.run(L10MinorProjectApplication.class, args);
    }

}

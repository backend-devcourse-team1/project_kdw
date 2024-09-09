package org.programers.grids_and_circles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GridsAndCirclesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GridsAndCirclesApplication.class, args);
    }

}

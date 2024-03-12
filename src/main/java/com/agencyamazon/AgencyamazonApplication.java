package com.agencyamazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.agencyamazon"})
@EnableScheduling
@EnableCaching
public class AgencyamazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgencyamazonApplication.class, args);
    }

}

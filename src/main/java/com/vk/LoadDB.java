/**
 * LoadDB.java
 */
package com.vk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@Slf4j
class LoadDB {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
           System.out.println("Preloading " + userRepository.save(new User("Adam", "Gregor", "adam.gregor@test.com")));
            System.out.println("Preloading " + userRepository.save(new User("Bianca", "Lang", "biancalang@test.com")));

        };
    }
}

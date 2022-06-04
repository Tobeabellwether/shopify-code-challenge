package com.example.demo.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> itemRepository.saveAll(Arrays.asList(
                new Item("Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item("Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item("Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item("TV", "Samsung", "B3", LocalDate.of(2022, Month.FEBRUARY, 1)),
                new Item("TV", "Samsung", "B3", LocalDate.of(2022, Month.FEBRUARY, 1)),
                new Item("Mobile phone", "Apple", "C1", LocalDate.of(2022, Month.MARCH, 1))));
    }
}

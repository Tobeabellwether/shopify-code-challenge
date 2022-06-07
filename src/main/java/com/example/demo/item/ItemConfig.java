package com.example.demo.item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ItemConfig {
    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> itemRepository.saveAll(List.of(
                new Item(1L, "Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item(2L,"Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item(3L,"Laptop", "Lenovo", "A1", LocalDate.of(2022, Month.JANUARY, 1)),
                new Item(4L,"TV", "Samsung", "B3", LocalDate.of(2022, Month.FEBRUARY, 1)),
                new Item(5L,"TV", "Samsung", "B3", LocalDate.of(2022, Month.FEBRUARY, 1)),
                new Item(6L,"Mobile phone", "Apple", "C1", LocalDate.of(2022, Month.MARCH, 1))));
    }
}

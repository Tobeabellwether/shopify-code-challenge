package com.example.demo.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatusOrderById(int i);
    Boolean existsByIdAndStatus(Long id, int status);
}
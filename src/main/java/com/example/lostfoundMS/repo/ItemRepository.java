package com.example.lostfoundMS.repo;

import com.example.lostfoundMS.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
package com.example.lostfoundMS.services;

import com.example.lostfoundMS.entities.Item;
import com.example.lostfoundMS.repo.ItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


}

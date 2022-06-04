package com.example.demo.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("item does not exists"));
    }

    public List<Item> getItems(List<Long> ids) {
        ArrayList<Item> items = new ArrayList<>();
        for (Long id: ids) items.add(getItem(id));
        return items;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        Long id = item.getId();
        if (id == null) throw new IllegalStateException("id can not be null");
        Optional<Item> itemOptionalId = itemRepository.findById(id);
        if (itemOptionalId.isPresent()) throw new IllegalStateException("id " + id + " already in database");
        itemRepository.save(item);
    }

    public void addItems(List<Item> items) {
        for (Item item: items) addItem(item);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) throw new IllegalStateException("item with id " + id + " does not exists");
        itemRepository.deleteById(id);
    }

    public void deleteItems(List<Long> ids) {
        for (Long id: ids) deleteItem(id);
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    @Transactional
    public void updateItem(Long id, String type, String brand, String model, LocalDate checkInDate) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("item with id " + id + " does not exists"));

        BiPredicate<String, String> shouldUpdate = (oldAttribute, newAttribute)
                -> newAttribute != null && newAttribute.length() > 0 && !oldAttribute.equals(newAttribute);

        if (shouldUpdate.test(item.getType(), type)) item.setType(type);
        if (shouldUpdate.test(item.getModel(), model)) item.setModel(model);
        if (shouldUpdate.test(item.getBrand(), brand)) item.setBrand(brand);
        if (checkInDate != null && !item.getCheckInDate().equals(checkInDate)) item.setCheckInDate(checkInDate);
    }

    @Transactional
    public void updateItems(List<Item> items) {
        for (Item item: items)
            updateItem(item.getId(), item.getType(), item.getBrand(), item.getModel(), item.getCheckInDate());
    }
    
}

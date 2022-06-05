package com.example.demo.item;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
                .orElseThrow(() -> new IllegalStateException("item with id " + id + " does not exists"));
    }

    public List<Item> getItems(List<Long> ids) {
        ArrayList<Item> items = new ArrayList<>();
        for (Long id: ids) items.add(getItem(id));
        return items;
    }

    public List<Item> getAllItems() {
        return itemRepository.findByStatus(0);
    }

    public List<Item> getAllDeletedItems() {
        return itemRepository.findByStatus(1);
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

    public void undeleteItem(Long id) {
        if(itemRepository.existsByIdAndStatus(id,1)) {
            Item deletedItem = getItem(id);
            Item item = new Item(deletedItem.getId(), deletedItem.getType(), deletedItem.getBrand(),
                    deletedItem.getModel(), deletedItem.getCheckInDate(), 0);
            itemRepository.deleteById(id);
            addItem(item);
        }
        else throw new IllegalStateException("item with id " + id + " can't or not need to be undeleted");
    }

    public void unDeleteItems(List<Long> ids) {
        for (Long id : ids) undeleteItem(id);
    }

    public void deleteItem(Long id, String deletionComment) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("item with id " + id + " does not exists"));
        itemRepository.deleteById(id);
        if (item.getStatus() == 0) addItem(new DeletedItem(item, deletionComment));
    }

    public void deleteItems(List<Long> ids, String deletionComment) {
        for (Long id: ids) deleteItem(id, deletionComment);
    }

    public void deleteAllItems(String deletionComment) {
        List<Item> items = getAllItems();
        for (Item item : items) deleteItem(item.getId(), deletionComment);
    }

    @Transactional
    public void updateItem(Item item) {
        Long id = item.getId();
        String type = item.getType();
        String model = item.getModel();
        String brand = item.getBrand();
        LocalDate checkInDate = item.getCheckInDate();

        Item oldItem = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("item with id " + id + " does not exists"));

        BiPredicate<String, String> shouldUpdate = (oldAttribute, newAttribute)
                -> newAttribute != null && newAttribute.length() > 0 && !oldAttribute.equals(newAttribute);

        if (shouldUpdate.test(oldItem.getType(), type)) oldItem.setType(type);
        if (shouldUpdate.test(oldItem.getModel(), model)) oldItem.setModel(model);
        if (shouldUpdate.test(oldItem.getBrand(), brand)) oldItem.setBrand(brand);
        if (checkInDate != null && !oldItem.getCheckInDate().equals(checkInDate)) oldItem.setCheckInDate(checkInDate);
    }

    @Transactional
    public void updateItems(List<Item> items) {
        for (Item item: items)
            updateItem(item);
    }

}

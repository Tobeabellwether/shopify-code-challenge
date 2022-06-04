package com.example.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/item")
public class ItemController {

    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "{ids}")
    public List<Item> getItems(@PathVariable("ids") List<Long> ids){
        return itemService.getItems(ids);
    }

    @GetMapping(path = "all")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping()
    public void addItems(@RequestBody List<Item> items) {
        itemService.addItems(items);
    }

    @DeleteMapping(path = "{ids}")
    public void deleteItems(@PathVariable("ids") List<Long> ids) {
        itemService.deleteItems(ids);
    }

    @DeleteMapping(path = "all")
    public void deleteAllItems() {
        itemService.deleteAllItems();
    }

    @PutMapping(path = "{id}")
    public void updateItem(@PathVariable("id") Long id,
                           @RequestParam(required = false) String type,
                           @RequestParam(required = false) String brand,
                           @RequestParam(required = false) String model,
                           @RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate) {
        itemService.updateItem(id, type, brand, model, checkInDate);
    }

    @PutMapping
    public void updateItems(@RequestBody List<Item> items) {
        itemService.updateItems(items);
    }

}

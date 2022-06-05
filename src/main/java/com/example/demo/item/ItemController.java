package com.example.demo.item;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("{ids}")
    public ModelAndView getItems(@PathVariable("ids") List<Long> ids) {
        ModelAndView mav = new ModelAndView("getItems");
        List<Item> items = itemService.getItems(ids);
        mav.addObject("items", items);
        return mav;
    }

    @GetMapping("all")
    public ModelAndView getAllItems() {
        ModelAndView mav = new ModelAndView("getItems");
        List<Item> items = itemService.getAllItems();
        mav.addObject("items", items);
        return mav;
    }

    @GetMapping("deleted")
    public ModelAndView getAllDeletedItems() {
        ModelAndView mav = new ModelAndView("getDeletedItems");
        List<Item> items = itemService.getAllDeletedItems();
        mav.addObject("items", items);
        return mav;
    }

    @GetMapping("add")
    public ModelAndView addItem() {
        ModelAndView mav = new ModelAndView("addItem");
        mav.addObject("item", new Item());
        return mav;
    }

    @PostMapping("add")
    public RedirectView addItem(@ModelAttribute Item item) {
        itemService.addItem(item);
        return new RedirectView("all");
    }

//    @PostMapping
//    public void addItems(@RequestBody List<Item> items) {
//        itemService.addItems(items);
//    }

    @PostMapping(path = "{ids}")
    public void unDeleteItems(@PathVariable("ids") List<Long> ids) {
        itemService.unDeleteItems(ids);
    }

    @DeleteMapping(path = "{ids}")
    public void deleteItems(@PathVariable("ids") List<Long> ids,
                            @RequestParam(required = false) String deletionComment) {
        itemService.deleteItems(ids, deletionComment);
    }

    @DeleteMapping(path = "all")
    public void deleteAllItems(@RequestParam(required = false) String deletionComment) {
        itemService.deleteAllItems(deletionComment);
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

package com.example.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("")
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
    public ModelAndView addItemForm() {
        ModelAndView mav = new ModelAndView("addItem");
        mav.addObject("item", new Item());
        return mav;
    }
    @PostMapping("add")
    public RedirectView addItem(@ModelAttribute Item item) {
        itemService.addItem(item);
        return new RedirectView("");
    }

    @GetMapping("update")
    public ModelAndView updateItemForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("updateItem");
        Item item = itemService.getItem(id);
        mav.addObject("item", item);
        return mav;
    }

    @PostMapping("update")
    public RedirectView updateItem(@ModelAttribute Item item) {
        itemService.updateItem(item);
        return new RedirectView("");
    }

    @GetMapping("delete")
    public RedirectView deleteItem(@RequestParam Long id,
                                   @RequestParam(required = false) String deletionComment) {
        itemService.deleteItem(id, deletionComment);
        return new RedirectView("deleted");
    }

    @GetMapping("undelete")
    public RedirectView undeleteItem(@RequestParam Long id) {
        itemService.undeleteItem(id);
        return new RedirectView("");
    }


////Batch operations if needed, front-end not implemented yet
//    @PostMapping
//    public void addItems(@RequestBody List<Item> items) {
//        itemService.addItems(items);
//    }
//
//    @PutMapping
//    public void updateItems(@RequestBody List<Item> items) {
//        itemService.updateItems(items);
//    }
//
//    @DeleteMapping(path = "{ids}")
//    public void deleteItems(@PathVariable("ids") List<Long> ids,
//                            @RequestParam(required = false) String deletionComment) {
//        itemService.deleteItems(ids, deletionComment);
//    }
//
//    @DeleteMapping(path = "")
//    public void deleteAllItems(@RequestParam(required = false) String deletionComment) {
//        itemService.deleteAllItems(deletionComment);
//    }
//
//    @PostMapping(path = "{ids}")
//    public void unDeleteItems(@PathVariable("ids") List<Long> ids) {
//        itemService.unDeleteItems(ids);
//    }

}

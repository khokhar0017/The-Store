package com.mycart.app.TheStore.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycart.app.TheStore.Model.Item;
import com.mycart.app.TheStore.Services.ItemService;

@RestController
@RequestMapping("home")
public class ItemController {

  @Autowired private ItemService service;

  @GetMapping("/allItems")
  public List<Item> getAllItems() {
    return service.getAllItems();
  }

  @GetMapping(path = "/item/{id}")
  public Item getItemById(@PathVariable final Long id) {
    return service.getItemById(id);
  }

  @PostMapping(path = "/addItem", consumes = "application/json", produces = "application/json")
  public void addItem(@RequestBody final List<Item> items) {
    System.out.println(items);
    service.addItems(items);
  }

  @PostMapping(path = "/deleteItem", consumes = "application/json", produces = "application/json")
  public void deleteItems(@RequestBody final List<Long> ids) {
    service.deleteItems(ids);
  }
}

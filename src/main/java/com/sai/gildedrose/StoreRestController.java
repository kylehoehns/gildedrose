package com.sai.gildedrose;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
public class StoreRestController {

  private final StoreService storeService;

  public StoreRestController(StoreService storeService) {
    this.storeService = storeService;
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateStoreItems() {
    storeService.updateStoreItems();
  }

  @GetMapping(path = "/items")
  @ResponseStatus(HttpStatus.OK)
  public List<Item> getItems() {
    return storeService.getItems();
  }

  @PostMapping(path = "/items")
  @ResponseStatus(HttpStatus.CREATED)
  public Item addItem(@RequestBody Item item) {
    return storeService.addItem(item);
  }

  @GetMapping(path = "/items/{id}")
  public ResponseEntity<Item> getItem(@PathVariable("id") UUID id) {
    return ResponseEntity.of(storeService.getItem(id));
  }


}

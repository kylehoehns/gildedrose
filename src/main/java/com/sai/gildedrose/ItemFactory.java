package com.sai.gildedrose;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ItemFactory {

  public Item fromPersisted(PersistedItem persistedItem) {
    return new Item(
        persistedItem.getId(),
        persistedItem.getName(),
        persistedItem.getQuality(),
        persistedItem.getSellIn()
    );
  }

  public PersistedItem toPersisted(Item item) {
    PersistedItem persistedItem = new PersistedItem();
    persistedItem.setId(UUID.randomUUID());
    persistedItem.setName(item.name());
    persistedItem.setQuality(item.quality());
    persistedItem.setSellIn(item.sellIn());
    return persistedItem;
  }

}

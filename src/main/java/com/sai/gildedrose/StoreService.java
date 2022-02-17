package com.sai.gildedrose;

import com.sai.gildedrose.strategies.BasicItemStrategy;
import com.sai.gildedrose.strategies.ItemStrategy;
import com.sai.gildedrose.strategies.SpecificItemStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreService {

  private final ItemFactory itemFactory;
  private final ItemRepository itemRepository;
  private final List<SpecificItemStrategy> specificItemStrategies;
  private final BasicItemStrategy basicItemStrategy;

  public StoreService(ItemFactory itemFactory,
                      ItemRepository itemRepository,
                      List<SpecificItemStrategy> specificItemStrategies,
                      BasicItemStrategy basicItemStrategy) {
    this.itemFactory = itemFactory;
    this.itemRepository = itemRepository;
    this.specificItemStrategies = specificItemStrategies;
    this.basicItemStrategy = basicItemStrategy;
  }

  public List<Item> getItems() {
    return itemRepository.findAll().stream()
        .map(itemFactory::fromPersisted)
        .toList();
  }

  public Item addItem(Item item) {
    var persisted = itemRepository.save(itemFactory.toPersisted(item));
    return itemFactory.fromPersisted(persisted);
  }

  public Optional<Item> getItem(UUID id) {
    return itemRepository.findById(id).map(itemFactory::fromPersisted);
  }

  public void updateStoreItems() {
    var items = itemRepository.findAll().stream().map(item -> getStrategy(item).handle(item)).toList();
    itemRepository.saveAll(items);
  }

  private ItemStrategy getStrategy(PersistedItem item) {
    return specificItemStrategies.stream()
        .filter(s -> s.handles(item))
        .findFirst()
        .map(ItemStrategy.class::cast)
        .orElse(basicItemStrategy);
  }
}

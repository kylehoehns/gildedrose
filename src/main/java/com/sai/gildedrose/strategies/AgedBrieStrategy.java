package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class AgedBrieStrategy extends AbstractItemStrategy implements SpecificItemStrategy {

  @Override
  public boolean handles(PersistedItem item) {
    return "Aged Brie".equalsIgnoreCase(item.getName());
  }

  @Override
  protected PersistedItem handleQuality(PersistedItem item) {
    item.setQuality(item.getQuality() + 1);
    return item;
  }
}
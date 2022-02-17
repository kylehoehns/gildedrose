package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class LegendaryItemStrategy extends BasicItemStrategy implements SpecificItemStrategy {

  @Override
  public boolean handles(PersistedItem item) {
    return "Sulfuras".equalsIgnoreCase(item.getName());
  }

  @Override
  public PersistedItem handle(PersistedItem item) {
    return item;
  }
}

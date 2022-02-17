package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class ConjuredItemStrategy extends BasicItemStrategy implements SpecificItemStrategy {

  @Override
  public boolean handles(PersistedItem item) {
    return item.getName().startsWith("Conjured");
  }

  @Override
  protected PersistedItem handleQuality(PersistedItem item) {
    return setQuality(item, item.getQuality() - 2);
  }
}

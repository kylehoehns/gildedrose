package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class BackstagePassStrategy extends BasicItemStrategy implements SpecificItemStrategy {
  @Override
  public boolean handles(PersistedItem item) {
    return "Backstage Pass".equalsIgnoreCase(item.getName());
  }

  @Override
  protected void handleQuality(PersistedItem item) {
    int updatedQuality;
    int daysUntilConcert = item.getSellIn();
    if (daysUntilConcert > 10) {
      updatedQuality = item.getQuality() + 1;
    } else if (daysUntilConcert >= 5) {
      updatedQuality = item.getQuality() + 2;
    } else if (daysUntilConcert >= 0) {
      updatedQuality = item.getQuality() + 3;
    } else {
      updatedQuality = 0;
    }
    setQuality(item, updatedQuality);
  }
}

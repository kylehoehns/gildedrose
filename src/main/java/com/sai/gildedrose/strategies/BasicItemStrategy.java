package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class BasicItemStrategy implements ItemStrategy {

  private static final int MIN_QUALITY = 0;
  private static final int MAX_QUALITY = 50;

  @Override
  public PersistedItem handle(PersistedItem item) {
    handleSellIn(item);
    handleQuality(item);
    return item;
  }

  protected void handleSellIn(PersistedItem item) {
    item.setSellIn(item.getSellIn() - 1);
  }

  protected void handleQuality(PersistedItem item) {
    var updatedQuality = item.getQuality() - 1;
    if (item.getSellIn() < 0) {
      updatedQuality = item.getQuality() - 2;
    }
    setQuality(item, updatedQuality);
  }

  final void setQuality(PersistedItem item, int quality) {
    item.setQuality(Math.max(MIN_QUALITY, Math.min(quality, MAX_QUALITY)));
  }
}

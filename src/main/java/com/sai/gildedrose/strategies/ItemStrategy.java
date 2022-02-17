package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;

public interface ItemStrategy {

  default boolean handles(PersistedItem item) {
    return false;
  }

  PersistedItem handle(PersistedItem item);
}

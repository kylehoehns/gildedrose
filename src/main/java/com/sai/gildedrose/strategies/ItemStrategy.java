package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;

public interface ItemStrategy {

  boolean handles(PersistedItem item);

  PersistedItem handle(PersistedItem item);
}

package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;

public interface ItemStrategy {

  PersistedItem handle(PersistedItem item);
}

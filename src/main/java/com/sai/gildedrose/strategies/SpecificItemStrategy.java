package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;

public interface SpecificItemStrategy extends ItemStrategy {

  boolean handles(PersistedItem item);
}

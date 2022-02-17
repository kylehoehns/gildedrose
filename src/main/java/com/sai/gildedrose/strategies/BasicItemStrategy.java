package com.sai.gildedrose.strategies;

import com.sai.gildedrose.PersistedItem;
import org.springframework.stereotype.Component;

@Component
public class BasicItemStrategy extends AbstractItemStrategy implements ItemStrategy {

  @Override
  public boolean handles(PersistedItem item) {
    return true;
  }

}

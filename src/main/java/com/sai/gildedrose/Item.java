package com.sai.gildedrose;

import java.util.UUID;

public record Item(UUID id, String name, int quality, int sellIn) {
}

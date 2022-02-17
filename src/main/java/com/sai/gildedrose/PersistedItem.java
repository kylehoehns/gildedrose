package com.sai.gildedrose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Item")
public class PersistedItem {

  @Id
  private UUID id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="quality", nullable = false)
  private int quality;

  @Column(name="sell_in", nullable = false)
  private int sellIn;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuality() {
    return quality;
  }

  public void setQuality(int quality) {
    this.quality = quality;
  }

  public int getSellIn() {
    return sellIn;
  }

  public void setSellIn(int sellIn) {
    this.sellIn = sellIn;
  }
}

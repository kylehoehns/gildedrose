package com.sai.gildedrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
class EndToEndTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void givenNoItemsInTheStore_whenRetrievingItems_thenReturnsNoItems() throws Exception {
    mockMvc.perform(get("/store/items")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().string("[]"));
  }

  @Test
  void givenItemsInTheStore_whenRetrievingItems_thenReturnsItems() throws Exception {
    addItem("Sword", 10, 5);

    verifyItemValues("Sword", 10, 5);
  }

  @Test
  void givenABasicItemInTheStore_whenUpdatingItems_thenReducesQualityAndSellInBy1() throws Exception {
    addItem("Sword", 10, 5);

    updateStore();

    verifyItemValues("Sword", 9, 4);
  }

  @Test
  void givenAgedBrieInTheStore_whenUpdatingItems_thenIncreasesQualityBy1AndReducesSellInBy1() throws Exception {
    addItem("Aged Brie", 10, 5);

    updateStore();

    verifyItemValues("Aged Brie", 11, 4);
  }

  @Test
  void givenALegendaryItemInTheStore_whenUpdatingItems_thenDoesNotChangeQualityOrSellIn() throws Exception {
    addItem("Sulfuras", 80, 0);

    updateStore();

    verifyItemValues("Sulfuras", 80, 0);
  }

  @Test
  void givenAConjuredItemInTheStore_whenUpdatingItems_thenDecreasesQualityBy2AndReducesSellInBy1() throws Exception {
    addItem("Conjured Sword", 40, 10);

    updateStore();

    verifyItemValues("Conjured Sword", 38, 9);
  }

  @Test
  void givenABackstagePassInTheStoreThatExpiresInMoreThan10Days_whenUpdatingItems_thenIncreasesQualityBy1() throws Exception {
    addItem("Backstage Pass", 40, 20);

    updateStore();

    verifyItemValues("Backstage Pass", 41, 19);
  }

  @Test
  void givenABackstagePassInTheStoreThatExpiresIn7Days_whenUpdatingItems_thenIncreasesQualityBy2() throws Exception {
    addItem("Backstage Pass", 40, 7);

    updateStore();

    verifyItemValues("Backstage Pass", 42, 6);
  }

  @Test
  void givenABackstagePassInTheStoreThatExpiresIn3Days_whenUpdatingItems_thenIncreasesQualityBy3() throws Exception {
    addItem("Backstage Pass", 40, 3);

    updateStore();

    verifyItemValues("Backstage Pass", 43, 2);
  }

  @Test
  void givenABackstagePassInTheStoreThatHasExpired_whenUpdatingItems_thenSetsQualityTo0() throws Exception {
    addItem("Backstage Pass", 40, 0);

    updateStore();

    verifyItemValues("Backstage Pass", 0, -1);
  }

  private void verifyItemValues(String name, int quality, int sellIn) throws Exception {
    mockMvc.perform(get("/store/items")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name").value(name))
        .andExpect(jsonPath("$[0].quality").value(quality))
        .andExpect(jsonPath("$[0].sellIn").value(sellIn));
  }

  private void updateStore() throws Exception {
    mockMvc.perform(post("/store/update")).andExpect(status().isNoContent());
  }

  private void addItem(String name, int quality, int sellIn) throws Exception {
    mockMvc.perform(post("/store/items")
            .content(asJsonString(new Item(null, name, quality, sellIn)))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(name));
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}

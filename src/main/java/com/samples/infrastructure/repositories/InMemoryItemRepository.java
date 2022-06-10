package com.samples.infrastructure.repositories;

import com.samples.domain.entities.Item;
import com.samples.domain.repositories.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This repository implements {@link ItemRepository } in memory.
 */
public class InMemoryItemRepository implements ItemRepository {

  private static final List<Item> ITEMS = new ArrayList<>();

  /**
   * {@inheritDoc}.
   */
  @Override
  public Item persist(final Item item) {
    ITEMS.add(item);
    return item;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Optional<Item> findItemById(final String itemId) {
    return ITEMS.stream()
      .filter(item -> itemId.equals(item.getId()))
      .findFirst();
  }
}

package com.samples.domain.repositories;

import com.samples.domain.entities.Item;
import java.util.Optional;

/**
 * This abstraction exposes methods to manage item information.
 */
public interface ItemRepository {

  /**
   * Find item given an id.
   *
   * @param itemId the item id to find.
   * @return the found item.
   */
  Optional<Item> findItemById(String itemId);

  /**
   * Save an item on storage.
   *
   * @param item the item to save.
   * @return the item persisted.
   */
  Item persist(Item item);
}

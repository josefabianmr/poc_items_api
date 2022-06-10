package com.samples.application.usecases.create;

import com.samples.domain.entities.Item;
import com.samples.domain.repositories.ItemRepository;
import com.samples.domain.services.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Try;

/**
 * This use case implements {@link ItemCreator}
 */
public class DefaultItemCreator implements ItemCreator {

  private final IdGenerator idGenerator;

  private final ItemRepository itemRepository;

  public DefaultItemCreator(final IdGenerator idGenerator, final ItemRepository itemRepository) {
    this.idGenerator = idGenerator;
    this.itemRepository = itemRepository;
  }

  @Override
  public Either<Throwable, Item> create(final CreateItemCommand command) {
    return Try.of(idGenerator::generate)
      .map(id -> buildItem(id, command))
      .map(itemRepository::persist)
      .toEither();
  }

  private Item buildItem(final String id, final CreateItemCommand command) {
    final var item = Item.create(id, command.getTitle(), command.getCategoryId());
    item.addPrice(command.getValue(), command.getCurrency());
    item.addAttributes(command.getAttributes());
    return item;
  }

}


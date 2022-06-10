package com.samples.application.usecases.create;

import com.samples.domain.entities.Item;
import com.samples.domain.vo.Currency;
import com.samples.domain.vo.ItemAttribute;
import io.vavr.control.Either;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * This use case allows to create an item.
 */
public interface ItemCreator {

  Either<Throwable, Item> create(CreateItemCommand command);

  @Getter
  @Builder
  class CreateItemCommand {

    private final String title;

    private final String categoryId;

    private final List<ItemAttribute> attributes;

    private final BigDecimal value;

    private final Currency currency;
  }
}

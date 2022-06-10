package com.samples.application;

import static org.mockito.Mockito.when;

import com.samples.application.usecases.create.DefaultItemCreator;
import com.samples.application.usecases.create.ItemCreator;
import com.samples.domain.entities.Item;
import com.samples.domain.repositories.ItemRepository;
import com.samples.domain.services.IdGenerator;
import com.samples.domain.vo.Currency;
import com.samples.domain.vo.ItemAttribute;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The test cases for {@link DefaultItemCreator}.
 */
@ExtendWith(MockitoExtension.class)
public class DefaultItemCreatorTest implements WithAssertions {

  @Spy
  private IdGenerator idGenerator;

  @Mock
  private ItemRepository itemRepository;

  @InjectMocks
  private DefaultItemCreator creator;

  @Test
  public void createWhenGeneratorThrowsError() {

    when(idGenerator.generate()).thenThrow(new IllegalArgumentException());

    final var response = creator.create(ItemCreator.CreateItemCommand.builder().build());

    assertThat(response.isLeft()).isTrue();
    assertThat(response.getLeft()).isInstanceOf(IllegalArgumentException.class);

  }

  @Test
  public void createWithSuccessFlow() {

    final var item = buildItem();
    when(itemRepository.persist(Mockito.any(Item.class))).thenReturn(item);

    final var response = creator.create(ItemCreator.CreateItemCommand.builder()
      .title(item.getTitle())
      .categoryId(item.getCategoryId())
      .value(BigDecimal.TEN)
      .currency(Currency.COP)
      .attributes(List.of(ItemAttribute.builder().build()))
      .build());

    assertThat(response.isLeft()).isFalse();
    assertThat(response.isRight()).isTrue();
    assertThat(response.get().getTitle()).isEqualTo("zapatillas");

  }

  private Item buildItem() {
    final var item = Item.create("001", "zapatillas", "SNEAKERS");
    item.addPrice(BigDecimal.TEN, Currency.COP);

    return item;
  }
}

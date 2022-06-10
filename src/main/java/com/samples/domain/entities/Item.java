package com.samples.domain.entities;

import com.samples.domain.exceptions.InvalidItemException;
import com.samples.domain.vo.Currency;
import com.samples.domain.vo.ItemAttribute;
import com.samples.domain.vo.ItemStatus;
import com.samples.domain.vo.Price;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * This entity allows to manage an item.
 */
@Getter
@AllArgsConstructor
public class Item {

  private String id;

  private String title;

  private Price price;

  private String categoryId;

  private List<ItemAttribute> attributes;

  private ItemStatus status;

  public static Item create(final String id, final String title, final String categoryId) {
    validateItem(categoryId, title);
    return new Item(id, title, Price.buildDefault(), categoryId, new ArrayList<>(), ItemStatus.ACTIVE);
  }

  public void addPrice(final BigDecimal value, final Currency currency) {
    this.price = Price.create(value, currency);
  }

  public void addAttributes(final List<ItemAttribute> attributes) {
    this.attributes.addAll(attributes);
  }

  private static void validateItem(final String categoryId, final String title) {
    validateCategoryId(categoryId);
    validateTitle(title);
  }

  private static void validateCategoryId(final String categoryId) {
    if (StringUtils.isBlank(categoryId)) {
      throw new InvalidItemException("The category id should not be null");
    }
  }

  private static void validateTitle(final String title) {
    if (StringUtils.isBlank(title)) {
      throw new InvalidItemException("The item title id should not be null");
    }
  }
}

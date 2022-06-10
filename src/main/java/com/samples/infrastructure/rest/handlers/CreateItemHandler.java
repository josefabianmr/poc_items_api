package com.samples.infrastructure.rest.handlers;

import com.samples.domain.vo.Currency;
import io.vertx.ext.web.RoutingContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The creation item handler.
 */
@FunctionalInterface
public interface CreateItemHandler extends Consumer<RoutingContext> {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class CreateItemRequest {

    private String title;

    private String categoryId;

    private BigDecimal value;

    private Currency currency;

    private List<Attribute> attributes;
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  class CreateItemResponse {

    private String id;

    private String title;
  }


  /**
   * The attribute definition.
   */
  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  class Attribute {

    private String id;

    private String name;

    private String valueName;
  }

}

package com.samples.infrastructure.rest.vertx.handlers;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

import com.samples.application.usecases.create.ItemCreator;
import com.samples.domain.entities.Item;
import com.samples.domain.exceptions.InvalidAmountException;
import com.samples.domain.exceptions.InvalidItemException;
import com.samples.domain.vo.ItemAttribute;
import com.samples.infrastructure.factories.ItemCreatorFactory;
import com.samples.infrastructure.rest.handlers.AbstractHandler;
import com.samples.infrastructure.rest.handlers.CreateItemHandler;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This handler implements {@link com.samples.infrastructure.rest.handlers.CreateItemHandler}.
 */
@Singleton
public class VertxCreateItemHandler extends AbstractHandler implements CreateItemHandler {

  private final ItemCreator itemCreator;

  @Inject
  public VertxCreateItemHandler(final ItemCreatorFactory itemCreatorFactory) {
    this.itemCreator = itemCreatorFactory.create();
  }

  @Override
  public void accept(final RoutingContext ctx) {
    ctx.request().bodyHandler(body -> {
      final CreateItemRequest request = body.toJsonObject().mapTo(CreateItemRequest.class);
      itemCreator.create(buildCommand(request))
        .fold(
          error ->
            Match(error).of(
              Case($(instanceOf(InvalidAmountException.class)), () -> buildBadRequest(ctx)),
              Case($(instanceOf(InvalidItemException.class)), () -> buildBadRequest(ctx)),
              Case($(), () -> buildServiceError(ctx))
            ),
          item ->
            ctx.response()
              .setStatusCode(HttpResponseStatus.CREATED.code())
              .send(Json.encodePrettily(buildResponse(item)))
        );
    });
  }

  private ItemCreator.CreateItemCommand buildCommand(final CreateItemRequest request) {
    return ItemCreator.CreateItemCommand.builder()
      .title(request.getTitle())
      .categoryId(request.getCategoryId())
      .value(request.getValue())
      .currency(request.getCurrency())
      .attributes(request.getAttributes().stream()
        .map(attribute -> ItemAttribute.builder()
          .id(attribute.getId())
          .name(attribute.getName())
          .valueName(attribute.getValueName())
          .build())
        .collect(Collectors.toList())
      )
      .build();
  }

  private CreateItemResponse buildResponse(final Item item) {
    return CreateItemResponse.builder().id(item.getId()).title(item.getTitle()).build();
  }
}

package com.samples.infrastructure.rest.handlers;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

/**
 * The abstract handler.
 */
public abstract class AbstractHandler {

  protected Future<Void> buildServiceError(final RoutingContext ctx) {
    return ctx.response()
      .setStatusCode(HttpResponseStatus.SERVICE_UNAVAILABLE.code())
      .send();
  }

  protected Future<Void> buildBadRequest(final RoutingContext ctx) {
    return ctx.response()
      .setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
      .send();
  }

}

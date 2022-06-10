package com.samples.infrastructure.rest.vertx;

import com.samples.infrastructure.config.DefaultModule;
import com.samples.infrastructure.rest.handlers.CreateItemHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  private static final String BASE_PATH = "/items";

  private final Router router;

  private final CreateItemHandler createItemHandler;

  public MainVerticle() {
    this.router = Router.router(vertx);
    this.createItemHandler = DefaultModule.getInjector().getInstance(CreateItemHandler.class);
  }

  @Override
  public void start() {
    router.post(BASE_PATH).handler(createItemHandler::accept);
    run();
  }

  private void run() {
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(9191)
      .onSuccess(server ->
        System.out.println(
          "HTTP server started on port " + server.actualPort()
        )
      );
  }
}

package com.samples.infrastructure.config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.samples.domain.repositories.ItemRepository;
import com.samples.domain.services.IdGenerator;
import com.samples.infrastructure.repositories.InMemoryItemRepository;
import com.samples.infrastructure.rest.handlers.CreateItemHandler;
import com.samples.infrastructure.rest.vertx.handlers.VertxCreateItemHandler;
import com.samples.infrastructure.services.InMemoryIdGenerator;

/**
 * The config module.
 */
public class DefaultModule extends AbstractModule {

  @Override
  protected void configure() {

    //Repositories
    bind(ItemRepository.class).to(InMemoryItemRepository.class);

    //Services
    bind(IdGenerator.class).to(InMemoryIdGenerator.class);

    //Handlers
    bind(CreateItemHandler.class).to(VertxCreateItemHandler.class);

  }

  public static Injector getInjector() {
    return Guice.createInjector(new DefaultModule());
  }
}

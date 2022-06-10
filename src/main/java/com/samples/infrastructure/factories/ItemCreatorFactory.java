package com.samples.infrastructure.factories;

import com.samples.application.usecases.create.DefaultItemCreator;
import com.samples.application.usecases.create.ItemCreator;
import com.samples.domain.repositories.ItemRepository;
import com.samples.domain.services.IdGenerator;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * This factory allows to create the item creator use case.
 */
@Singleton
public class ItemCreatorFactory {

  private final Provider<ItemRepository> itemRepositoryProvider;

  private final Provider<IdGenerator> idGeneratorProvider;

  @Inject
  public ItemCreatorFactory(final Provider<ItemRepository> itemRepositoryProvider,
                            final Provider<IdGenerator> idGeneratorProvider) {
    this.itemRepositoryProvider = itemRepositoryProvider;
    this.idGeneratorProvider = idGeneratorProvider;
  }

  public ItemCreator create() {
    return new DefaultItemCreator(idGeneratorProvider.get(), itemRepositoryProvider.get());
  }

}

package com.samples.infrastructure.services;

import com.samples.domain.services.IdGenerator;
import java.util.UUID;

/**
 * This service implements {@link IdGenerator}.
 */
public class InMemoryIdGenerator implements IdGenerator {

  /**
   * {@inheritDoc}.
   */
  @Override
  public String generate() {
    return UUID.randomUUID().toString();
  }
}

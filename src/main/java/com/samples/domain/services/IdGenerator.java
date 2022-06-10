package com.samples.domain.services;

/**
 * The id generator service.
 */
public interface IdGenerator {

  /**
   * Generate new unique id.
   *
   * @return the unique id.
   */
  String generate();
}

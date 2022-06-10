package com.samples.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The attribute definition.
 */
@Getter
@Builder
@AllArgsConstructor
public class ItemAttribute {

  private final String id;

  private final String name;

  private final String valueName;
}

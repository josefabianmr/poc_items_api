package com.samples.domain.vo;

import com.samples.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The prices value.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {

    private final BigDecimal value;

    private final Currency currency;

    public static Price create(final BigDecimal value, final Currency currency) {
        validate(value);
        return new Price(value, currency);
    }

    private static void validate(final BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) > 0) {
            throw new InvalidAmountException("The amount must be greater than zero");
        }
    }

    public static Price buildDefault() {
        return new Price(BigDecimal.ZERO, Currency.ARS);
    }
}
package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class CoinCalculatorTest {

    @Test
    void testValidScenario1() {
        List<Double> denominations = Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0);
        double targetAmount = 7.03;

        // Update expected result to ascending order
        List<Double> expected = Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0);
        List<Double> result = CoinCalculator.getMinimumCoins(targetAmount, denominations);

        assertEquals(expected, result);
    }

    @Test
    void testValidScenario2() {
        List<Double> denominations = Arrays.asList(1.0, 2.0, 50.0);
        double targetAmount = 103.0;

        // Update expected result to ascending order
        List<Double> expected = Arrays.asList(1.0, 2.0, 50.0, 50.0);
        List<Double> result = CoinCalculator.getMinimumCoins(targetAmount, denominations);

        assertEquals(expected, result);
    }

    @Test
    void testTargetAmountZero() {
        List<Double> denominations = Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0);
        double targetAmount = 0.0;

        List<Double> expected = Collections.emptyList();
        List<Double> result = CoinCalculator.getMinimumCoins(targetAmount, denominations);

        assertEquals(expected, result);
    }

    @Test
    void testTargetAmountExceedsLimit() {
        List<Double> denominations = Arrays.asList(1.0, 2.0, 5.0);
        double targetAmount = 20000.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CoinCalculator.getMinimumCoins(targetAmount, denominations));

        assertEquals("Target amount must be between 0 and 10,000.00", exception.getMessage());
    }

    @Test
    void testNegativeTargetAmount() {
        List<Double> denominations = Arrays.asList(1.0, 2.0, 5.0);
        double targetAmount = -5.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CoinCalculator.getMinimumCoins(targetAmount, denominations));

        assertEquals("Target amount must be between 0 and 10,000.00", exception.getMessage());
    }

    @Test
    void testEmptyDenominations() {
        List<Double> denominations = Collections.emptyList();
        double targetAmount = 50.0;

        List<Double> expected = Collections.emptyList();
        List<Double> result = CoinCalculator.getMinimumCoins(targetAmount, denominations);

        assertEquals(expected, result);
    }
}

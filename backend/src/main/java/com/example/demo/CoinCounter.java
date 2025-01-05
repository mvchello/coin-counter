package com.example.demo;

import java.util.*;

public class CoinCounter {

    // Method to calculate minimum coins
    public static List<Double> getMinimumCoins(double targetAmount, List<Double> denominations) {
        if (targetAmount < 0 || targetAmount > 10000.00) {
            throw new IllegalArgumentException("Target amount must be between 0 and 10,000.00");
        }

        // Sort denominations in descending order for easier processing
        denominations.sort(Collections.reverseOrder());

        List<Double> result = new ArrayList<>();
        for (double coin : denominations) {
            while (targetAmount >= coin) {
                targetAmount = Math.round((targetAmount - coin) * 100.0) / 100.0; // Avoid floating-point errors
                result.add(coin);
            }
        }

        // Sort result in ascending order before returning
        result.sort(Double::compareTo);

        return result;
    }

    public static void main(String[] args) {
        // Example usage
        List<Double> denominations1 = Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0);
        double targetAmount1 = 7.03;
        System.out.println("Example 1: " + getMinimumCoins(targetAmount1, denominations1));

        List<Double> denominations2 = Arrays.asList(1.0, 2.0, 50.0);
        double targetAmount2 = 103.0;
        System.out.println("Example 2: " + getMinimumCoins(targetAmount2, denominations2));
    }
}

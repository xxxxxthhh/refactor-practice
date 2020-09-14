package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;
    private String countType;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
        this.countType = "";
    }

    public int countEven() {
        this.countType = "Even";
        return countMethod(countType);
    }

    public int countOdd() {
        this.countType = "Odd";
        return countMethod(countType);
    }

    public int countPositive() {
        this.countType = "Positive";
        return countMethod(countType);
    }

    public int countNegative() {
        this.countType = "negative";
        return countMethod(countType);
    }

    public int countMethod(String countType) {
        int count = 0;
        for (int number : numbers) {
            count = getCount(countType, count, "negative", number < 0);
            count = getCount(countType, count, "Positive", number >= 0);
            count = getCount(countType, count, "Odd", number %2 == 1);
            count = getCount(countType, count, "Even", number %2 == 0);
        }
        return count;
    }

    private int getCount(String countType, int count, String type, boolean b) {
        if (countType.equals(type) && b) count++;
        return count;
    }
}

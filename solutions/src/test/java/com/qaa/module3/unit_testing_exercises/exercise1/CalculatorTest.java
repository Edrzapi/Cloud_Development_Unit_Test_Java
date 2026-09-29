package com.qaa.module3.unit_testing_exercises.exercise1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SOLUTION for exercise 1 - testing existing code.
 *
 * Three or more test cases per method, covering normal values and borderline
 * values, as the exercise guide asks for.
 */
class CalculatorTest {

    /** Tolerance for double comparisons. */
    private static final double DELTA = 0.0001;

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ------------------------------------------------------------------ add

    @Test
    @DisplayName("add returns 40 when adding 10 and 30")
    void addReturnsSumOfTwoSmallNumbers() {
        // arrange
        double num1 = 10;
        double num2 = 30;

        // act
        double actual = calculator.add(num1, num2);

        // assert
        assertEquals(40, actual, DELTA);
    }

    @Test
    @DisplayName("add returns a negative total when both numbers are negative")
    void addReturnsNegativeTotalForTwoNegativeNumbers() {
        // arrange
        double num1 = -10;
        double num2 = -30;

        // act
        double actual = calculator.add(num1, num2);

        // assert
        assertEquals(-40, actual, DELTA);
    }

    @Test
    @DisplayName("add returns the other number unchanged when one number is zero")
    void addReturnsOtherNumberWhenOneNumberIsZero() {
        // arrange
        double num1 = 10;
        double num2 = 0;

        // act
        double actual = calculator.add(num1, num2);

        // assert
        assertEquals(10, actual, DELTA);
    }

    @Test
    @DisplayName("add overflows to positive infinity at the top of the double range")
    void addOverflowsToInfinityAtTheTopOfTheDoubleRange() {
        // arrange
        double num1 = Double.MAX_VALUE;
        double num2 = Double.MAX_VALUE;

        // act
        double actual = calculator.add(num1, num2);

        // assert
        // This is the borderline case the guide asks about: the largest total
        // a double can hold. Past it, Java does not throw, it returns infinity.
        assertTrue(Double.isInfinite(actual), "expected positive infinity but was " + actual);
    }

    // ------------------------------------------------------------- subtract

    @Test
    @DisplayName("subtract returns 20 when subtracting 10 from 30")
    void subtractReturnsDifferenceOfTwoNormalNumbers() {
        // arrange
        double num1 = 30;
        double num2 = 10;

        // act
        double actual = calculator.subtract(num1, num2);

        // assert
        assertEquals(20, actual, DELTA);
    }

    @Test
    @DisplayName("subtract returns a negative result when the second number is larger")
    void subtractReturnsNegativeResultWhenSecondNumberIsLarger() {
        // arrange
        double num1 = 10;
        double num2 = 30;

        // act
        double actual = calculator.subtract(num1, num2);

        // assert
        assertEquals(-20, actual, DELTA);
    }

    @Test
    @DisplayName("subtract returns zero when a number is subtracted from itself")
    void subtractReturnsZeroWhenNumberIsSubtractedFromItself() {
        // arrange
        double num1 = Double.MAX_VALUE;
        double num2 = Double.MAX_VALUE;

        // act
        double actual = calculator.subtract(num1, num2);

        // assert
        assertEquals(0, actual, DELTA);
    }

    @Test
    @DisplayName("subtract overflows to negative infinity at the bottom of the double range")
    void subtractOverflowsToNegativeInfinityAtTheBottomOfTheDoubleRange() {
        // arrange
        double num1 = -Double.MAX_VALUE;
        double num2 = Double.MAX_VALUE;

        // act
        double actual = calculator.subtract(num1, num2);

        // assert
        assertEquals(Double.NEGATIVE_INFINITY, actual);
    }

    // ------------------------------------------------------------- multiply

    @Test
    @DisplayName("multiply returns 300 when multiplying 10 by 30")
    void multiplyReturnsProductOfTwoNormalNumbers() {
        // arrange
        double num1 = 10;
        double num2 = 30;

        // act
        double actual = calculator.multiply(num1, num2);

        // assert
        assertEquals(300, actual, DELTA);
    }

    @Test
    @DisplayName("multiply returns zero when either number is zero")
    void multiplyReturnsZeroWhenEitherNumberIsZero() {
        // arrange
        double num1 = 10;
        double num2 = 0;

        // act
        double actual = calculator.multiply(num1, num2);

        // assert
        assertEquals(0, actual, DELTA);
    }

    @Test
    @DisplayName("multiply returns a positive result when both numbers are negative")
    void multiplyReturnsPositiveResultWhenBothNumbersAreNegative() {
        // arrange
        double num1 = -10;
        double num2 = -30;

        // act
        double actual = calculator.multiply(num1, num2);

        // assert
        assertEquals(300, actual, DELTA);
    }

    @Test
    @DisplayName("multiply underflows to zero for two of the smallest doubles")
    void multiplyUnderflowsToZeroForTwoVerySmallNumbers() {
        // arrange
        double num1 = Double.MIN_VALUE;
        double num2 = Double.MIN_VALUE;

        // act
        double actual = calculator.multiply(num1, num2);

        // assert
        // The true answer is far too small for a double, so the result is 0.
        assertEquals(0, actual);
    }

    // --------------------------------------------------------------- divide

    @Test
    @DisplayName("divide returns 3 when dividing 30 by 10")
    void divideReturnsQuotientOfTwoNormalNumbers() {
        // arrange
        double num1 = 30;
        double num2 = 10;

        // act
        double actual = calculator.divide(num1, num2);

        // assert
        assertEquals(3, actual, DELTA);
    }

    @Test
    @DisplayName("divide returns a fraction when the divisor is larger than the dividend")
    void divideReturnsFractionWhenDivisorIsLargerThanDividend() {
        // arrange
        double num1 = 10;
        double num2 = 30;

        // act
        double actual = calculator.divide(num1, num2);

        // assert
        assertEquals(0.3333, actual, DELTA);
    }

    @Test
    @DisplayName("divide returns the dividend unchanged when dividing by one")
    void divideReturnsDividendWhenDividingByOne() {
        // arrange
        double num1 = 30;
        double num2 = 1;

        // act
        double actual = calculator.divide(num1, num2);

        // assert
        assertEquals(30, actual, DELTA);
    }

    @Test
    @DisplayName("divide throws IllegalArgumentException when dividing by zero")
    void divideThrowsIllegalArgumentExceptionWhenDividingByZero() {
        // arrange
        double num1 = 10;
        double num2 = 0;

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(num1, num2));

        // assert
        assertEquals("Division by zero: divisor must not be 0", thrown.getMessage());
    }
}

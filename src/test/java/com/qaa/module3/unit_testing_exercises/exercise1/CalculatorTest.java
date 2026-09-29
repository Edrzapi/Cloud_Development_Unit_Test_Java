package com.qaa.module3.unit_testing_exercises.exercise1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Exercise 1 - testing existing code.
 *
 * Write your test plan first (see tasks/TEST_PLAN_TEMPLATE.md in this
 * project), then turn each row of the plan into one test method here.
 *
 * One fully worked test is provided below as an example. The rest are stubs.
 * Each stub is marked with @Disabled so that the project still builds and the
 * test suite still passes before you have written anything. Delete the
 * @Disabled line when you start work on a stub.
 *
 * Every test should have three visible steps:
 *   arrange - set up the objects and the input values
 *   act     - call the method under test, once
 *   assert  - check the result is what your test plan said it would be
 */
class CalculatorTest {

    // The object under test. A fresh one is created before every test method
    // so that no test can be affected by another test.
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ---------------------------------------------------------------------
    // WORKED EXAMPLE - this is test case 1 from the exercise guide.
    // Copy this shape for your own tests.
    // ---------------------------------------------------------------------

    @Test
    @DisplayName("add returns 40 when adding 10 and 30")
    void addReturnsSumOfTwoSmallNumbers() {
        // arrange
        double num1 = 10;
        double num2 = 30;
        double expected = 40;

        // act
        double actual = calculator.add(num1, num2);

        // assert
        // The third argument is the delta, the amount of rounding error we are
        // willing to accept. Doubles are not exact, so comparing them with a
        // plain equals is a common source of flaky tests.
        assertEquals(expected, actual, 0.0001);
    }

    // ---------------------------------------------------------------------
    // TODO stubs. Remove @Disabled and write the body of each one.
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("add returns a negative total when both numbers are negative")
    void addReturnsNegativeTotalForTwoNegativeNumbers() {
        // TODO assert that add(-10, -30) returns -40.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("add overflows to positive infinity at the top of the double range")
    void addOverflowsToInfinityAtTheTopOfTheDoubleRange() {
        // TODO borderline case: assert that adding Double.MAX_VALUE to itself
        // returns Double.POSITIVE_INFINITY rather than a number.
        // Hint: assertTrue(Double.isInfinite(actual)) reads well here.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns the difference of two normal numbers")
    void subtractReturnsDifferenceOfTwoNormalNumbers() {
        // TODO assert that subtract(30, 10) returns 20.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns a negative result when the second number is larger")
    void subtractReturnsNegativeResultWhenSecondNumberIsLarger() {
        // TODO assert that subtract(10, 30) returns -20.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns zero when a number is subtracted from itself")
    void subtractReturnsZeroWhenNumberIsSubtractedFromItself() {
        // TODO borderline case: assert that subtract(Double.MAX_VALUE,
        // Double.MAX_VALUE) returns 0.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns the product of two normal numbers")
    void multiplyReturnsProductOfTwoNormalNumbers() {
        // TODO assert that multiply(10, 30) returns 300.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns zero when either number is zero")
    void multiplyReturnsZeroWhenEitherNumberIsZero() {
        // TODO assert that multiply(10, 0) returns 0.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns a very small number when multiplying two very small numbers")
    void multiplyUnderflowsToZeroForTwoVerySmallNumbers() {
        // TODO borderline case: assert that multiply(Double.MIN_VALUE,
        // Double.MIN_VALUE) underflows to 0.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("divide returns the quotient of two normal numbers")
    void divideReturnsQuotientOfTwoNormalNumbers() {
        // TODO assert that divide(30, 10) returns 3.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("divide returns a fraction when the divisor is larger than the dividend")
    void divideReturnsFractionWhenDivisorIsLargerThanDividend() {
        // TODO assert that divide(10, 30) returns 0.3333... to a sensible delta.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("divide throws IllegalArgumentException when dividing by zero")
    void divideThrowsIllegalArgumentExceptionWhenDividingByZero() {
        // TODO borderline case: assert that divide(10, 0) throws
        // IllegalArgumentException with the message
        // "Division by zero: divisor must not be 0".
        // Hint: assertThrows returns the exception it caught, so you can then
        // assert on its getMessage().
    }
}

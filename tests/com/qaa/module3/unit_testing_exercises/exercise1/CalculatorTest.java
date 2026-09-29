package com.qaa.module3.unit_testing_exercises.exercise1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EXERCISE 1: testing existing code.
 *
 * Calculator is finished code that you are not allowed to change. Your job is to
 * prove it behaves as described, and to find out what it really does at the edges
 * of the double range.
 *
 * WHAT YOU DO HERE
 * Fill in every @Disabled stub below with a real test. Aim for at least three
 * cases per method: a normal one, an obvious one, and the borderline value where
 * a double stops being a number.
 *
 * TWO PARTS, IN THIS ORDER
 *   1. Write the test plan first. Copy tasks/TEST_PLAN_TEMPLATE.md and fill in the
 *      "Exercise 1" table: one row per case, with its inputs and its expected result.
 *   2. Then implement the plan down here, one row per test method.
 *
 * HOW THE TODOS WORK
 * Every unwritten stub carries @Disabled("TODO"), which JUnit reports as skipped
 * rather than failed, so the suite is green on a fresh clone. Delete the @Disabled
 * line to activate a stub, then write its body. The skip count is your progress bar.
 *
 * HOW TO RUN
 *   mvn test                          run everything
 *   mvn test -Dtest=CalculatorTest    run just this class
 *
 * THE FULL BRIEF
 * tasks/01_testing_existing_code.md
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

    // @BeforeEach marks a setup method that JUnit runs before EVERY @Test in this
    // class, so each test starts from a clean Calculator rather than inheriting
    // whatever the previous test left behind. That independence is what lets you
    // run one test on its own and trust the result.
    //
    // The sibling repositories spell the same idea differently: C# and NUnit use
    // [SetUp], and Python with unittest uses a method named setUp. Same mechanism,
    // three spellings.
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ---------------------------------------------------------------------
    // WORKED EXAMPLE - this is test case 1 from the exercise guide.
    // Copy this shape for your own tests. Note the name:
    // method, scenario, expected result.
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
        //
        // @DisplayName above is JUnit's way of giving a test a readable name in
        // the report. NUnit has no direct equivalent and leans on the method
        // name instead, which is why the C# sibling has no such line.
        assertEquals(expected, actual, 0.0001);
    }

    // ---------------------------------------------------------------------
    // TODO stubs - add. Remove @Disabled and write the body of each one.
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("add returns a negative total when both numbers are negative")
    void addReturnsNegativeTotalForTwoNegativeNumbers() {
        // Should assert that add(-10, -30) returns -40, comparing with a delta
        // of 0.0001 as the worked example does.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("add overflows to positive infinity at the top of the double range")
    void addOverflowsToInfinityAtTheTopOfTheDoubleRange() {
        // Borderline case. Should assert that add(Double.MAX_VALUE, Double.MAX_VALUE)
        // returns Double.POSITIVE_INFINITY rather than a number.
        // Hint: assertTrue(Double.isInfinite(actual)) reads well here.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - subtract
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns the difference of two normal numbers")
    void subtractReturnsDifferenceOfTwoNormalNumbers() {
        // Should assert that subtract(30, 10) returns 20.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns a negative result when the second number is larger")
    void subtractReturnsNegativeResultWhenSecondNumberIsLarger() {
        // Should assert that subtract(10, 30) returns -20, that is, that taking a
        // bigger number from a smaller one goes below zero.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("subtract returns zero when a number is subtracted from itself")
    void subtractReturnsZeroWhenNumberIsSubtractedFromItself() {
        // Borderline case. Should assert that subtract(Double.MAX_VALUE,
        // Double.MAX_VALUE) returns exactly 0, even at the top of the range.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - multiply
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns the product of two normal numbers")
    void multiplyReturnsProductOfTwoNormalNumbers() {
        // Should assert that multiply(10, 30) returns 300.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns zero when either number is zero")
    void multiplyReturnsZeroWhenEitherNumberIsZero() {
        // Should assert that multiply(10, 0) returns 0, that is, that multiplying
        // by zero always gives zero.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("multiply returns a very small number when multiplying two very small numbers")
    void multiplyUnderflowsToZeroForTwoVerySmallNumbers() {
        // Borderline case at the smallest end. Should assert that
        // multiply(Double.MIN_VALUE, Double.MIN_VALUE) underflows to 0.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - divide
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("divide returns the quotient of two normal numbers")
    void divideReturnsQuotientOfTwoNormalNumbers() {
        // Should assert that divide(30, 10) returns 3.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("divide returns a fraction when the divisor is larger than the dividend")
    void divideReturnsFractionWhenDivisorIsLargerThanDividend() {
        // Should assert that divide(10, 30) returns 0.3333... to a sensible delta.
        // This is the test where the delta argument earns its keep: the exact value
        // does not fit in a double, so pick the precision you are prepared to accept.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("divide throws IllegalArgumentException when dividing by zero")
    void divideThrowsIllegalArgumentExceptionWhenDividingByZero() {
        // Borderline case. Should assert that divide(10, 0) throws
        // IllegalArgumentException with the message
        // "Division by zero: divisor must not be 0".
        // Hint: assertThrows returns the exception it caught, so you can then
        // assert on its getMessage().
    }
}

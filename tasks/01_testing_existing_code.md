# Exercise 1 - Testing existing code

This exercise uses the `Calculator` class found in the `exercise1` package of this
repository.

| | |
|---|---|
| Class under test | `src/exercise1/Calculator.java` |
| Test class you edit | `tests/exercise1/CalculatorTest.java` |
| Plan | [TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md), the "Exercise 1 - Calculator" table |

Clone this repository and open the folder holding `pom.xml` as a Maven project in your IDE
to get started. IntelliJ IDEA, Eclipse and VS Code will all import it directly.

## Part 1 - Create a test plan

In this exercise, you are required to create a test plan which consists of test cases for
the `Calculator` class's 4 methods. Use the following template for creating your test
cases:

| ID | Method | Description | Inputs | Expected output | Actual output |
|----|--------|-------------|--------|-----------------|---------------|
| 1 | `add(double num1, double num2)` | Adding two small numbers | num1=10, num2=30 | 40 | |
| 2 | | | | | |
| 3 | | | | | |
| 4 | | | | | |

One test case has been created for you as an example. It is expected that you produce at
least three test cases per method:

- Test borderline input values, i.e., what are the highest values you can add? What about
  the smallest?
- Test at least one normal input combination.

The same table, with room to fill in, is in
[TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md).

Borderline values are the point of this exercise, so think about what a `double` can
actually hold. What is `Double.MAX_VALUE + Double.MAX_VALUE`? What does dividing by zero
give you? Write down what you expect **before** you run it.

## Part 2 - Implement your test plan

The `Calculator` class has already been created; use your test plan to guide the
development of tests for the methods of this class.

Open `tests/exercise1/CalculatorTest.java`.
One fully worked test is already there, the `add` row from the table above. The rest are
`@Disabled` stubs, one per row you are expected to write. Delete the `@Disabled` line as
you start each one.

Run your tests with:

```bash
mvn test
```

or, while you are working on this class only:

```bash
mvn test -Dtest=CalculatorTest
```

## Done looks like

Every `CalculatorTest` stub you have taken on has moved from skipped to passing, and the
skipped count has fallen by the same number. At the start that filtered run reports:

```
Tests run: 12, Failures: 0, Errors: 0, Skipped: 11
```

When exercise 1 is finished, all 12 `CalculatorTest` tests run and pass, and nothing in
that class is skipped.

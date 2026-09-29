# Exercise 2 - Testing exceptions

This exercise uses the `UserService` class defined in the `exercise2` package of this
repository.

| | |
|---|---|
| Class under test | `src/exercise2/UserService.java` |
| Test class you edit | `tests/exercise2/UserServiceTest.java` |
| Plan | [TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md), the "Exercise 2 - UserService" table |

Clone this repository and open the folder holding `pom.xml` as a Maven project in your IDE
to get started.

## Part 1 - Create a test plan

In this exercise, you are required to create a test plan which consists of test cases for
the `UserService` class's two methods. Use the following template for creating your test
cases:

| ID | Method | Desc. | Inputs | Exp. Output | Act. Output |
|----|--------|-------|--------|-------------|-------------|
| 1 | `login(String username, String password)` | Register a valid user, login successfully with said valid user. | Register username="bobby" password="Codes123". Login username="bobby" password="Codes123" | `"bobby"` | |
| 2 | `register(String username, String password)` | Register a user with an invalid password due to missing number. | Register username="bobby" password="Codes" | `IllegalArgumentException("Password must contain at least 1 number character")` | |
| 3 | | | | | |
| 4 | | | | | |

> **Footnote on row 2.** This row is reproduced exactly as the original course guide prints
> it, and it is wrong. `"Codes"` is only 5 characters long, and `register` checks the
> length rule before any of the character rules, so the exception you actually get is
> `IllegalArgumentException("Password must contain at least 6 characters")`. Use a longer
> password such as `"Codesss"` if you want to reach the number rule. Read the rules in
> `UserService.register` in order and you will see why. This is noted in
> `CODE_CORRECTIONS.md`, issue 3.

Two example test cases have been created for you. It is expected that you produce a test
case for every possible exception that could be thrown.

Read `UserService.register` and `UserService.login` line by line and list every `throw` you
find, including the ones for null and whitespace-only inputs. There are more than you would
guess. Cover the borderline values too: a username of exactly 4 characters is allowed, 3 is
not; a password of exactly 6 is allowed, 5 is not.

## Part 2 - Implement your test plan

The `UserService` class has already been created; use your test plan to guide the
development of tests for the methods of this class.

Open `tests/exercise2/UserServiceTest.java`.
One fully worked test is provided and the rest are `@Disabled` stubs. Delete the
`@Disabled` line as you start each one.

The pattern for testing an exception in JUnit 5 is:

```java
IllegalArgumentException thrown = assertThrows(
        IllegalArgumentException.class,
        () -> service.register(null, "Codes123"));
assertEquals("Username must not be null", thrown.getMessage());
```

`assertThrows` fails the test if the code inside the lambda does **not** throw, and returns
the exception it caught so that you can assert on the message. Assert on the message, not
just the type: a method that throws the right kind of exception with the wrong explanation
in it is still wrong.

Run your tests with:

```bash
mvn test
```

or, while you are working on this class only:

```bash
mvn test -Dtest=UserServiceTest
```

## Done looks like

All 17 `UserServiceTest` tests run and pass, with nothing skipped in that class.

Once you have finished, read `CODE_CORRECTIONS.md` in the root of this repository. This code
was ported from an older exercise repository and carried two real defects, which have since
been fixed; the file records what they were, how a test finds them, and what changed. It is
worth reading even though the code is now correct, because finding faults like those is
exactly what this exercise is training you to do.

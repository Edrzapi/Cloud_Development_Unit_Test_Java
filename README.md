# Unit testing exercises (Java)

This repository is your workspace for **SDL3 Module 5: Testing**. It holds some small
pieces of Java code, a set of half-written test classes, and the exercise guide that tells
you how to finish them. You write the tests; you do not change the code under test.

**The exercise guide is in this repository: [tasks/README.md](tasks/README.md).** You do
not need the PDF. Everything is there, one page per exercise, with a test plan template
beside it. Start there once you have the project running.

Three exercises build up from testing plain methods, to testing exceptions, to mocking a
collaborator with Mockito, plus a test-driven stretch task at the end.

## Prerequisites and setup

You need:

- **Java 17.** Check with `java -version`; it should report a `17.x` version. The project
  is compiled at release 17 and will not build on an older JDK.
- **Maven 3.9 or newer.** Check with `mvn -v`. Maven is required: it fetches JUnit and
  Mockito for you and runs the tests. There is nothing else to install.
- An internet connection the first time you build, so Maven can download the test
  libraries into its local cache.

Then clone the repository and open the `java` folder, the one holding `pom.xml`:

- **IntelliJ IDEA:** File, Open, select the `java` folder. IntelliJ recognises `pom.xml`
  and imports it as a Maven project automatically.
- **Eclipse:** File, Import, Maven, Existing Maven Projects, then select the `java` folder.
- **VS Code:** open the `java` folder with the Extension Pack for Java installed.

You do not have to use an IDE. The command line on its own is enough for every exercise:
an editor plus `mvn test` will get you all the way through.

## How to run the tests

From the `java` folder, the one holding `pom.xml`:

```bash
mvn test
```

On a fresh clone that build **passes**. This is what a green run looks like:

```
[INFO] Results:
[INFO]
[WARNING] Tests run: 47, Failures: 0, Errors: 0, Skipped: 44
[INFO]
[INFO] BUILD SUCCESS
```

Do not be alarmed by 44 skipped. Almost every test method is an unfinished stub marked
`@Disabled`, and JUnit reports a disabled test as skipped rather than failed, so the project
builds cleanly before you have written a line.

**The skip count is your progress bar.** It starts at 44 and should fall by one every time
you finish a stub. When you have done the lot it reaches 0, and 47 tests run and pass.

While you are working on a single class, run just that class:

```bash
mvn test -Dtest=CalculatorTest
```

## How to do one TODO

Each stub looks roughly like this:

```java
@Test
@Disabled("TODO")
@DisplayName("add returns a negative total when both numbers are negative")
void addReturnsNegativeTotalForTwoNegativeNumbers() {
    // TODO assert that add(-10, -30) returns -40.
}
```

1. **Delete the `@Disabled("TODO")` line.** That alone turns the test on, and the skip count
   falls by one.
2. **Replace the `// TODO` comment with three steps**, in this order, and label them with
   comments so the shape stays visible:
   - *arrange:* create the object and the input values your test plan row specifies.
   - *act:* call the method under test, once, and keep the result in a variable.
   - *assert:* check that result against the expected value from your plan.
3. Run `mvn test` again. The test should now run and pass.

The first test in `CalculatorTest` is already written for you as a worked example with those
three steps labelled. Copy its shape.

Do one at a time. Do not delete every `@Disabled` at once, or you will be staring at
dozens of failures with no idea which is which.

## What code you are working with

All of it is under `src/main/java/com/qaa/module3/unit_testing_exercises/`. Do not change
it; your job is to test it.

### `exercise1/Calculator.java` - class `Calculator`

A no-argument constructor, `new Calculator()`, and four methods. Every parameter and every
return value is a `double`.

| Method | What it does |
|---|---|
| `double add(double num1, double num2)` | Returns `num1 + num2`. |
| `double subtract(double num1, double num2)` | Returns `num1 - num2`. |
| `double multiply(double num1, double num2)` | Returns `num1 * num2`. |
| `double divide(double num1, double num2)` | Returns `num1 / num2`, but throws `IllegalArgumentException("Division by zero: divisor must not be 0")` when `num2` is `0`. |

Because these return `double`, compare them with the three-argument
`assertEquals(expected, actual, delta)`, where the delta is the rounding error you will
accept. A plain two-argument comparison of doubles is a classic source of flaky tests.

### `exercise2/UserService.java` - class `UserService`

A no-argument constructor, `new UserService()`, which starts with an empty in-memory store
of users. Two methods, both returning the trimmed username as a `String`.

| Method | What it does |
|---|---|
| `String register(String username, String password)` | Validates the username and password against the rules below, stores the pair, and returns the trimmed username. Throws `IllegalArgumentException` with a specific message for each rule broken. |
| `String login(String username, String password)` | Looks the user up and checks the password. Returns the trimmed username. Throws `IllegalArgumentException` for null, empty or wrong-password input, and `RuntimeException("Invalid username supplied")` when no such user exists. |

Both methods trim their arguments before doing anything else. `register` checks its rules
in a fixed order, and the first one broken is the one you get an exception for: username not
null, username not blank, password not null, password not blank, username at least 4
characters, username not already taken, password at least 6 characters, password contains an
uppercase letter, a lowercase letter and a digit. Part of the exercise is discovering that
order for yourself by reading the messages the tests report.

### `exercise3/User.java` - class `User`

A plain data object holding the user's details.

| Member | Notes |
|---|---|
| `User()` | No-argument constructor. |
| `User(int id, String username, String password)` | Full constructor. |
| `int getId()` / `void setId(int id)` | |
| `String getUsername()` / `void setUsername(String username)` | |
| `String getPassword()` / `void setPassword(String password)` | |
| `boolean equals(Object obj)`, `int hashCode()`, `String toString()` | All overridden. `equals` compares all three fields, so two separately created `User` objects with the same id, username and password are equal, and `assertEquals` on them passes. |

### `exercise3/UserRepository.java` - interface `UserRepository`

An interface only. There is no class in the repository that implements it, which is the
whole point of exercise 3: Mockito makes a stand-in.

| Method | What it is for |
|---|---|
| `boolean exists(String trimmedUsername)` | Says whether that username is already taken. |
| `User register(User user)` | Stores the user and returns the stored `User`. |
| `User login(User user)` | Looks the user up and returns the matching `User`. |

### `exercise3/UserController.java` - class `UserController`

| Member | What it does |
|---|---|
| `UserController(UserRepository userRepository)` | Constructor. The controller takes its repository as a constructor argument, which is what lets a test hand it a mock. |
| `User register(User user)` | Throws `IllegalArgumentException("User must not be null")` for a null user, then applies the same field rules as `UserService.register`, using `repository.exists(...)` for the uniqueness check, and finally returns `repository.register(user)`. |
| `User login(User user)` | Throws `IllegalArgumentException` for a null user, or null or empty username or password, then returns `repository.login(user)`. |

Note the differences from exercise 2: `register` gains the null-`User` exception, and
`login` has fewer of its own exceptions, because the repository now does the looking up.

The stretch task asks you to write `ConcreteUserRepository`, your own real implementation
of `UserRepository` backed by a `List<User>`, test-first.

## The framework

This project uses **JUnit 5 (Jupiter)** for the tests and **Mockito** for the mocking in
exercise 3. Both are already declared in `pom.xml`; there is nothing to install or
configure. These are the annotations and methods you will actually type.

### JUnit 5

| Thing | What it is for |
|---|---|
| `@Test` | Marks a method as a test. JUnit runs every method carrying it. |
| `@BeforeEach` | Marks a method that runs before *every* test in the class, used to create a fresh object under test so no test can affect another. |
| `@AfterEach` | Marks a method that runs after every test, for tidying up. |
| `@Disabled` | Tells JUnit to skip this test. Every unfinished stub carries one; delete it to switch the test on. |
| `@DisplayName("...")` | Gives the test a readable name in the report. |
| `assertEquals(expected, actual)` | Fails unless the two are equal. For doubles use `assertEquals(expected, actual, delta)`. |
| `assertTrue(condition)` | Fails unless the condition is `true`. `assertFalse` is its opposite. |
| `assertThrows(SomeException.class, () -> code)` | Fails unless running that code throws that exception. It returns the exception it caught, so you can then assert on its message. |
| `assertAll(...)` | Runs several assertions and reports all of their failures, rather than stopping at the first. |

Import them statically, for example
`import static org.junit.jupiter.api.Assertions.assertEquals;`. The stubs already have the
imports you need at the top of each file.

**`assertEquals` takes expected first, then actual.** Getting them the wrong way round is
the most common early mistake. The test still passes or fails correctly, but the failure
message reads backwards, telling you it expected your result and got the right answer,
which will waste your time.

### Mockito

| Thing | What it is for |
|---|---|
| `@ExtendWith(MockitoExtension.class)` | Goes on the test class. It is what makes `@Mock` and `@InjectMocks` do anything under JUnit 5. |
| `@Mock` | Creates a fake stand-in for a type, here `UserRepository`. Its methods do nothing and return defaults until you tell them otherwise. |
| `@InjectMocks` | Creates the real object under test, here `UserController`, and passes the mocks into its constructor for you. |
| `when(mock.method(args)).thenReturn(value)` | Tells the mock what to return for that call. This is the "arrange" half of a mocking test. `thenThrow(...)` makes it throw instead. |
| `verify(mock).method(args)` | Checks the controller really did call that method with those arguments. This is the part that tests the interaction rather than the return value. |

Import these statically too, for example
`import static org.mockito.Mockito.when;`.

## Sibling repositories

The same exercises exist in **Python** and **C#**, in folders beside this one. They cover
identical ground with the test framework native to each language, and each carries a table
in its own README explaining how the ideas here are spelled there. If Java is not your
language, use the one that is; the test plans and the reasoning transfer unchanged.

# Unit testing exercises (Java)

Exercises for **SDL3 Module 5: Testing**.

Three exercises, building up from testing plain methods, to testing exceptions, to mocking
a collaborator with Mockito, plus a test-driven stretch task.

**The exercise guide is in this repository: [tasks/README.md](tasks/README.md).** You do
not need the PDF; everything is there, with the test plan template beside it.

## What you need

- Java 17 (`java -version`)
- Maven 3.9 or newer (`mvn -v`)
- Any IDE that opens a Maven project: Eclipse, IntelliJ IDEA, VS Code

## Getting started

```bash
git clone <this repository>
cd java
mvn test
```

The suite should pass immediately. That is expected: most of the test methods are
unfinished stubs marked `@Disabled`, so they are reported as skipped rather than failed.
Your job is to fill them in, one at a time, deleting the `@Disabled` line as you go.

```
Tests run: 47, Failures: 0, Errors: 0, Skipped: 44
```

Run a single test class while you work on it:

```bash
mvn test -Dtest=CalculatorTest
```

## Layout

```
java/
  pom.xml                     JUnit 5.10.2, Mockito 5.11.0, Java 17
  README.md                   this file
  tasks/                      the exercise guide, one page per exercise
  tasks/README.md             start here: contents, timings, prerequisites
  tasks/TEST_PLAN_TEMPLATE.md the test plan tables, ready to fill in
  CODE_CORRECTIONS.md             READ THIS AFTER EXERCISES 1 AND 2, not before
  src/main/java/...           the code under test, do not change it
  src/test/java/...           your tests, one skeleton class per exercise
  solutions/                  the completed tests, kept out of the normal build
```

## The exercises

### Exercise 1 - testing existing code

`src/main/java/.../exercise1/Calculator.java`, tested in
`src/test/java/.../exercise1/CalculatorTest.java`.

Full instructions: [tasks/01_testing_existing_code.md](tasks/01_testing_existing_code.md).

1. Write a test plan for the four methods, using the table in
   `tasks/TEST_PLAN_TEMPLATE.md`. At
   least three cases per method. Cover borderline values, not only comfortable ones: what
   is the largest total a `double` can hold? What happens past it?
2. Turn each row into a test.

One fully worked test is already there, the `add` row the guide supplies.

### Exercise 2 - testing exceptions

`src/main/java/.../exercise2/UserService.java`, tested in
`src/test/java/.../exercise2/UserServiceTest.java`.

Full instructions: [tasks/02_testing_exceptions.md](tasks/02_testing_exceptions.md).

1. Write a test plan covering **every** exception `register` and `login` can throw.
2. Implement it, using `assertThrows` and checking the exception message, not just its
   type.

Record what really happened in the "actual output" column of your plan as you go. One row
of the original guide's own example table does not match the code; the footnote in the task
page says why. Afterwards, read `CODE_CORRECTIONS.md`: this code was ported from an older
exercise repository that carried two real defects, both since fixed, and the file records
what they were and what changed.

### Exercise 3 - mocking in a unit test

`src/main/java/.../exercise3/`, tested in
`src/test/java/.../exercise3/UserControllerTest.java`.

`UserController` depends on a `UserRepository`, which is only an interface. Mockito creates
a stand in for it so that the controller can be tested on its own.

Full instructions: [tasks/03_mocking.md](tasks/03_mocking.md).

1. Update your exercise 2 plan for the controller. Add a **Class** column. `register` has a
   new exception; `login` has fewer than before.
2. Implement the tests with `@Mock`, `@InjectMocks`, `when(...).thenReturn(...)` and
   `verify(...)`.

**Stretch task:** plan and then test drive a `ConcreteUserRepository` that implements
`UserRepository` and stores its users in a `List<User>`. Write each test before the method
that satisfies it. Full instructions:
[tasks/04_stretch_tdd_repository.md](tasks/04_stretch_tdd_repository.md).

## Running the solutions

The completed answers live in `solutions/src/test/java` and are **not** part of the normal
build, so `mvn test` never runs them and you will not stumble over them in your IDE's test
explorer.

```bash
mvn test -P solutions
```

```
Tests run: 71, Failures: 0, Errors: 0, Skipped: 0
```

Every solution test asserts the behaviour the stated rules describe. The code under test
was ported from an older exercise repository that carried two real defects; both have been
fixed, each with a comment in the source explaining why the code is written the way it is.
`CODE_CORRECTIONS.md` records what was wrong, what changed, and the one issue that is still
live, which is in the worksheet rather than in the code.

Try the exercises before you look.

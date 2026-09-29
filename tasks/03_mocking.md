# Exercise 3 - Mocking in a unit test

This exercise relies on the `User` and `UserController` classes and the `UserRepository`
interface in the `exercise3` package of this repository.

| | |
|---|---|
| Classes under test | `src/main/java/com/qaa/module3/unit_testing_exercises/exercise3/UserController.java`, with `User.java` and `UserRepository.java` alongside it |
| Test class you edit | `tests/com/qaa/module3/unit_testing_exercises/exercise3/UserControllerTest.java` |
| Plan | [TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md), the "Exercise 3 - UserController with a mocked repository" table |

Mockito is already on the classpath; `pom.xml` pins JUnit 5.10.2 and Mockito 5.11.0. There
is nothing to install.

## Part 1 - Update the test plan from exercise 2

The test plan from exercise 2 can be reused for this example. Modify the test plan to
accommodate the changes to the `login` and `register` methods present in the
`UserController` class.

As we are now dealing with multiple classes, it is also recommended to add a **Class**
column to the test table. The template adds a column for what the mocked repository is told
to return as well.

| ID | Class | Method | Description | Inputs | Mock setup | Expected output | Actual output |
|----|-------|--------|-------------|--------|------------|-----------------|---------------|
| 1 | `UserController` | `register(User user)` | Register a valid user | `new User(0, "bobby", "Codes123")` | `exists("bobby")` returns false, `register(user)` returns the saved user | the saved `User` | |
| 2 | | | | | | | |

Changes to account for:

- There is a new exception that could be thrown in the `register` method, when the `User`
  object itself is null.
- Some exceptions have been removed from the `login` method, as it is expected that the
  repository implementation would handle those cases in this example, i.e., invalid
  usernames or passwords.

The password rules are the same as exercise 2, because `UserController.register` carries
its own copy of them. Those rows come across unchanged.

## Part 2 - Implement the tests

Implement your unit test plan, as done with the previous examples.

Be careful when writing your tests for the `login` and `register` methods; it is expected
that you use the Mockito framework to mock interactions with the repository.

Make sure to mock the repository and inject it into the controller with the `@Mock` and
`@InjectMocks` annotations respectively. The repository methods being mocked are
`UserRepository.exists()`, `UserRepository.register()` and `UserRepository.login()`.

Three annotations do the work:

```java
@ExtendWith(MockitoExtension.class)   // switches the Mockito support on
class UserControllerTest {

    @Mock                             // creates the fake UserRepository
    private UserRepository repository;

    @InjectMocks                      // builds a UserController and passes the fake in
    private UserController controller;
```

and two methods do the rest:

```java
when(repository.exists("bobby")).thenReturn(false);   // tell the fake how to answer
verify(repository).register(input);                   // check the fake was called
```

`verifyNoInteractions(repository)` is the other way round, and is how you prove the
controller rejected a bad user **before** it went anywhere near storage.

Open `tests/com/qaa/module3/unit_testing_exercises/exercise3/UserControllerTest.java`.
One fully worked test is provided; the rest are `@Disabled` stubs.

Run your tests with:

```bash
mvn test
```

or, while you are working on this class only:

```bash
mvn test -Dtest=UserControllerTest
```

## Done looks like

All 18 `UserControllerTest` tests run and pass, with nothing skipped, except the single
stretch-task placeholder at the bottom of the class, which you delete when you start
[exercise 4](04_stretch_tdd_repository.md).

With exercises 1, 2 and 3 complete, `mvn test` reports 47 tests run, 0 failures and 1
skipped, that one being the stretch placeholder.

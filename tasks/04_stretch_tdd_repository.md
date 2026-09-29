# Exercise 3, Part 3 - Test-driven development (stretch task)

If you complete exercise 3, create a test plan for the methods of the `UserRepository`
interface.

Once a suitable plan is created, create your tests, and implement the interface as a class.
Call it `ConcreteUserRepository`. The *Concrete* in the name indicates that it is a class
and not an interface or abstract class.

Store the instances of `User` in a `List<User>` instance variable on the concrete
repository class.

| | |
|---|---|
| Interface to implement | `src/main/java/com/qaa/module3/unit_testing_exercises/exercise3/UserRepository.java` |
| Class you create | `ConcreteUserRepository`, in the same package as your tests: `src/test/java/com/qaa/module3/unit_testing_exercises/exercise3/` |
| Test class you create | `ConcreteUserRepositoryTest`, in that same folder |
| Plan | [TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md), the "Exercise 3 stretch task" table |

The repository class lives in the test tree rather than in `src/main/java` on purpose:
writing it is your task, so it must not appear in the code you were handed.

The interface you are implementing is:

```java
public interface UserRepository {
    public boolean exists(String trimmedUsername);
    public User register(User user);
    public User login(User user);
}
```

## Advice

The order matters. This is test-driven development, so the test comes first, every time.

1. After creating the plan, create the concrete repository class and implement the
   `UserRepository` interface.
2. Add the empty method stubs.
3. Create the test class `ConcreteUserRepositoryTest`.
4. Start creating the `register` tests.
5. Create the implementation of `ConcreteUserRepository.register()` as you write the test.
6. Repeat steps 4 and 5 for the `login` and `exists` methods.

Write one failing test, make it pass with the smallest change that will do, then write the
next one. Resist the urge to implement all three methods first and test afterwards; the
discipline is the whole point of the exercise.

Things your plan should decide before you write any code:

- What does `register` return? The stored `User`, presumably with an id assigned. Which id
  does the first user get, and the second?
- What should `register` do when the username is already stored?
- What should `login` do when no such user exists, and when the password does not match?
- Is `exists` case sensitive? Whatever you decide, write a test that pins it down.

## Getting started

In `UserControllerTest` there is a placeholder stub at the bottom of the class:

```java
@Test
@Disabled("TODO stretch task")
@DisplayName("placeholder for the stretch task")
void stretchTaskPlaceholder() {
```

Delete it once `ConcreteUserRepositoryTest` exists.

Run your tests with:

```bash
mvn test
```

or, while you are working on this class only:

```bash
mvn test -Dtest=ConcreteUserRepositoryTest
```

## Done looks like

`mvn test` reports 0 failures and 0 skipped: every stub in the repository has been written,
the placeholder is gone, and your own `ConcreteUserRepositoryTest` tests are running
alongside the rest.

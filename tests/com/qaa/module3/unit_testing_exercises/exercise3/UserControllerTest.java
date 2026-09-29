package com.qaa.module3.unit_testing_exercises.exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * EXERCISE 3: mocking in a unit test.
 *
 * UserController talks to a UserRepository. There is no class in this repository
 * that implements that interface, and even if there were we would not want a unit
 * test to depend on it. Instead we hand the controller a FAKE repository created
 * by Mockito and tell that fake what to return.
 *
 * WHAT YOU DO HERE
 * Write one test per exception UserController.register and UserController.login
 * can throw, plus the happy path for login. Two of them also check the
 * INTERACTION rather than the return value: that the controller really did ask
 * the repository, or really did not.
 *
 * TWO PARTS, IN THIS ORDER
 *   1. Write the test plan first. Copy tasks/TEST_PLAN_TEMPLATE.md and fill in the
 *      "Exercise 3" table, noting for each row what the mock must be told to return.
 *   2. Then implement the plan down here, one row per test method.
 *
 * HOW THE TODOS WORK
 * Every unwritten stub carries @Disabled("TODO"), which JUnit reports as skipped
 * rather than failed, so the suite is green on a fresh clone. Delete the @Disabled
 * line to activate a stub, then write its body.
 *
 * HOW TO RUN
 *   mvn test                              run everything
 *   mvn test -Dtest=UserControllerTest    run just this class
 *
 * THE FULL BRIEF
 * tasks/03_mocking.md
 *
 * Three annotations do the work:
 *   @ExtendWith(MockitoExtension.class) switches the Mockito support on
 *   @Mock                               creates the fake UserRepository
 *   @InjectMocks                        builds a UserController and passes the
 *                                       fake into its constructor
 *
 * Two methods do the rest:
 *   when(mock.method(args)).thenReturn(value)  tell the fake how to answer
 *   verify(mock).method(args)                  check the fake was called
 *   verifyNoInteractions(mock)                 check it was never touched
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    // THE FIXTURE. This class has no @BeforeEach method, because
    // @ExtendWith(MockitoExtension.class) is doing that job: before every @Test it
    // creates a new fake for each @Mock field and a new object under test for the
    // @InjectMocks field, so no stubbing or recorded call can leak from one test
    // into the next. That is exactly what @BeforeEach gives you in exercises 1 and
    // 2, only written declaratively.
    //
    // The sibling repositories have no such annotations. In C# with NUnit and Moq
    // you build the mock by hand in a [SetUp] method and pass mock.Object to the
    // constructor; in Python you build a Mock in setUp and pass it in the same way.
    // Java is the odd one out here, so it is worth knowing what the annotations
    // stand in for.

    // The fake repository. Its methods return null or false until we stub them.
    @Mock
    private UserRepository repository;

    // The real object under test, with the fake repository injected into it.
    // Constructor injection is what makes this possible: UserController takes its
    // repository as a constructor argument, so a test can supply any stand-in.
    @InjectMocks
    private UserController controller;

    // ---------------------------------------------------------------------
    // WORKED EXAMPLE - a valid registration. The controller should ask the
    // repository whether the username is taken, and then ask it to store the user.
    // ---------------------------------------------------------------------

    @Test
    @DisplayName("register saves the user and returns it when the details are valid")
    void registerSavesUserAndReturnsItWhenDetailsAreValid() {
        // arrange
        User input = new User(0, "bobby", "Codes123");
        User saved = new User(1, "bobby", "Codes123");
        // The controller asks the repository whether the username is taken,
        // then asks it to save. Tell the fake how to answer both questions.
        when(repository.exists("bobby")).thenReturn(false);
        when(repository.register(input)).thenReturn(saved);

        // act
        User actual = controller.register(input);

        // assert
        // User overrides equals on all three fields, so two separately created
        // User objects with the same values are equal and assertEquals passes.
        assertEquals(saved, actual);
        // Also check the controller really did ask the repository to save. This
        // is the half of the test that return values cannot tell you.
        verify(repository).register(input);
    }

    // ---------------------------------------------------------------------
    // TODO stubs - register
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the user is null")
    void registerThrowsWhenUserIsNull() {
        // Should assert the message is "User must not be null". This is the new
        // exception the exercise guide mentions in part 1: UserService had no
        // equivalent, because it took two strings rather than a User.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is null")
    void registerThrowsWhenUsernameIsNull() {
        // Should assert the message is "Username must not be null", passing a real
        // User whose username field is null.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is whitespace only")
    void registerThrowsWhenUsernameIsWhitespaceOnly() {
        // Should assert the message is "Username must not be whitespace only".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is null")
    void registerThrowsWhenPasswordIsNull() {
        // Should assert the message is "Password must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is whitespace only")
    void registerThrowsWhenPasswordIsWhitespaceOnly() {
        // Should assert the message is "Password must not be whitespace only".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is too short")
    void registerThrowsWhenUsernameIsTooShort() {
        // Borderline case. A 3 character username such as "bob" should give
        // "Username must contain at least 4 characters".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the repository says the username exists")
    void registerThrowsWhenRepositorySaysUsernameExists() {
        // The one stub in this class that needs the mock told what to say. Should
        // stub when(repository.exists("bobby")).thenReturn(true) and then assert
        // the message is "Username already exists". Note the difference from
        // exercise 2: the controller does not know the answer itself, it asks.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is too short")
    void registerThrowsWhenPasswordIsTooShort() {
        // Borderline case. A 5 character password such as "Code1" should give
        // "Password must contain at least 6 characters".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no uppercase character")
    void registerThrowsWhenPasswordHasNoUppercaseCharacter() {
        // Should assert the message is
        // "Password must contain at least 1 uppercase character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no lowercase character")
    void registerThrowsWhenPasswordHasNoLowercaseCharacter() {
        // Should assert the message is
        // "Password must contain at least 1 lowercase character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no number")
    void registerThrowsWhenPasswordHasNoNumber() {
        // Should assert the message is
        // "Password must contain at least 1 number character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register never touches the repository when validation fails")
    void registerNeverTouchesRepositoryWhenValidationFails() {
        // An interaction test with nothing to return. Should attempt a
        // registration that fails validation, then use
        // verifyNoInteractions(repository) to prove nothing was written. Import it
        // with: import static org.mockito.Mockito.verifyNoInteractions;
    }

    // ---------------------------------------------------------------------
    // TODO stubs - login. Note the guide's point: the controller no longer decides
    // whether the user is real, the repository does. So there is less to check
    // here than there was in exercise 2.
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("login returns the user the repository returns")
    void loginReturnsUserTheRepositoryReturns() {
        // Should stub repository.login(input) to return a user, assert the
        // controller hands that same user straight back, and verify it asked the
        // repository once.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the user is null")
    void loginThrowsWhenUserIsNull() {
        // Should assert the message is "User must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username or password is null")
    void loginThrowsWhenUsernameOrPasswordIsNull() {
        // Should assert the message is "Username and password must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username or password is empty")
    void loginThrowsWhenUsernameOrPasswordIsEmpty() {
        // Should assert the message is "Username and password must not be empty",
        // and that the repository was never asked to log anyone in.
    }

    // ---------------------------------------------------------------------
    // The stretch task has its own file: ConcreteUserRepositoryTest, in this same
    // package. Start it once this class is finished.
    // ---------------------------------------------------------------------
}

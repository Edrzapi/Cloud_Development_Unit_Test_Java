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
 * Exercise 3 - mocking in a unit test.
 *
 * UserController talks to a UserRepository. There is no real repository, and
 * even if there were we would not want a unit test to depend on it. Instead we
 * hand the controller a fake repository created by Mockito and tell that fake
 * what to return.
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
 *
 * One fully worked test is provided. The rest are @Disabled stubs.
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    // The fake repository. Its methods return null or false until we stub them.
    @Mock
    private UserRepository repository;

    // The real object under test, with the fake repository injected into it.
    @InjectMocks
    private UserController controller;

    // ---------------------------------------------------------------------
    // WORKED EXAMPLE - a valid registration.
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
        assertEquals(saved, actual);
        // Also check the controller really did ask the repository to save.
        verify(repository).register(input);
    }

    // ---------------------------------------------------------------------
    // TODO stubs - register
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the user is null")
    void registerThrowsWhenUserIsNull() {
        // TODO assert the message is "User must not be null". This is the new
        // exception the exercise guide mentions in part 1.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is null")
    void registerThrowsWhenUsernameIsNull() {
        // TODO assert the message is "Username must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is whitespace only")
    void registerThrowsWhenUsernameIsWhitespaceOnly() {
        // TODO assert the message is "Username must not be whitespace only".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is null")
    void registerThrowsWhenPasswordIsNull() {
        // TODO assert the message is "Password must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is whitespace only")
    void registerThrowsWhenPasswordIsWhitespaceOnly() {
        // TODO assert the message is "Password must not be whitespace only".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is too short")
    void registerThrowsWhenUsernameIsTooShort() {
        // TODO assert the message is
        // "Username must contain at least 4 characters".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the repository says the username exists")
    void registerThrowsWhenRepositorySaysUsernameExists() {
        // TODO stub when(repository.exists("bobby")).thenReturn(true) and
        // assert the message is "Username already exists".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is too short")
    void registerThrowsWhenPasswordIsTooShort() {
        // TODO assert the message is
        // "Password must contain at least 6 characters".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no uppercase character")
    void registerThrowsWhenPasswordHasNoUppercaseCharacter() {
        // TODO assert the message is
        // "Password must contain at least 1 uppercase character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no lowercase character")
    void registerThrowsWhenPasswordHasNoLowercaseCharacter() {
        // TODO assert the message is
        // "Password must contain at least 1 lowercase character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no number")
    void registerThrowsWhenPasswordHasNoNumber() {
        // TODO assert the message is
        // "Password must contain at least 1 number character".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register never touches the repository when validation fails")
    void registerNeverTouchesRepositoryWhenValidationFails() {
        // TODO after a failed registration, use verifyNoInteractions(repository)
        // to prove nothing was written.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - login
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("login returns the user the repository returns")
    void loginReturnsUserTheRepositoryReturns() {
        // TODO stub repository.login(input) to return a user and assert the
        // controller hands that same user back.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the user is null")
    void loginThrowsWhenUserIsNull() {
        // TODO assert the message is "User must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username or password is null")
    void loginThrowsWhenUsernameOrPasswordIsNull() {
        // TODO assert the message is
        // "Username and password must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username or password is empty")
    void loginThrowsWhenUsernameOrPasswordIsEmpty() {
        // TODO assert the message is
        // "Username and password must not be empty".
    }

    // ---------------------------------------------------------------------
    // Stretch task - test driven development.
    //
    // Write a plan for the three UserRepository methods, then create
    // ConcreteUserRepository implementing UserRepository, storing users in a
    // List<User>. Write each test BEFORE the method it tests. Put those tests
    // in a new class, ConcreteUserRepositoryTest, in this package.
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("placeholder for the stretch task")
    void stretchTaskPlaceholder() {
        // TODO delete this stub once ConcreteUserRepositoryTest exists. The
        // first test to write is: register adds the user to the list and
        // returns it.
    }
}

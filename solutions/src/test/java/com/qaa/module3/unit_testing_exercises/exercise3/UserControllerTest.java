package com.qaa.module3.unit_testing_exercises.exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * SOLUTION for exercise 3 - mocking in a unit test.
 *
 * The repository is a Mockito mock, injected into the controller. No real
 * storage is involved, so these tests only ever check the controller's own
 * validation and the calls it makes.
 *
 * The controller carries its own copy of the password rules, so the password
 * fix from exercise 2 was applied here too. See CODE_CORRECTIONS.md for what was
 * wrong in the original.
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserController controller;

    // ------------------------------------------------------------- register

    @Test
    @DisplayName("register saves the user and returns it when the details are valid")
    void registerSavesUserAndReturnsItWhenDetailsAreValid() {
        // arrange
        User input = new User(0, "bobby", "Codes123");
        User saved = new User(1, "bobby", "Codes123");
        when(repository.exists("bobby")).thenReturn(false);
        when(repository.register(input)).thenReturn(saved);

        // act
        User actual = controller.register(input);

        // assert
        assertEquals(saved, actual);
        verify(repository).register(input);
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the user is null")
    void registerThrowsWhenUserIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(null));

        // assert
        assertEquals("User must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is null")
    void registerThrowsWhenUsernameIsNull() {
        // arrange
        User input = new User(0, null, "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Username must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is whitespace only")
    void registerThrowsWhenUsernameIsWhitespaceOnly() {
        // arrange
        User input = new User(0, "   ", "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Username must not be whitespace only", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is null")
    void registerThrowsWhenPasswordIsNull() {
        // arrange
        User input = new User(0, "bobby", null);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is whitespace only")
    void registerThrowsWhenPasswordIsWhitespaceOnly() {
        // arrange
        User input = new User(0, "bobby", "   ");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must not be whitespace only", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is 3 characters long")
    void registerThrowsWhenUsernameIsTooShort() {
        // arrange
        User input = new User(0, "bob", "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Username must contain at least 4 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the repository says the username exists")
    void registerThrowsWhenRepositorySaysUsernameExists() {
        // arrange
        User input = new User(0, "bobby", "Codes123");
        when(repository.exists("bobby")).thenReturn(true);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Username already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is 5 characters long")
    void registerThrowsWhenPasswordIsTooShort() {
        // arrange
        User input = new User(0, "bobby", "Code1");
        when(repository.exists("bobby")).thenReturn(false);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must contain at least 6 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no uppercase character")
    void registerThrowsWhenPasswordHasNoUppercaseCharacter() {
        // arrange
        User input = new User(0, "bobby", "codes1");
        when(repository.exists("bobby")).thenReturn(false);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must contain at least 1 uppercase character", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no lowercase character")
    void registerThrowsWhenPasswordHasNoLowercaseCharacter() {
        // arrange
        User input = new User(0, "bobby", "CODES1");
        when(repository.exists("bobby")).thenReturn(false);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must contain at least 1 lowercase character", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no number")
    void registerThrowsWhenPasswordHasNoNumber() {
        // arrange
        User input = new User(0, "bobby", "Codesss");
        when(repository.exists("bobby")).thenReturn(false);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.register(input));

        // assert
        assertEquals("Password must contain at least 1 number character", thrown.getMessage());
    }

    @Test
    @DisplayName("register accepts a password whose only number is a zero")
    void registerAcceptsPasswordWhoseOnlyNumberIsZero() {
        // arrange
        // Same rules as exercise 2, and the same fix. The original character
        // class was [A-Z|a-z|1-9], and 1-9 excludes 0, so this used to be
        // rejected with the uppercase message.
        User input = new User(0, "bobby", "Codes0");
        User saved = new User(1, "bobby", "Codes0");
        when(repository.exists("bobby")).thenReturn(false);
        when(repository.register(input)).thenReturn(saved);

        // act
        User actual = controller.register(input);

        // assert
        assertEquals(saved, actual);
        verify(repository).register(input);
    }

    @Test
    @DisplayName("register accepts a symbol in the password, because no rule forbids one")
    void registerAcceptsASymbolInThePassword() {
        // arrange
        // The rules say what a password must contain, never which characters
        // are forbidden, and each rule is now a contains check.
        User input = new User(0, "bobby", "Cod|es1");
        User saved = new User(1, "bobby", "Cod|es1");
        when(repository.exists("bobby")).thenReturn(false);
        when(repository.register(input)).thenReturn(saved);

        // act
        User actual = controller.register(input);

        // assert
        assertEquals(saved, actual);
        verify(repository).register(input);
    }

    @Test
    @DisplayName("register never touches the repository when validation fails")
    void registerNeverTouchesRepositoryWhenValidationFails() {
        // arrange
        User input = new User(0, "bob", "Codes123");

        // act
        assertThrows(IllegalArgumentException.class, () -> controller.register(input));

        // assert
        // Nothing was read and nothing was written, which is what we want from
        // a request that fails validation.
        verifyNoInteractions(repository);
    }

    // ---------------------------------------------------------------- login

    @Test
    @DisplayName("login returns the user the repository returns")
    void loginReturnsUserTheRepositoryReturns() {
        // arrange
        User input = new User(0, "bobby", "Codes123");
        User found = new User(1, "bobby", "Codes123");
        when(repository.login(input)).thenReturn(found);

        // act
        User actual = controller.login(input);

        // assert
        assertEquals(found, actual);
        verify(repository).login(input);
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the user is null")
    void loginThrowsWhenUserIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.login(null));

        // assert
        assertEquals("User must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the username is null")
    void loginThrowsWhenUsernameIsNull() {
        // arrange
        User input = new User(0, null, "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.login(input));

        // assert
        assertEquals("Username and password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the password is null")
    void loginThrowsWhenPasswordIsNull() {
        // arrange
        User input = new User(0, "bobby", null);

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.login(input));

        // assert
        assertEquals("Username and password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the username is empty")
    void loginThrowsWhenUsernameIsEmpty() {
        // arrange
        User input = new User(0, "", "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.login(input));

        // assert
        assertEquals("Username and password must not be empty", thrown.getMessage());
    }

    @Test
    @DisplayName("login never touches the repository when validation fails")
    void loginNeverTouchesRepositoryWhenValidationFails() {
        // arrange
        User input = new User(0, "", "");

        // act
        assertThrows(IllegalArgumentException.class, () -> controller.login(input));

        // assert
        verifyNoInteractions(repository);
    }
}

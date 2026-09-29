package com.qaa.module3.unit_testing_exercises.exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SOLUTION for the exercise 3 stretch task.
 *
 * These are the tests that drive ConcreteUserRepository. In a real TDD session
 * you write one of these, watch it fail, then write just enough of the
 * repository to make it pass, and repeat.
 *
 * No mocking here. The repository is the thing under test, so we use the real
 * object.
 */
class ConcreteUserRepositoryTest {

    private ConcreteUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ConcreteUserRepository();
    }

    // ------------------------------------------------------------- register

    @Test
    @DisplayName("register stores the user and gives it an id of 1")
    void registerStoresUserAndGivesItAnIdOfOne() {
        // arrange
        User input = new User(0, "bobby", "Codes123");

        // act
        User actual = repository.register(input);

        // assert
        assertEquals(1, actual.getId());
        assertEquals("bobby", actual.getUsername());
        assertTrue(repository.exists("bobby"));
    }

    @Test
    @DisplayName("register gives the second user the next id")
    void registerGivesTheSecondUserTheNextId() {
        // arrange
        repository.register(new User(0, "bobby", "Codes123"));

        // act
        User actual = repository.register(new User(0, "sally", "Codes456"));

        // assert
        assertEquals(2, actual.getId());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the user is null")
    void registerThrowsWhenUserIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> repository.register(null));

        // assert
        assertEquals("User must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is already stored")
    void registerThrowsWhenUsernameIsAlreadyStored() {
        // arrange
        repository.register(new User(0, "bobby", "Codes123"));

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> repository.register(new User(0, "bobby", "Codes456")));

        // assert
        assertEquals("Username already exists", thrown.getMessage());
    }

    // --------------------------------------------------------------- exists

    @Test
    @DisplayName("exists returns false when nothing has been registered")
    void existsReturnsFalseWhenNothingHasBeenRegistered() {
        // arrange, act
        boolean actual = repository.exists("bobby");

        // assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("exists returns true for a username that has been registered")
    void existsReturnsTrueForRegisteredUsername() {
        // arrange
        repository.register(new User(0, "bobby", "Codes123"));

        // act
        boolean actual = repository.exists("bobby");

        // assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("exists returns false for a null username")
    void existsReturnsFalseForNullUsername() {
        // arrange, act
        boolean actual = repository.exists(null);

        // assert
        assertFalse(actual);
    }

    // ---------------------------------------------------------------- login

    @Test
    @DisplayName("login returns the stored user when the details match")
    void loginReturnsStoredUserWhenDetailsMatch() {
        // arrange
        User stored = repository.register(new User(0, "bobby", "Codes123"));

        // act
        User actual = repository.login(new User(0, "bobby", "Codes123"));

        // assert
        assertEquals(stored, actual);
    }

    @Test
    @DisplayName("login throws RuntimeException when the username is not stored")
    void loginThrowsRuntimeExceptionWhenUsernameIsNotStored() {
        // arrange, act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> repository.login(new User(0, "nobody", "Codes123")));

        // assert
        assertEquals("Invalid username supplied", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the password is wrong")
    void loginThrowsWhenPasswordIsWrong() {
        // arrange
        repository.register(new User(0, "bobby", "Codes123"));

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> repository.login(new User(0, "bobby", "Wrong123")));

        // assert
        assertEquals("Invalid password supplied", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the user is null")
    void loginThrowsWhenUserIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> repository.login(null));

        // assert
        assertEquals("User must not be null", thrown.getMessage());
    }
}

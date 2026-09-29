package com.qaa.module3.unit_testing_exercises.exercise2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SOLUTION for exercise 2 - testing exceptions.
 *
 * One test per exception that register and login can throw, plus the happy
 * paths.
 *
 * IMPORTANT FOR THE TRAINER: UserService used to carry two real defects, a
 * login that looked users up by password and a broken password regular
 * expression. Both have been fixed, so every test below asserts the behaviour
 * the stated rules describe. CODE_CORRECTIONS.md records what was wrong in the
 * original and what changed.
 */
class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    // ------------------------------------------------------------- register

    @Test
    @DisplayName("register returns the trimmed username for a valid user")
    void registerReturnsTrimmedUsernameForValidUser() {
        // arrange
        String username = "  bobby  ";
        String password = "Codes123";

        // act
        String actual = service.register(username, password);

        // assert
        assertEquals("bobby", actual);
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is null")
    void registerThrowsWhenUsernameIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register(null, "Codes123"));

        // assert
        assertEquals("Username must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is whitespace only")
    void registerThrowsWhenUsernameIsWhitespaceOnly() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("   ", "Codes123"));

        // assert
        assertEquals("Username must not be whitespace only", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is null")
    void registerThrowsWhenPasswordIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", null));

        // assert
        assertEquals("Password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is whitespace only")
    void registerThrowsWhenPasswordIsWhitespaceOnly() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "   "));

        // assert
        assertEquals("Password must not be whitespace only", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username is 3 characters long")
    void registerThrowsWhenUsernameIsTooShort() {
        // arrange, act
        // Borderline: 4 characters is allowed, 3 is not.
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bob", "Codes123"));

        // assert
        assertEquals("Username must contain at least 4 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("register accepts a username of exactly 4 characters")
    void registerAcceptsUsernameOfExactlyFourCharacters() {
        // arrange
        String username = "bobb";

        // act
        String actual = service.register(username, "Codes123");

        // assert
        assertEquals("bobb", actual);
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the username already exists")
    void registerThrowsWhenUsernameAlreadyExists() {
        // arrange
        service.register("bobby", "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "Codes456"));

        // assert
        assertEquals("Username already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password is 5 characters long")
    void registerThrowsWhenPasswordIsTooShort() {
        // arrange, act
        // Borderline: 6 characters is allowed, 5 is not.
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "Code1"));

        // assert
        assertEquals("Password must contain at least 6 characters", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no uppercase character")
    void registerThrowsWhenPasswordHasNoUppercaseCharacter() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "codes1"));

        // assert
        assertEquals("Password must contain at least 1 uppercase character", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no lowercase character")
    void registerThrowsWhenPasswordHasNoLowercaseCharacter() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "CODES1"));

        // assert
        assertEquals("Password must contain at least 1 lowercase character", thrown.getMessage());
    }

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no number")
    void registerThrowsWhenPasswordHasNoNumber() {
        // arrange, act
        // The exercise guide's example row uses "Codes", but that is only 5
        // characters so the length rule fires first. "Codesss" is long enough
        // to reach the number rule.
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register("bobby", "Codesss"));

        // assert
        assertEquals("Password must contain at least 1 number character", thrown.getMessage());
    }

    @Test
    @DisplayName("register accepts a password whose only number is a zero")
    void registerAcceptsPasswordWhoseOnlyNumberIsZero() {
        // arrange, act
        // "Codes0" satisfies every stated rule: 6 characters, an uppercase
        // letter, lowercase letters and a number. The original character class
        // was [A-Z|a-z|1-9], and 1-9 excludes 0, so this used to be rejected
        // with the uppercase message. The number rule is now [0-9].
        String actual = service.register("bobby", "Codes0");

        // assert
        assertEquals("bobby", actual);
    }

    @Test
    @DisplayName("register accepts a symbol in the password, because no rule forbids one")
    void registerAcceptsASymbolInThePassword() {
        // arrange, act
        // The rules say what a password must CONTAIN, never which characters
        // are forbidden. Each rule is now a contains check, so a symbol passes
        // as long as the uppercase, lowercase and number rules are satisfied.
        String actual = service.register("bobby", "Cod|es1");

        // assert
        assertEquals("bobby", actual);
    }

    // ---------------------------------------------------------------- login

    @Test
    @DisplayName("login returns the username after a successful registration")
    void loginReturnsUsernameAfterSuccessfulRegistration() {
        // arrange
        // Test case 1 of the exercise guide. The original looked the user up
        // by password, so this used to throw "Invalid username supplied".
        service.register("bobby", "Codes123");

        // act
        String actual = service.login("bobby", "Codes123");

        // assert
        assertEquals("bobby", actual);
    }

    @Test
    @DisplayName("login returns the trimmed username, the same value register returned")
    void loginReturnsTheTrimmedUsername() {
        // arrange
        service.register("  bobby  ", "Codes123");

        // act
        String actual = service.login("  bobby  ", "Codes123");

        // assert
        // The original returned the raw argument, so this used to be "  bobby  ".
        assertEquals("bobby", actual);
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the password does not match the stored one")
    void loginThrowsWhenPasswordDoesNotMatchTheStoredOne() {
        // arrange
        // This branch was unreachable in normal use before the fix, because
        // the lookup by password missed first.
        service.register("bobby", "Codes123");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login("bobby", "wrong1A"));

        // assert
        assertEquals("Invalid password supplied", thrown.getMessage());
    }

    @Test
    @DisplayName("login rejects another user's password rather than letting it through")
    void loginRejectsAnotherUsersPassword() {
        // arrange
        // A second proof that the password branch is now reached: "sally" is a
        // real user, but "Codes123" is bobby's password, not hers.
        service.register("bobby", "Codes123");
        service.register("sally", "Passw1rd");

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login("sally", "Codes123"));

        // assert
        assertEquals("Invalid password supplied", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the username is null")
    void loginThrowsWhenUsernameIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login(null, "Codes123"));

        // assert
        assertEquals("Username and password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the password is null")
    void loginThrowsWhenPasswordIsNull() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login("bobby", null));

        // assert
        assertEquals("Username and password must not be null", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the username is empty")
    void loginThrowsWhenUsernameIsEmpty() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login("   ", "Codes123"));

        // assert
        assertEquals("Username and password must not be empty", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws IllegalArgumentException when the password is empty")
    void loginThrowsWhenPasswordIsEmpty() {
        // arrange, act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.login("bobby", "   "));

        // assert
        assertEquals("Username and password must not be empty", thrown.getMessage());
    }

    @Test
    @DisplayName("login throws RuntimeException when the user is not known")
    void loginThrowsRuntimeExceptionWhenUserIsNotKnown() {
        // arrange, act
        // Nobody has registered, so the lookup by username finds nothing.
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> service.login("nobody", "Codes123"));

        // assert
        assertEquals("Invalid username supplied", thrown.getMessage());
    }
}

package com.qaa.module3.unit_testing_exercises.exercise2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Exercise 2 - testing exceptions.
 *
 * The exercise guide asks for a test case for every exception that
 * UserService.register and UserService.login can throw. Write the test plan
 * first, then one test method per row.
 *
 * The pattern for testing an exception in JUnit 5 is:
 *
 *     IllegalArgumentException thrown = assertThrows(
 *             IllegalArgumentException.class,
 *             () -> service.register(null, "Codes123"));
 *     assertEquals("Username must not be null", thrown.getMessage());
 *
 * assertThrows fails the test if the code inside the lambda does NOT throw,
 * and returns the exception it caught so that you can assert on the message.
 *
 * One fully worked test is provided. The rest are @Disabled stubs. Delete the
 * @Disabled line when you start work on one.
 *
 * A word of warning, and this is the point of the exercise: this is existing
 * code and it is not necessarily correct. If a test that follows the stated
 * rules fails, do not assume your test is wrong. Read the source, work out
 * what the code really does, and record it.
 */
class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    // ---------------------------------------------------------------------
    // WORKED EXAMPLE - test case 2 from the exercise guide.
    //
    // Note the password. The guide's own example row uses "Codes", but that
    // is only 5 characters, so the length rule is checked first and you get
    // "Password must contain at least 6 characters" instead. "Codesss" is long
    // enough to reach the number rule the guide meant to demonstrate. This is
    // an error in the worksheet, not in the code; CODE_CORRECTIONS.md records it.
    // ---------------------------------------------------------------------

    @Test
    @DisplayName("register throws IllegalArgumentException when the password has no number")
    void registerThrowsWhenPasswordHasNoNumber() {
        // arrange
        String username = "bobby";
        String password = "Codesss";

        // act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.register(username, password));

        // assert
        assertEquals("Password must contain at least 1 number character", thrown.getMessage());
    }

    // ---------------------------------------------------------------------
    // TODO stubs - register
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("register returns the trimmed username for a valid user")
    void registerReturnsTrimmedUsernameForValidUser() {
        // TODO assert that register("  bobby  ", "Codes123") returns "bobby".
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
        // TODO borderline case: a 3 character username such as "bob" should
        // give "Username must contain at least 4 characters".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username already exists")
    void registerThrowsWhenUsernameAlreadyExists() {
        // TODO register the same username twice, then assert the second call
        // gives "Username already exists".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is too short")
    void registerThrowsWhenPasswordIsTooShort() {
        // TODO borderline case: a 5 character password such as "Code1" should
        // give "Password must contain at least 6 characters".
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
    @DisplayName("register accepts a password whose only number is a zero")
    void registerAcceptsPasswordWhoseOnlyNumberIsZero() {
        // TODO "Codes0" contains a number, so by the stated rules register
        // should accept it and return "bobby". Write that test and run it.
        // Zero is a number: check that your own test data does not quietly
        // assume otherwise.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - login
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("login returns the username after a successful registration")
    void loginReturnsUsernameAfterSuccessfulRegistration() {
        // TODO test case 1 from the exercise guide: register "bobby" with
        // "Codes123", then login with the same details and assert the result
        // is "bobby".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username is null")
    void loginThrowsWhenUsernameIsNull() {
        // TODO assert the message is "Username and password must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the password is null")
    void loginThrowsWhenPasswordIsNull() {
        // TODO same message as above, this time with a null password.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username is empty")
    void loginThrowsWhenUsernameIsEmpty() {
        // TODO assert the message is "Username and password must not be empty".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws RuntimeException when the user is not known")
    void loginThrowsRuntimeExceptionWhenUserIsNotKnown() {
        // TODO log in without registering first and assert that a
        // RuntimeException with the message "Invalid username supplied" is
        // thrown.
    }
}

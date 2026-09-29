package exercise2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EXERCISE 2: testing exceptions.
 *
 * UserService.register and UserService.login reject bad input with a specific
 * message for each rule broken. Proving the right message comes back for the right
 * reason is the whole of this exercise.
 *
 * WHAT YOU DO HERE
 * Write one test per exception each method can throw, plus the happy path for both.
 * register checks its rules in a fixed order, and the first rule broken is the one
 * you get the message for, so your test data has to break exactly one rule at a time.
 *
 * TWO PARTS, IN THIS ORDER
 *   1. Write the test plan first. Copy tasks/TEST_PLAN_TEMPLATE.md and fill in the
 *      "Exercise 2" table: one row per exception, with the input that triggers it
 *      and the exact message you expect.
 *   2. Then implement the plan down here, one row per test method.
 *
 * HOW THE TODOS WORK
 * Every unwritten stub carries @Disabled("TODO"), which JUnit reports as skipped
 * rather than failed, so the suite is green on a fresh clone. Delete the @Disabled
 * line to activate a stub, then write its body.
 *
 * HOW TO RUN
 *   mvn test                            run everything
 *   mvn test -Dtest=UserServiceTest     run just this class
 *
 * THE FULL BRIEF
 * tasks/02_testing_exceptions.md
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
 * A word of warning, and this is the point of the exercise: this is existing
 * code and it is not necessarily correct. If a test that follows the stated
 * rules fails, do not assume your test is wrong. Read the source, work out
 * what the code really does, and record it.
 */
class UserServiceTest {

    // The object under test. A fresh one before every test, so the in-memory
    // store of users starts empty each time.
    private UserService service;

    // @BeforeEach marks a setup method that JUnit runs before EVERY @Test in this
    // class. It matters more here than in exercise 1: UserService remembers the
    // users it has registered, so without a fresh instance a "username already
    // exists" failure would leak from one test into the next.
    //
    // The sibling repositories spell the same idea differently: C# and NUnit use
    // [SetUp], and Python with unittest uses a method named setUp.
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
    // an error in the worksheet, not in the code.
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
        // Should assert that register("  bobby  ", "Codes123") returns "bobby",
        // that is, that register trims its arguments before storing them.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is null")
    void registerThrowsWhenUsernameIsNull() {
        // Should assert the message is "Username must not be null".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username is whitespace only")
    void registerThrowsWhenUsernameIsWhitespaceOnly() {
        // Should assert the message is "Username must not be whitespace only",
        // using a username such as "   " that is not null but trims away to nothing.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password is null")
    void registerThrowsWhenPasswordIsNull() {
        // Should assert the message is "Password must not be null". Give it a
        // valid username, or you will hit a username rule first.
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
        // "Username must contain at least 4 characters". Four characters is the
        // first length that passes, so "bob" is the value right below the edge.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the username already exists")
    void registerThrowsWhenUsernameAlreadyExists() {
        // Should register the same username twice, then assert the second call
        // gives "Username already exists". This is the one test that needs two
        // calls to arrange, and the one @BeforeEach is protecting.
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
        // "Password must contain at least 1 uppercase character", using a password
        // that is long enough and breaks only the uppercase rule, such as "codes123".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register throws IllegalArgumentException when the password has no lowercase character")
    void registerThrowsWhenPasswordHasNoLowercaseCharacter() {
        // Should assert the message is
        // "Password must contain at least 1 lowercase character", using a password
        // such as "CODES123" that breaks only that rule.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("register accepts a password whose only number is a zero")
    void registerAcceptsPasswordWhoseOnlyNumberIsZero() {
        // Borderline case, and a trap. "Codes0" contains a number, so by the
        // stated rules register should accept it and return "bobby". Write that
        // test and run it. Zero is a number: check that your own test data does
        // not quietly assume otherwise.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - login
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO")
    @DisplayName("login returns the username after a successful registration")
    void loginReturnsUsernameAfterSuccessfulRegistration() {
        // Test case 1 from the exercise guide. Should register "bobby" with
        // "Codes123", then login with the same details and assert the result
        // is "bobby".
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username is null")
    void loginThrowsWhenUsernameIsNull() {
        // Should assert the message is "Username and password must not be null".
        // Note that login uses one message for both fields, unlike register.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the password is null")
    void loginThrowsWhenPasswordIsNull() {
        // Same message as above, this time with a null password and a valid username.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws IllegalArgumentException when the username is empty")
    void loginThrowsWhenUsernameIsEmpty() {
        // Should assert the message is "Username and password must not be empty",
        // using an empty string rather than null, which is the next case along.
    }

    @Test
    @Disabled("TODO")
    @DisplayName("login throws RuntimeException when the user is not known")
    void loginThrowsRuntimeExceptionWhenUserIsNotKnown() {
        // Should log in without registering first, and assert that a
        // RuntimeException with the message "Invalid username supplied" is thrown.
        // Note the type: this one is a plain RuntimeException, not an
        // IllegalArgumentException like every other case in this class.
    }
}

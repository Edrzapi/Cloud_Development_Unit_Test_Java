package exercise3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EXERCISE 3, PART 3 (stretch): test-driven development.
 *
 * There is no ConcreteUserRepository class yet. That is the point. In the three
 * exercises before this one the code already existed and you wrote tests for it.
 * Here the TEST COMES FIRST and the class does not exist until you create it.
 *
 * WHAT YOU DO HERE, in this order:
 *
 *   1. Write the test plan for exists, register and login. Copy
 *      tasks/TEST_PLAN_TEMPLATE.md and fill in the "Exercise 3 stretch task" table.
 *   2. Create ConcreteUserRepository in src/exercise3/, beside the UserRepository
 *      interface it implements. Give it empty method bodies that throw
 *      UnsupportedOperationException for now, and a private List<User> field to
 *      store the users in. It is in the same package as this test class, so
 *      there is no import to add.
 *   3. Write ONE test below. Run it. Watch it FAIL. A test you have never seen fail
 *      is a test you cannot trust.
 *   4. Write just enough of the implementation to make that one test pass.
 *   5. Tidy up, then go back to step 3 for the next test.
 *
 * You will also want a @BeforeEach method giving every test a fresh repository, the
 * way the other exercise classes do. Add one once your class exists:
 *
 *     private ConcreteUserRepository repository;
 *
 *     @BeforeEach
 *     void setUp() {
 *         repository = new ConcreteUserRepository();
 *     }
 *
 * @BeforeEach runs before EVERY @Test in the class, so no registered user leaks
 * from one test into the next. The sibling repositories spell it [SetUp] in C# and
 * setUp in Python.
 *
 * HOW THE TODOS WORK
 * There is no worked example in this file: writing the first test is your job.
 * Every stub below carries @Disabled, so the suite stays green until you start.
 * Delete the @Disabled line on the one stub you are working on, and no more.
 *
 * The stubs below are a suggested plan, not the only one. Your design decisions
 * are yours to make, as long as your tests pin them down:
 *   - what does register return, and which id does the first user get?
 *   - what does register do when the username is already stored?
 *   - what does login do for an unknown user, and for a wrong password?
 *   - is exists case sensitive?
 *
 * HOW TO RUN
 *   mvn test                                       run everything
 *   mvn test -Dtest=ConcreteUserRepositoryTest     run just this class
 *
 * THE FULL BRIEF
 * tasks/04_stretch_tdd_repository.md
 */
class ConcreteUserRepositoryTest {

    // ---------------------------------------------------------------------
    // TODO stubs - register. Write these first: nothing else can be tested
    // until something can be stored.
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO stretch task. Create ConcreteUserRepository first, then write this.")
    @DisplayName("register stores the user and gives it an id")
    void registerStoresTheUserAndGivesItAnId() {
        // Should register a User such as new User(0, "bobby", "Codes123") and
        // assert that what comes back carries the username that went in and the id
        // your design hands out to the first user. Assert that exists("bobby") now
        // returns true as well, so the test proves it was really stored.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("register gives the second user the next id")
    void registerGivesTheSecondUserTheNextId() {
        // Should register two different users and assert the second one's id
        // follows the first. This is the test that pins down how ids are handed
        // out, rather than leaving it to chance.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("register rejects a username that is already stored")
    void registerRejectsUsernameThatIsAlreadyStored() {
        // Should register a username, then register it again and assert your
        // chosen failure. Decide what that is, then write the test that requires
        // it: an IllegalArgumentException with a message of your choosing is the
        // usual answer.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("register rejects a null user")
    void registerRejectsNullUser() {
        // Should assert what register does when handed null. Again, your design
        // decision, made deliberately and recorded in the test.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - exists
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("exists returns false when nothing has been registered")
    void existsReturnsFalseWhenNothingHasBeenRegistered() {
        // Should assert that a brand new, empty repository does not know any
        // username. The cheapest test in the file, and a good one to start on.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("exists returns true for a username that has been registered")
    void existsReturnsTrueForRegisteredUsername() {
        // Should register a user, then assert exists returns true for that
        // username.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("exists handles a null username")
    void existsHandlesNullUsername() {
        // Borderline case. Should assert what exists does with null: returning
        // false is the simplest answer, throwing is also defensible. Pick one and
        // make the test require it.
    }

    // ---------------------------------------------------------------------
    // TODO stubs - login
    // ---------------------------------------------------------------------

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("login returns the stored user when the details match")
    void loginReturnsStoredUserWhenDetailsMatch() {
        // Should register a user, then assert that logging in with the same
        // username and password returns the STORED user, including the id the
        // repository gave it. User.equals compares all three fields, so a plain
        // assertEquals on the two User objects is enough.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("login rejects a username that was never registered")
    void loginRejectsUsernameThatWasNeverRegistered() {
        // Should log in without registering first and assert your chosen failure.
        // UserService throws RuntimeException("Invalid username supplied") for
        // this case, which is a reasonable precedent to follow.
    }

    @Test
    @Disabled("TODO stretch task")
    @DisplayName("login rejects a wrong password")
    void loginRejectsWrongPassword() {
        // Should register a user, then log in with the right username and the
        // wrong password. Assert what your design does: throw, or return null.
        // Whichever you choose, the test is what makes it a decision rather than
        // an accident.
    }
}

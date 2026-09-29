# Test plan template

Fill this in **before** you write any test code. One row per test case. Then turn each row
into one test method.

Fill in the "actual output" column only after you have run the test. If it does not match
the expected output, do not just change the expectation. Work out whether the test is wrong
or the code is wrong, and write down which.

---

## Exercise 1 - Calculator

The exercise guide supplies the first row. You need **at least three cases per method**,
covering borderline values (what is the largest total you can produce? the smallest?) and
at least one normal combination.

| ID | Method | Description | Inputs | Expected output | Actual output |
|----|--------|-------------|--------|-----------------|---------------|
| 1 | `add(double num1, double num2)` | Adding two small numbers | num1=10, num2=30 | 40 | |
| 2 | | | | | |
| 3 | | | | | |
| 4 | | | | | |
| 5 | | | | | |
| 6 | | | | | |
| 7 | | | | | |
| 8 | | | | | |
| 9 | | | | | |
| 10 | | | | | |
| 11 | | | | | |
| 12 | | | | | |

---

## Exercise 2 - UserService

You need a case for **every exception** that `register` and `login` can throw, plus the
successful paths.

The guide supplies two example rows. Row 2 is reproduced exactly as printed, and it is
wrong: `"Codes"` is 5 characters, so the length rule fires first and the real message is
`"Password must contain at least 6 characters"`. Use a longer password such as `"Codesss"`
if you want to reach the number rule the guide meant to demonstrate. There is a fuller
footnote under the same table in [02_testing_exceptions.md](02_testing_exceptions.md).

| ID | Method | Description | Inputs | Expected output | Actual output |
|----|--------|-------------|--------|-----------------|---------------|
| 1 | `login(String username, String password)` | Register a valid user, then log in successfully with that user | Register: username="bobby", password="Codes123". Login: username="bobby", password="Codes123" | `"bobby"` | |
| 2 | `register(String username, String password)` | Register a user with an invalid password, missing a number | username="bobby", password="Codes" | `IllegalArgumentException("Password must contain at least 1 number character")` | |
| 3 | | | | | |
| 4 | | | | | |
| 5 | | | | | |
| 6 | | | | | |
| 7 | | | | | |
| 8 | | | | | |
| 9 | | | | | |
| 10 | | | | | |
| 11 | | | | | |
| 12 | | | | | |
| 13 | | | | | |
| 14 | | | | | |

---

## Exercise 3 - UserController with a mocked repository

Reuse the exercise 2 plan and adjust it. Note the extra **Class** column, and the extra
column for what the mocked repository is told to return.

Changes to account for:

- `register` has a new exception, thrown when the `User` object itself is null.
- `login` has fewer exceptions than before. Invalid usernames and passwords are now the
  repository's problem, not the controller's.

| ID | Class | Method | Description | Inputs | Mock setup | Expected output | Actual output |
|----|-------|--------|-------------|--------|------------|-----------------|---------------|
| 1 | `UserController` | `register(User user)` | Register a valid user | `new User(0, "bobby", "Codes123")` | `exists("bobby")` returns false, `register(user)` returns the saved user | the saved `User` | |
| 2 | | | | | | | |
| 3 | | | | | | | |
| 4 | | | | | | | |
| 5 | | | | | | | |
| 6 | | | | | | | |
| 7 | | | | | | | |
| 8 | | | | | | | |
| 9 | | | | | | | |
| 10 | | | | | | | |

---

## Exercise 3 stretch task - ConcreteUserRepository

Plan the three `UserRepository` methods first, then write each test before the method that
makes it pass.

| ID | Class | Method | Description | Inputs | Expected output | Actual output |
|----|-------|--------|-------------|--------|-----------------|---------------|
| 1 | `ConcreteUserRepository` | `register(User user)` | Store a new user and return it | `new User(0, "bobby", "Codes123")` | the stored `User`, with an id | |
| 2 | | | | | | |
| 3 | | | | | | |
| 4 | | | | | | |
| 5 | | | | | | |
| 6 | | | | | | |

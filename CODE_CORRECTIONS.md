# What was wrong in the original, and what changed

Read this **after** you have attempted exercises 1 and 2, or if you are the trainer.

The classes in `src/main/java` were ported from the original exercise repository, which
carried two real defects. They have now been **fixed**, so that a student writing tests
faithfully from the stated rules gets tests that pass, and so that a student reading the
source learns why it is written the way it is. Each fix has a short comment sitting above
the check it explains.

This file is the record: what was wrong, how a test finds it, and what the fix was. Tests
written faithfully from the stated rules now pass because the code is right, not because
the expectations were bent around a fault.

One issue is still live, issue 3 below. It is in the worksheet, not in the code.

---

## Fixed 1 - UserService.login looked the user up by password, not by username

**Where:** `src/main/java/.../exercise2/UserService.java`, in `login`.

**Was:**

```java
String savedPassword = users.get(trimmedPassword);
if (savedPassword == null) throw new RuntimeException("Invalid username supplied");

if (!trimmedPassword.equals(savedPassword)) throw new IllegalArgumentException("Invalid password supplied");

return username;
```

The `users` map is keyed by username. The lookup used `trimmedPassword`, so it asked the
map for a user whose **username** was the password just typed in. For any normally
registered user that lookup returned `null` and login always failed. The
`"Invalid password supplied"` branch was unreachable in normal use for the same reason,
and `login` returned the raw `username` argument rather than the trimmed one.

**Now:**

```java
// look the user up by their username, then check the password they supplied
// against the one we stored. Keying the map by password would only ever match
// a user whose name happened to equal their own password.
String savedPassword = users.get(trimmedUsername);
if (savedPassword == null) throw new RuntimeException("Invalid username supplied");

if (!trimmedPassword.equals(savedPassword)) throw new IllegalArgumentException("Invalid password supplied");

// return the trimmed username, to match what register returns
return trimmedUsername;
```

**What a student should notice**

This is test case 1 from the exercise guide, and it now does what the guide says:

```java
UserService service = new UserService();
service.register("bobby", "Codes123");
assertEquals("bobby", service.login("bobby", "Codes123"));
```

| Input | Before the fix | Now |
|-------|----------------|-----|
| register `"bobby"`/`"Codes123"`, then login with the same | `RuntimeException: Invalid username supplied` | returns `"bobby"` |
| login `"bobby"`/`"wrong1A"` after registering `"bobby"` | `RuntimeException: Invalid username supplied` | `IllegalArgumentException: Invalid password supplied` |
| login `"nobody"`/`"Codes123"` | `RuntimeException: Invalid username supplied` | `RuntimeException: Invalid username supplied`, unchanged |
| login `"  bobby  "`/`"Codes123"` after registering `"  bobby  "` | returned `"  bobby  "` | returns `"bobby"` |

The wider lesson: a defect can hide a whole branch of code. Before the fix there was no
ordinary input that could reach `"Invalid password supplied"` at all, so a coverage report
would have shown a line nobody could ever execute. That is worth looking for in your own
work.

---

## Fixed 2 - the password character rules used a broken regular expression

**Where:** `src/main/java/.../exercise2/UserService.java` in `register`, and the same three
lines copied into `src/main/java/.../exercise3/UserController.java` in `register`.

**Was:**

```java
trimmedPassword.matches("[A-Z|a-z|1-9]*[A-Z]+[A-Z|a-z|1-9]*")
trimmedPassword.matches("[A-Z|a-z|1-9]*[a-z]+[A-Z|a-z|1-9]*")
trimmedPassword.matches("[A-Z|a-z|1-9]*[1-9]+[A-Z|a-z|1-9]*")
```

Three faults in three lines:

1. **`1-9` excludes zero.** A digit `0` was not accepted as a number.
2. **The pipes are literal characters.** Inside a character class `|` means the pipe
   character, not alternation, so `[A-Z|a-z|1-9]` quietly listed `|` as an allowed
   character. Alternation was never what the author intended anyway.
3. **`String.matches` matches the whole string.** A password containing any character
   outside that class therefore failed *every* one of the three rules. The uppercase rule
   runs first, so that was the message the user saw, and it named the wrong rule.

**Now:** one plain "contains" check per rule, in the same order, with the same messages.

```java
// password must contain at least 1 uppercase character. A "contains" check, not a
// whole-string match: a whole-string match fails on any character the class does not
// list, so a single unexpected symbol would report the wrong rule.
if (!trimmedPassword.matches(".*[A-Z].*")) throw new IllegalArgumentException("Password must contain at least 1 uppercase character");

// password must contain at least 1 lowercase character
if (!trimmedPassword.matches(".*[a-z].*")) throw new IllegalArgumentException("Password must contain at least 1 lowercase character");

// password must contain at least 1 number, and 0 counts as a number
if (!trimmedPassword.matches(".*[0-9].*")) throw new IllegalArgumentException("Password must contain at least 1 number character");
```

The exception messages are unchanged, character for character. Only which inputs reach
them has changed.

**What a student should notice**

| Password | Before the fix | Now |
|----------|----------------|-----|
| `"Codes0"` | `IllegalArgumentException: Password must contain at least 1 uppercase character` | accepted, returns `"bobby"` |
| `"codes1"` | `... at least 1 uppercase character` | `... at least 1 uppercase character`, unchanged |
| `"CODES1"` | `... at least 1 lowercase character` | `... at least 1 lowercase character`, unchanged |
| `"Codesss"` | `... at least 1 number character` | `... at least 1 number character`, unchanged |
| `"Cod\|es1"` | accepted, by accident | accepted, on purpose |

Two lessons here. First, a rule that says what a password must **contain** is not the same
as a rule that says which characters are **allowed**, and writing one as the other is how
`"Codes0"` came to be rejected for a missing capital letter. Nothing in the stated rules
forbids a symbol, so `"Cod|es1"` is still accepted; if symbols ought to be forbidden, that
is a new rule with its own message, not a side effect of this one.

Second, an error message that names the wrong rule is worse than no message at all. Always
test the message, not just the exception type.

---

## Still live: issue 3 - the exercise guide's own example row does not match the code

Not a code defect, and not fixed, because there is nothing in the code to fix. It is a trap
the students will walk into.

Example row 2 in the guide's exercise 2 test plan says that registering
`username="bobby"`, `password="Codes"` produces
`IllegalArgumentException("Password must contain at least 1 number character")`.

It does not. `"Codes"` is only 5 characters, and the length rule is checked before the
content rules, so the real message is:

```
Password must contain at least 6 characters
```

The worked example in `tests/.../exercise2/UserServiceTest.java` therefore uses
`"Codesss"`, which is long enough to reach the rule the guide meant to demonstrate. The
same footnote appears under the table in `tasks/02_testing_exceptions.md`.

Trainers may want to mention this when handing the guide out, or leave it as something for
the students to find.

---

## Summary for the trainer

| # | Class | What was wrong | State |
|---|-------|----------------|-------|
| 1 | `UserService.login` | Map lookup keyed on the password, so login never succeeded and the invalid-password branch was unreachable | Fixed, with a comment in the source |
| 2 | `UserService.register`, `UserController.register` | `[A-Z\|a-z\|1-9]` excluded `0`, treated `\|` as a literal, and matched the whole string, so the wrong rule was blamed | Fixed in both classes, with a comment in the source |
| 3 | Exercise guide, not the code | Example row uses a 5 character password, so the length rule fires first | Still live, documented here and in `tasks/02_testing_exceptions.md` |

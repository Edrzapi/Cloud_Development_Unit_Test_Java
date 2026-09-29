# SDL3 Module 5: Testing - Exercise Guide

Everything you need to work through Module 5 is in this repository. You do not need the PDF
guide; these pages carry the same exercises, in the same order, with the wording and the
example test cases translated to this Java project.

## The exercises

| # | Task | What it is about | Roughly |
|---|------|------------------|---------|
| 1 | [01_testing_existing_code.md](01_testing_existing_code.md) | Testing existing code. Plan and test the four `Calculator` methods. | 45 minutes |
| 2 | [02_testing_exceptions.md](02_testing_exceptions.md) | Testing exceptions. Every exception `UserService.register` and `login` can throw. | 60 minutes |
| 3 | [03_mocking.md](03_mocking.md) | Mocking. Test `UserController` with Mockito standing in for its repository. | 60 minutes |
| 4 | [04_stretch_tdd_repository.md](04_stretch_tdd_repository.md) | Stretch task. Test drive a `ConcreteUserRepository`. | as long as you have |

Work through them in order. Exercise 3 reuses the test plan you wrote for exercise 2, and
the stretch task only makes sense once exercise 3 is done.

Each exercise has the same two halves:

- **Part 1 - create a test plan.** On paper or in the template, before you write any code.
- **Part 2 - implement your test plan.** One test method per row of the plan.

## The test plan template

[TEST_PLAN_TEMPLATE.md](TEST_PLAN_TEMPLATE.md), in this folder. It has a table per
exercise, with the guide's own example rows already filled in and blank rows underneath.
Copy it, or edit it in place; it is yours.

Fill in the "actual output" column only **after** you have run the test. If it does not
match what you expected, do not simply change the expectation. Work out whether the test is
wrong or the code is wrong, and write down which.

## Before you start

You need:

- Java 17 (`java -version`)
- Maven 3.9 or newer (`mvn -v`)
- Any IDE that opens a Maven project: IntelliJ IDEA, Eclipse, VS Code

Clone this repository, open the folder holding `pom.xml` as a Maven project, and run:

```bash
mvn test
```

It should pass immediately, reporting a large number of skipped tests. That is expected:
the test methods are unfinished stubs marked `@Disabled`, and your job is to fill them in.

```
Tests run: 47, Failures: 0, Errors: 0, Skipped: 44
```

## Where everything lives

```
src/exerciseN/       the code under test, do not change it
tests/exerciseN/     your tests, one skeleton class per exercise
tasks/               these pages and the test plan template
```

The original course guide linked out to a GitHub repository for the exercise source. This
repository replaces it, so those links have been swapped for the local paths above.

# Solutions

The completed tests for all three exercises, plus the stretch task.

These are deliberately kept out of the default build. `mvn test` from the project root
compiles and runs `src/test/java` only, so a student who clones the repository never has
the answers compiled into their IDE's test explorer.

To run them, from the project root (the folder holding `pom.xml`):

```bash
mvn test -P solutions
```

The profile simply repoints the `test.source.dir` property at this folder.

Contents:

```
solutions/src/test/java/com/qaa/module3/unit_testing_exercises/
  exercise1/CalculatorTest.java             16 tests
  exercise2/UserServiceTest.java            23 tests
  exercise3/UserControllerTest.java         21 tests
  exercise3/ConcreteUserRepositoryTest.java 11 tests
  exercise3/ConcreteUserRepository.java     the stretch task implementation
```

`ConcreteUserRepository` lives in this test tree rather than in `src/main/java`, because
writing it is the student's stretch task and it must not appear in the code they are
given.

Every test here asserts the behaviour the stated rules describe. Earlier versions of this
suite carried tests whose display name started with `DEFECT:`, which asserted the behaviour
the code really had rather than the behaviour its rules described. Those two defects have
now been fixed in `src/main/java`, so the tests assert the correct behaviour and the suite
passes because the code is right. `CODE_CORRECTIONS.md` in the project root records what was
wrong and what changed.

package com.qaa.module3.unit_testing_exercises.exercise3;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLUTION for the exercise 3 stretch task - test driven development.
 *
 * An in memory implementation of UserRepository that stores its users in a
 * List<User>, built one test at a time as the exercise guide describes.
 *
 * This class lives in the solutions test tree on purpose. Writing it is the
 * student's job, so it must not appear in src/main/java where they would find
 * it already done.
 *
 * The behaviour below is one reasonable reading of the interface. A student who
 * chooses different exception types is not wrong, as long as their test plan
 * and their tests agree with their implementation.
 */
public class ConcreteUserRepository implements UserRepository {

    /** The "database". Users live here for as long as the object does. */
    private final List<User> users = new ArrayList<>();

    /** Ids are handed out in order, starting at 1. */
    private int nextId = 1;

    @Override
    public boolean exists(String trimmedUsername) {
        if (trimmedUsername == null) return false;

        for (User user : users) {
            if (trimmedUsername.equals(user.getUsername())) return true;
        }
        return false;
    }

    @Override
    public User register(User user) {
        if (user == null) throw new IllegalArgumentException("User must not be null");
        if (exists(user.getUsername())) throw new IllegalArgumentException("Username already exists");

        User stored = new User(nextId, user.getUsername(), user.getPassword());
        nextId = nextId + 1;
        users.add(stored);
        return stored;
    }

    @Override
    public User login(User user) {
        if (user == null) throw new IllegalArgumentException("User must not be null");

        for (User stored : users) {
            if (stored.getUsername().equals(user.getUsername())) {
                // Username found, so now check the password.
                if (stored.getPassword().equals(user.getPassword())) return stored;
                throw new IllegalArgumentException("Invalid password supplied");
            }
        }
        throw new RuntimeException("Invalid username supplied");
    }
}

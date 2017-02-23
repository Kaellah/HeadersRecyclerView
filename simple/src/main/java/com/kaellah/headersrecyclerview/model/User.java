package com.kaellah.headersrecyclerview.model;

import android.support.annotation.NonNull;

import com.kaellah.headersrecyclerview.utility.Utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class User {

    private String mName;
    private String mDescription;

    @NonNull
    private static User random(@NonNull SecureRandom rnd, @NonNull Random random) {
        return new User("User: " + Utils.randomString(rnd, Utils.randInt(4, 10, random)), "Description: " + Utils.randomString(rnd, Utils.randInt(4, 10, random)));
    }

    @NonNull
    public static List<User> randomList(@NonNull SecureRandom rnd, int size, @NonNull Random random) {
        final List<User> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(User.random(rnd, random));
        }
        return list;
    }

    public User(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }
}

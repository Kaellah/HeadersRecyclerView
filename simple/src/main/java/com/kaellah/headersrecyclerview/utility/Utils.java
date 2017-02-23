package com.kaellah.headersrecyclerview.utility;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 23.02.17
 */

public class Utils {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static int randInt(int min, int max, Random rand) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static String randomString(SecureRandom rnd, int len) {
        final StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}

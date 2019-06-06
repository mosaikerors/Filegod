package com.mosaiker.filegod;

import java.util.Random;

public class Util {
    public static String randomNumber(int num) {
        if (num <= 0) {
            return null;
        }
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}

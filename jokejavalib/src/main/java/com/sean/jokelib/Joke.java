package com.sean.jokelib;

import java.util.Random;

public class Joke {
    public static String getJoke() {
        String[] otherJokes = retrieveOtherJokes();
        return getRandom(otherJokes);
    }

    private static String[] retrieveOtherJokes() {
        String[] defaultEmpty = new String[]{};
        return defaultEmpty;
    }

    private static String getRandom(String[] array) {
        if (array.length == 0) {
            return null;
        }
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private static String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}
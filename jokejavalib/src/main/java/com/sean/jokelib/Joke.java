package com.sean.jokelib;

import java.util.Random;

public class Joke {
    private static final String[] DEFAULT_JOKES = {
          "What did the PC say to the Jam Maker? \n DOES NOT COMPOTE!",
          "Why is six afraid of seven? \n Because seven \"eight\" nine."
    };

    public static String getJoke() {
        String[] otherJokes = retrieveOtherJokes();
        String[] allJokes = concat(DEFAULT_JOKES, otherJokes);
        return getRandom(allJokes);
    }


    private static String[] retrieveOtherJokes() {
        String[] defaultEmpty = new String[]{};
        return defaultEmpty;
    }

    private static String getRandom(String[] array) {
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
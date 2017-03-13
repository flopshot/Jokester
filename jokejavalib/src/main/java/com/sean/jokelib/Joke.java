package com.sean.jokelib;

import java.util.Random;

public class Joke {
    private static final String[] JOKE_DATA = {
          "What did the spider do on the computer? \n Made a website!",
          "What did the computer do at lunchtime? \n Had a byte! ",
          "What does a baby computer call his father? \n Data! ",
          "Why did the computer keep sneezing? \n It had a virus! ",
          "What is a computer virus? \n A terminal illness! ",
          "Why was the computer cold? \n It left it's Windows open! ",
          "Why was there a bug in the computer? \n Because it was looking for a byte to eat? ",
          "Why did the computer squeak? \n Because someone stepped on it's mouse! ",
          "What do you get when you cross a computer and a life guard? \n A screensaver! ",
          "Where do all the cool mice live? \n In their mousepads ",
          "What do you get when you cross a computer with an elephant? \n Lots of memory!"
    };

    public static String[] getJokeData() {
        return JOKE_DATA;
    }

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
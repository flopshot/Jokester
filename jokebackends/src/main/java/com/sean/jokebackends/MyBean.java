package com.sean.jokebackends;


import com.sean.jokelib.Joke;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    private static final String[] JOKE_DATA = Joke.getJokeData();

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public String[] getJokeData() {return JOKE_DATA;}
}
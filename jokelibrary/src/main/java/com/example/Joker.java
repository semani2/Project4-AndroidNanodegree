package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Joker implements IJoker{

    private Random randomGenerator;
    private ArrayList<String> jokesList;

    public Joker() {
        randomGenerator = new Random();
        jokesList = new ArrayList();

        initJokesList();
    }

    private void initJokesList() {
        jokesList = new ArrayList<String>(Arrays.asList(Jokes.jokes));
    }

    @Override
    public String getJoke() {
        int index = randomGenerator.nextInt(jokesList.size());
        return jokesList.get(index);
    }
}

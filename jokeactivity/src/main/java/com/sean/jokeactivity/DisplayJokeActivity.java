package com.sean.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class DisplayJokeActivity extends AppCompatActivity {

    private static final String INTENT_STRING_KEY = "intentKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView jokeView = (TextView) findViewById(R.id.joke_view);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
              .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
              .build();
        mAdView.loadAd(adRequest);

        Intent mainIntent = getIntent();
        if( mainIntent.getExtras() != null) {
            jokeView.setText(mainIntent.getStringExtra(INTENT_STRING_KEY));
        } else {
            jokeView.setText(getDefaultJoke());
        }
    }

    private String getDefaultJoke() {
        String[] defaultJokeArray = getResources().getStringArray(R.array.default_jokes);
        int rnd = new Random().nextInt(defaultJokeArray.length);
        return defaultJokeArray[rnd];
    }
}
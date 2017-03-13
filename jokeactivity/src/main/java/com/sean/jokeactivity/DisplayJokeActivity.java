package com.sean.jokeactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sean.jokebackends.myApi.MyApi;

import java.io.IOException;
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

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private static MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                      new AndroidJsonFactory(), null)
                      // options for running against local devappserver
                      // - 10.0.2.2 is localhost's IP address in Android emulator
                      // - turn off compression when running against local devappserver
                      .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                      .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                          @Override
                          public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                              abstractGoogleClientRequest.setDisableGZipContent(true);
                          }
                      });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                return myApiService.sayHi(name).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
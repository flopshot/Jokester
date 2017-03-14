package com.sean.jokeactivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sean.jokebackends.myApi.MyApi;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static com.sean.jokeactivity.Utility.DISPLAY_JOKE_ACTIVITY_STRING_KEY;

/**
 * Task to get joke data then launch DisplayJokeActivity
 */

public class EndpointAsyncTask extends AsyncTask<Context, Void, List<String>> {
    private static MyApi myApiService = null;
    private Context mContext;

    @Override
    protected List<String> doInBackground(Context... params) {
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

        mContext = params[0];

        try {
            return myApiService.getJokes().execute().getJokeData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> results) {
        String joke = selectJoke(results);

        Intent intentDisplayJoke = new Intent(mContext, DisplayJokeActivity.class);
        intentDisplayJoke.putExtra(DISPLAY_JOKE_ACTIVITY_STRING_KEY, joke);
        intentDisplayJoke.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intentDisplayJoke);

        Toast.makeText(mContext, joke, Toast.LENGTH_LONG).show();
    }

    private String selectJoke(List<String> jokes) {
        int rnd = new Random().nextInt(jokes.size());
        return jokes.get(rnd);
    }
}

package com.sean.jokester;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.sean.jokeactivity.EndpointAsyncTask;

import static com.sean.jokeactivity.Utility.FINALIZED_KEY;

public class MainActivity extends AppCompatActivity {
    ProgressBar spinner;
    EndpointAsyncTask mEndpointTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //If app was finalized in last onDestroy, hard set Visibility of spinner to GONE
        SharedPreferences prefs = PreferenceManager
              .getDefaultSharedPreferences(getApplicationContext());
        boolean hideSpinner = prefs.getBoolean(FINALIZED_KEY, true);
        if (hideSpinner) {
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        SharedPreferences prefs = PreferenceManager
              .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FINALIZED_KEY, false);
        editor.apply();

        spinner.setVisibility(View.VISIBLE);
        // 'Joke' library retrieves random Joke from default jokes and jokes retrieved from service

        mEndpointTask = new EndpointAsyncTask(BuildConfig.PAID_VERSION);
        mEndpointTask.execute(getApplicationContext());
    }

    @Override
    protected void onPause() {
        if (isFinishing()) {
            if (mEndpointTask != null) {
                mEndpointTask.cancel(true);

                SharedPreferences prefs = PreferenceManager
                      .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(FINALIZED_KEY, true);
                editor.apply();
            }
        }
        super.onPause();
    }
}
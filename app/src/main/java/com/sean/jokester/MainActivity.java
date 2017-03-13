package com.sean.jokester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sean.jokeactivity.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {

    private static final String INTENT_STRING_KEY = "intentKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        // 'Joke' library retrieves random Joke from default jokes and jokes retrieved from service
        Intent jokeActivityIntent = new Intent();
        jokeActivityIntent.setClass(getApplicationContext(), DisplayJokeActivity.class);
        startActivity(jokeActivityIntent);
        // Toast.makeText(this, joke, Toast.LENGTH_LONG).show();
    }
}
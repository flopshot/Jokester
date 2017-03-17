package com.sean.jokeactivity;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Test created for response of AsyncTask Joke Api
 */
@RunWith(AndroidJUnit4.class)

public class TestEndpointAsyncTask {

    private final Context mContext =  getInstrumentation().getTargetContext().getApplicationContext();

    private String mJsonString = null;
    private Exception mError = null;
    private CountDownLatch signal = null;

    @Before
    public void init() throws Exception {
        signal = new CountDownLatch(1);
    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();
    }

    @Test
    public void testEndpointTask() throws Throwable {
        EndpointAsyncTask task = new EndpointAsyncTask();
        task.setListener(new EndpointAsyncTask.GetTaskListener() {
            @Override
            public void onComplete(String joke, Exception e) {
                mJsonString = joke;
                mError = e;
                signal.countDown();
            }
        }).execute(mContext);
        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJsonString));
    }
}


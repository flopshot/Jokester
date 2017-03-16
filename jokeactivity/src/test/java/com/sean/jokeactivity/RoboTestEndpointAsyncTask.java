//
//package com.sean.jokeactivity;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.RuntimeEnvironment;
//import org.robolectric.annotation.Config;
//import org.robolectric.shadows.ShadowApplication;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
//
//public class RoboTestEndpointAsyncTask {
//    private final Context mContext = RuntimeEnvironment.application;
//
//    private String mJsonString = null;
//    private Exception mError = null;
////    private CountDownLatch signal = null;
//
////    @Before
////    public void init() throws Exception {
////        signal = new CountDownLatch(1);
////    }
////
////    @After
////    public void tearDown() throws Exception {
////        signal.countDown();
////    }
//
//    @Test
//    public void testEndpointAsyncTask() throws InterruptedException {
//        EndpointAsyncTask task = new EndpointAsyncTask();
//        task.setListener(new EndpointAsyncTask.GetTaskListener() {
//            @Override
//            public void onComplete(String joke, Exception e) {
//                mJsonString = joke;
//                mError = e;
////                signal.countDown();
//            }
//        }).execute(mContext);
////        signal.await();
//        ShadowApplication.runBackgroundTasks();
//        //Robolectric.flushBackgroundThreadScheduler();
//
//        assertNull(mError);
//        assertFalse(TextUtils.isEmpty(mJsonString));
//    }
//}

package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.asynctasks.EndpointsAsyncTask;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import ui.MainActivity;

/**
 * Created by sai on 1/9/17.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private static String response = null;

    @Test
    public void demoTest() throws Exception {
        final MainActivity mainActivity = activityRule.getActivity();

        final CountDownLatch signal = new CountDownLatch(1);

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointsAsyncTask(){
                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);
                        response = result;
                        signal.countDown();
                    }
                }.execute(mainActivity);
            }
        });

        signal.await(10, TimeUnit.SECONDS);
        Assert.assertNotNull(response);
    }
}

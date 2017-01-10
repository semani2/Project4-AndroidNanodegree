package com.udacity.gradle.builditbigger.asynctasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.sai.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import events.ToggleProgressDisplayEvent;
import sai.developement.androidjokeslibrary.JokeActivity;

/**
 * Created by sai on 1/9/17.
 */

public class EndpointsAsyncTask extends AsyncTask<Activity, Void, String> {
    private static MyApi myApiService = null;
    private Activity activity;

    @Override
    protected String doInBackground(Activity... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://my-project-1470326577912.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        activity = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        EventBus.getDefault().post(new ToggleProgressDisplayEvent(false));

        Bundle args = new Bundle();
        args.putString(JokeActivity.JOKE_INTENT_KEY, result);

        Intent showJokeIntent = new Intent(activity, JokeActivity.class);
        showJokeIntent.putExtras(args);

        activity.startActivity(showJokeIntent);
    }
}

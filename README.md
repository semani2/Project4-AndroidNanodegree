# Gradle for Android and Java Project

This project was done as part of the Udacity Android nanodegree program.

This project consists of the following:
- A multiple flavored android application.
- A java library that provides jokes.
- A Google Cloud Endpoints project that serves those provided jokes
- An Android library that displays the jokes to the users. 
- An Android app that fetches jokes from the GCE module and passes them to the Android library for display.

### Android Flavors ###
The Android app consits of two flavors : Paid, Free.

The Free flavor of the app displays ads to the users, where as the Paid version of the app does not display any ads. 

### Android testing ###
The Android app uses an AsyncTask to fetch the jokes from the Google Cloud Endpoints module. 
A simple JUnit test has been added to verify that the AsyncTask fetches a non empty joke from the end-point. 

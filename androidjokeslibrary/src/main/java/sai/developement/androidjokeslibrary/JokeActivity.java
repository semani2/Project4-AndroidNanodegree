package sai.developement.androidjokeslibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import timber.log.Timber;

public class JokeActivity extends AppCompatActivity {

    private TextView jokeTextView;

    public static final String JOKE_INTENT_KEY = "joke_intent_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if(getIntent().getExtras() == null || !getIntent().hasExtra(JOKE_INTENT_KEY)) {
            Timber.e("No joke string in intent");
            finish();
        }

        jokeTextView = (TextView) findViewById(R.id.jokeTextView);

        showJoke();
    }

    private void showJoke() {
        jokeTextView.setText(getIntent().getStringExtra(JOKE_INTENT_KEY));
    }

}

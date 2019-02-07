package tig567899.symbilitychallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    private final Handler mHideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        long now = System.currentTimeMillis();

        SimpleDateFormat df = new SimpleDateFormat("EEEE MMM dd, yyyy");
        String formattedDate = df.format(now);

        TextView clock = findViewById(R.id.date);
        clock.setText(formattedDate);

        mHideHandler.postDelayed(() ->
        {
            startActivity(new Intent(SplashActivity.this, GridActivity.class));
        }, 5000);
    }
}

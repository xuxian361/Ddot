package com.sundy.Ddot.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sundy.Ddot.R;

public class LoadingActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.d_register);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

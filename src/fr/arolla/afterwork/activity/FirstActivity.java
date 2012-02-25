package fr.arolla.afterwork.activity;

import android.app.Activity;
import android.os.Bundle;

public class FirstActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}

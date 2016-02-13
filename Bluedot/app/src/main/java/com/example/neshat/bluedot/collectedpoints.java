package com.example.neshat.bluedot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class collectedpoints extends AppCompatActivity
{
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectedpoints);
        b1 = (Button)findViewById(R.id.button3);
    }

    public void showavailable(View view)
    {
        //Intent intent = new Intent(collectedpoints.this, Existedpoints.class);
       // startActivity(intent);
        b1.setEnabled(false);
        finish();
    }

    public void backtopro(View view)
    {
        Intent intent = new Intent(collectedpoints.this, profile.class);
        startActivity(intent);
        finish();
    }

}

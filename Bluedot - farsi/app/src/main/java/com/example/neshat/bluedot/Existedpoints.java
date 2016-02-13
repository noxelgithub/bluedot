package com.example.neshat.bluedot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.neshat.bluedot.helper.SQLiteHandler;
import com.example.neshat.bluedot.helper.SessionManager;
//import static com.example.neshat.bluedot.Pointcalculator.totalcalculator;
//import static com.example.neshat.bluedot.Pointcalculator.digicalculator;

public class Existedpoints extends AppCompatActivity {


    public Pointcalculator pointcalculator = new Pointcalculator();
    private Button btnLogout;
    private SQLiteHandler db;
    private Button collect;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existedpoints);
        db = new SQLiteHandler(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        collect = (Button)findViewById(R.id.buttoncollect1);
    }



   public void showcollected (View view)
    {
        Intent intent = new Intent(Existedpoints.this, collectedpoints.class);
        startActivity(intent);
        collect.setEnabled(false);

        finish();
    }
    public void backtopro(View view)
    {
        Intent intent = new Intent(Existedpoints.this, profile.class);
        startActivity(intent);
        finish();
    }
    public void buy_digi(View view)
    {

    }

    public void buy_lg(View view)
    {

        //collect.setEnabled(false);
       // pointcalculator.digicalculator();
        Intent intent = new Intent(Existedpoints.this, Pointcalculator.class);
       startActivity(intent);

    }
}

package com.example.neshat.bluedot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.example.neshat.bluedot.helper.SQLiteHandlerforbeacon;
import com.example.neshat.bluedot.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity3 extends AppCompatActivity {
    public static final String TAG = "MyGeofencingApp";
    public int clickcounts = 0;
    public int dpoint = 0 ;
    public long starttime = 0;
    public long secondtime = 0;
    public long difference = 0;
    public static UUID uuid, uuid2;
    public static int majorNumber = 37981;
    public static int minorNumber = 21739;
    public static int majorNumber2 = 2458;
    public static int minorNumber2 = 38141;
    public Pointcalculator pointcalculator;
    private ProgressDialog pDialog;

    private SessionManager session;
    private SQLiteHandlerforbeacon db;

    public BeaconManager beaconManager;
    private static final Region textilePage2 = new Region("Our Region", uuid.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), majorNumber, minorNumber);
    private static final Region textilePage1 = new Region("Our Region", uuid2.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), majorNumber2, minorNumber2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

      // pointcalculator = new Pointcalculator();

        Log.d(TAG, "Beacons monitoring service created");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        showNotification1("Bluedot", "Pishnahade vije bank e gardeshgari");
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> beacons) {

                Log.d(TAG, "entered");
                Toast.makeText(MainActivity3.this, "Entered", Toast.LENGTH_LONG).show();
                if (region == textilePage1) {
                    showNotification1("Bluedot", " Ajans e Aftab!");


                } //else if (region == textilePage2) {
                 //   showNotification2("BEEKN", " Pishnahad e vije bank e gardeshgari!");

           //     }


            }//end of onEnteredRegion

            @Override
            public void onExitedRegion(Region region)
            {
                Log.d(TAG, "exited");
                Toast.makeText(MainActivity3.this, "Exited", Toast.LENGTH_LONG).show();
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.cancel(1);

                Notification noti = new Notification.Builder(MainActivity3.this)
                        .setContentTitle("Exited")
                        .setContentText("See you!")
                        .build();

                mNotificationManager.notify(2, noti);
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(textilePage1);
                beaconManager.startMonitoring(textilePage2);
            }
        });
        Log.d(TAG, "Beacons monitoring service starting");
        Toast.makeText(this, "Beacons monitoring service starting", Toast.LENGTH_SHORT).show();
    }

  /*  public void showNotification1(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity3.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }//end of showNotification*/

  /*  public void showNotification2(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity4.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }//end of showNotification*/


    public void digi1(View view)
    {
     //  clickcounts = clickcounts + 1;
       // Toast.makeText(getApplicationContext(), clickcounts , Toast.LENGTH_LONG).show();

      //  if(clickcounts == 1)
      //  {
         //   starttime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "Congratulations! you've just earned 80 Points!" , Toast.LENGTH_LONG).show();
            // dpoint = pointcalculator.digicalculator();
           // int tpoint = pointcalculator.totalcalculator();
          //  Toast.makeText(getApplicationContext(),dpoint, Toast.LENGTH_LONG);
            //send these two points to main db now
            goToUrl("http://www.aftabnetad.com");
      //  }
       /* else if( clickcounts > 0)
        {
            secondtime = System.currentTimeMillis();
            difference = (secondtime - starttime) / 1000;

            if( difference > 86400)
            {
                Toast.makeText(getApplicationContext(), "Congratulations! you've just earned 50 Points!" , Toast.LENGTH_LONG).show();
               // int dpoint = pointcalculator.digicalculator();
               // int tpoint = pointcalculator.totalcalculator();
               // Toast.makeText(getApplicationContext(),dpoint, Toast.LENGTH_LONG);
                //send these two points to main db now
               // goToUrl("http://www.digikala.com");
            }

            else if ( difference <= 86400)
            {
                Toast.makeText(getApplicationContext(), "To collect more points try again in 1 day!" , Toast.LENGTH_LONG).show();
            }

        }*/



    }

    private void goToUrl(String url)
    {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void showNotification1(String title, String message)
    {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Beacons Are Around You!", Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity3.this, "Entered", Toast.LENGTH_LONG).show();
        Intent notifyIntent = new Intent(this, MainActivity4.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }//end of showNotification

    private void  fetchLink(final String Major, final String Minor) {
        // Tag used to cancel the request
        String tag_string_req = "fetch_link";

        pDialog.setMessage("Fetching beacon ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FETCH_BEACON_LINKTOAD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "FETCH RESPONSE: " + response.toString());
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // Now store the user in sqlite


                        JSONObject user = jObj.getJSONObject("beacon");
                        String linktoad = user.getString("linktoad");


                        // Inserting row in users table
                     //   db.addUser(major, email, uid, created_at);

                        Toast.makeText(getApplicationContext(), "Link successfully retrieved!", Toast.LENGTH_LONG).show();
                    } else {

                        // Error occurred in location retrieving. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                        Log.d("Debug", errorMsg);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "link retriving Error: " + error.toString());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Major", Major);
                params.put("Minor", Minor);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }



    private void showDialog()
    {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog()
    {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

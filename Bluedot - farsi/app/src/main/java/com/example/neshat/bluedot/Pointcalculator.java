package com.example.neshat.bluedot;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.neshat.bluedot.helper.PreferenceData;
import com.example.neshat.bluedot.helper.SQLiteHandler;
import com.example.neshat.bluedot.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pointcalculator extends AppCompatActivity
{

    //here you have saved..Use this to get and use it.
    //  public SQLiteHandler db;

    // public HashMap<String, String> user = db.getUserDetails();

    //read the value from server
    public SessionManager session;
    public PreferenceData preferenceData;
    public  int totalpoints;
    public  int digipoints;
    public String ttlp;
    public String cp;
    private SharedPreferences prefs;
    public   String email ;
    private SQLiteHandler db;

    //  String email = user.get("email");
    public ProgressDialog pD;
    private static final String TAG = Pointcalculator.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointcalculator);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        session =new SessionManager(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());
       digicalculator();

     //   preferenceData = new PreferenceData(getActivity().getContext());


    }
    public  void  digicalculator()
    {
        try {
            Log.d("hereeeeeeeeeeeeee", "hereee");

             HashMap<String, String> user = db.getUserDetails();
            email = user.get("email");
            Log.d("emailllll", email);
            fetchPoints(50, 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //

        // return digipoints;
    }

    /*  Activity getAct() {
          Context context = this;
          while (context instanceof ContextWrapper) {
              if (context instanceof Activity) {
                  return (Activity) context;
              }
              context = ((ContextWrapper) context).getBaseContext();
          }
          return null;
      }*/
    private void fetchPoints( final int point, final int company)
    {
        // Tag used to cancel the request
        String tag_string_req = "fetch_points";
        Log.d("ggggggggg", "digicallll");

//         Toast.makeText(Pointcalculator.this,"in fetch", Toast.LENGTH_LONG).show();
       //   pD.setMessage("Fetching Points ...");
        showDialog(10);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_FETCH_USER_POINTS, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
                //  Toast.makeText(getApplicationContext(), "hereee", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onResponse");
                Log.d(TAG, "FETCH RESPONSE: " + response.toString());
                //  Toast.makeText(, response.toString(), Toast.LENGTH_LONG).show();

                //hideDialog(10);

                try {

                    JSONObject jObj = new JSONObject(response);
                    // boolean error = jObj.getBoolean("error");
                    //  if (!error)
                    //  {
                    // Now store the user in sqlite
                    Log.d("jobj", jObj.toString());
                    boolean error = jObj.getBoolean("error");
                    if(!error) {
                        JSONObject user = jObj.getJSONObject("user");
                        String totalpts = (user.getString("totalpoints"));
                        String digipts = (user.getString("digipoints"));
                        Log.d("totalpts", totalpts);
                        Log.d("digipts", digipts);
                        //Toast.makeText(getBaseContext(), "totalpoints : " + totalpts, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getBaseContext(), "digipoints : " + digipts, Toast.LENGTH_SHORT).show();

                        try {
                            totalpoints = Integer.parseInt(totalpts);
                            digipoints = Integer.parseInt(digipts);

                            if(company == 1 )
                            {
                                totalpoints = totalpoints + point;
                                digipoints = digipoints + point;

                                insertPoints(totalpoints, digipoints, 1);

                                //send back to DB and show in app screen
                            }
                            //Log.d("Integer", "value:" + totalpoints);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                            Log.d("int errorrrrr", "inttttttttttt");
                        }


                        // Inserting row in users table
                        // db.addUser(name, email, uid, created_at);

                        //Toast.makeText(getApplicationContext(), "Points successfully retrieved!", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e(TAG, "Points retriving Error: " + error.toString());
                // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams()
            {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);



                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void insertPoints( final int tpoint, final int cpoint , final int company)
    {

        // Tag used to cancel the request
        String tag_string_req = "insert_points";
        Log.d("insert", "inserttttttttttttttt");

        ttlp = String.valueOf(tpoint);
        cp = String.valueOf(cpoint);

        Log.d("ttlp", ttlp);
        Log.d("cp", cp);
        // Toast.makeText(Pointcalculator.this,"in fetch", Toast.LENGTH_LONG).show();
          //pD.setMessage("inserting Points ...");
        showDialog(10);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_INSERT_USER_POINTS, new Response.Listener<String>()
        {


            @Override
            public void onResponse(String response)
            {
                try {
                    Log.d(TAG, "onResponse");
                    Log.d(TAG, "insert RESPONSE: " + response.toString());
                    JSONObject jObj = new JSONObject(response);
                    // boolean error = jObj.getBoolean("error");
                    //  if (!error)
                    //  {
                    // Now store the user in sqlite
                    Log.d("jobjinsert", jObj.toString());
                    boolean error = jObj.getBoolean("error");
                    Log.d("error on insert", "" +error);



                    // Inserting row in users table
                    // db.addUser(name, email, uid, created_at);

                    //Toast.makeText(getApplicationContext(), "Points successfully retrieved!", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }



        }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e(TAG, "Points Inserting Error: " + error.toString());
                // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams()
            {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("totalpoints",ttlp );
                if(company == 1)
                    params.put("digipoints",cp);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

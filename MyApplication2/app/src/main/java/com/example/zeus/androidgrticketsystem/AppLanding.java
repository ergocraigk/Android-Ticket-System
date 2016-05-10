package com.example.zeus.androidgrticketsystem;


import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

<<<<<<< HEAD
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
=======
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

>>>>>>> origin/master
import org.json.JSONObject;


public class AppLanding extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button loginBtn;
    TextView successOrFailMessage;
    TextView paramsSent;

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    RequestQueue requestQueue = VolleySinglton.getInstance().getRequestQueue();
    final String login = "http://craigkoch.greenrivertech.net/loginAndroid.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (EditText) findViewById(R.id.userNameTextBox);
        password = (EditText) findViewById(R.id.passwordTextBox);
        loginBtn = (Button) findViewById(R.id.loginButton);
        paramsSent = (TextView) findViewById(R.id.paramsSent);
        successOrFailMessage = (TextView) findViewById(R.id.SuccessOrFailMessage);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                loginMethod(login);
=======

                JSONObject params = new JSONObject();
                //params.put("username","rajesh@gmail.com");
                //params.put("password","hellothere");

                JsonObjectRequest loginRequest = new JsonObjectRequest(
                        DownloadManager.Request.Method.POST,
                        "http://192.168.2.67/tmp/test.php",
                        params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.d("","");

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("", "");
                            }
                        });


                requestQueue.add(loginRequest);


                if (successOrFailMessage.getText() == "true") {
                    Intent loginSuccessIntent = new Intent(AppLanding.this, openTicketsActivity.class);
                    String message = userName.getText().toString();
                    loginSuccessIntent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(loginSuccessIntent);
                }
>>>>>>> origin/master
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void loginMethod(String url){

        JSONObject params = new JSONObject();
        try {
            params.put("username", userName.getText().toString());
            params.put("password", password.getText().toString());
            //paramsSent.setText(params.get("username").toString() + params.get("password").toString());
        } catch (JSONException e){
            Log.d("", "");
        }

        final JsonObjectRequest loginRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            if (jsonObject.get("success").equals("true")) {
                                successOrFailMessage.setText("hello");
                                Intent loginSuccessIntent = new Intent(AppLanding.this, TicketTableActivity.class);
                                String message = userName.getText().toString();
                                loginSuccessIntent.putExtra(EXTRA_MESSAGE, message);
                                startActivity(loginSuccessIntent);
                            }
                        } catch (JSONException e) {
                            Log.d("", "");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("", "");
                        successOrFailMessage.setText("User name or password was incorrect. Please try again.");
                    }
                });

        requestQueue.add(loginRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AppLanding Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zeus.androidgrticketsystem/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AppLanding Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.zeus.androidgrticketsystem/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

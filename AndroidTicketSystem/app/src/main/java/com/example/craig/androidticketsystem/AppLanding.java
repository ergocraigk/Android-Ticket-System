package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AppLanding extends AppCompatActivity {

    //this is a comment
    EditText userName;
    EditText password;
    Button loginBtn;
    TextView successOrFailMessage;
    TextView paramsSent;

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";

    RequestQueue requestQueue = VolleySinglton.getInstance().getRequestQueue();
    final String login = "http://craigkoch.greenrivertech.net/loginAndroid.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                loginMethod(login);
            }
        });
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleTicketActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    Button leavebtn;
    String receivedTicket;
    TextView textView;

    String url = "http://craigkoch.greenrivertech.net/AndroidTicketSystem/SingleTicket.php";

    TextView ticketid;
    TextView name;
    Spinner techChoice;
    Spinner category;
    // urgency? domain?
    TextView statusData;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leavebtn = (Button) findViewById(R.id.singleToTicketTable);
        Intent titintent = getIntent();
        receivedTicket = titintent.getStringExtra(TicketTableActivity.EXTRA_MESSAGE);

        receivedTicket = receivedTicket.substring(0,11);

        SubmitTicketMethod(url);

        leavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketUpdateSuccess = new Intent(SingleTicketActivity.this, TicketTableActivity.class);
                String message = "We send back stuff now ok goodbye";
                ticketUpdateSuccess.putExtra(EXTRA_MESSAGE, message);
                startActivity(ticketUpdateSuccess);
            }
        });

    }

    void SubmitTicketMethod(String url){

        JSONObject params = new JSONObject();
        try {
            params.put("ticketid", receivedTicket);
        } catch (JSONException e){
            Log.d("", "");
        }

        final JsonObjectRequest SubmitTicket = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("Error Message", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "fail" + volleyError, Toast.LENGTH_LONG).show();
                    }
                });

        AppLanding.requestQueue.add(SubmitTicket);
    }

}
/*
receive data from intent from TicketTableActivity
display 1 single ticket's information
1 inent to return to TicketTableActivity
send update information in json Object format via request que to ____________.php
 */
package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SubmitTicketActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    //RequestQueue requestQueue = VolleySinglton.getInstance().getRequestQueue();
    final String url = "http://craigkoch.greenrivertech.net/AndroidTicketSystem/AndroidSubmitTicket.php";
    Button leavebtn;
    Button submitTicketbtn;
    EditText firstname;
    EditText lastname;
    Spinner urgency;
    EditText description;
    EditText email;
    Spinner domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);

        urgency = (Spinner) findViewById(R.id.urgency);
        String[] urgencyOptions = new String[]{"Low", "Medium", "High"};
        ArrayAdapter<String> urgencyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, urgencyOptions);
        urgency.setAdapter(urgencyAdapter);

        description = (EditText) findViewById(R.id.description);
        email = (EditText) findViewById(R.id.email);

        domain = (Spinner) findViewById(R.id.domain);
        String[] domainOptions = new String[]{"Faculty", "Staff", "Student"};
        ArrayAdapter<String> domainAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, domainOptions);
        domain.setAdapter(domainAdapter);


        submitTicketbtn = (Button) findViewById(R.id.submitTicketbtn);
        submitTicketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SubmitTicketMethod(url);
            }
        });


        leavebtn = (Button) findViewById(R.id.singleToTicketTable);
        Intent titintent = getIntent();
        String titmessage = titintent.getStringExtra(TicketTableActivity.EXTRA_MESSAGE);

        leavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketUpdateSuccess = new Intent(SubmitTicketActivity.this, TicketTableActivity.class);
                String message = "We send back stuff now ok goodbye";
                ticketUpdateSuccess.putExtra(EXTRA_MESSAGE, message);
                startActivity(ticketUpdateSuccess);
            }
        });
    }

    void SubmitTicketMethod(String url){

        JSONObject params = new JSONObject();
        try {
            params.put("fname", firstname.getText().toString());
            params.put("lname", lastname.getText().toString());
            params.put("urgency", urgency.getSelectedItem().toString());// no get text testing
            params.put("description", description.getText().toString());
            params.put("domain", domain.getSelectedItem().toString());//no get text testing
            params.put("email", email.getText().toString());

            //paramsSent.setText(params.get("username").toString() + params.get("password").toString());
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

                        try {
                            if (jsonObject.get("success").equals("success")) {
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Log.d("", "");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("Error Message", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                    }
                });

        AppLanding.requestQueue.add(SubmitTicket);
    }
}
/*
1 intent to rturn to TicketTableACTIVITY
Receive intent from tickettableactivity
send update information in json Object format via request que to ____________.php
 */
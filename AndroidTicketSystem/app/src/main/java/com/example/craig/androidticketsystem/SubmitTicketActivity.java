package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SubmitTicketActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    Button leavebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
}
/*
1 intent to rturn to TicketTableACTIVITY
Receive intent from tickettableactivity
send update information in json Object format via request que to ____________.php
 */
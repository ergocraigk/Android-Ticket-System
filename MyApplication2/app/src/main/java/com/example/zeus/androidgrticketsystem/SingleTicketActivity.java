package com.example.zeus.androidgrticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;



public class SingleTicketActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    Button leavebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leavebtn = (Button) findViewById(R.id.singleToTicketTable);
        Intent titintent = getIntent();
        String titmessage = titintent.getStringExtra(SingleTicketActivity.EXTRA_MESSAGE);

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
}


/*
receive data from intent from TicketTableActivity
display 1 single ticket's information
1 inent to return to TicketTableActivity
send update information in json Object format via request que to ____________.php
 */
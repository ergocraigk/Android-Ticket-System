package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketTableActivity extends AppCompatActivity {

    TextView receivedMessage;
    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";
    Button leavebtn;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent logintent = getIntent();
        String logmessage = logintent.getStringExtra(AppLanding.EXTRA_MESSAGE);

        receivedMessage = (TextView) findViewById(R.id.receivedMessage);
        receivedMessage.setText(logmessage);

        Intent stintent = getIntent();
        String stmessage = stintent.getStringExtra(SingleTicketActivity.EXTRA_MESSAGE);

        receivedMessage = (TextView) findViewById(R.id.receivedMessage);
        receivedMessage.setText(stmessage);

        leavebtn = (Button) findViewById(R.id.singleToTicketTable);

        leavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketSelectSuccess = new Intent(TicketTableActivity.this, SingleTicketActivity.class);
                String message = "We send back stuff now ok goodbye";
                ticketSelectSuccess.putExtra(EXTRA_MESSAGE, message);
                startActivity(ticketSelectSuccess);
            }
        });

        submitbtn = (Button) findViewById(R.id.ticketTableToTSubmitTable);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketSubmitSuccess = new Intent(TicketTableActivity.this, SubmitTicketActivity.class);
                String message = "We send back stuff now ok goodbye";
                ticketSubmitSuccess.putExtra(EXTRA_MESSAGE, message);
                startActivity(ticketSubmitSuccess);
            }
        });
    }
}
/*
receive intent from applanding/login
1 intent to go to singleTicketActivity and send data for 1 ticket to single Ticket Activity
receive intent from single ticket activity
1 intent to go to submitTicketActivity
1 intent to receive intent from submitTicketActivity
Pull query for multiple ticket Information from database
 */
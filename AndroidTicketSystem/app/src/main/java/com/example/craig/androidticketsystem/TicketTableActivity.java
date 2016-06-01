package com.example.craig.androidticketsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TicketTableActivity extends AppCompatActivity {

    TextView receivedMessage;
    TextView dataComingIn;
    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";

    final String url = "http://craigkoch.greenrivertech.net/AndroidTicketSystem/AndroidTicketTable.php";
    Button leavebtn;
    Button submitbtn;

    JSONObject json_data;

    ArrayList<String> jsonResponse = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jsonResponse);

        TicketTableMethod(url);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Intent logintent = getIntent();
        String logmessage = logintent.getStringExtra(AppLanding.EXTRA_MESSAGE);

        receivedMessage = (TextView) findViewById(R.id.receivedMessage);
        receivedMessage.setText(logmessage);

        Intent stintent = getIntent();
        String stmessage = stintent.getStringExtra(SingleTicketActivity.EXTRA_MESSAGE);

        receivedMessage = (TextView) findViewById(R.id.receivedMessage);
        receivedMessage.setText(stmessage);

        dataComingIn = (TextView) findViewById(R.id.dataComingIn);
        //dataComingIn.setText("jsonString");


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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void TicketTableMethod(String url) {

        JsonArrayRequest req = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("Error Message", response.toString());



                        try {
                            // Parsing json array response
                            // loop through each json object

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jobject = (JSONObject) response.get(i);

                                String array = "";
                                array += jobject.get("ticketid").toString();
                                array += jobject.get("firstname").toString();
                                array += jobject.get("lastname").toString();

                                jsonResponse.add(array);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(getApplicationContext(),
                                jsonResponse.toString(),
                                Toast.LENGTH_LONG).show();
                        //hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error message 2", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                //hidepDialog();
            }
        });

        AppLanding.requestQueue.add(req);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TicketTable Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.craig.androidticketsystem/http/host/path")
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
                "TicketTable Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.craig.androidticketsystem/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
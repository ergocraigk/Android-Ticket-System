package com.example.zeus.androidgrticketsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AppLanding extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button loginBtn;
    TextView successOrFailMessage;

    public final static String EXTRA_MESSAGE = "this is the extra message we had to have for some reason.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (EditText) findViewById(R.id.userNameTextBox);
        password = (EditText) findViewById(R.id.passwordTextBox);
        loginBtn = (Button) findViewById(R.id.loginButton);
        successOrFailMessage = (TextView) findViewById(R.id.SuccessOrFailMessage);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginSuccessIntent = new Intent(AppLanding.this, openTicketsActivity.class);
                String message = userName.getText().toString();
                loginSuccessIntent.putExtra(EXTRA_MESSAGE, message);
                startActivity(loginSuccessIntent);
            }
        });
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
}

package com.example.catherinaxu.mycityfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class NewEntryActivity extends Activity {

private static final int NUM_RESULTS = 1;
private static final String INPUT = "input";
private static final int GET_DESTINATION = 10;
private static Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
    }

    private void passMarkerFields(String text) {
        Intent intent = new Intent();
        intent.putExtra("latitude", address.getLatitude());
        intent.putExtra("longitude", address.getLongitude());
        intent.putExtra("name", address.getFeatureName());
        intent.putExtra("info", text);
        setResult(GET_DESTINATION, intent);
        finish();
    }

    public void setDescription(View view) {
        EditText edittext = (EditText) findViewById(R.id.info);
        String text = edittext.getText().toString();
        if (text.equals("")) {
            Toast.makeText(this, "Please enter your description", Toast.LENGTH_SHORT).show();
        } else {
            passMarkerFields(text);
        }
    }
    public void searchLocation(View view) {
        EditText edittext = (EditText) findViewById(R.id.destination);
        String text = edittext.getText().toString();
        Log.d(INPUT, "The user input is " + text);
        if (text.equals("")) {
            Toast.makeText(this, "Please enter the destination", Toast.LENGTH_SHORT).show();
        } else {
            Geocoder geocoder = new Geocoder(this, Locale.US);
            if (!geocoder.isPresent()) { //what is this error?
                Toast.makeText(this, "Please install Google Maps to continue", Toast.LENGTH_SHORT).show();
            }
            try {
                List<Address> matches = geocoder.getFromLocationName(text, NUM_RESULTS);
                if (matches.size() == 0) {
                    Toast.makeText(this, "Destination not found. Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Destination found! Added to map.", Toast.LENGTH_SHORT).show();
                    address = matches.get(0);
                    //passMarkerToMap(matches.get(0));
                }
            } catch (IOException exception) {
                Toast.makeText(this, "No network connection. Please check and try again.", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_entry, menu);
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

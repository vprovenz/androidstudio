package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user taps the Calculate button
     * this keeps crashing wheever try to press calculate twice?
     * something with redo?*/
    public void calculateBill(View view) {
        EditText inputPrice = (EditText) findViewById(R.id.editText3);
        EditText inputTip = (EditText) findViewById(R.id.editText2);
        EditText inputSplit = (EditText) findViewById(R.id.editText4);

        //crashes if any of these fields are empty: make more robust
        if (inputPrice.getText().toString().matches("")) {
            Toast.makeText(this, "Please enter a price", Toast.LENGTH_LONG).show();
            return;
        }
        double price = Double.parseDouble(inputPrice.getText().toString());

        if (inputTip.getText().toString().matches("")) {
            Toast.makeText(this, "Please enter a tip percent from 0-100", Toast.LENGTH_LONG).show();
            return;
        }
        double tip = Double.parseDouble(inputTip.getText().toString()) * 0.01;
        if (tip<0 || tip>100) {
            Toast.makeText(this, "Please enter a tip percent from 0-100", Toast.LENGTH_LONG).show();
            return;
        }

        if (inputSplit.getText().toString().matches("")) {
            Toast.makeText(this, "Please enter split", Toast.LENGTH_LONG).show();
            return;
        }
        double split = Double.parseDouble(inputSplit.getText().toString());
        if (split<1) {
            Toast.makeText(this, "Please enter positive number for split", Toast.LENGTH_LONG).show();
            return;
        }

        // Math.floor(value * 100) / 100;
        double tipDouble = Math.floor(price * tip * 100)/100;
        double finalDouble = Math.floor(((price * tip) + price) * 100)/100;
        double pricePerPersonDouble = Math.floor((((price * tip) + price)/split) * 100)/100;

        String finalTip = String.valueOf(tipDouble);
        String finalPrice = String.valueOf(finalDouble);
        String finalPricePerPerson = String.valueOf(pricePerPersonDouble);

        TextView textView1 = findViewById(R.id.textView5);
        textView1.setText(String.format("$%s", finalPrice));
        TextView textView2 = findViewById(R.id.textView8);
        textView2.setText(String.format("$%s", finalPricePerPerson));
        TextView textView3 = findViewById(R.id.textView10);
        textView3.setText(String.format("$%s", finalTip));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_button, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        // Any other things you have to do when creating the options menu...
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

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
        double price = Double.parseDouble(inputPrice.getText().toString());

        //make sure input is from 0-100. default is 0 or toast "please enter val 0-1"
        double tip = Double.parseDouble(inputTip.getText().toString()) * 0.01;

        //if no input assume 0
        double split = Double.parseDouble(inputSplit.getText().toString());

        double finalDouble = (price * tip) + price;
        double pricePerPersonDouble = ((price * tip) + price)/split;

        String finalPrice = String.valueOf(finalDouble);
        String finalPricePerPerson = String.valueOf(pricePerPersonDouble);
        TextView textView = findViewById(R.id.textView5);
        textView.setText(finalPrice);
        TextView textView2 = findViewById(R.id.textView8);
        textView2.setText(finalPricePerPerson);

        Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(split), Toast.LENGTH_LONG);
        toast.show();
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

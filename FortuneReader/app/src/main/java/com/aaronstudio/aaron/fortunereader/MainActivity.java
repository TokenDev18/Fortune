package com.aaronstudio.aaron.fortunereader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText editText;
    public Button button, buttonDone;
    public TextView textView;
    public String userString;
    FortuneTeller fortune = new FortuneTeller();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Instantiating objects for buttons, textviews, editText, etc...
        textView = (TextView) findViewById(R.id.text_here);
        button = (Button) findViewById(R.id.button_testluck);
        buttonDone = (Button) findViewById(R.id.press_done);
        editText = (EditText) findViewById(R.id.edit_message);



        //On click listener for the send button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userString = editText.getText().toString(); //getting input from user
                textView.setText(fortune.getFortune(userString)); //Setting text view with user input
                editText.getText().clear();
            }
        });

        //On click listener for the done button
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.builder_title); //Setting alert dialog title
                builder.setMessage(R.string.builder_message); //Setting alert dialog message
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

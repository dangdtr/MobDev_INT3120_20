package com.example.slide4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView selection;
    String[] items = {"Android","IPhone","WindowsMobile",
            "Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    Spinner spin = null;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selection = (TextView) findViewById(R.id.selection_1);
        spin = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);

        aa.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(aa);
        spin.setPrompt("Select an item");

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Select" + selectedItem, Toast.LENGTH_LONG).show();

                selection.setText("Selected Item: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                Toast.makeText(MainActivity.this, "Nothing select", Toast.LENGTH_LONG).show();
                selection.setText("Selected Item: NONE");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.item_slide4_1) {
            Intent intent = new Intent(this, Assignment41.class);
            startActivity(intent);

            Toast.makeText(this, "Move to slide 4", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
        if (id == R.id.item_slide4_2) {
            Intent intent = new Intent(this, Assignment42.class);
            startActivity(intent);

            Toast.makeText(this, "Move to slide 4", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

//        if (id == R.id.item_slide6) {
////            Intent intent = new Intent(this, ContextMenuActivity.class);
////            startActivity(intent);
//
//            Toast.makeText(this, "Move to slide 6", Toast.LENGTH_LONG).show();
//            return super.onOptionsItemSelected(item);
//        }

        if (id == R.id.item_slide7) {
            Intent intent = new Intent(this, MainSlide7.class);
            startActivity(intent);


            Toast.makeText(this, "Move to slide 7", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }



}

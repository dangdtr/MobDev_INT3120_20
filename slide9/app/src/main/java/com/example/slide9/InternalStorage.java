package com.example.slide9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InternalStorage extends AppCompatActivity {

    EditText textmsg;

    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_storage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textmsg=(EditText)findViewById(R.id.editText1);

        String path = String.valueOf(getCacheDir());
        Log.e("PATH_CACHE", path);
    }

    public void WriteBtn(View v) {
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v) {
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Move to Main", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
        if (id == R.id.item2) {
            Intent intent = new Intent(this, InternalStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to InternalStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item3) {
            Intent intent = new Intent(this, ExternalStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to ExternalStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item4) {
            Intent intent = new Intent(this, SQLiteStorage.class);
            startActivity(intent);
            Toast.makeText(this, "Move to SQLiteStorage", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item5) {
            Intent intent = new Intent(this, WeatherActivityy.class);
            startActivity(intent);
            Toast.makeText(this, "Move to ", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }


}
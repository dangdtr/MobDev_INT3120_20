package com.example.slide9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorage extends AppCompatActivity {
    EditText inputText;

    TextView response;
    Button saveButton,readButton, delbutton;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        inputText = (EditText) findViewById(R.id.myInputText);
        response = (TextView) findViewById(R.id.response);


        saveButton =
                (Button) findViewById(R.id.saveExternalStorage);

        saveButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(inputText.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText("");
                response.setText("SampleFile.txt saved to External Storage..." + String.valueOf(myExternalFile));
            }
        });

        readButton = (Button) findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText(myData);
                response.setText("SampleFile.txt data retrieved from External Storage");
            }
        });

        delbutton = (Button) findViewById(R.id.delExternalStorage);
        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExternalStoragePrivateFile();
            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveButton.setEnabled(false);
        }
        else {
//            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            myExternalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

            String path = String.valueOf(myExternalFile);
            Log.e("EXTERNAL_PATH", path);
        }


    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    void deleteExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getExternalFilesDir(null), "DemoFile.jpg");
        Log.e("PATH", String.valueOf(file));
        if (file != null) {
            file.delete();
        }
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
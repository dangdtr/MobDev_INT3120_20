package com.example.slide6;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slide6.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.firstFragment);

    }
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment selectedFragment = null;
        int itemId = item.getItemId(); // Get the selected item's ID

        if (itemId == R.id.firstFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, firstFragment)
                    .commit();
            return true;
        }

        if (itemId == R.id.secondFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, secondFragment)
                    .commit();
            return true;
        }

        if (itemId == R.id.thirdFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.item_first) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, firstFragment)
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.firstFragment);

            Toast.makeText(this, "Move to first fragment", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item_second) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, secondFragment)
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.secondFragment);

            Toast.makeText(this, "Move to second fragment", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item2_1) {
            Intent intent = new Intent(this, ContextMenuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Move to other activity", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

}

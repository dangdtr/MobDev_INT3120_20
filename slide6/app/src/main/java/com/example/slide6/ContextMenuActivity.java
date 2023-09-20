package com.example.slide6;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ContextMenuActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu_ac);

        textView = findViewById(R.id.textView);

        // Register the TextView for the context menu
        registerForContextMenu(textView);

        Button button = findViewById(R.id.button_context_menu);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ContextMenuActivity when the button is clicked
                System.out.println("Here");
                Intent intent = new Intent(ContextMenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}
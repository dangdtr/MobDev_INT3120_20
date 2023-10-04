package com.example.slide10;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditContactActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonSave, buttonCancel;
    private long contactId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        contactId = getIntent().getLongExtra("contact_id", -1);

        if (contactId != -1) {
            // Editing existing contact
            loadContactDetails(contactId);
        }


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the contact details to the ContactsProvider
                saveContact();
                Intent intent = new Intent(AddEditContactActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity without saving
            }
        });
    }

    private void saveContact() {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();

        ContentValues values = new ContentValues();
        values.put(ContactsDbHelper.COLUMN_NAME, name);
        values.put(ContactsDbHelper.COLUMN_PHONE, phone);
        Uri contactUri;

        if (contactId == -1) {
            // Add new contact
            contactUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
        } else {
            // Update existing contact
            contactUri = Uri.withAppendedPath(ContactsProvider.CONTENT_URI, String.valueOf(contactId));
            int rowsAffected = getContentResolver().update(contactUri, values, null, null);

            if (rowsAffected == 0) {
                Toast.makeText(this, "Failed to update contact", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (contactUri != null) {
            if (contactId == -1) {
                Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        } else {
            Toast.makeText(this, "Failed to add contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadContactDetails(long contactId) {
        // Load contact details based on the contactId and populate the EditText fields
        Cursor cursor = getContentResolver().query(
                ContactsProvider.CONTENT_URI.buildUpon().appendPath(String.valueOf(contactId)).build(),
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsDbHelper.COLUMN_NAME));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsDbHelper.COLUMN_PHONE));

            editTextName.setText(name);
            editTextPhone.setText(phone);

            cursor.close();
        }
    }
}

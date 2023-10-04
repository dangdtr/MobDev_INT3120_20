package com.example.slide10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private Button buttonAdd;

    private ArrayList<Contact> contactList;
    public ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewContacts = findViewById(R.id.listViewContacts);
        buttonAdd = findViewById(R.id.buttonAdd);

        contactList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, R.layout.contact_item, R.id.textViewName, contactList);


        ContactAdapter adapter = new ContactAdapter(this, contactList, this);



        listViewContacts.setAdapter(adapter);

        loadContacts();

        // Set up click listeners
        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = contactList.get(position);
                Toast.makeText(MainActivity.this, "Clicked: " + selectedContact.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditContactActivity.class);
                startActivity(intent);

            }
        });
    }

    private void loadContacts() {
        // Query the Content Provider to get all contacts
        // Các cột dữ liệu cần lấy
        String[] mProjection = {
                ContactsDbHelper.COLUMN_ID,      // Hằng số cho tên cột _ID
                ContactsDbHelper.COLUMN_NAME,     // Hằng số cho tên cột word
                ContactsDbHelper.COLUMN_PHONE};  // Hằng số cho tên cột locale

        // Lấy từ cần truy vấn từ giao diện
//        mSearchString = mSearchWord.getText().toString();

        // Yêu cầu truy vấn các từ chứa chuỗi đưa vào
        String mSelectionClause = ContactsDbHelper.COLUMN_NAME + " like ?";
        String[] mSelectionArgs = {""};
//        mSelectionArgs[0] = "%" + mSearchString + "%";

        Cursor mCursor = getContentResolver().query(
                ContactsProvider.CONTENT_URI,
                mProjection,
                null,
                null,
                null
        );

        assert mCursor != null;


        while (mCursor.moveToNext()) {
            @SuppressLint("Range") long id = mCursor.getLong(mCursor.getColumnIndex(ContactsDbHelper.COLUMN_ID));
            @SuppressLint("Range") String name = mCursor.getString(mCursor.getColumnIndex(ContactsDbHelper.COLUMN_NAME));
            @SuppressLint("Range") String phone = mCursor.getString(mCursor.getColumnIndex(ContactsDbHelper.COLUMN_PHONE));
            Log.e("CONTACT", id +  name + phone);
            Contact contact = new Contact(id, name, phone);
            contactList.add(contact);
        }

//        adapter.notifyDataSetChanged();


        mCursor.close();

    }



}

package com.example.slide10;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Activity mActivity;

    public ContactAdapter(Context context, List<Contact> contacts, Activity activity) {
        super(context, 0, contacts);
        mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact contact = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        // Lookup view for data population
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        // Populate the data into the template view using the data object
        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());

        // Handle the "Edit" button click
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddEditContactActivity.class);
                intent.putExtra("contact_id", contact.getId());
                getContext().startActivity(intent);                Toast.makeText(getContext(), "Edit button clicked for " + contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // Handle the "Delete" button click
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri contactUri = getContext().getContentResolver().delete(ContactsProvider.CONTENT_URI, contact.getId());

                Uri contactUri = ContentUris.withAppendedId(ContactsProvider.CONTENT_URI, contact.getId());
                int rowsDeleted = getContext().getContentResolver().delete(contactUri, null, null);

                if (rowsDeleted > 0) {
                    mActivity.recreate();
                    Toast.makeText(getContext(), "Deleted: " + contact.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the case where the deletion was not successful
                    Toast.makeText(getContext(), "Failed to delete contact", Toast.LENGTH_SHORT).show();
                }

                notifyDataSetChanged(); // Notify the adapter that the data set has changed
                Toast.makeText(getContext(), "Delete button clicked for " + contact.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}

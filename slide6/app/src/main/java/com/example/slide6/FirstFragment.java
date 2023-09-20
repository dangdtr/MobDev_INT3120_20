package com.example.slide6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class FirstFragment extends Fragment {

    public FirstFragment(){
        // require a empty public constructor
    }
    private Button button;

    public Button IntBtn;
    public View FragmentView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {


        this.button =  view.findViewById(R.id.button_to_context_menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ContextMenuActivity when the button is clicked
                Intent intent = new Intent(getActivity(), ContextMenuActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        IntBtn = v.findViewById(R.id.button_open_context_menu);
        registerForContextMenu(IntBtn);
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item2_ctx) {
            Toast.makeText(getActivity(), "This is a message of item2", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item3_ctx) {
            Toast.makeText(getActivity(), "This is a message of item3", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.item4_ctx) {
            Toast.makeText(getActivity(), "This is a message of item4", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }

        return super.onContextItemSelected(item);

    }




}

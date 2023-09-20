package com.example.slide6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    public Button IntBtn;

    public SecondFragment(){
        // require a empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        IntBtn = v.findViewById(R.id.button_to_pop_menu);

        IntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1Clicked();
                popupInOtherView();
            }
        });

        return v;
    }

    private void button1Clicked() {

        PopupMenu popup = new PopupMenu(getActivity(), this.IntBtn);
        popup.inflate(R.menu.popup_menu);

        Menu menu = popup.getMenu();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.item2_pop) {
                    Toast.makeText(getActivity(), "This is a message of item2", Toast.LENGTH_LONG).show();
                    return true;
                }

                if (id == R.id.item3_pop) {
                    Toast.makeText(getActivity(), "This is a message of item3", Toast.LENGTH_LONG).show();
                    return true;
                }

                if (id == R.id.item4_pop) {
                    Toast.makeText(getActivity(), "This is a message of item4", Toast.LENGTH_LONG).show();
                    return true;
                }

                return true;
            }
        });

        popup.show();

    }

    private void popupInOtherView() {

        PopupMenu popup = new PopupMenu(getActivity(), getView().findViewById(R.id.secondFragment));
        popup.inflate(R.menu.popup_menu);

        Menu menu = popup.getMenu();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.item2_pop) {
                    Toast.makeText(getActivity(), "This is a message of item2", Toast.LENGTH_LONG).show();
                    return true;
                }

                if (id == R.id.item3_pop) {
                    Toast.makeText(getActivity(), "This is a message of item3", Toast.LENGTH_LONG).show();
                    return true;
                }

                if (id == R.id.item4_pop) {
                    Toast.makeText(getActivity(), "This is a message of item4", Toast.LENGTH_LONG).show();
                    return true;
                }

                return true;
            }
        });

        popup.show();

    }


}
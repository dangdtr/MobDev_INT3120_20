package com.example.slide77;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.PopupMenu;
import android.widget.Toast;

public class IntentActivity extends Activity {
    private Button buttonBackToMain = null;
    private Button buttonIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        this.buttonBackToMain = (Button) this.findViewById(R.id.button_back_to_main);
        this.buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        this.buttonIntent = (Button) this.findViewById(R.id.button_intent);
        this.buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1Clicked();

            }
        });

    }

    private void button1Clicked() {

        PopupMenu popup = new PopupMenu(this, this.buttonIntent);
        popup.inflate(R.menu.popup_menu);


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.searchIntent) {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, "How to A+ mobile");
                    startActivity(intent);

                    return true;
                }

                if (id == R.id.phoneIntent) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:068686868"));
                    startActivity(intent);
                    return true;
                }

                if (id == R.id.smsIntent) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:0888999666"));
                    intent.putExtra("sms body", "are we going to UET?");
                    startActivity(intent);
                    return true;
                }

                if (id == R.id.pictureIntent) {
                    Intent intent = new Intent();
                    intent.setType("image/pictures/");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivity(intent);

                    return true;
                }

                if (id == R.id.contactsIntent) {
                    String myData = "content://contacts/people/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
                    startActivity(intent);
                    return true;
                }

                if (id == R.id.geoIntent) {
                    String geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
                    startActivity(intent);
                    return true;
                }

                if (id == R.id.musicIntent) {
                    Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                    startActivity(intent);
                    return true;
                }

                return true;
            }
        });

        popup.show();

    }
}
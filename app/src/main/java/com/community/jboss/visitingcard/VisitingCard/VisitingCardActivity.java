package com.community.jboss.visitingcard.VisitingCard;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.community.jboss.visitingcard.Maps.MapsActivity;
import com.community.jboss.visitingcard.R;
import com.community.jboss.visitingcard.SettingsActivity;

import java.util.ArrayList;

public class VisitingCardActivity extends AppCompatActivity {

    public ArrayList<ListDetail> getListViewElements() {
        ArrayList<ListDetail> ret = new ArrayList<>();
        // Make network calls or read from local storage and fill in this arrayList

        ListDetail listDetails = new ListDetail();
        listDetails.setName("Name");
        listDetails.setDescription("Name LastName");
        ret.add(listDetails);

        listDetails = new ListDetail();
        listDetails.setName("Email");
        listDetails.setDescription("abc@xyz.com");
        ret.add(listDetails);

        listDetails = new ListDetail();
        listDetails.setName("Phone");
        listDetails.setDescription("0123456789");
        ret.add(listDetails);

        listDetails = new ListDetail();
        listDetails.setName("GitHub");
        listDetails.setDescription("github.com/profileName");
        ret.add(listDetails);

        listDetails = new ListDetail();
        listDetails.setName("Linkedin");
        listDetails.setDescription("linkedin.com/profile");
        ret.add(listDetails);

        listDetails = new ListDetail();
        listDetails.setName("Twitter");
        listDetails.setDescription("twitter.com/profile");
        ret.add(listDetails);

        return ret;
    }
    public void setListElements(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ListView listView = (ListView) findViewById(R.id.list_view);
                ArrayList<ListDetail> list_details = getListViewElements();
                listView.setAdapter(new ListBaseAdapter(getApplicationContext(), list_details));

//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visiting_card);

        // TODO: Add a ImageView and a number of EditText to get his/her Visiting Card details (Currently authenticated User)

        // TODO: Clicking the ImageView should invoke an implicit intent to take an image using camera / pick an image from the Gallery.

        // TODO: Replace FAB icon with a SAVE icon
        // TODO: On Click on FAB should make a network call to store the entered information in the cloud using POST method(Do this in NetworkUtils class)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Proceed to Maps Activity", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent toVisitingCard = new Intent(VisitingCardActivity.this, MapsActivity.class);
                                startActivity(toVisitingCard);
                            }
                        }).show();
            }
        });

        setListElements();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(VisitingCardActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.community.jboss.visitingcard.Maps;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.community.jboss.visitingcard.R;
import com.community.jboss.visitingcard.VisitingCard.ViewVisitingCard;
import com.community.jboss.visitingcard.VisitingCard.VisitingCardActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    public static ArrayList<ListDetails> getListViewElements() {
        ArrayList<ListDetails> ret = new ArrayList<ListDetails>();

        for (int i = 0; i < 10; i++) {
            ListDetails listDetails = new ListDetails();
            listDetails.setName("Name"+i);
            listDetails.setEmail("email"+i+"@domain.com");
            ret.add(listDetails);
        }

        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        View bottomSheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

        ArrayList<ListDetails> list_details = getListViewElements();
        final ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(new ListBaseAdapter(this, list_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = listView.getItemAtPosition(i);
                ListDetails obj_listDetails = (ListDetails) obj;
                // now you can get all the information you need thru obj_listDetails.get
            }
        });
        // TODO: List item click should result in launching of ViewVisitingCard Acitivity with the info of the tapped Visiting card.
        // for the above, just implement passing of info between intents, the info can be accessed thru the object above.


        TextView near_text = (TextView) findViewById(R.id.cardsNearMeText);
        near_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toVisitingCardView = new Intent(MapsActivity.this, ViewVisitingCard.class);
                startActivity(toVisitingCardView);
            }
        });

        //TODO: Create Custom pins for the selected location
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //TODO: Implement geo-fencing(NOT AS A WHOLE) just visual representation .i.e., a circle of an arbitrary radius with the PIN being the centre of it.
        //TODO: Make the circle color as @color/colorAccent

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();
                            LatLng locn = new LatLng(lat, lon);
                            mMap.addMarker(new MarkerOptions().position(locn).title("Marker in Delhi!"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(locn));
                        } else {
                            Toast myToast = Toast.makeText(getApplicationContext(), "Error getting location!", Toast.LENGTH_LONG);
                            myToast.show();
                        }
                    }
                });
    }


    // TODO: Replace the stating location with user's current location.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Delhi, and move the camera.
        LatLng delhi = new LatLng(29, 77);
        mMap.addMarker(new MarkerOptions().position(delhi).title("Marker in Delhi!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi));
    }
}

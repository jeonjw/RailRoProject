package kr.ac.ajou.railroproject;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }


    @Override

    public void onMapReady(final GoogleMap map) {
        double mapX = getIntent().getExtras().getDouble("MAP_X");
        double mapY = getIntent().getExtras().getDouble("MAP_Y");
        String title = getIntent().getExtras().getString("TITLE");

        LatLng SEOUL = new LatLng(mapY, mapX);

        System.out.println("MAP : " + mapX + " " + mapY);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title(title);
        map.addMarker(markerOptions);


        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }


}



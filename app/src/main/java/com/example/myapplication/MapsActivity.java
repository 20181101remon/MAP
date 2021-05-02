package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    Intent in=new Intent();

    //    設定經度與緯度
    private static final LatLng satation01=new LatLng(25.04622,121.517451);
    private Marker marker01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        processViews();

    }
    public void processViews(){

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        讀取型態為SupportMapFragment的Google Map元件
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        註冊地圖元件建立完成監聽物件
        mapFragment.getMapAsync(this);


    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //    地圖元件建立完成時，Android自動呼叫這個方法
//    參數是建立好的Google Map物件
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        移動地圖
        CameraUpdate cameraUpdate =CameraUpdateFactory.newLatLngZoom(satation01,13);
//      設定地圖類型
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//        移動地圖到指定的座標
        mMap.moveCamera(cameraUpdate);
        processMarker();
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnInfoWindowClickListener(this);
    }

    public void processMarker(){
//        先建立一個Maker用的MarkerOptions物件
        MarkerOptions markerOptions =new MarkerOptions();


        markerOptions.position(satation01)
                .title("台北車站")
                .snippet(String.valueOf(satation01.latitude+','+satation01.longitude));
        marker01=mMap.addMarker(markerOptions);

    }
    //點擊InfoWindow時觸發
    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "onInfoWindowClick:", Toast.LENGTH_LONG).show();
    }


    public  void golist(View v) {
        in.setClass(this,MainActivity2.class);
        startActivity(in);
    }

}
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private GoogleMap mMap;
    Intent in=new Intent();

    //    設定經度與緯度
    private static final LatLng satation01=new LatLng(25.04622,121.517451);
    private Marker marker01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        processViews();
// 為navigatin_view設置點擊事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // 點選時收起選單
                drawer.closeDrawer(GravityCompat.START);

                // 取得選項id
                int id = item.getItemId();

                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.action_home) {
                    // 按下「首頁」要做的事
                    Toast.makeText(MainActivity2.this, "首頁", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (id == R.id.action_help) {
                    // 按下「使用說明」要做的事
                    Toast.makeText(MainActivity2.this, "使用說明", Toast.LENGTH_SHORT).show();
                    return true;
                }
                // 略..

                return false;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void processViews(){

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        讀取型態為SupportMapFragment的Google Map元件
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        註冊地圖元件建立完成監聽物件
        mapFragment.getMapAsync((OnMapReadyCallback) this);


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
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(satation01,13);
//      設定地圖類型
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

//        移動地圖到指定的座標
        mMap.moveCamera(cameraUpdate);
        processMarker();
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);
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
        in.setClass(this,MainActivity.class);
        startActivity(in);
    }

}
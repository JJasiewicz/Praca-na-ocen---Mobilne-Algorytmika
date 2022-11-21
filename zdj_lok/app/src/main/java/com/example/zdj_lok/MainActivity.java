package com.example.zdj_lok;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView Imageview;
    Button btn, przyciskpokazdane;
    byte[] byteArray;
    Boolean btntekst = false;
    TextView kraj, miasto ,koordynaty;
    RelativeLayout x,y,z;
    double longitude;
    double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Imageview = findViewById(R.id.img);
        btn = findViewById(R.id.btn);
        przyciskpokazdane = findViewById(R.id.dane);
        kraj = findViewById(R.id.kraj);
        miasto = findViewById(R.id.miasto);
        koordynaty = findViewById(R.id.koordynaty);
        x = findViewById(R.id.first);
        y = findViewById(R.id.second);
        z = findViewById(R.id.thirt);
        x.setVisibility(View.INVISIBLE);
        y.setVisibility(View.INVISIBLE);
        z.setVisibility(View.INVISIBLE);
        Locale locale = Locale.getDefault();
        String Skraj = locale.getDisplayCountry();


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "NIE DALES POZWOLENIA NA LOKALIZACJE", Toast.LENGTH_LONG).show();
            return;
        }else {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
           Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
        try {
            //addresses = geocoder.getFromLocation(longitude, latitude, 1);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
            String cityName = addresses.get(0).getAddressLine(0);
            miasto.setText(cityName);
            kraj.setText(Skraj);
            koordynaty.setText("" + longitude + " ; " + latitude);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100);
        }
        btn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
                btn.setHint("ZROB PHOTO PONOWNIE");
            }else{
                Toast.makeText(getApplicationContext(), "Brak permisji do kamery", Toast.LENGTH_LONG).show();
            }
        });
        przyciskpokazdane.setOnClickListener(view -> {
            if (!btntekst){
                btntekst = true;
            }else{
                btntekst = false;
            }
            if (btntekst){
                przyciskpokazdane.setText("UKRYJ DANE");

                //pokaz wypisane dane
                x.setVisibility(View.VISIBLE);
                y.setVisibility(View.VISIBLE);
                z.setVisibility(View.VISIBLE);
            }else {
                przyciskpokazdane.setText("POKAZ DANE");

                //ukryj wypisane dane
                x.setVisibility(View.INVISIBLE);
                y.setVisibility(View.INVISIBLE);
                z.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Imageview.setImageBitmap(bitmap);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
        }
    }
}
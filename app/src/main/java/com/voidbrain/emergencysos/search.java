package com.voidbrain.emergencysos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class search extends AppCompatActivity {
    private double lats, longitudes;
    private LocationManager lms;

    private RadioButton air1,p1,bs1,rs1,ps1,h1,atm1,gs1;
    private Button frag,vim1;
    private String nameloc;
    private String vicloc;
    private double latloc,longloc;
    private String type;
    private TextView nametxt1,addresstxt1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        lms = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getlocation();

        frag=(Button)findViewById(R.id.frag);
        air1=(RadioButton)findViewById(R.id.air);
        rs1=(RadioButton)findViewById(R.id.rs);
        bs1=(RadioButton)findViewById(R.id.bs);
        ps1=(RadioButton)findViewById(R.id.ps);
        h1=(RadioButton)findViewById(R.id.h);
        vim1=(Button)findViewById(R.id.vim);
        addresstxt1=(TextView)findViewById(R.id.addresstxt);
        nametxt1=(TextView)findViewById(R.id.nametxt) ;
        atm1=(RadioButton) findViewById(R.id.atm);
        gs1=(RadioButton)findViewById(R.id.gs);
        p1=(RadioButton)findViewById(R.id.p);
        vim1.setVisibility(View.GONE);
        addresstxt1.setVisibility(View.GONE);
        nametxt1.setVisibility(View.GONE);
        frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag.setVisibility(View.GONE);
                if(h1.isChecked())
                {
                    type="hospital";
                }
                else if (bs1.isChecked())
                {
                    type="bus_station";
                }
                else if (rs1.isChecked())
                {
                    type="train_station";
                }
                else if (ps1.isChecked())
                {
                    type="police";
                }
                else if(air1.isChecked())
                {
                    type="airport";
                }
                else if(atm1.isChecked())
                {
                    type="atm";
                }
                else if(gs1.isChecked())
                {
                    type="gas_station";
                }
                else if(p1.isChecked())
                {
                    type="parking";
                }
                else {
                    Toast.makeText(search.this,"Nothing selected please go back",Toast.LENGTH_LONG).show();
                    return ;
                }
                 String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lats+","+longitudes+"&rankby=distance&types="+type+"&sensor=false&key=AIzaSyB2fZv0IyhP7Wujh3B-cnpcZBnhIL3SLXE";
                StringRequest sr=new StringRequest(url, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj1=new JSONObject(response);
                            JSONArray obj2=obj1.getJSONArray("results");
                            JSONObject obj3=obj2.getJSONObject(0);
                            JSONObject obj4=obj3.getJSONObject("geometry");
                            JSONObject obj5=obj4.getJSONObject("location");
                            latloc=obj5.getDouble("lat");
                            longloc=obj5.getDouble("lng");
                            nameloc=obj3.getString("name");
                            vicloc=obj3.getString("vicinity");
                            vim1.setVisibility(View.VISIBLE);
                            addresstxt1.setVisibility(View.VISIBLE);
                            nametxt1.setVisibility(View.VISIBLE);
                            addresstxt1.setText(vicloc);
                            nametxt1.setText(nameloc);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        frag.setVisibility(View.VISIBLE);
                        Toast.makeText(search.this,"Cannot Load Requested Data",Toast.LENGTH_LONG).show();

                    }
                });
                RequestQueue q = Volley.newRequestQueue(search.this);
                q.add(sr);


            }
        });

        vim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag.setVisibility(View.VISIBLE);
                Intent viewinm=new Intent(search.this,MapsActivity.class);
                viewinm.putExtra("latloc1",latloc);
                viewinm.putExtra("longloc1",longloc);
                viewinm.putExtra("nameloc",nameloc);

                startActivity(viewinm);
            }
        });



    }

    public void getlocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        Location locations = lms.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (locations != null) {
            lats = locations.getLatitude();
            longitudes = locations.getLongitude();


        } else {
            Toast.makeText(search.this, "Unable to find current location", Toast.LENGTH_LONG).show();

        }

    }




}
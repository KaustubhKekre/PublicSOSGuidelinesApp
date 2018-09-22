package com.voidbrain.emergencysos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class police extends AppCompatActivity {
    Button amb ,fire;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police);
        amb=(Button)findViewById(R.id.amb);
        fire=(Button)findViewById(R.id.fire);
        amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disamb =new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","102",null));
                startActivity(disamb);

            }
        });
        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disfire =new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","100",null));
                startActivity(disfire);


            }
        });
        TextView dis=(TextView)findViewById(R.id.measures);
        String s="Encourage security awareness in your family and discuss what to do if there is a security threat.\n" +
                "Maintain situational awareness of your surroundings at all times.\n Pay attention to activity happening around you in order to identify anything unusual.\n" +
                "Protect your personal information. Do not reveal details of your personal life to strangers.\n" +
                "Be vigilant at public places, which are more vulnerable to bomb attacks.\n Keep an eye out for unattended packages or bags.\nInspect your car’s condition and check for proper tire inflation.\n" +
                "Inspect belts and hose. If your car has turned old then get them replaced.\n" +
                "To avoid tread separation and blowouts check for proper tire inflation.\n" +
                "Check car battery and change if you think it’s coming of age.\n" +
                "Inspect hoses and belts. Get them replaced if your car is a little old.\n" +
                "Wear seat belts to eliminate accidents.";
        dis.setText(s);
    }
}
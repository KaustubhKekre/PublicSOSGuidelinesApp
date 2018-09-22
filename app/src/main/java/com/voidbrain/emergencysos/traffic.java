package com.voidbrain.emergencysos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class traffic extends AppCompatActivity {
    Button fire;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic);
        fire=(Button)findViewById(R.id.fire);

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent disfire =new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","100",null));
                startActivity(disfire);


            }
        });
        TextView dis=(TextView)findViewById(R.id.measures);
        String s="Drive defensively, obeying all the driving rules. \nExercise Caution when Driving at Night, prevent overtaking and Blind Spots.\n Drive at a Safe Speed,keeping safe distance .\n Avoid Distractions, do not drink and drive. \nReadiness for Emergencies, Remaining Calm / Avoiding the Rage in Heavily Congested Traffic Remain calm and be patient. \nFollow the advice of local emergency officials.\nListen to your radio or television for news and instructions. \nIf the event occurs near you, check for injuries. Give first aid and get help for seriously injured people.";
        dis.setText(s);
    }
}
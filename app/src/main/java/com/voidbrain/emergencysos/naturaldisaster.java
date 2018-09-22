package com.voidbrain.emergencysos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class naturaldisaster extends AppCompatActivity {
    Button amb ,fire;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naturaldisaster);
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
                Intent disfire =new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","101",null));
                startActivity(disfire);


            }
        });
        TextView dis=(TextView)findViewById(R.id.measures);
        String s="If you have not been ordered to evacuate, stay in a safe area or shelter during a natural disaster.\n" +
                "Listen to your portable radio for important updates and instructions from local authorities.\n" +
                "If power is lost, use a generator with caution.  Only operate it outside  away from windows, doors or vents.\n" +
                "Stay in your safe area and do not drive until the danger has passed.\nImmediately pull the nearest fire alarm pull station as you exit the building.\n" +
                "When evacuating the building, be sure to feel doors for heat before" +
                "opening them to be sure there is no fire danger on the other side.\n" +
                "If there is smoke in the air, stay low to the ground, especially your\n" +
                "head, to reduce inhalation exposure.\n Keep on hand on the wall to" +
                "prevent disorientation and crawl to the nearest exit.\n" +
                "Once away and clear from danger, call your report contact and inform\n" +
                "them of the fire.\n" +
                "Go to your refuge area and await further instructions from emergency personnel.";
        dis.setText(s);
    }
}
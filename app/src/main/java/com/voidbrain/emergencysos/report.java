package com.voidbrain.emergencysos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class report extends AppCompatActivity {
    RadioButton fireb,fld,quake,wter,fight,tsu,terr,acc,trf;
    Button onSubmit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        fireb=(RadioButton)findViewById(R.id.fire);
        fld=(RadioButton)findViewById(R.id.flood);
        quake=(RadioButton)findViewById(R.id.earthquake);
        fight=(RadioButton)findViewById(R.id.violence);
        tsu=(RadioButton)findViewById(R.id.tsunami);
        terr=(RadioButton)findViewById(R.id.terror);
        acc=(RadioButton)findViewById(R.id.accident);
        trf=(RadioButton)findViewById(R.id.traffic);
        onSubmit=(Button) findViewById(R.id.sub);
        onSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fireb.isChecked() || fld.isChecked() || quake.isChecked() || tsu.isChecked()) {
                    Intent a1=new Intent(report.this,naturaldisaster.class);
                    startActivity(a1);

                } else if (acc.isChecked() || terr.isChecked() || fight.isChecked()) {
                    Intent b1=new Intent(report.this,police.class);
                    startActivity(b1);
                }
                else if (trf.isChecked()) {
                    Intent c1=new Intent(report.this,traffic.class);
                    startActivity(c1);
                }

            }
        });


}
}

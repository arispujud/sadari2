package com.example.apkit.sadari2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by nugrohoads on 5/31/2016.
 */
public class StaticPayudara extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staticpayudara);

        ImageView gmb1 = (ImageView) findViewById(R.id.gmb1);

        Button btnp_1_1 = (Button) findViewById(R.id.btnp_1_1);
        Button btnp_1_2 = (Button) findViewById(R.id.btnp_1_2);
        Button btnp_1_3 = (Button) findViewById(R.id.btnp_1_3);

        btnp_1_1.setOnClickListener(this);
        btnp_1_2.setOnClickListener(this);
        btnp_1_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View tmb) {
        switch (tmb.getId()){
            case R.id.btnp_1_1:
                startActivity(new Intent(StaticPayudara.this,Pay1.class));
                break;
            case R.id.btnp_1_2:
                startActivity(new Intent(StaticPayudara.this,Pay2.class));
                break;
            case R.id.btnp_1_3:
                startActivity(new Intent(StaticPayudara.this,Pay3.class));
                break;
        }
    }

}

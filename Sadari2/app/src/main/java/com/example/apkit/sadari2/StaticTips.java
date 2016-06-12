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
public class StaticTips extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statictips);

        ImageView gmb3 = (ImageView) findViewById(R.id.gmb3);

        Button btnp_3_1 = (Button) findViewById(R.id.btnp_3_1);
        Button btnp_3_2 = (Button) findViewById(R.id.btnp_3_2);
        Button btnp_3_3 = (Button) findViewById(R.id.btnp_3_3);

        btnp_3_1.setOnClickListener(this);
        btnp_3_2.setOnClickListener(this);
        btnp_3_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View tmb) {
        switch (tmb.getId()) {
            case R.id.btnp_3_1:
                startActivity(new Intent(StaticTips.this,Tips1.class));
                break;
            case R.id.btnp_3_2:
                startActivity(new Intent(StaticTips.this,Tips2.class));
                break;
            case R.id.btnp_3_3:
                startActivity(new Intent(StaticTips.this,Tips3.class));
                break;
        }
    }
}
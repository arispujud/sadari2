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
public class StaticGayaHidup extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staticgayahidup);

        ImageView gmb4 = (ImageView) findViewById(R.id.gmb4);

        Button btnp_4_1 = (Button) findViewById(R.id.btnp_4_1);
        Button btnp_4_2 = (Button) findViewById(R.id.btnp_4_2);

        btnp_4_1.setOnClickListener(this);
        btnp_4_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View tmb) {
        switch (tmb.getId()) {
            case R.id.btnp_4_1:
                startActivity(new Intent(StaticGayaHidup.this,Gaya1.class));
                break;
            case R.id.btnp_4_2:
                startActivity(new Intent(StaticGayaHidup.this,Gaya2.class));
                break;
        }
    }
}

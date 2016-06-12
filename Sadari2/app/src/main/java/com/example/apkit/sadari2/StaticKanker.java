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
public class StaticKanker extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statickanker);

        ImageView gmb2 = (ImageView) findViewById(R.id.gmb2);

        Button btnp_2_1 = (Button) findViewById(R.id.btnp_2_1);
        Button btnp_2_2 = (Button) findViewById(R.id.btnp_2_2);
        Button btnp_2_3 = (Button) findViewById(R.id.btnp_2_3);

        btnp_2_1.setOnClickListener(this);
        btnp_2_2.setOnClickListener(this);
        btnp_2_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View tmb) {
        switch (tmb.getId()) {
            case R.id.btnp_2_1:
                startActivity(new Intent(StaticKanker.this,Kanker1.class));
                break;
            case R.id.btnp_2_2:
                startActivity(new Intent(StaticKanker.this,Kanker2.class));
                break;
            case R.id.btnp_2_3:
                startActivity(new Intent(StaticKanker.this,Kanker3.class));
                break;
        }
    }
}

package com.example.apkit.sadari2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by nugrohoads on 5/26/2016.
 */
public class JadwalmenstruActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalmenstru);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Button buttonsetjadwal = (Button) findViewById(R.id.buttonsetjadwal);
        buttonsetjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JadwalmenstruActivity.this,SettingActivity.class));
                Toast.makeText(JadwalmenstruActivity.this, "Anda telah Mengeset Alarm", Toast.LENGTH_LONG).show();
            }
        });
    }
}

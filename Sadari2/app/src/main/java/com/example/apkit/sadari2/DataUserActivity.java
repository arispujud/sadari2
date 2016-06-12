package com.example.apkit.sadari2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DataUserActivity extends AppCompatActivity {
    EditText tanggal,berat,usia;
    Spinner riwayat, konsumsipil, identitas;
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    int idUser;
    String tanggalstr  = "";
    String beratstr    = "";
    String riwayatstr  = "";
    String usiastr     = "";
    String konsumsistr = "";
    String identistr   = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        tanggal = (EditText)findViewById(R.id.tanggal);
        berat = (EditText)findViewById(R.id.berat);
        riwayat = (Spinner)findViewById(R.id.riwayat);
        usia = (EditText)findViewById(R.id.usiapertama);
        konsumsipil=(Spinner) findViewById(R.id.konsumsipil);
        identitas=(Spinner) findViewById(R.id.identitas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.riwayat,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.konsumsipil,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.identitas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        riwayat.setAdapter(adapter);
        konsumsipil.setAdapter(adapter1);
        identitas.setAdapter(adapter2);

        idUser=Integer.parseInt(sessionManager.getPreferences(DataUserActivity.this,"ID"));

        if(sessionManager.getPreferences(DataUserActivity.this,"TGL_LAHIR")!="") {tanggal.setText(sessionManager.getPreferences(DataUserActivity.this,"TGL_LAHIR"));}
        if(sessionManager.getPreferences(DataUserActivity.this,"BERAT")!="") {berat.setText(sessionManager.getPreferences(DataUserActivity.this, "BERAT"));}
        if(sessionManager.getPreferences(DataUserActivity.this,"KANKER")!="") {riwayat.setSelection(adapter.getPosition(sessionManager.getPreferences(DataUserActivity.this, "KANKER")));}
        if(sessionManager.getPreferences(DataUserActivity.this,"TAHUN_HAID")!="") {usia.setText(sessionManager.getPreferences(DataUserActivity.this, "TAHUN_HAID"));}
        if(sessionManager.getPreferences(DataUserActivity.this,"KONSUMSI_PIL")!="") {konsumsipil.setSelection(adapter1.getPosition(sessionManager.getPreferences(DataUserActivity.this, "KONSUMSI_PIL")));}
        if(sessionManager.getPreferences(DataUserActivity.this,"STATUS_USER")!="") {identitas.setSelection(adapter2.getPosition(sessionManager.getPreferences(DataUserActivity.this,"STATUS_USER")));}
        Button buttonakun=(Button) findViewById(R.id.buttonakun);
        buttonakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggalstr  = tanggal.getText().toString();
                beratstr    = berat.getText().toString();
                riwayatstr  = riwayat.getSelectedItem().toString();
                usiastr     = usia.getText().toString();
                konsumsistr = konsumsipil.getSelectedItem().toString();
                identistr   = identitas.getSelectedItem().toString();
                if(beratstr.equals("") || tanggalstr.equals("") || usiastr.equals("")){
                    Toast.makeText(getApplicationContext(),"Lengkapi data terlebih dahulu!",Toast.LENGTH_LONG).show();
                }
                else {
                    //insert the detailes in database
//                        ContactAkun cc = new ContactAkun();
//                        cc.setTanggal(tanggalstr);
//                        cc.setBerat(beratstr);
//                        cc.setRiwayat(riwayatstr);
//                        cc.setUsiapertama(usiastr);
//                        cc.setKonsumsipil(konsumsistr);
//                        cc.setIdentitas(identistr);
//                        helper1.insertContact(cc);
                    myDB.updateUserDetail(idUser, sessionManager.getPreferences(DataUserActivity.this, "NAMA"), sessionManager.getPreferences(DataUserActivity.this, "EMAIL"), sessionManager.getPreferences(DataUserActivity.this, "PASSWORD"), tanggalstr, Integer.parseInt(beratstr), riwayatstr, Integer.parseInt(usiastr), konsumsistr, identistr);
                    sessionManager.setPreferences(DataUserActivity.this, "TGL_LAHIR", tanggalstr);
                    sessionManager.setPreferences(DataUserActivity.this, "BERAT", beratstr);
                    sessionManager.setPreferences(DataUserActivity.this, "KANKER", riwayatstr);
                    sessionManager.setPreferences(DataUserActivity.this, "TAHUN_HAID", usiastr);
                    sessionManager.setPreferences(DataUserActivity.this, "KONSUMSI_PIL", konsumsistr);
                    sessionManager.setPreferences(DataUserActivity.this, "STATUS_USER", identistr);
                    startActivity(new Intent(DataUserActivity.this, Panduan.class));
                    Toast.makeText(DataUserActivity.this, "Data Telah Tersimpan", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

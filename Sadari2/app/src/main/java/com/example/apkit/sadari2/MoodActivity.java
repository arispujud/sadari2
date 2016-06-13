package com.example.apkit.sadari2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MoodActivity extends AppCompatActivity {
    GridView gridViewEmot, gridViewPayudara, gridViewHaid;
    Button btnSimpan;
    int idxEmot, idxPD, idxHaid, id_catatan;
    DBHelper myDB= new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    String waktu, catatanPribadi;
    boolean status=false;
    int lastPosEm, lastPosPD, lastPosHaid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        gridViewEmot = (GridView) findViewById(R.id.gridView_emot);
        gridViewEmot.setAdapter(new EmotImagesAdapter(this,-1));

        gridViewPayudara = (GridView) findViewById(R.id.gridView_payudara);
        gridViewPayudara.setAdapter(new PayudaraImagesAdapter(this,-1));

        gridViewHaid = (GridView) findViewById(R.id.gridView_haid);
        gridViewHaid.setAdapter(new HaidImagesAdapter(this,-1));

        btnSimpan = (Button) findViewById(R.id.btn_mood);

        waktu = getDateTime();

        btnSimpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDB.getReadableDatabase();
                String query = "SELECT * FROM " + myDB.TB_CATATAN + " ORDER BY " + myDB.CATATAN_ID + " DESC";
                Cursor res = db.rawQuery(query, null);
                try {
                    res.moveToFirst();
                    while (res.isAfterLast() == false) {
                        if (res.getString(res.getColumnIndex(myDB.CATATAN_USER_ID)).equals(sessionManager.getPreferences(MoodActivity.this, "ID"))) {
                            //valBulan = adapter.getPosition(res.getString(res.getColumnIndex(myDB.HAID_BULAN)));
                            //String waktu = res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU));
                            if(res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU)).indexOf(getDateTime())!=-1 && res.getString(res.getColumnIndex(myDB.CATATAN_STATUS)).equals("3")){
                                id_catatan=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_ID)));
                                catatanPribadi = res.getString(res.getColumnIndex(myDB.CATATAN_ISI));
                                status=true;
                            }
                        }
                        res.moveToNext();
                    }
                    if(status==true){
                        myDB.updateCatatan(id_catatan,Integer.parseInt(sessionManager.getPreferences(MoodActivity.this, "ID")), waktu,String.valueOf(idxEmot), String.valueOf(idxPD), String.valueOf(idxHaid), catatanPribadi.toString(), 3);
                        Toast.makeText(getApplicationContext(),"DiUpdate",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        myDB.insertCatatan(Integer.parseInt(sessionManager.getPreferences(MoodActivity.this, "ID")), waktu,String.valueOf(idxEmot), String.valueOf(idxPD), String.valueOf(idxHaid), "", 3);
                        Toast.makeText(getApplicationContext(),"DiSimpan",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                }
                Intent i = new Intent(MoodActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("goto","catatan");
                startActivity(i);
            }
        });


        gridViewEmot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                idxEmot = position + 1;
                gridViewEmot.setAdapter(new EmotImagesAdapter(getApplicationContext(),position));
            }
        });

        gridViewPayudara.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                idxPD=position+1;
                gridViewPayudara.setAdapter(new PayudaraImagesAdapter(getApplicationContext(),position));
            }
        });

        gridViewHaid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                idxHaid=position+1;
                gridViewHaid.setAdapter(new HaidImagesAdapter(getApplicationContext(),position));
            }
        });
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



}

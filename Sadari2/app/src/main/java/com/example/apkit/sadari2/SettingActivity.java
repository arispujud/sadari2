package com.example.apkit.sadari2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nugrohoads on 5/26/2016.
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    int jHari=0;
    int valBulan=0;
    int valTgl=0;
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button buttonubahprof = (Button) findViewById(R.id.buttonubahprof);
        Button buttonsetalarm = (Button) findViewById(R.id.buttonsetalarm);
        Button buttontampil = (Button) findViewById(R.id.buttontampil);

        buttonubahprof.setOnClickListener(this);
        buttonsetalarm.setOnClickListener(this);
        buttontampil.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonubahprof:
                 startActivity(new Intent(SettingActivity.this,DataUserActivity.class));
                break;
            case R.id.buttontampil:
                startActivity(new Intent(SettingActivity.this,LogBaruActivity.class));
                break;
            case R.id.buttonsetalarm:
                dialogAddHaid();
                break;
        }
    }

    public void dialogAddHaid(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this);
        alertDialog.setIcon(getResources().getDrawable(R.drawable.icon));
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_haid, null);
        alertDialog.setView(view);
        final Spinner txtTglHaid = (Spinner) view.findViewById(R.id.dialog_sp_tgl);
        final Spinner txtBulanHaid = (Spinner) view.findViewById(R.id.dialog_sp_bulan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bulan,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtBulanHaid.setAdapter(adapter);
        final List<String> listTanggal;
        listTanggal = new ArrayList<String>();
        for(int i=1; i<=31; i++){
            listTanggal.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapterTanggal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listTanggal);
        adapterTanggal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTglHaid.setAdapter(adapterTanggal);

        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_HAID + " ORDER BY " + myDB.HAID_ID + " ASC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.HAID_USER_ID)).equals(sessionManager.getPreferences(SettingActivity.this, "ID"))) {
                    //valBulan = adapter.getPosition(res.getString(res.getColumnIndex(myDB.HAID_BULAN)));
                    valBulan = Integer.parseInt(res.getString(res.getColumnIndex(myDB.HAID_BULAN))) - 1;
                    valTgl = Integer.parseInt(res.getString(res.getColumnIndex(myDB.HAID_TANGGAL))) - 1;
                }
                res.moveToNext();
            }
        }
        catch (Exception e){
            valBulan = 0;
            valTgl = 0;
        }
        //Toast.makeText(getApplicationContext(), String.valueOf(valBulan), Toast.LENGTH_LONG).show();
        txtTglHaid.setSelection(valTgl);
        txtBulanHaid.setSelection(valBulan);
        alertDialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valTgl = Integer.parseInt(txtTglHaid.getSelectedItem().toString());
                valBulan = txtBulanHaid.getSelectedItemPosition() + 1;
                switch (valBulan) {
                    case 1:
                        jHari = 31;
                        break;
                    case 2:
                        jHari = 29;
                        break;
                    case 3:
                        jHari = 31;
                        break;
                    case 4:
                        jHari = 30;
                        break;
                    case 5:
                        jHari = 31;
                        break;
                    case 6:
                        jHari = 30;
                        break;
                    case 7:
                        jHari = 31;
                        break;
                    case 8:
                        jHari = 31;
                        break;
                    case 9:
                        jHari = 30;
                        break;
                    case 10:
                        jHari = 31;
                        break;
                    case 11:
                        jHari = 30;
                        break;
                    case 12:
                        jHari = 31;
                        break;
                }
                //Toast.makeText(getApplicationContext(),"Kesalahan!! Bulan ke-" + tgl + " hanya memiliki " + jHari + " hari",Toast.LENGTH_LONG).show();
                if (valTgl > jHari) {
                    Toast.makeText(getApplicationContext(), "Kesalahan!! Bulan ke-" + valBulan + " hanya memiliki " + jHari + " hari", Toast.LENGTH_LONG).show();
                } else {
                    myDB.insertHaid(Integer.parseInt(sessionManager.getPreferences(getApplicationContext(), "ID")), valTgl, valBulan);
                }
            }
        });
        alertDialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}

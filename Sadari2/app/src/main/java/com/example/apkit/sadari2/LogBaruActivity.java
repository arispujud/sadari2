package com.example.apkit.sadari2;

import android.app.ActionBar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class LogBaruActivity extends AppCompatActivity {
    DBHelper myDB;
    SessionManager sessionManager = new SessionManager();
    String tgl, lasttgl;
    int color1=Color.rgb(255,237,249);
    int color2=Color.rgb(255,255,255);
    int id_catatan, idxEmot, idxPD, idxHaid;
    boolean statusLatihan=false;
    boolean statusSadari=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_baru);
        TableLayout ll = (TableLayout) findViewById(R.id.tabel);
        addHeaderTable(ll);
        myDB = new DBHelper(this);
        LogBaruActivity.this.setTitle("Hasil Rekap");
        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_CATATAN + " ORDER BY " + myDB.CATATAN_ID + " DESC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            int i=1;
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.CATATAN_USER_ID)).equals(sessionManager.getPreferences(LogBaruActivity.this, "ID"))) {
                    String strDate = res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU));
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy-MM-dd", Locale.getDefault());
                    Date date = format.parse(strDate);

                    tgl=String.valueOf(format.format(date));
                    id_catatan=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_ID)));
                    idxEmot=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_MOOD)));
                    idxPD=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_PD)));
                    idxHaid=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_HAID)));

                    String query1 = "SELECT * FROM " + myDB.TB_LATIHAN + " WHERE " + myDB.LATIHAN_WAKTU + " LIKE '" + tgl + "%' AND " +myDB.SADARI_USER_ID+"="+sessionManager.getPreferences(LogBaruActivity.this,"ID")+" ORDER BY " + myDB.LATIHAN_ID + " DESC";
                    Cursor res1 = db.rawQuery(query1, null);
                    int countRes1=res1.getCount();
                    res1.close();
                    if(countRes1>0){
                        statusLatihan=true;
                        //Toast.makeText(getApplicationContext(), String.valueOf(countRes1),Toast.LENGTH_SHORT).show();
                    }
                    String query2 = "SELECT * FROM " + myDB.TB_SADARI + " WHERE " + myDB.SADARI_WAKTU + " LIKE '" + tgl + "%' AND " +myDB.SADARI_USER_ID+"="+sessionManager.getPreferences(LogBaruActivity.this,"ID")+" ORDER BY " + myDB.SADARI_ID + " DESC";
                    Cursor res2 = db.rawQuery(query2, null);
                    if(res2.getCount()>0){
                        statusSadari=true;
                        //Toast.makeText(getApplicationContext(), String.valueOf(res2.getCount()),Toast.LENGTH_SHORT).show();
                    }

                    if((!tgl.equals(lasttgl))&&(idxEmot>0||idxPD>0||idxHaid>0)){
                        TableRow row= new TableRow(this);
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                        TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
                        TableRow.LayoutParams lp3 = new TableRow.LayoutParams(30,75);
                        row.setLayoutParams(lp);
                        //row.setPadding(dp(1), dp(1), dp(1), dp(1));
                        //row.setBackgroundColor(Color.parseColor("#000000"));
                        lp.setMargins(0, 0, 0, 0);

                        TextView txtTanggal = new TextView(this);
                        txtTanggal.setText(String.valueOf(i));
                        txtTanggal.setBackgroundColor(getWarna(i));
                        txtTanggal.setPadding(dp(1), dp(1), dp(1), dp(1));
                        txtTanggal.setGravity(Gravity.CENTER_HORIZONTAL);
                        txtTanggal.setText(tgl);
                        txtTanggal.setLayoutParams(lp2);
                        TextView txtSadari = new TextView(this);
                        txtSadari.setText("yes"); //nama
                        txtSadari.setBackgroundColor(getWarna(i));
                        //txtSadari.setPadding(dp(1), dp(1), dp(1), dp(1));
                        txtSadari.setLayoutParams(lp2);
                        txtSadari.setGravity(Gravity.CENTER_HORIZONTAL);
                        TextView txtLatihan = new TextView(this);
                        txtLatihan.setText("Ya"); //banyak
                        txtLatihan.setBackgroundColor(getWarna(i));
                        //txtLatihan.setPadding(dp(1), dp(1), dp(1), dp(1));
                        txtLatihan.setLayoutParams(lp2);
                        txtLatihan.setGravity(Gravity.CENTER_HORIZONTAL);

                        ImageView imSadari = new ImageView(this);
                        if(statusSadari==true){
                            imSadari.setImageResource(R.drawable.check);
                        }
                        else{
                            imSadari.setImageResource(R.drawable.cross);
                        }
                        imSadari.setBackgroundColor(getWarna(i));
                        //imHaid.setPadding(dp(1), dp(1), dp(1), dp(1));
                        imSadari.setLayoutParams(lp3);

                        ImageView imLatihan = new ImageView(this);
                        if(statusLatihan==true){
                            imLatihan.setImageResource(R.drawable.check);
                        }
                        else{
                            imLatihan.setImageResource(R.drawable.cross);
                        }
                        imLatihan.setBackgroundColor(getWarna(i));
                        //imHaid.setPadding(dp(1), dp(1), dp(1), dp(1));
                        imLatihan.setLayoutParams(lp3);

                        ImageView imEmot = new ImageView(this);
                        imEmot.setImageResource(mMood[idxEmot-1]);
                        imEmot.setBackgroundColor(getWarna(i));
                        //imEmot.setPadding(dp(1), dp(1), dp(1), dp(1));
                        imEmot.setLayoutParams(lp3);
                        ImageView imPD = new ImageView(this);
                        imPD.setImageResource(mPD[idxPD-1]);
                        imPD.setBackgroundColor(getWarna(i));
                        //imPD.setPadding(dp(1), dp(1), dp(1), dp(1));
                        imPD.setLayoutParams(lp3);
                        ImageView imHaid = new ImageView(this);
                        imHaid.setImageResource(mHaid[idxHaid-1]);
                        imHaid.setBackgroundColor(getWarna(i));
                        //imHaid.setPadding(dp(1), dp(1), dp(1), dp(1));
                        imHaid.setLayoutParams(lp3);



                        row.addView(txtTanggal);
                        row.addView(imSadari);
                        row.addView(imLatihan);
                        row.addView(imEmot);
                        row.addView(imPD);
                        row.addView(imHaid);
                        ll.addView(row,i);
                        i+=1;
                        lasttgl=tgl;
                    }
                    statusLatihan=false;
                    statusSadari=false;
                }
                res.moveToNext();
            }
        }
        catch (Exception e){
        }
    }

    public void addHeaderTable(TableLayout tb){
        TableRow row= new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        row.setPadding(dp(1), dp(1), dp(1), dp(1));
        //row.setBackgroundColor(Color.parseColor("#000000"));
        lp.setMargins(dp(1), 0, 0, 0);
        TextView txtTanggal = new TextView(this);
        txtTanggal.setText("Tanggal");
        txtTanggal.setBackgroundColor(Color.rgb(255,237,249));
        txtTanggal.setTextColor(Color.rgb(225,54,122));
        txtTanggal.setPadding(dp(1), dp(1), dp(1), dp(1));
        txtTanggal.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView txtSadari = new TextView(this);
        txtSadari.setText("Sadari");
        txtSadari.setBackgroundColor(Color.rgb(255,237,249));
        txtSadari.setTextColor(Color.rgb(225,54,122));
        txtSadari.setPadding(dp(1), dp(1), dp(1), dp(1));
        txtSadari.setLayoutParams(lp);
        txtSadari.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView txtLatihan = new TextView(this);
        txtLatihan.setText("Latihan");
        txtLatihan.setBackgroundColor(Color.rgb(255,237,249));
        txtLatihan.setTextColor(Color.rgb(225,54,122));
        txtLatihan.setPadding(dp(1), dp(1), dp(1), dp(1));
        txtLatihan.setLayoutParams(lp);
        txtLatihan.setGravity(Gravity.CENTER_HORIZONTAL);
        lp2.span=3;
        TextView txtKondisi = new TextView(this);
        txtKondisi.setText("Kondisi");
        txtKondisi.setBackgroundColor(Color.rgb(255,237,249));
        txtKondisi.setTextColor(Color.rgb(225,54,122));
        txtKondisi.setPadding(dp(1), dp(1), dp(1), dp(0));
        txtKondisi.setLayoutParams(lp2);
        txtKondisi.setGravity(Gravity.CENTER_HORIZONTAL);

        row.addView(txtTanggal);
        row.addView(txtSadari);
        row.addView(txtLatihan);
        row.addView(txtKondisi);
        tb.addView(row,0);
    }
    public int dp(int x){
        final float scale = getResources().getDisplayMetrics().density;
        int dot= (int) (x * scale + 0.5f);
        return dot;
    }
    public int getWarna(int x){
        int warna;
        if(x % 2 == 0 ){
            warna=color1;
        }
        else{
            warna=color2;
        }
        return warna;
    }
    public Integer[] mMood = {
            R.drawable.em_gelisah, R.drawable.em_jatuh_cinta, R.drawable.em_kesal,
            R.drawable.em_malas, R.drawable.em_marah, R.drawable.em_sakit,
            R.drawable.em_sedih, R.drawable.em_semangat, R.drawable.em_tenang
    };
    public Integer[] mPD = {
            R.drawable.kbaik, R.drawable.kbengkak,
            R.drawable.kbenjol, R.drawable.kbenjolkecil,
            R.drawable.kberdarah, R.drawable.kgatal,
            R.drawable.kjeruk, R.drawable.kkemerahan,
            R.drawable.kkulit, R.drawable.kkuning,
            R.drawable.kmelorot, R.drawable.knyeri,
            R.drawable.kputmasuk, R.drawable.kubahbentuk
    };
    public Integer[] mHaid = {
            R.drawable.mbaik, R.drawable.mkramperut,
            R.drawable.mnyeribahu, R.drawable.mnyeriketiak,
            R.drawable.mnyerileher, R.drawable.mnyeripayudara,
            R.drawable.mnyeripinggang, R.drawable.mnyeripunggung
    };
}

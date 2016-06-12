package com.example.apkit.sadari2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CatatanFragment extends Fragment {
    Button btnSimpan;
    EditText txtNotes;
    CalendarView calender;
    SessionManager sessionManager = new SessionManager();
    int id_catatan, idxEmot, idxPD, idxHaid;
    String waktu;
    ImageView imEmot, imPD, imHaid;
    public CatatanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final DBHelper myDB = new DBHelper(getActivity().getApplicationContext());
        final View rootView = inflater.inflate(R.layout.fragment_catatan, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        btnSimpan = (Button) rootView.findViewById(R.id.btn_catatan_simpan);
        txtNotes = (EditText) rootView.findViewById(R.id.notes);
        calender = (CalendarView) rootView.findViewById(R.id.calendarView);
        imEmot = (ImageView) rootView.findViewById(R.id.ima_emot);
        imPD = (ImageView) rootView.findViewById(R.id.ima_pd);
        imHaid = (ImageView) rootView.findViewById(R.id.ima_haid);
        waktu = getDateTime();

        imEmot.setVisibility(View.INVISIBLE);
        imPD.setVisibility(View.INVISIBLE);
        imHaid.setVisibility(View.INVISIBLE);
        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_CATATAN + " ORDER BY " + myDB.CATATAN_ID + " DESC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.CATATAN_USER_ID)).equals(sessionManager.getPreferences(getActivity(), "ID"))) {
                    //valBulan = adapter.getPosition(res.getString(res.getColumnIndex(myDB.HAID_BULAN)));
                    //String waktu = res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU));
                    if(res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU)).indexOf(getDateTime())!=-1 && res.getString(res.getColumnIndex(myDB.CATATAN_STATUS)).equals("3")){
                        txtNotes.setText(res.getString(res.getColumnIndex(myDB.CATATAN_ISI)));
                        btnSimpan.setText("Update");
                        id_catatan=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_ID)));
                        idxEmot=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_MOOD)));
                        idxPD=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_PD)));
                        idxHaid=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_HAID)));
                    }
                }
                res.moveToNext();
            }
        }
        catch (Exception e){
        }

        if(idxEmot!=0){
            imEmot.setVisibility(View.VISIBLE);
            imEmot.setImageResource(mMood[idxEmot-1]);
        }
        else{
            imEmot.setVisibility(View.INVISIBLE);
        }
        if(idxPD!=0){
            imPD.setVisibility(View.VISIBLE);
            imPD.setImageResource(mPD[idxPD-1]);
        }
        else{
            imPD.setVisibility(View.INVISIBLE);
        }
        if(idxHaid!=0){
            imHaid.setVisibility(View.VISIBLE);
            imHaid.setImageResource(mHaid[idxHaid-1]);
        }
        else{
            imHaid.setVisibility(View.INVISIBLE);
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSimpan.getText().toString().equals("Simpan")) {
                    myDB.insertCatatan(Integer.parseInt(sessionManager.getPreferences(getActivity(), "ID")), waktu,"", "", "", txtNotes.getText().toString(), 3);
                    Toast.makeText(getActivity().getApplicationContext(),"Catatan disimpan!",Toast.LENGTH_SHORT).show();
                    btnSimpan.setText("Update");
                }
                else{
                    myDB.updateCatatan(id_catatan,Integer.parseInt(sessionManager.getPreferences(getActivity(), "ID")), waktu,"", "", "", txtNotes.getText().toString(), 3);
                    Toast.makeText(getActivity().getApplicationContext(),"Catatan diupdate!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String tgl= String.valueOf(dayOfMonth);
                if(tgl.length()<2){
                    tgl="0"+tgl;
                }
                String bln= String.valueOf(month+1);
                if(bln.length()<2){
                    bln="0"+bln;
                }
                String thn= String.valueOf(year);
                waktu=thn + "-" + bln + "-" + tgl;
                Toast.makeText(getActivity().getApplicationContext(),waktu,Toast.LENGTH_SHORT).show();
                SQLiteDatabase db = myDB.getReadableDatabase();
                String query = "SELECT * FROM " + myDB.TB_CATATAN + " ORDER BY " + myDB.CATATAN_ID + " DESC";
                Cursor res = db.rawQuery(query, null);
                try {
                    res.moveToFirst();
                    boolean ketemu = false;
                    while (res.isAfterLast() == false) {
                        if (res.getString(res.getColumnIndex(myDB.CATATAN_USER_ID)).equals(sessionManager.getPreferences(getActivity(), "ID"))) {
                            //valBulan = adapter.getPosition(res.getString(res.getColumnIndex(myDB.HAID_BULAN)));
                            //String waktu = res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU));
                            if(res.getString(res.getColumnIndex(myDB.CATATAN_WAKTU)).indexOf(waktu)!=-1 && res.getString(res.getColumnIndex(myDB.CATATAN_STATUS)).equals("3")){
                                txtNotes.setText(res.getString(res.getColumnIndex(myDB.CATATAN_ISI)));
                                btnSimpan.setText("Update");
                                id_catatan=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_ID)));
                                ketemu=true;
                                id_catatan=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_ID)));
                                idxEmot=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_MOOD)));
                                idxPD=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_PD)));
                                idxHaid=Integer.parseInt(res.getString(res.getColumnIndex(myDB.CATATAN_KONDISI_HAID)));
                                if(idxEmot!=0){
                                    imEmot.setVisibility(View.VISIBLE);
                                    imEmot.setImageResource(mMood[idxEmot-1]);
                                }
                                else{
                                    imEmot.setVisibility(View.INVISIBLE);
                                }
                                if(idxPD!=0){
                                    imPD.setVisibility(View.VISIBLE);
                                    imPD.setImageResource(mPD[idxPD-1]);
                                }
                                else{
                                    imPD.setVisibility(View.INVISIBLE);
                                }
                                if(idxHaid!=0){
                                    imHaid.setVisibility(View.VISIBLE);
                                    imHaid.setImageResource(mHaid[idxHaid-1]);
                                }
                                else{
                                    imHaid.setVisibility(View.INVISIBLE);
                                }
                            }
                            else{
                                if(ketemu==false) {
                                    txtNotes.setText("");
                                    txtNotes.setHint("Notes");
                                    btnSimpan.setText("Simpan");
                                    imEmot.setVisibility(View.INVISIBLE);
                                    imPD.setVisibility(View.INVISIBLE);
                                    imHaid.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                        res.moveToNext();
                    }
                }
                catch (Exception e){
                }
            }
        });
        return rootView;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
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

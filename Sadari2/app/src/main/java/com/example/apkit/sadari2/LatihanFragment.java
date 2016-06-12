package com.example.apkit.sadari2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LatihanFragment extends Fragment {
    Button btn[]=new Button[24];
    SessionManager sessionManager = new SessionManager();
    int tglNow, tglIdx;
    public LatihanFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_latihan, container, false);
        final DBHelper myDB = new DBHelper(getActivity().getApplicationContext());

        btn[0] = (Button) rootView.findViewById(R.id.btn_1_1);
        btn[1] = (Button) rootView.findViewById(R.id.btn_1_2);
        btn[2] = (Button) rootView.findViewById(R.id.btn_1_3);
        btn[3] = (Button) rootView.findViewById(R.id.btn_1_4);
        btn[4] = (Button) rootView.findViewById(R.id.btn_1_5);
        btn[5] = (Button) rootView.findViewById(R.id.btn_1_6);

        btn[6] = (Button) rootView.findViewById(R.id.btn_2_1);
        btn[7] = (Button) rootView.findViewById(R.id.btn_2_2);
        btn[8] = (Button) rootView.findViewById(R.id.btn_2_3);
        btn[9] = (Button) rootView.findViewById(R.id.btn_2_4);
        btn[10] = (Button) rootView.findViewById(R.id.btn_2_5);
        btn[11] = (Button) rootView.findViewById(R.id.btn_2_6);

        btn[12] = (Button) rootView.findViewById(R.id.btn_3_1);
        btn[13] = (Button) rootView.findViewById(R.id.btn_3_2);
        btn[14] = (Button) rootView.findViewById(R.id.btn_3_3);
        btn[15] = (Button) rootView.findViewById(R.id.btn_3_4);
        btn[16] = (Button) rootView.findViewById(R.id.btn_3_5);
        btn[17] = (Button) rootView.findViewById(R.id.btn_3_6);

        btn[18] = (Button) rootView.findViewById(R.id.btn_4_1);
        btn[19] = (Button) rootView.findViewById(R.id.btn_4_2);
        btn[20] = (Button) rootView.findViewById(R.id.btn_4_3);
        btn[21] = (Button) rootView.findViewById(R.id.btn_4_4);
        btn[22] = (Button) rootView.findViewById(R.id.btn_4_5);
        btn[23] = (Button) rootView.findViewById(R.id.btn_4_6);

        for(int i=0; i<btn.length; i++){
            btn[i].setEnabled(true);
        }

        tglNow = Integer.parseInt(getDateTime());
        if(tglNow<6 && tglNow>0){
            tglIdx=tglNow-1;
        }
        else if(tglNow<12&&tglNow>5){
            tglIdx=tglNow-2;
        }
        else if(tglNow<18&&tglNow>11){
            tglIdx=tglNow-3;
        }
        else{
            tglIdx=tglNow-4;
        }

        if(tglIdx<24){
            btn[tglIdx].setTypeface(null, Typeface.BOLD);
            btn[tglIdx].setBackgroundColor(Color.rgb(0,220,0));
        }

        for(int i=0; i<btn.length; i++){
            btn[i].setEnabled(true);
            if(i<tglIdx){
                btn[i].setText("X");
            }
        }

        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_LATIHAN + " ORDER BY " + myDB.LATIHAN_ID + " DESC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.LATIHAN_USER_ID)).equals(sessionManager.getPreferences(getActivity(), "ID"))) {
                    String strDate = res.getString(res.getColumnIndex(myDB.LATIHAN_WAKTU));
                    SimpleDateFormat format = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date date = format.parse(strDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    tglNow = day;
                    if(tglNow<6 && tglNow>0){
                        tglIdx=tglNow-1;
                    }
                    else if(tglNow<12&&tglNow>5){
                        tglIdx=tglNow-2;
                    }
                    else if(tglNow<18&&tglNow>11){
                        tglIdx=tglNow-3;
                    }
                    else{
                        tglIdx=tglNow-4;
                    }
                    if(tglIdx<24){
                        btn[tglIdx].setText("V");
                    }
                }
                res.moveToNext();
            }
        }
        catch (Exception e){
        }


        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",0);
                startActivity(i);
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",1);
                startActivity(i);
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",2);
                startActivity(i);;
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",3);
                startActivity(i);
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",4);
                startActivity(i);
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",5);
                startActivity(i);
            }
        });

        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",6);
                startActivity(i);
            }
        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",7);
                startActivity(i);
            }
        });
        btn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",8);
                startActivity(i);
            }
        });
        btn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",9);
                startActivity(i);
            }
        });
        btn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",10);
                startActivity(i);
            }
        });
        btn[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",11);
                startActivity(i);
            }
        });

        btn[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",12);
                startActivity(i);
            }
        });
        btn[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",13);
                startActivity(i);
            }
        });
        btn[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",14);
                startActivity(i);
            }
        });
        btn[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",15);
                startActivity(i);
            }
        });
        btn[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",16);
                startActivity(i);
            }
        });
        btn[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",17);
                startActivity(i);
            }
        });

        btn[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",18);
                startActivity(i);
            }
        });
        btn[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",19);
                startActivity(i);
            }
        });
        btn[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",20);
                startActivity(i);
            }
        });
        btn[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",21);
                startActivity(i);
            }
        });
        btn[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",22);
                startActivity(i);
            }
        });
        btn[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LatihanActivity.class);
                i.putExtra("idx",23);
                startActivity(i);
            }
        });
        return rootView;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

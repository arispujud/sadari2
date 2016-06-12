package com.example.apkit.sadari2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class LogActivity extends AppCompatActivity {
    private ListView list_view;
    private AdapterLog adapterLog;
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        list_view = (ListView) findViewById(R.id.listView_log);
        adapterLog = new AdapterLog(LogActivity.this, R.layout.list_log);
        list_view.setAdapter(adapterLog);

        SQLiteDatabase db = myDB.getReadableDatabase();
        String query = "SELECT * FROM " + myDB.TB_LOG + " ORDER BY " + myDB.LOG_ID + " DESC";
        Cursor res = db.rawQuery(query, null);
        try {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(res.getColumnIndex(myDB.LOG_USER_ID)).equals(sessionManager.getPreferences(LogActivity.this, "ID"))) {
                    DetailLog detail = new DetailLog();
                    detail.setIdLog(res.getString(res.getColumnIndex(myDB.LOG_ID)));
                    detail.setIdLogUser(res.getString(res.getColumnIndex(myDB.LOG_USER_ID)));
                    detail.setWaktu(res.getString(res.getColumnIndex(myDB.LOG_WAKTU)));
                    detail.setIsi(res.getString(res.getColumnIndex(myDB.LOG_ISI)));
                    adapterLog.add(detail);
                    count++;
                }
                res.moveToNext();
            }
            if(count==0){
                DetailLog detail = new DetailLog();
                detail.setIsi("Data tidak ditemukan");
                detail.setWaktu("");
                adapterLog.add(detail);
            }
        }
        catch (Exception e){
            DetailLog detail = new DetailLog();
            detail.setIsi("Data tidak ditemukan");
            adapterLog.add(detail);
        }

//        db = myDB.getReadableDatabase();
//        query = "SELECT * FROM " + myDB.TB_LATIHAN + " ORDER BY " + myDB.LATIHAN_ID + " DESC";
//        res = db.rawQuery(query, null);
//        try {
//            res.moveToFirst();
//            while (res.isAfterLast() == false) {
//                if (res.getString(res.getColumnIndex(myDB.LATIHAN_USER_ID)).equals(sessionManager.getPreferences(LogActivity.this, "ID"))) {
//                    DetailLog detail = new DetailLog();
//                    detail.setIdLog(res.getString(res.getColumnIndex(myDB.LATIHAN_ID)));
//                    detail.setIdLogUser(res.getString(res.getColumnIndex(myDB.LATIHAN_USER_ID)));
//                    detail.setWaktu(res.getString(res.getColumnIndex(myDB.LATIHAN_WAKTU)));
//                    detail.setIsi(res.getString(res.getColumnIndex(myDB.LATIHAN_LANGKAH)));
//                    adapterLog.add(detail);
//                    count++;
//                }
//                res.moveToNext();
//            }
//            if(count==0){
//                DetailLog detail = new DetailLog();
//                detail.setIsi("Data tidak ditemukan");
//                detail.setWaktu("");
//                adapterLog.add(detail);
//            }
//        }
//        catch (Exception e){
//            DetailLog detail = new DetailLog();
//            detail.setIsi("Data tidak ditemukan");
//            adapterLog.add(detail);
//        }
    }
}

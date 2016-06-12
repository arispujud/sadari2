package com.example.apkit.sadari2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by APKIT on 5/22/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "sadariDB.db";
    public static final String TB_USER = "user";
    public static final String USER_ID= "id_user";
    public static final String USER_NAMA = "nama";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_TGL_LAHIR = "tgl_lahir";
    public static final String USER_BERAT = "berat";
    public static final String USER_KANKER = "kanker";
    public static final String USER_TAHUN_HAID = "haid";
    public static final String USER_KONSUMSI_PIL = "pil";
    public static final String USER_STATUS = "status";

    public static final String TB_LOG = "history";
    public static final String LOG_ID = "id_log";
    public static final String LOG_USER_ID = "id_user";
    public static final String LOG_WAKTU= "waktu";
    public static final String LOG_ISI = "isi";

    public static final String TB_SADARI = "sadari";
    public static final String SADARI_ID = "id_sadari";
    public static final String SADARI_USER_ID = "id_user";
    public static final String SADARI_WAKTU= "waktu";
    public static final String SADARI_LANGKAH = "langkah";

    public static final String TB_LATIHAN = "latihan";
    public static final String LATIHAN_ID = "id_latihan";
    public static final String LATIHAN_USER_ID = "id_user";
    public static final String LATIHAN_WAKTU= "waktu";
    public static final String LATIHAN_LANGKAH = "langkah";

    public static final String TB_HAID = "haid";
    public static final String HAID_ID = "id_haid";
    public static final String HAID_USER_ID = "id_user";
    public static final String HAID_TANGGAL= "tanggal";
    public static final String HAID_BULAN = "bulan";

    public static final String TB_CATATAN = "catatan";
    public static final String CATATAN_ID = "id_catatan";
    public static final String CATATAN_USER_ID = "id_user";
    public static final String CATATAN_WAKTU = "waktu";
    public static final String CATATAN_MOOD= "mood";
    public static final String CATATAN_KONDISI_PD= "kondisi_pd";
    public static final String CATATAN_KONDISI_HAID= "kondisi_haid";
    public static final String CATATAN_ISI= "isi";
    public static final String CATATAN_STATUS= "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TB_USER + " (" +
                        USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USER_NAMA + " TEXT, " +
                        USER_EMAIL + " TEXT, " +
                        USER_PASSWORD + " TEXT, " +
                        USER_TGL_LAHIR + " TEXT, " +
                        USER_BERAT + " INTEGER, " +
                        USER_KANKER + " TEXT, " +
                        USER_TAHUN_HAID + " INTEGER, " +
                        USER_KONSUMSI_PIL + " TEXT, " +
                        USER_STATUS + " TEXT) "
        );
        db.execSQL("CREATE TABLE " + TB_LOG + " (" +
                        LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        LOG_USER_ID + " INTEGER, " +
                        LOG_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        LOG_ISI + " TEXT, " +
                        "FOREIGN KEY(" + LOG_USER_ID + ") REFERENCES " + TB_USER + "(" + USER_ID + ") " +
                        "ON DELETE CASCADE)"
        );
        db.execSQL("CREATE TABLE " + TB_SADARI + " (" +
                        SADARI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SADARI_USER_ID + " INTEGER, " +
                        SADARI_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        SADARI_LANGKAH + " TEXT, " +
                        "FOREIGN KEY(" + SADARI_USER_ID + ") REFERENCES " + TB_USER + "(" + USER_ID + ") " +
                        "ON DELETE CASCADE)"
        );
        db.execSQL("CREATE TABLE " + TB_LATIHAN + " (" +
                        LATIHAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        LATIHAN_USER_ID + " INTEGER, " +
                        LATIHAN_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        LATIHAN_LANGKAH + " TEXT, " +
                        "FOREIGN KEY(" + LATIHAN_USER_ID + ") REFERENCES " + TB_USER + "(" + USER_ID + ") " +
                        "ON DELETE CASCADE)"
        );
        db.execSQL("CREATE TABLE " + TB_HAID + " (" +
                        HAID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HAID_USER_ID + " INTEGER, " +
                        HAID_TANGGAL + " INTEGER, " +
                        HAID_BULAN + " INTEGER, " +
                        "FOREIGN KEY(" + HAID_USER_ID + ") REFERENCES " + TB_USER + "(" + USER_ID + ") " +
                        "ON DELETE CASCADE)"
        );
        db.execSQL("CREATE TABLE " + TB_CATATAN + " (" +
                        CATATAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CATATAN_USER_ID + " INTEGER, " +
                        CATATAN_WAKTU + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        CATATAN_MOOD + " TEXT, " +
                        CATATAN_KONDISI_PD + " TEXT, " +
                        CATATAN_KONDISI_HAID + " TEXT, " +
                        CATATAN_ISI + " TEXT, " +
                        CATATAN_STATUS + " INTEGER, " +
                        "FOREIGN KEY(" + CATATAN_USER_ID + ") REFERENCES " + TB_USER + "(" + USER_ID + ") " +
                        "ON DELETE CASCADE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
        onCreate(db);
    }

    public boolean insertLog(int id_user, String isi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOG_USER_ID, id_user);
        contentValues.put(LOG_WAKTU, getDateTime());
        contentValues.put(LOG_ISI, isi);
        db.insert(TB_LOG, null, contentValues);
        return true;
    }
    public boolean insertSadari(int id_user, String isi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SADARI_USER_ID, id_user);
        contentValues.put(SADARI_WAKTU, getDateTime());
        contentValues.put(SADARI_LANGKAH, isi);
        db.insert(TB_SADARI, null, contentValues);
        return true;
    }
    public boolean insertLatihan(int id_user, String isi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LATIHAN_USER_ID, id_user);
        contentValues.put(LATIHAN_WAKTU, getDateTime());
        contentValues.put(LATIHAN_LANGKAH, isi);
        db.insert(TB_LATIHAN, null, contentValues);
        return true;
    }
    public boolean insertHaid(int id_user, int tanggal, int bulan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HAID_USER_ID, id_user);
        contentValues.put(HAID_TANGGAL, tanggal);
        contentValues.put(HAID_BULAN, bulan);
        db.insert(TB_HAID, null, contentValues);
        return true;
    }
    public boolean insertCatatan(int id_user, String waktu, String mood, String kondisi_pd, String kondisi_haid, String catatan, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATATAN_USER_ID, id_user);
        contentValues.put(CATATAN_WAKTU, waktu);
        contentValues.put(CATATAN_MOOD, mood);
        contentValues.put(CATATAN_KONDISI_PD, kondisi_pd);
        contentValues.put(CATATAN_KONDISI_HAID, kondisi_haid);
        contentValues.put(CATATAN_ISI, catatan);
        contentValues.put(CATATAN_STATUS, status);
        db.insert(TB_CATATAN, null, contentValues);
        return true;
    }
    public boolean updateCatatan(int id, int id_user, String waktu, String mood, String kondisi_pd, String kondisi_haid, String catatan, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATATAN_USER_ID, id_user);
        contentValues.put(CATATAN_WAKTU, waktu);
        contentValues.put(CATATAN_MOOD, mood);
        contentValues.put(CATATAN_KONDISI_PD, kondisi_pd);
        contentValues.put(CATATAN_KONDISI_HAID, kondisi_haid);
        contentValues.put(CATATAN_ISI, catatan);
        contentValues.put(CATATAN_STATUS, status);
        db.update(TB_CATATAN, contentValues, CATATAN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean insertUser(String nama, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAMA, nama);
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PASSWORD, password);
        db.insert(TB_USER, null, contentValues);
        return true;
    }

    public Cursor getDataUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TB_USER + " WHERE " + USER_ID +"="+id+"", null );
        return res;
    }

    public int rowsUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TB_USER);
        return numRows;
    }

    public boolean updateUser (Integer id, String nama, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAMA, nama);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PASSWORD, password);
        db.update(TB_USER, contentValues, USER_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateUserDetail (Integer id, String nama, String email, String pass, String tglLAhir, int beratBadan, String riwayatKanker, int tahunHaid, String konsumsiPil, String status ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAMA, nama);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PASSWORD, pass);
        contentValues.put(USER_TGL_LAHIR, tglLAhir);
        contentValues.put(USER_BERAT, beratBadan);
        contentValues.put(USER_KANKER, riwayatKanker);
        contentValues.put(USER_TAHUN_HAID, tahunHaid);
        contentValues.put(USER_KONSUMSI_PIL, konsumsiPil);
        contentValues.put(USER_STATUS, status);
        db.update(TB_USER, contentValues, USER_ID + " = ? ", new String[] { Integer.toString(id) } );
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query="UPDATE " + TB_USER + " SET " +
//                USER_TGL_LAHIR + " = " + tglLAhir + ", " +
//                USER_BERAT + " = " + beratBadan + ", " +
//                USER_KANKER + " = " + riwayatKanker + ", " +
//                USER_TAHUN_HAID + " = " + tahunHaid + ", " +
//                USER_KONSUMSI_PIL + " = " + konsumsiPil + ", " +
//                USER_STATUS + " = " + status + " " +
//                "WHERE " + USER_ID + " = " + id;
//        db.execSQL(query);
        return true;
    }

    public Integer deleteUser (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TB_USER, USER_ID + " = ? ", new String[] { Integer.toString(id) });
    }

    public String searchUserPass(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + USER_EMAIL + "," + USER_PASSWORD + " FROM " + TB_USER;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
}

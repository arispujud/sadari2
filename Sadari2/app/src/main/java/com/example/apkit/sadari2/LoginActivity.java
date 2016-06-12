package com.example.apkit.sadari2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    EditText email, pass;
    String dbID, dbNama, dbEmail, dbPass, dbTglLahir, dbBerat, dbKanker, dbTahunHaid, dbKonsumsiPil,dbStatus;
    boolean ketemu=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.emaillogin);
        pass=(EditText)findViewById(R.id.sandilogin);
    }
    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.buttonlogin)
        {
            String strEmail = email.getText().toString();
            String strPass = pass.getText().toString();

            //String password = helper.searchUserPass(strEmail);
            SQLiteDatabase db = myDB.getReadableDatabase();
            String query= "SELECT * FROM "+myDB.TB_USER;
            Cursor res = db.rawQuery(query ,null);
            res.moveToFirst();
            while (res.isAfterLast()==false && ketemu ==false){
                if(res.getString(res.getColumnIndex(myDB.USER_EMAIL)).equals(strEmail)){
                    ketemu=true;
                    dbPass=res.getString(res.getColumnIndex(myDB.USER_PASSWORD));
                    dbID=res.getString(res.getColumnIndex(myDB.USER_ID));
                    dbNama=res.getString(res.getColumnIndex(myDB.USER_NAMA));
                    dbEmail=res.getString(res.getColumnIndex(myDB.USER_EMAIL));
                    dbTglLahir=res.getString(res.getColumnIndex(myDB.USER_TGL_LAHIR));
                    dbBerat=res.getString(res.getColumnIndex(myDB.USER_BERAT));
                    dbKanker=res.getString(res.getColumnIndex(myDB.USER_KANKER));
                    dbTahunHaid=res.getString(res.getColumnIndex(myDB.USER_TAHUN_HAID));
                    dbKonsumsiPil=res.getString(res.getColumnIndex(myDB.USER_KONSUMSI_PIL));
                    dbStatus=res.getString(res.getColumnIndex(myDB.USER_STATUS));
                }
                res.moveToNext();
            }
            sessionManager.setPreferences(LoginActivity.this, "STATUS_LOGIN", "0");
            sessionManager.setPreferences(LoginActivity.this,"ID","");
            sessionManager.setPreferences(LoginActivity.this,"NAMA","");
            sessionManager.setPreferences(LoginActivity.this,"EMAIL","");
            sessionManager.setPreferences(LoginActivity.this,"PASSWORD","");
            sessionManager.setPreferences(LoginActivity.this,"TGL_LAHIR","");
            sessionManager.setPreferences(LoginActivity.this,"BERAT","");
            sessionManager.setPreferences(LoginActivity.this,"KANKER","");
            sessionManager.setPreferences(LoginActivity.this,"TAHUN_HAID","");
            sessionManager.setPreferences(LoginActivity.this,"KONSUMSI_PIL","");
            sessionManager.setPreferences(LoginActivity.this,"STATUS_USER","");
            if(strPass.equals(dbPass) && ketemu==true)
            {
                sessionManager.setPreferences(LoginActivity.this,"STATUS_LOGIN","1");
                sessionManager.setPreferences(LoginActivity.this,"ID",dbID);
                sessionManager.setPreferences(LoginActivity.this,"NAMA",dbNama);
                sessionManager.setPreferences(LoginActivity.this,"EMAIL",dbEmail);
                sessionManager.setPreferences(LoginActivity.this,"PASSWORD",dbPass);
                sessionManager.setPreferences(LoginActivity.this,"TGL_LAHIR",dbTglLahir);
                sessionManager.setPreferences(LoginActivity.this,"BERAT",dbBerat);
                sessionManager.setPreferences(LoginActivity.this,"KANKER",dbKanker);
                sessionManager.setPreferences(LoginActivity.this,"TAHUN_HAID",dbTahunHaid);
                sessionManager.setPreferences(LoginActivity.this,"KONSUMSI_PIL",dbKonsumsiPil);
                sessionManager.setPreferences(LoginActivity.this,"STATUS_USER",dbStatus);
                Toast.makeText(getApplicationContext(), "Selamat Datang " + dbNama, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),dbKanker,Toast.LENGTH_LONG).show();
                if(dbTglLahir==null || dbBerat==null || dbTahunHaid==null ) {
                    startActivity(new Intent(LoginActivity.this, DataUserActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                    this.finish();
                }
                else{
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    this.finish();
                }
            }
            else
            {
                Toast temp = Toast.makeText(LoginActivity.this , "Email dan Kata Sandi Salah!", Toast.LENGTH_SHORT);
                temp.show();
            }
        }
        if(v.getId() == R.id.buttondaftar)
        {
            Intent i = new Intent(LoginActivity.this,DaftarActivity.class);
            startActivity(i);

        }
    }
}

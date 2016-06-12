package com.example.apkit.sadari2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DaftarActivity extends AppCompatActivity {

    //DatabaseHelper helper = new DatabaseHelper(this);
    EditText nama, email, pass;
    DBHelper myDB = new DBHelper(this);
    String strNama, strEmail, strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        nama = (EditText)findViewById(R.id.namadepan);
        email = (EditText)findViewById(R.id.emaildaftar);
        pass = (EditText)findViewById(R.id.sandidaftar);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.buttondaftar2)
        {
            strNama = nama.getText().toString();
            strEmail = email.getText().toString();
            strPass = pass.getText().toString();

            //insert the detailes in database
//                Contact c = new Contact();
//                c.setNamadepan(namedstr);
//                c.setNamabelakang(namebstr);
//                c.setEmaildaftar(emailstr);
//                c.setSandidaftar(passstr);
//
//                helper.insertContact(c);
            myDB.insertUser(strNama,strEmail,strPass);
            startActivity(new Intent(DaftarActivity.this, LoginActivity.class));
            Toast.makeText(DaftarActivity.this,"Pendaftaran Berhasil",Toast.LENGTH_LONG).show();
        }
    }
}

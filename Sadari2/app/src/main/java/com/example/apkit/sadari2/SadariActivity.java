package com.example.apkit.sadari2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SadariActivity extends AppCompatActivity {
    int count=0;
    RelativeLayout sadariLayout;
    ImageButton btn;
    DBHelper myDB = new DBHelper(this);
    SessionManager sessionManager = new SessionManager();
    int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadari);
        sadariLayout = (RelativeLayout) findViewById(R.id.sadari_layout);
        btn = (ImageButton) findViewById(R.id.btn_sadari);
        this.setTitle("SADARI");
        idUser = Integer.parseInt(sessionManager.getPreferences(SadariActivity.this,"ID"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                switch (count){
                    case 1 : sadariLayout.setBackgroundResource(R.drawable.sadari2);
                        myDB.insertSadari(idUser,"SADARI 1");
                        myDB.insertLog(idUser,"SADARI 1");
                        break;
                    case 2 : sadariLayout.setBackgroundResource(R.drawable.langkah1);
                        SadariActivity.this.setTitle("Langkah 1");
                        myDB.insertSadari(idUser,"SADARI 2");
                        myDB.insertLog(idUser,"SADARI 2");
                        break;
                    case 3 : sadariLayout.setBackgroundResource(R.drawable.langkah2);
                        SadariActivity.this.setTitle("Langkah 2");
                        myDB.insertSadari(idUser, "Langkah 1");
                        myDB.insertLog(idUser, "Langkah 1");
                        break;
                    case 4 : sadariLayout.setBackgroundResource(R.drawable.langkah3);
                        SadariActivity.this.setTitle("Langkah 3");
                        myDB.insertSadari(idUser, "Langakah 2");
                        myDB.insertLog(idUser, "Langkah 2");
                        break;
                    case 5 : sadariLayout.setBackgroundResource(R.drawable.langkah4);
                        SadariActivity.this.setTitle("Langkah 4");
                        myDB.insertSadari(idUser, "Langakah 3");
                        myDB.insertLog(idUser, "Langkah 3");
                        break;
                    case 6 : sadariLayout.setBackgroundResource(R.drawable.langkah5);
                        SadariActivity.this.setTitle("Langkah 5");
                        myDB.insertSadari(idUser, "Langakah 4");
                        myDB.insertLog(idUser, "Langkah 4");
                        break;
                    case 7 : sadariLayout.setBackgroundResource(R.drawable.langkah5a);
                        SadariActivity.this.setTitle("Langkah 5A");
                        myDB.insertSadari(idUser, "Langakah 5");
                        myDB.insertLog(idUser, "Langkah 5");
                        break;
                    case 8 : sadariLayout.setBackgroundResource(R.drawable.langkah5b);
                        SadariActivity.this.setTitle("Langkah 5B");
                        myDB.insertSadari(idUser, "Langkah 5A");
                        myDB.insertLog(idUser, "Langkah 5A");
                        break;
                    case 9 : sadariLayout.setBackgroundResource(R.drawable.langkah5c);
                        SadariActivity.this.setTitle("Langkah 5C");
                        myDB.insertSadari(idUser, "Langkah 5B");
                        myDB.insertLog(idUser, "Langkah 5B");
                        break;
                    case 10 : sadariLayout.setBackgroundResource(R.drawable.latihanfinish);
                        SadariActivity.this.setTitle("Selesai");
                        myDB.insertSadari(idUser, "Langkah 5C");
                        myDB.insertLog(idUser, "Langkah 5C");
                        break;
                    case 11:startActivity(new Intent(SadariActivity.this, MoodActivity.class));
                        finish();
                        break;

                }
            }
        });
    }
}

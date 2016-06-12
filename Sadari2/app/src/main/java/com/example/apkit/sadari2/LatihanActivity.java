package com.example.apkit.sadari2;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import java.security.PublicKey;

public class LatihanActivity extends AppCompatActivity {
    ImageButton btnNext;
    Button btnPlay;
    VideoView videoView;
    RelativeLayout  mylayout;
    SessionManager sessionManager = new SessionManager();
    DBHelper myDB = new DBHelper(this);
    int idx,count,idUser;
    String strKet="Latihan 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_latihan);

        btnNext = (ImageButton) findViewById(R.id.btn_lat_next);
        btnPlay = (Button) findViewById(R.id.btn_play);
        videoView = (VideoView) findViewById(R.id.videoView1);
        mylayout = (RelativeLayout) findViewById(R.id.latihan_layout);
        idUser = Integer.parseInt(sessionManager.getPreferences(LatihanActivity.this,"ID"));
        count=0;
        Intent intent=getIntent();
        idx=intent.getIntExtra("idx",0);
        Toast.makeText(LatihanActivity.this, "Latihan ke-" +String.valueOf(idx+1) + " di bulan ini.",Toast.LENGTH_SHORT).show();
        //String uriPath2 = "android.resource://com.example.apkit.sadari2/"+R.raw.lat1;
        Uri uri2 = Uri.parse(getURLVideo(idx,0));
        videoView.setVideoURI(uri2);
        videoView.requestFocus();
        videoView.start();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LatihanActivity.this,MainActivity.class));
                if(count<9) {
                    myDB.insertLatihan(idUser, strKet);
                    myDB.insertLog(idUser, strKet);
                }
                count++;
                if(count<8) {
                    Uri uri = Uri.parse(getURLVideo(idx, count));
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    videoView.start();
                }

//                if(count==5){
//                    startActivity(new Intent(LatihanActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//                else{
//                    mylayout.setBackgroundResource(R.drawable.la);
//                }
                switch (count){
                    case 1 : //mylayout.setBackgroundResource(R.drawable.latihan2);
                        strKet="Latihan 2";
                        break;
                    case 2 : //mylayout.setBackgroundResource(R.drawable.latihan3);
                        strKet="Latihan 3";
                        break;
                    case 3 : //mylayout.setBackgroundResource(R.drawable.latihan4);
                        strKet="Latihan 4";
                        if(idx>=0&&idx<5){
                            count=8;
                        }
                        break;
                    case 4 : //mylayout.setBackgroundResource(R.drawable.latihan5);
                        strKet="Latihan 5";
                        break;
                    case 5 : //mylayout.setBackgroundResource(R.drawable.latihan5);
                        strKet="Latihan 6";
                        if(idx>=9&&idx<=11){
                            count=8;
                        }
                        break;
                    case 6 : //mylayout.setBackgroundResource(R.drawable.latihan5);
                        strKet="Latihan 7";
                        if((idx>=6&&idx<=8)||(idx>=12&&idx<=17)){
                            count=8;
                        }
                        break;
                    case 7 : //mylayout.setBackgroundResource(R.drawable.latihan5);
                        strKet="Latihan 8";
                        if(idx>=21&&idx<=23){
                            count=8;
                        }
                        break;
                    case 8 : //mylayout.setBackgroundResource(R.drawable.latihan5);
                        strKet="Latihan 9";
                        break;
                    case 9 : mylayout.setBackgroundResource(R.drawable.latihanfinish);
                        videoView.setVisibility(View.INVISIBLE);
                        break;
                    case 10:
                        startActivity(new Intent(LatihanActivity.this, MoodActivity.class));
                        finish();
                        break;
                }
            }
        });
    }
    public String getURLVideo(int index,int counting){
        String uriPath = "android.resource://com.example.apkit.sadari2/";
        int[] urutan={0,1,2,3,4,5,6,7,8,9};
        if(index>=0 && index<=2){
            urutan[0]=1;
            urutan[1]=2;
            urutan[2]=3;
            urutan[3]=4;
        }
        else if (index>=3 && index<=5){
            urutan[0]=1;
            urutan[1]=5;
            urutan[2]=8;
            urutan[3]=9;
        }

        else if (index>=6 && index<=8){
            urutan[0]=1;
            urutan[1]=2;
            urutan[2]=3;
            urutan[3]=4;
            urutan[4]=5;
            urutan[5]=6;
            urutan[6]=7;
        }
        else if (index>=9 && index<=11){
            urutan[0]=1;
            urutan[1]=5;
            urutan[2]=8;
            urutan[3]=9;
            urutan[4]=12;
            urutan[5]=13;
        }

        else if (index>=12 && index<=14){
            urutan[0]=1;
            urutan[1]=2;
            urutan[2]=3;
            urutan[3]=4;
            urutan[4]=5;
            urutan[5]=7;
            urutan[6]=10;
        }
        else if (index>=15 && index<=17){
            urutan[0]=1;
            urutan[1]=5;
            urutan[2]=8;
            urutan[3]=9;
            urutan[4]=10;
            urutan[5]=12;
            urutan[6]=13;
        }

        else if (index>=18 && index<=20){
            urutan[0]=1;
            urutan[1]=2;
            urutan[2]=3;
            urutan[3]=4;
            urutan[4]=5;
            urutan[5]=6;
            urutan[6]=7;
            urutan[7]=10;
            urutan[8]=11;
        }
        else if (index>=21 && index<=23){
            urutan[0]=1;
            urutan[1]=5;
            urutan[2]=8;
            urutan[3]=9;
            urutan[4]=10;
            urutan[5]=11;
            urutan[6]=12;
            urutan[7]=13;
        }

        switch(urutan[counting]){
            case 1:
                uriPath+=R.raw.lat1;
                mylayout.setBackgroundResource(R.drawable.latihan1);
                break;
            case 2:
                uriPath+=R.raw.lat2;
                mylayout.setBackgroundResource(R.drawable.latihan2);
                break;
            case 3:
                uriPath+=R.raw.lat3;
                mylayout.setBackgroundResource(R.drawable.latihan3);
                break;
            case 4:
                uriPath+=R.raw.lat4;
                mylayout.setBackgroundResource(R.drawable.latihan4);
                break;
            case 5:
                uriPath+=R.raw.lat5;
                mylayout.setBackgroundResource(R.drawable.latihan5);
                break;
            case 6:
                uriPath+=R.raw.lat6;
                mylayout.setBackgroundResource(R.drawable.latihan6);
                break;
            case 7:
                uriPath+=R.raw.lat7;
                mylayout.setBackgroundResource(R.drawable.latihan7);
                break;
            case 8:
                uriPath+=R.raw.lat8;
                mylayout.setBackgroundResource(R.drawable.latihan8);
                break;
            case 9:
                uriPath+=R.raw.lat9;
                mylayout.setBackgroundResource(R.drawable.latihan9);
                break;
            case 10:
                uriPath+=R.raw.lat10;
                mylayout.setBackgroundResource(R.drawable.latihan10);
                break;
            case 11:
                uriPath+=R.raw.lat11;
                mylayout.setBackgroundResource(R.drawable.latihan11);
                break;
            case 12:
                uriPath+=R.raw.lat12;
                mylayout.setBackgroundResource(R.drawable.latihan12);
                break;
            case 13:
                uriPath+=R.raw.lat13;
                mylayout.setBackgroundResource(R.drawable.latihan13);
                break;

        }
        return uriPath;
    }

}

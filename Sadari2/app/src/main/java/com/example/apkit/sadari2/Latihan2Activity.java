package com.example.apkit.sadari2;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

/**
 * Created by nugrohoads on 5/28/2016.
 */
public class Latihan2Activity extends AppCompatActivity {
    private ImageButton imageButtonlat2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latihan2);
        imageButtonlat2 = (ImageButton) findViewById(R.id.imageButtonlat2);
        //imageButtonlat2.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent i = new Intent(cllatihan2.this, cllatihan3.class);
        //        startActivity(i);
        //    }
        //});

        Button buttonPlayVideo2 = (Button)findViewById(R.id.button2);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView2);
        String uriPath2 = "android.resource://com.example.nugrohoads.sadari/"+R.raw.lat2;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();
        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView2);

                String uriPath = "android.resource://com.example.nugrohoads.sadari/" + R.raw.lat2;
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });
    }
}

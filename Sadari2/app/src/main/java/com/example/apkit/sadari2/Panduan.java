package com.example.apkit.sadari2;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

/**
 * Created by nugrohoads on 6/10/2016.
 */
public class Panduan extends AppCompatActivity {
    private ImageButton btn_pan_next;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panduan);
        btn_pan_next = (ImageButton) findViewById(R.id.btn_pan_next);
        btn_pan_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Panduan.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button buttonPlayVideo2 = (Button)findViewById(R.id.btn_playpan);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoViewpan);
        String uriPath2 = "android.resource://com.example.apkit.sadari2/" + R.raw.narasi;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();
        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoViewpan);

                String uriPath = "android.resource://com.example.apkit.sadari2/" + R.raw.narasi;
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });
    }}


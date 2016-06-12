package com.example.apkit.sadari2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by root on 28/05/16.
 */
public class PayudaraImagesAdapter extends BaseAdapter {
    private Context mContext;
    private int idx;
    public PayudaraImagesAdapter(Context c, int id){
        mContext=c;
        idx=id;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(95, 95));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        if(idx==position){
            imageView.setBackgroundColor(Color.rgb(255,200,200));
        }
        return imageView;
    }
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.kbaik, R.drawable.kbengkak,
            R.drawable.kbenjol, R.drawable.kbenjolkecil,
            R.drawable.kberdarah, R.drawable.kgatal,
            R.drawable.kjeruk, R.drawable.kkemerahan,
            R.drawable.kkulit, R.drawable.kkuning,
            R.drawable.kmelorot, R.drawable.knyeri,
            R.drawable.kputmasuk, R.drawable.kubahbentuk
    };
}

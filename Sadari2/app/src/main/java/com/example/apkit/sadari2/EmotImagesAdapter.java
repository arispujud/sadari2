package com.example.apkit.sadari2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by root on 28/05/16.
 */
public class EmotImagesAdapter extends BaseAdapter {
    private Context mContext;
    private int idx;

    public EmotImagesAdapter(Context c,int id){
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
            imageView.setImageResource(mThumbIdsTerpilih[position]);
        }
        return imageView;
    }
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.em_gelisah, R.drawable.em_jatuh_cinta, R.drawable.em_kesal,
            R.drawable.em_malas, R.drawable.em_marah, R.drawable.em_sakit,
            R.drawable.em_sedih, R.drawable.em_semangat, R.drawable.em_tenang
    };
    public Integer[] mThumbIdsTerpilih = {
            R.drawable.em_gelisah_terpilih, R.drawable.em_jatuh_cinta_terpilih, R.drawable.em_kesal_terpilih,
            R.drawable.em_malas_terpilih, R.drawable.em_marah_terpilih, R.drawable.em_sakit_terpilih,
            R.drawable.em_sedih_terpilih, R.drawable.em_semangat_terpilih, R.drawable.em_tenang_terpilih
    };
}

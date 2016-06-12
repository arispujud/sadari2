package com.example.apkit.sadari2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by APKIT on 5/23/2016.
 */
public class AdapterLog extends ArrayAdapter<DetailLog> {
    public Activity context;
    public int textViewResourceId;
    public AdapterLog(Activity context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView==null) {
            LayoutInflater layoutinflanter = context.getLayoutInflater();
            view = layoutinflanter.inflate(textViewResourceId, null);

            ViewHolder viewholder = new ViewHolder();
            viewholder.isi = (TextView) view.findViewById(R.id.isi_log);
            viewholder.waktu = (TextView) view.findViewById(R.id.waktu_log);
            view.setTag(viewholder);
        }else{
            view = convertView;
        }
        ViewHolder viewHolder=(ViewHolder) view.getTag();
        DetailLog detail = getItem(position);
        viewHolder.isi.setText(detail.getIsi().toString());
        viewHolder.waktu.setText(detail.getWaktu().toString());

        return view;
    }

    public class ViewHolder{
        public TextView isi;
        public TextView waktu;
    }
}

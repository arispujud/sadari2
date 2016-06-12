package com.example.apkit.sadari2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class KonsultasiFragment extends Fragment{

    public KonsultasiFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_konsultasi, container, false);

        ImageView gambarkonsul=(ImageView) rootView.findViewById(R.id.gambarkonsul);

        EditText keluhan=(EditText) rootView.findViewById(R.id.keluhan);
        String kel= keluhan.getText().toString();
        EditText detailkeluhan=(EditText) rootView.findViewById(R.id.keluhandetail);
        String detkel=detailkeluhan.getText().toString();

        Button buttonkirim=(Button) rootView.findViewById(R.id.buttonkirim);
        Button buttonunggah=(Button) rootView.findViewById(R.id.buttonunggah);

        buttonkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Anda Telah Mengirim Pesan", Toast.LENGTH_LONG).show();
            }
        });
        buttonunggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Anda Telah Mengunggah Data", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
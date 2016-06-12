package com.example.apkit.sadari2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class PayudaraFragment extends Fragment {

    public PayudaraFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_payudara, container, false);

        ImageButton btnp_1 = (ImageButton) rootView.findViewById(R.id.btnp_1);
        ImageButton btnp_2 = (ImageButton) rootView.findViewById(R.id.btnp_2);
        ImageButton btnp_3 = (ImageButton) rootView.findViewById(R.id.btnp_3);
        ImageButton btnp_4 = (ImageButton) rootView.findViewById(R.id.btnp_4);

        btnp_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),StaticPayudara.class));
            }
        });

        btnp_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),StaticKanker.class));
            }
        });

        btnp_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),StaticTips.class));
            }
        });

        btnp_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),StaticGayaHidup.class));
            }
        });

        return rootView;
    }


}

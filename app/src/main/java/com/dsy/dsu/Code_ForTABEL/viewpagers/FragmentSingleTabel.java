package com.dsy.dsu.Code_ForTABEL.viewpagers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsy.dsu.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabel extends Fragment {
    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabel newInstance(Integer value ) {
        FragmentSingleTabel fragment = new FragmentSingleTabel();
        Bundle args = new Bundle();
        args.putInt("value",value);
        args.putString("date",new Date().toLocaleString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_tabel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textfragnetviewpager = (TextView)  view.findViewById(R.id.textfragnetviewpager);
        Integer value =getArguments().getInt("value");
        textfragnetviewpager.setText(value.toString());
        // TODO: 20.06.2023
        TextView textfragnetviewpager2 = (TextView)  view.findViewById(R.id.textfragnetviewpager2);
        String date =getArguments().getString("date");
        textfragnetviewpager2.setText(date);
    }
}
package com.dsy.dsu.Code_ForTABEL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsy.dsu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabels#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabels extends Fragment {

    public static FragmentSingleTabels newInstance(String param1, String param2) {
        FragmentSingleTabels fragment = new FragmentSingleTabels();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_single_tabels, container, false);
    }
}